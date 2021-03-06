package edu.stanford.cs147.planit;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class BalloonPoppingView extends LinearLayout {
    public List<Balloon> balloons;
    public static ArrayList<Balloon> savedBalloons = new ArrayList<>();;
    public List<Balloon> poppedBalloons;
    public List<Balloon> retrievedBalloons;
    public Balloon currBalloon;
    private AlertDialog dialog = null;
    private Handler handler;
    private Runnable mLongPressed;
    private Bitmap popImage;
    private boolean poppingState;
    private static final long PRESS_DURATION = 750l;

    public Map<String, Integer> map = new HashMap<String, Integer>();

    public void setMap() {
        map.put("p39details", R.drawable.p39details);
        map.put("adetails", R.drawable.adetails);
        map.put("ledetails", R.drawable.ledetails);
        map.put("cadetails", R.drawable.cadetails);
        map.put("lsdetails", R.drawable.lsdetails);
        map.put("attdetails", R.drawable.attdetails);
        map.put("bgdetails", R.drawable.bgdetails);
        map.put("fwdetails", R.drawable.fwdetails);
        map.put("ggbdetails", R.drawable.ggbdetails);

    }

    public BalloonPoppingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        balloons = new ArrayList<Balloon>();
        poppedBalloons = new ArrayList<Balloon>();
        retrievedBalloons = new ArrayList<Balloon>();
        poppingState = false;
        setWillNotDraw(false);
        BitmapDrawable popImageDrawable = (BitmapDrawable) getResources().getDrawable(R.drawable.pop);
        popImage = popImageDrawable.getBitmap();
        handler = new Handler();
        mLongPressed = new Runnable() {
            public void run() {
                if (currBalloon != null) {
                    showDetailsDialog();
                }
            }
        };
    }

    public void setBalloons(List<Balloon> balloons) {
        this.balloons = balloons;
        if(balloons.size() > 0) currBalloon = balloons.get(0);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (Balloon balloon : balloons) {
            balloon.drawBalloon(canvas);
        }
        if (poppingState) {
            RectF popRectF = new RectF();
            popRectF.left = currBalloon.getLeft();
            popRectF.top = currBalloon.getTop();
            popRectF.right = popRectF.left + 400f;
            popRectF.bottom = popRectF.top + 500f;
            canvas.drawBitmap(popImage, null, popRectF, null);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            currBalloon = getBalloonAtXY(e.getX(), e.getY());
            handler.postDelayed(mLongPressed, PRESS_DURATION);
        //} else if (e.getAction() == MotionEvent.ACTION_MOVE) {
          //  handler.removeCallbacks(mLongPressed);
        } else if (e.getAction() == MotionEvent.ACTION_UP) {
            handler.removeCallbacks(mLongPressed);
            long duration = e.getEventTime() - e.getDownTime();
            if (duration > PRESS_DURATION) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            } else {
                currBalloon = getBalloonAtXY(e.getX(), e.getY());
                if (currBalloon != null) {
                    popBalloon();
                }
            }
        }
        return true;
    }

    private void showDetailsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
        View layout = inflater.inflate(R.layout.dialog_feature_details, null);
        TextView text = (TextView) layout.findViewById(R.id.idea);
        text.setText(currBalloon.getIdea());
        ImageView details = (ImageView)  layout.findViewById(R.id.details);
        details.setImageResource(map.get(currBalloon.getDetails()));
        builder.setView(layout);
        dialog = builder.show();
        dialog.getWindow().setLayout(1000, 962);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    private Balloon getBalloonAtXY(float x, float y) {
        for (int i = balloons.size() - 1; i >= 0; i--) {
            Balloon balloon = balloons.get(i);
            if (!balloon.isPopped() && x > balloon.getLeft() && x < balloon.getRight() && y > balloon.getTop() && y < balloon.getBottom()) {
                return balloon;
            }
        }
        return null;
    }

    public void popBalloon() {
        currBalloon.popBalloon();
        poppedBalloons.add(currBalloon);
        poppingState = true;
        invalidate();
        MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.popping);
        mp.setVolume(1500, 1500);
        mp.start();
        new CountDownTimer(1000, 1000) {
            @Override
            public void onTick(long n) {
                // blank
            }
            @Override
            public void onFinish() {
                currBalloon = null;
                poppingState = false;
                invalidate();
            }
        }.start();
    }
}
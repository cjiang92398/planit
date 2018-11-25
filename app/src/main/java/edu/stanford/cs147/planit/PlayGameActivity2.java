package edu.stanford.cs147.planit;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by johnsonsong on 11/24/18.
 */

public class PlayGameActivity2 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game_2);
        final ArrayList<String> ideasList = new ArrayList<String>();
        final EditText textInput = findViewById(R.id.ideaInput);


        startTimer();

        textInput.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_ENTER) {
                    String s = textInput.getText().toString();
                    if (s.length() != 0) ideasList.add(s);
                    textInput.getText().clear();

                    // ADD BALLOON every 3 ideas
                    // USER lift up every idea, but RESET after three
                    // BACKGROUND SHIFT every 6 ideas
                    if (ideasList.size() % 3 != 0) {
                        DisplayMetrics displayMetrics = getApplicationContext().getResources().getDisplayMetrics();
                        ImageView user = (ImageView) findViewById(R.id.coralpenguin);
                        ViewGroup.MarginLayoutParams marginParams = (ViewGroup.MarginLayoutParams) user.getLayoutParams();
                        int top = (int) ((120-((ideasList.size()%3)*10) * displayMetrics.density)+0.5);
                        int left = (int) ((150 * displayMetrics.density) + 0.5);;
                        marginParams.setMargins(
                                left, top,0, 0);
                        user.requestLayout();
                    } else {
                        // ADD BALLOON :-(
                        DisplayMetrics displayMetrics = getApplicationContext().getResources().getDisplayMetrics();
                        ImageView user = (ImageView) findViewById(R.id.coralpenguin);
                        ViewGroup.MarginLayoutParams marginParams = (ViewGroup.MarginLayoutParams) user.getLayoutParams();
                        int top = (int) ((120 * displayMetrics.density)+0.5);
                        int left = (int) ((150 * displayMetrics.density) + 0.5);;
                        marginParams.setMargins(
                                left, top,0, 0);
                        user.requestLayout();
                    }

                    if (ideasList.size() == 6) {
                        RelativeLayout bg = (RelativeLayout)findViewById(R.id.background);
                        bg.setBackgroundResource(R.drawable.background2);
                    }
                    if (ideasList.size() == 12) {
                        RelativeLayout bg = (RelativeLayout)findViewById(R.id.background);
                        bg.setBackgroundResource(R.drawable.background3);
                    }
                    if (ideasList.size() == 18) {
                        RelativeLayout bg = (RelativeLayout)findViewById(R.id.background);
                        bg.setBackgroundResource(R.drawable.background4);
                    }
                    if (ideasList.size() == 20) {
                        RelativeLayout bg = (RelativeLayout)findViewById(R.id.background);
                        bg.setBackgroundResource(R.drawable.background5);
                    }
                    return true;
                }
                return false;
            }
        });

        // does same thing as above. should decompose later.
//        textInput.setOnEditorActionListener(new EditText.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                if (actionId == EditorInfo.IME_ACTION_DONE) {
//                    String s = textInput.getText().toString();
//                    ideasList.add(s);
//                    textInput.getText().clear();
//                    Log.d("entered", ideasList.toString());
//                    return true;
//                }
//                return false;
//            }
//        });
    }

    public void startTimer() {
        final TextView tv = (TextView) findViewById( R.id.timerText );
        new CountDownTimer(60999, 1000) {

            public void onTick(long millisUntilFinished) {
                tv.setText(new SimpleDateFormat("mm:ss").format(new Date(millisUntilFinished)));
            }

            public void onFinish() {
                tv.setText("Time's up!");
                showYouWonDialog();
            }
        }.start();
    }

    public void showYouWonDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setMessage("YOU WON!");
        dialogBuilder.setOnCancelListener(
                new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        // start activity to go home, once Lisa finishes
                    }
                }
        );
        AlertDialog dialog = dialogBuilder.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView textView = (TextView) dialog.findViewById(android.R.id.message);
        Typeface typeface = ResourcesCompat.getFont(getApplicationContext(), R.font.sniglet);
        textView.setTypeface(typeface, Typeface.BOLD);
        textView.setTextSize(64);
        textView.setTextColor(Color.WHITE);
        textView.setGravity(Gravity.CENTER);
        textView.setShadowLayer(4, 4, 4, Color.BLACK);
    }
}

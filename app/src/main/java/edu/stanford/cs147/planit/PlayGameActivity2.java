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

                    // ADD BALLOON every idea
                    // CHANGE BACKGROUND every idea
                    // after IDEA ONE: coral goes higher. blue goes lower.
                    // after IDEA TWO: coral goes higher. blue goes out of view. green goes lower.
                    // after IDEA THREE: coral wins/crosses finish line. green goes out of view.

                    if (ideasList.size() == 1) {
                        // CORAL GOES HIGHER
                        DisplayMetrics displayMetrics = getApplicationContext().getResources().getDisplayMetrics();
                        ImageView coral = (ImageView) findViewById(R.id.coralpenguin);
                        ViewGroup.MarginLayoutParams marginParamsCoral = (ViewGroup.MarginLayoutParams) coral.getLayoutParams();
                        int topCoral = (int) ((110 * displayMetrics.density)+0.5);
                        int leftCoral = (int) ((150 * displayMetrics.density) + 0.5);;
                        marginParamsCoral.setMargins(
                                leftCoral, topCoral,0, 0);
                        coral.requestLayout();

                        // BLUE GOES LOWER
                        ImageView blue = (ImageView) findViewById(R.id.bluepenguin);
                        ViewGroup.MarginLayoutParams marginParamsBlue = (ViewGroup.MarginLayoutParams) blue.getLayoutParams();
                        int topBlue = (int) ((130 * displayMetrics.density)+0.5);
                        int leftBlue = (int) ((150 * displayMetrics.density) + 0.5);;
                        marginParamsBlue.setMargins(
                                leftBlue, topBlue,0, 0);
                        blue.requestLayout();

                        // CHANGE BACKGROUND
                        RelativeLayout bg = (RelativeLayout)findViewById(R.id.background);
                        bg.setBackgroundResource(R.drawable.background2);
                    }

                    if (ideasList.size() == 2) {
                        // CORAL GOES HIGHER
                        DisplayMetrics displayMetrics = getApplicationContext().getResources().getDisplayMetrics();
                        ImageView coral = (ImageView) findViewById(R.id.coralpenguin);
                        ViewGroup.MarginLayoutParams marginParamsCoral = (ViewGroup.MarginLayoutParams) coral.getLayoutParams();
                        int topCoral = (int) ((120-((ideasList.size()%3)*10) * displayMetrics.density)+0.5);
                        int leftCoral = (int) ((150 * displayMetrics.density) + 0.5);;
                        marginParamsCoral.setMargins(
                                leftCoral, topCoral,0, 0);
                        coral.requestLayout();

                        // BLUE GOES OUT OF VIEW
                        ImageView blue = (ImageView) findViewById(R.id.bluepenguin);
                        blue.setVisibility(View.INVISIBLE);

                        // GREEN GOES LOWER
                        ImageView green = (ImageView) findViewById(R.id.greenpenguin);
                        ViewGroup.MarginLayoutParams marginParamsGreen = (ViewGroup.MarginLayoutParams) green.getLayoutParams();
                        int topGreen = (int) ((130 * displayMetrics.density)+0.5);
                        int leftGreen = (int) ((150 * displayMetrics.density) + 0.5);;
                        marginParamsGreen.setMargins(
                                leftGreen, topGreen,0, 0);
                        green.requestLayout();

                        // CHANGE BACKGROUND
                        RelativeLayout bg = (RelativeLayout)findViewById(R.id.background);
                        bg.setBackgroundResource(R.drawable.background3);
                    }

                    if (ideasList.size() == 3) {
                        // CORAL GOES HIGHER
                        DisplayMetrics displayMetrics = getApplicationContext().getResources().getDisplayMetrics();
                        ImageView coral = (ImageView) findViewById(R.id.coralpenguin);
                        ViewGroup.MarginLayoutParams marginParamsCoral = (ViewGroup.MarginLayoutParams) coral.getLayoutParams();
                        int topCoral = (int) ((120-((ideasList.size()%3)*10) * displayMetrics.density)+0.5);
                        int leftCoral = (int) ((150 * displayMetrics.density) + 0.5);;
                        marginParamsCoral.setMargins(
                                leftCoral, topCoral,0, 0);
                        coral.requestLayout();

                        // GREEN GOES OUT OF VIEW
                        ImageView green = (ImageView) findViewById(R.id.greenpenguin);
                        green.setVisibility(View.INVISIBLE);

                        // CHANGE BACKGROUND -- win background / finish line
                        RelativeLayout bg = (RelativeLayout)findViewById(R.id.background);
                        bg.setBackgroundResource(R.drawable.background4);
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
        new CountDownTimer(15999, 1000) {

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
                        Intent intent = new Intent(getApplicationContext(), UpdatedHomePage.class);
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

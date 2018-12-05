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
                        int topCoral = (int) ((160 * displayMetrics.density)+0.5);
                        int leftCoral = (int) ((165 * displayMetrics.density) + 0.5);
                        marginParamsCoral.setMargins(
                                leftCoral, topCoral,0, 0);
                        coral.requestLayout();

                        ImageView purpleBalloon = (ImageView) findViewById(R.id.purpleballoon);
                        ViewGroup.MarginLayoutParams marginParamsPurpleBalloon = (ViewGroup.MarginLayoutParams) purpleBalloon.getLayoutParams();
                        int topPurpleBalloon = (int) ((80 * displayMetrics.density)+0.5);
                        int leftPurpleBalloon = (int) ((175 * displayMetrics.density) + 0.5);
                        marginParamsPurpleBalloon.setMargins(leftPurpleBalloon, topPurpleBalloon, 0, 0);
                        purpleBalloon.requestLayout();

                        ImageView tealBalloon = (ImageView) findViewById(R.id.tealballoon);
                        tealBalloon.setVisibility(View.VISIBLE);

                        // BLUE GOES LOWER
                        ImageView blue = (ImageView) findViewById(R.id.bluepenguin);
                        ViewGroup.MarginLayoutParams marginParamsBlue = (ViewGroup.MarginLayoutParams) blue.getLayoutParams();
                        int topBlue = (int) ((200 * displayMetrics.density)+0.5);
                        int leftBlue = (int) ((270 * displayMetrics.density) + 0.5);;
                        marginParamsBlue.setMargins(
                                leftBlue, topBlue,0, 0);
                        blue.requestLayout();

                        ImageView greenBalloon = (ImageView) findViewById(R.id.greenballoon);
                        ViewGroup.MarginLayoutParams marginParamsGreenBalloon = (ViewGroup.MarginLayoutParams) greenBalloon.getLayoutParams();
                        int topGreenBalloon = (int) ((180 * displayMetrics.density)+0.5);
                        int leftGreenBalloon = (int) ((270 * displayMetrics.density) + 0.5);
                        marginParamsGreenBalloon.setMargins(leftGreenBalloon, topGreenBalloon, 0, 0);
                        greenBalloon.requestLayout();

                        // CHANGE BACKGROUND
                        RelativeLayout bg = (RelativeLayout)findViewById(R.id.background);
                        bg.setBackgroundResource(R.drawable.background2);
                    }

                    if (ideasList.size() == 2) {
                        // CORAL GOES HIGHER
                        DisplayMetrics displayMetrics = getApplicationContext().getResources().getDisplayMetrics();
                        ImageView coral = (ImageView) findViewById(R.id.coralpenguin);
                        ViewGroup.MarginLayoutParams marginParamsCoral = (ViewGroup.MarginLayoutParams) coral.getLayoutParams();
                        int topCoral = (int) ((140 * displayMetrics.density)+0.5);
                        int leftCoral = (int) ((165 * displayMetrics.density) + 0.5);;
                        marginParamsCoral.setMargins(
                                leftCoral, topCoral,0, 0);
                        coral.requestLayout();

                        ImageView purpleBalloon = (ImageView) findViewById(R.id.purpleballoon);
                        ViewGroup.MarginLayoutParams marginParamsPurpleBalloon = (ViewGroup.MarginLayoutParams) purpleBalloon.getLayoutParams();
                        int topPurpleBalloon = (int) ((60 * displayMetrics.density)+0.5);
                        int leftPurpleBalloon = (int) ((175 * displayMetrics.density) + 0.5);
                        marginParamsPurpleBalloon.setMargins(leftPurpleBalloon, topPurpleBalloon, 0, 0);
                        purpleBalloon.requestLayout();

                        ImageView tealBalloon = (ImageView) findViewById(R.id.tealballoon);
                        ViewGroup.MarginLayoutParams marginParamsTealBalloon = (ViewGroup.MarginLayoutParams) tealBalloon.getLayoutParams();
                        int topTealBalloon = (int) ((80 * displayMetrics.density)+0.5);
                        int leftTealBalloon = (int) ((135 * displayMetrics.density) + 0.5);
                        marginParamsTealBalloon.setMargins(leftTealBalloon, topTealBalloon, 0, 0);
                        tealBalloon.requestLayout();

                        ImageView lavenderBalloon = (ImageView) findViewById(R.id.lavenderballoon);
                        lavenderBalloon.setVisibility(View.VISIBLE);

                        // BLUE GOES OUT OF VIEW
                        ImageView blue = (ImageView) findViewById(R.id.bluepenguin);
                        blue.setVisibility(View.INVISIBLE);
                        ImageView greenBalloon = (ImageView) findViewById(R.id.greenballoon);
                        greenBalloon.setVisibility(View.INVISIBLE);

                        // GREEN GOES LOWER
                        ImageView green = (ImageView) findViewById(R.id.greenpenguin);
                        ViewGroup.MarginLayoutParams marginParamsGreen = (ViewGroup.MarginLayoutParams) green.getLayoutParams();
                        int topGreen = (int) ((200 * displayMetrics.density)+0.5);
                        int leftGreen = (int) ((50 * displayMetrics.density) + 0.5);;
                        marginParamsGreen.setMargins(
                                leftGreen, topGreen,0, 0);
                        green.requestLayout();

                        ImageView coralBalloon = (ImageView) findViewById(R.id.coralballoon);
                        ViewGroup.MarginLayoutParams marginParamsCoralBalloon = (ViewGroup.MarginLayoutParams) coralBalloon.getLayoutParams();
                        int topCoralBalloon = (int) ((180 * displayMetrics.density)+0.5);
                        int leftCoralBalloon = (int) ((50 * displayMetrics.density) + 0.5);
                        marginParamsCoralBalloon.setMargins(leftCoralBalloon, topCoralBalloon, 0, 0);
                        coralBalloon.requestLayout();

                        // CHANGE BACKGROUND
                        RelativeLayout bg = (RelativeLayout)findViewById(R.id.background);
                        bg.setBackgroundResource(R.drawable.background3);
                    }

                    if (ideasList.size() == 3) {
                        // CORAL GOES HIGHER
                        DisplayMetrics displayMetrics = getApplicationContext().getResources().getDisplayMetrics();
                        ImageView coral = (ImageView) findViewById(R.id.coralpenguin);
                        ViewGroup.MarginLayoutParams marginParamsCoral = (ViewGroup.MarginLayoutParams) coral.getLayoutParams();
                        int topCoral = (int) ((120 * displayMetrics.density)+0.5);
                        int leftCoral = (int) ((165 * displayMetrics.density) + 0.5);;
                        marginParamsCoral.setMargins(
                                leftCoral, topCoral,0, 0);
                        coral.requestLayout();

                        ImageView purpleBalloon = (ImageView) findViewById(R.id.purpleballoon);
                        ViewGroup.MarginLayoutParams marginParamsPurpleBalloon = (ViewGroup.MarginLayoutParams) purpleBalloon.getLayoutParams();
                        int topPurpleBalloon = (int) ((40 * displayMetrics.density)+0.5);
                        int leftPurpleBalloon = (int) ((175 * displayMetrics.density) + 0.5);
                        marginParamsPurpleBalloon.setMargins(leftPurpleBalloon, topPurpleBalloon, 0, 0);
                        purpleBalloon.requestLayout();

                        ImageView tealBalloon = (ImageView) findViewById(R.id.tealballoon);
                        ViewGroup.MarginLayoutParams marginParamsTealBalloon = (ViewGroup.MarginLayoutParams) tealBalloon.getLayoutParams();
                        int topTealBalloon = (int) ((60 * displayMetrics.density)+0.5);
                        int leftTealBalloon = (int) ((135 * displayMetrics.density) + 0.5);
                        marginParamsTealBalloon.setMargins(leftTealBalloon, topTealBalloon, 0, 0);
                        tealBalloon.requestLayout();

                        ImageView lavenderBalloon = (ImageView) findViewById(R.id.lavenderballoon);
                        ViewGroup.MarginLayoutParams marginParamsLavenderBalloon = (ViewGroup.MarginLayoutParams) lavenderBalloon.getLayoutParams();
                        int topLavenderBalloon = (int) ((60 * displayMetrics.density)+0.5);
                        int leftLavenderBalloon = (int) ((150 * displayMetrics.density) + 0.5);
                        marginParamsLavenderBalloon.setMargins(leftLavenderBalloon, topLavenderBalloon, 0, 0);
                        lavenderBalloon.requestLayout();


                        // GREEN GOES OUT OF VIEW
                        ImageView green = (ImageView) findViewById(R.id.greenpenguin);
                        green.setVisibility(View.INVISIBLE);
                        ImageView coralBalloon = (ImageView) findViewById(R.id.coralballoon);
                        coralBalloon.setVisibility(View.INVISIBLE);

                        // CHANGE BACKGROUND -- win background / finish line
                        RelativeLayout bg = (RelativeLayout)findViewById(R.id.background);
                        bg.setBackgroundResource(R.drawable.background4);

                        // SHOW YOU WON
                        showYouWonDialog();
                    }
                    return true;
                }
                return false;
            }
        });

        // does same thing as above. should decompose later.
        textInput.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    String s = textInput.getText().toString();
                    if (s.length() != 0) ideasList.add(s);
                    textInput.getText().clear();

                    if (ideasList.size() == 1) {
                        // CORAL GOES HIGHER
                        DisplayMetrics displayMetrics = getApplicationContext().getResources().getDisplayMetrics();
                        ImageView coral = (ImageView) findViewById(R.id.coralpenguin);
                        ViewGroup.MarginLayoutParams marginParamsCoral = (ViewGroup.MarginLayoutParams) coral.getLayoutParams();
                        int topCoral = (int) ((190 * displayMetrics.density)+0.5);
                        int leftCoral = (int) ((165 * displayMetrics.density) + 0.5);
                        marginParamsCoral.setMargins(
                                leftCoral, topCoral,0, 0);
                        coral.requestLayout();

                        ImageView purpleBalloon = (ImageView) findViewById(R.id.purpleballoon);
                        ViewGroup.MarginLayoutParams marginParamsPurpleBalloon = (ViewGroup.MarginLayoutParams) purpleBalloon.getLayoutParams();
                        int topPurpleBalloon = (int) ((110 * displayMetrics.density)+0.5);
                        int leftPurpleBalloon = (int) ((175 * displayMetrics.density) + 0.5);
                        marginParamsPurpleBalloon.setMargins(leftPurpleBalloon, topPurpleBalloon, 0, 0);
                        purpleBalloon.requestLayout();

                        ImageView tealBalloon = (ImageView) findViewById(R.id.tealballoon);
                        tealBalloon.setVisibility(View.VISIBLE);

                        // BLUE GOES LOWER
                        ImageView blue = (ImageView) findViewById(R.id.bluepenguin);
                        ViewGroup.MarginLayoutParams marginParamsBlue = (ViewGroup.MarginLayoutParams) blue.getLayoutParams();
                        int topBlue = (int) ((260 * displayMetrics.density)+0.5);
                        int leftBlue = (int) ((290 * displayMetrics.density) + 0.5);;
                        marginParamsBlue.setMargins(
                                leftBlue, topBlue,0, 0);
                        blue.requestLayout();

                        ImageView greenBalloon = (ImageView) findViewById(R.id.greenballoon);
                        ViewGroup.MarginLayoutParams marginParamsGreenBalloon = (ViewGroup.MarginLayoutParams) greenBalloon.getLayoutParams();
                        int topGreenBalloon = (int) ((250 * displayMetrics.density)+0.5);
                        int leftGreenBalloon = (int) ((290 * displayMetrics.density) + 0.5);
                        marginParamsGreenBalloon.setMargins(leftGreenBalloon, topGreenBalloon, 0, 0);
                        greenBalloon.requestLayout();

                        // CHANGE BACKGROUND
                        RelativeLayout bg = (RelativeLayout)findViewById(R.id.background);
                        bg.setBackgroundResource(R.drawable.background2);
                    }

                    if (ideasList.size() == 2) {
                        // CORAL GOES HIGHER
                        DisplayMetrics displayMetrics = getApplicationContext().getResources().getDisplayMetrics();
                        ImageView coral = (ImageView) findViewById(R.id.coralpenguin);
                        ViewGroup.MarginLayoutParams marginParamsCoral = (ViewGroup.MarginLayoutParams) coral.getLayoutParams();
                        int topCoral = (int) ((170 * displayMetrics.density)+0.5);
                        int leftCoral = (int) ((165 * displayMetrics.density) + 0.5);;
                        marginParamsCoral.setMargins(
                                leftCoral, topCoral,0, 0);
                        coral.requestLayout();

                        ImageView purpleBalloon = (ImageView) findViewById(R.id.purpleballoon);
                        ViewGroup.MarginLayoutParams marginParamsPurpleBalloon = (ViewGroup.MarginLayoutParams) purpleBalloon.getLayoutParams();
                        int topPurpleBalloon = (int) ((90 * displayMetrics.density)+0.5);
                        int leftPurpleBalloon = (int) ((175 * displayMetrics.density) + 0.5);
                        marginParamsPurpleBalloon.setMargins(leftPurpleBalloon, topPurpleBalloon, 0, 0);
                        purpleBalloon.requestLayout();

                        ImageView tealBalloon = (ImageView) findViewById(R.id.tealballoon);
                        ViewGroup.MarginLayoutParams marginParamsTealBalloon = (ViewGroup.MarginLayoutParams) tealBalloon.getLayoutParams();
                        int topTealBalloon = (int) ((110 * displayMetrics.density)+0.5);
                        int leftTealBalloon = (int) ((145 * displayMetrics.density) + 0.5);
                        marginParamsTealBalloon.setMargins(leftTealBalloon, topTealBalloon, 0, 0);
                        tealBalloon.requestLayout();

                        ImageView lavenderBalloon = (ImageView) findViewById(R.id.lavenderballoon);
                        lavenderBalloon.setVisibility(View.VISIBLE);

                        // BLUE GOES OUT OF VIEW
                        ImageView blue = (ImageView) findViewById(R.id.bluepenguin);
                        blue.setVisibility(View.INVISIBLE);
                        ImageView greenBalloon = (ImageView) findViewById(R.id.greenballoon);
                        greenBalloon.setVisibility(View.INVISIBLE);

                        // GREEN GOES LOWER
                        ImageView green = (ImageView) findViewById(R.id.greenpenguin);
                        ViewGroup.MarginLayoutParams marginParamsGreen = (ViewGroup.MarginLayoutParams) green.getLayoutParams();
                        int topGreen = (int) ((240 * displayMetrics.density)+0.5);
                        int leftGreen = (int) ((50 * displayMetrics.density) + 0.5);;
                        marginParamsGreen.setMargins(
                                leftGreen, topGreen,0, 0);
                        green.requestLayout();

                        ImageView coralBalloon = (ImageView) findViewById(R.id.coralballoon);
                        ViewGroup.MarginLayoutParams marginParamsCoralBalloon = (ViewGroup.MarginLayoutParams) coralBalloon.getLayoutParams();
                        int topCoralBalloon = (int) ((220 * displayMetrics.density)+0.5);
                        int leftCoralBalloon = (int) ((50 * displayMetrics.density) + 0.5);
                        marginParamsCoralBalloon.setMargins(leftCoralBalloon, topCoralBalloon, 0, 0);
                        coralBalloon.requestLayout();

                        // CHANGE BACKGROUND
                        RelativeLayout bg = (RelativeLayout)findViewById(R.id.background);
                        bg.setBackgroundResource(R.drawable.background3);
                    }

                    if (ideasList.size() == 3) {
                        // CORAL GOES HIGHER
                        DisplayMetrics displayMetrics = getApplicationContext().getResources().getDisplayMetrics();
                        ImageView coral = (ImageView) findViewById(R.id.coralpenguin);
                        ViewGroup.MarginLayoutParams marginParamsCoral = (ViewGroup.MarginLayoutParams) coral.getLayoutParams();
                        int topCoral = (int) ((150 * displayMetrics.density)+0.5);
                        int leftCoral = (int) ((165 * displayMetrics.density) + 0.5);;
                        marginParamsCoral.setMargins(
                                leftCoral, topCoral,0, 0);
                        coral.requestLayout();

                        ImageView purpleBalloon = (ImageView) findViewById(R.id.purpleballoon);
                        ViewGroup.MarginLayoutParams marginParamsPurpleBalloon = (ViewGroup.MarginLayoutParams) purpleBalloon.getLayoutParams();
                        int topPurpleBalloon = (int) ((70 * displayMetrics.density)+0.5);
                        int leftPurpleBalloon = (int) ((175 * displayMetrics.density) + 0.5);
                        marginParamsPurpleBalloon.setMargins(leftPurpleBalloon, topPurpleBalloon, 0, 0);
                        purpleBalloon.requestLayout();

                        ImageView tealBalloon = (ImageView) findViewById(R.id.tealballoon);
                        ViewGroup.MarginLayoutParams marginParamsTealBalloon = (ViewGroup.MarginLayoutParams) tealBalloon.getLayoutParams();
                        int topTealBalloon = (int) ((90 * displayMetrics.density)+0.5);
                        int leftTealBalloon = (int) ((145 * displayMetrics.density) + 0.5);
                        marginParamsTealBalloon.setMargins(leftTealBalloon, topTealBalloon, 0, 0);
                        tealBalloon.requestLayout();

                        ImageView lavenderBalloon = (ImageView) findViewById(R.id.lavenderballoon);
                        ViewGroup.MarginLayoutParams marginParamsLavenderBalloon = (ViewGroup.MarginLayoutParams) lavenderBalloon.getLayoutParams();
                        int topLavenderBalloon = (int) ((90 * displayMetrics.density)+0.5);
                        int leftLavenderBalloon = (int) ((175 * displayMetrics.density) + 0.5);
                        marginParamsLavenderBalloon.setMargins(leftLavenderBalloon, topLavenderBalloon, 0, 0);
                        lavenderBalloon.requestLayout();


                        // GREEN GOES OUT OF VIEW
                        ImageView green = (ImageView) findViewById(R.id.greenpenguin);
                        green.setVisibility(View.INVISIBLE);
                        ImageView coralBalloon = (ImageView) findViewById(R.id.coralballoon);
                        coralBalloon.setVisibility(View.INVISIBLE);

                        // CHANGE BACKGROUND -- win background / finish line
                        RelativeLayout bg = (RelativeLayout)findViewById(R.id.background);
                        bg.setBackgroundResource(R.drawable.background4);

                        // SHOW YOU WON
                        showYouWonDialog();
                    }
                }
                return false;
            }
        });
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
                        if (Boolean.toString(UpdatedHomePage.updatedHomeStarted).equals("false")) {
                            startActivity(new Intent(PlayGameActivity2.this, Home.class));
                        } else {
                            startActivity(new Intent(PlayGameActivity2.this, UpdatedHomePage.class));
                        }
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

    public void goToOngoingGames(View view) {
        startActivity(new Intent(PlayGameActivity2.this, OngoingGamesActivity.class));
        setContentView(R.layout.activity_ongoing_games);
    }
}

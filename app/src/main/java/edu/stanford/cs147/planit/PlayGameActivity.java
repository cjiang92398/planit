package edu.stanford.cs147.planit;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.os.Handler;

public class PlayGameActivity extends AppCompatActivity {

    private boolean wonGame = false;

    // @ johnson, you'll probably use an activity for playing the actual game
    // adding my code here for showing the dialog :)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_play_game);
        //GamePlayerView player = (GamePlayerView) findViewById(R.id.gamePlayerView);

        updateReadySetGoText();

        // now popup the keyboard and allow for user to input ideas.
//        acceptPlayerIdeas();

//        if (wonGame) {
//            showYouWonDialog();
//        }
    }

    public void acceptPlayerIdeas() {
        // PROBABLY IN A NEW XML VIEW and perhaps new activity:
            // add ticking timer at top
            // attach balloons to the penguin things
            // include space for edit text
        Intent intent = new Intent(this, PlayGameActivity2.class);
        startActivity(intent);
        setContentView(R.layout.activity_play_game_2);
    }

    public void updateReadySetGoText() {
        final TextView readySetGoText = (TextView) findViewById(R.id.readySetGo);
        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            public void run() {
                readySetGoText.setText("SET?");
            }
        }, 2000);
        h.postDelayed(new Runnable() {
            public void run() {
                readySetGoText.setText("GO!");
            }
        }, 4000);
        h.postDelayed(new Runnable() {
            public void run() {
                readySetGoText.setVisibility(View.INVISIBLE);
            }
        }, 5000);
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

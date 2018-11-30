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
        }, 3500);
        h.postDelayed(new Runnable() {
            public void run() {
                readySetGoText.setVisibility(View.INVISIBLE);
                Intent intent = new Intent(PlayGameActivity.this, PlayGameActivity2.class);
                startActivity(intent);
                finish();
            }
        }, 4500);
    }
}

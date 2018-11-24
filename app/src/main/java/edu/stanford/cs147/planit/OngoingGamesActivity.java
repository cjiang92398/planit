package edu.stanford.cs147.planit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class OngoingGamesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ongoing_games);
    }

    public void playGame(View view) {
        setContentView(R.layout.activity_play_game);
    }

    public void goHome(View view) {
        // go to GoHomeActivity once Lisa finishes
    }
}

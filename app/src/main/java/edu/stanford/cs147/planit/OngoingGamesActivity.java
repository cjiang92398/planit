package edu.stanford.cs147.planit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class OngoingGamesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ongoing_games);
    }

    public void checkinGame(View view) {
        Intent intent = new Intent(this, CheckinGameActivity.class);
        startActivity(intent);
    }

    public void goHome(View view) {
        if (Boolean.toString(UpdatedHomePage.updatedHomeStarted).equals("false")) {
            startActivity(new Intent(OngoingGamesActivity.this, Home.class));
        } else {
            startActivity(new Intent(OngoingGamesActivity.this, UpdatedHomePage.class));
        }
    }
}

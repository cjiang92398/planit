package edu.stanford.cs147.planit;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class CheckinGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkin_game);

        new CountDownTimer(1000, 1000) {
            @Override
            public void onTick(long n) {
                // blank
            }
            @Override
            public void onFinish() {
                ImageView imageView = (ImageView) findViewById(R.id.checkin);
                imageView.setImageResource(R.drawable.checkinfinal);
                new CountDownTimer(1000, 1000) {
                    @Override
                    public void onTick(long n) {
                        // blank
                    }
                    @Override
                    public void onFinish() {
                        Intent intent = new Intent(getApplicationContext(), PlayGameActivity.class);
                        startActivity(intent);
                    }
                }.start();
            }
        }.start();
    }
}

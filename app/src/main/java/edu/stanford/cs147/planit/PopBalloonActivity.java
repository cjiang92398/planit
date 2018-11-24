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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PopBalloonActivity extends AppCompatActivity {
    // @ johnson, you'll probably use an activity for playing the actual game
    // adding my code here for showing the dialog :)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_balloon);
        BalloonPoppingView popper = (BalloonPoppingView) findViewById(R.id.balloonPoppingView);
        Balloon balloon = new Balloon("San Francisco");
        List<Balloon> balloons = new ArrayList<Balloon>();
        balloons.add(balloon);
        popper.setBalloons(balloons);
        popper.invalidate();
    }
}

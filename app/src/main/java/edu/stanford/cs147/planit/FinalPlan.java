package edu.stanford.cs147.planit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class FinalPlan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_plan);

        //handle share button click
        final Button shareButton = findViewById(R.id.share);
        shareButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My Planit Plan");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Hi All! This is my plan made using Planit! www.planit.com/10293987");
                startActivity(Intent.createChooser(shareIntent, "Share with"));
            }
        });

        //handle return home button click
        final Button returnHomeButton = findViewById(R.id.returnHome);
        returnHomeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                    if (Boolean.toString(UpdatedHomePage.updatedHomeStarted).equals("false")) {
                        startActivity(new Intent(FinalPlan.this, Home.class));
                    } else {
                        startActivity(new Intent(FinalPlan.this, UpdatedHomePage.class));
                    }
            }
        });

    }
}

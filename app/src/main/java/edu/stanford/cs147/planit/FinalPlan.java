/*
* This activity represents the Final Plan page. Users can share their plans with friends via text.
 */

package edu.stanford.cs147.planit;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FinalPlan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_plan);

        //handle share button click
        final Button shareButton = findViewById(R.id.share);
        shareButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sharePlan();
            }
        });

        //handle return home button click
        final Button returnHomeButton = findViewById(R.id.returnHome);
        returnHomeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                    returnHome();
            }
        });

    }

    /*
     * This method allows users to text friends their Planit plan.
     */
    private void sharePlan(){
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My Planit Plan");
        shareIntent.putExtra(Intent.EXTRA_TEXT,
                "Hi All! This is my plan made using Planit! www.planit.com/10293987");
        startActivity(Intent.createChooser(shareIntent, "Share with"));
    }

    /*
     * This method brings the user to the correct home page.
     */
    private void returnHome(){
        if (Boolean.toString(UpdatedHomePage.updatedHomeStarted).equals("false")) {
            startActivity(new Intent(FinalPlan.this, Home.class));
        } else {
            startActivity(new Intent(FinalPlan.this, UpdatedHomePage.class));
        }
    }

}

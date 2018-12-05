/*
* This activity represents the Final Plan page. Users can share their plans with friends via text.
 */

package edu.stanford.cs147.planit;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FinalPlan extends AppCompatActivity {
    private BalloonPoppingView popper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_plan);
        popper = (BalloonPoppingView) findViewById(R.id.balloonPoppingView);

        listIdeas();

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

        //handle return back button click
        final Button backButton = findViewById(R.id.back);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(FinalPlan.this, PopBalloonActivity.class));
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
            Intent intent = new Intent(this, UpdatedHomePage.class);
            Bundle extras = new Bundle();
            extras.putBoolean("filtered", true);
            intent.putExtras(extras);
            startActivity(intent);
        }
    }

    /*
    * This method lists the ideas selected via balloon popping.
     */
    private void listIdeas(){
        ListView ideasListView = findViewById(R.id.ideasList);

        ArrayList<String> savedBalloons = new ArrayList<String>();
        for(int i = 0; i < popper.savedBalloons.size(); i++) {
            savedBalloons.add(popper.savedBalloons.get(i).getIdea());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, savedBalloons);
        ideasListView.setAdapter(adapter);


        final ImageView generalDropdown = findViewById(R.id.generalDropdown);
        if(savedBalloons.size() == 0) {
            generalDropdown.getLayoutParams().height = 716;
            final TextView noIdeasMessage = findViewById(R.id.noIdeasMessage);
            noIdeasMessage.setVisibility(View.VISIBLE);
        } else if (savedBalloons.size() == 1) {
            generalDropdown.getLayoutParams().height = 200;
        } else if (savedBalloons.size() == 2) {
            generalDropdown.getLayoutParams().height = 375;
        } else if (savedBalloons.size() == 3) {
            generalDropdown.getLayoutParams().height = 547;
        } else if (savedBalloons.size() == 4) {
            generalDropdown.getLayoutParams().height = 716;
        } else if (savedBalloons.size() == 5) {
            generalDropdown.getLayoutParams().height = 885;
        }


    }


}

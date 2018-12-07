package edu.stanford.cs147.planit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class Home extends AppCompatActivity {

    //list of destinations on home page
    public static ArrayList<String> destinationsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Content views
        final Button addDestinationButton = findViewById(R.id.addDestination);
        final Button gamesButton = findViewById(R.id.games);
        final ImageButton destination1Button = findViewById(R.id.destination1);
        final ImageButton destination2Button = findViewById(R.id.destination2);
        final ImageButton x1Button = findViewById(R.id.x1);
        final ImageButton x2Button = findViewById(R.id.x2);
        final TextView destination1TextView = findViewById(R.id.destination1Text);
        final TextView destination2TextView = findViewById(R.id.destination2Text);

        initializeHomePage(destination1TextView, destination1Button,
                destination2TextView, destination2Button);

        //handle destination1 button hold
        //shows delete option
        destination1Button.setOnLongClickListener(new View.OnLongClickListener(){
            public boolean onLongClick(View v){
                x1Button.setVisibility(View.VISIBLE);
                return true;
            }
        });
        destination1TextView.setOnLongClickListener(new View.OnLongClickListener(){
            public boolean onLongClick(View v){
                x1Button.setVisibility(View.VISIBLE);
                return true;
            }
        });

        //handle destination2 button hold
        //shows delete option
        destination2Button.setOnLongClickListener(new View.OnLongClickListener(){
            public boolean onLongClick(View v){
                x2Button.setVisibility(View.VISIBLE);
                return true;
            }
        });
        destination2TextView.setOnLongClickListener(new View.OnLongClickListener(){
            public boolean onLongClick(View v){
                x2Button.setVisibility(View.VISIBLE);
                return true;
            }
        });

        //handle x1 button click
        x1Button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                //update destinations
                if(destination2Button.getVisibility() != View.GONE) {
                    destination1TextView.setText(destination2TextView.getText());
                    removeDestination(destination2TextView, destination2Button);
                } else {
                    removeDestination(destination1TextView, destination1Button);
                }

                displayNotifications();
            }
        });

        //handle x2 button click
        x2Button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //update destinations
                removeDestination(destination2TextView, destination2Button);

                displayNotifications();
            }
        });

        //handle addDestination button click
        //transports user to destination setting view
        addDestinationButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                saveHomepageState(destination1TextView, destination2TextView);
                startActivity(new Intent(Home.this, DestinationSetting.class));
            }
        });

        //handle games button click
        //transports user to ongoing games page
        gamesButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                saveHomepageState(destination1TextView, destination2TextView);
                startActivity(new Intent(Home.this, OngoingGamesActivity.class));
            }
        });

    }

    /*
     * This method initializes the home page by displaying the appropriate destinations and
     * notifications.
     */
    private void initializeHomePage(TextView destination1TextView, ImageButton destination1Button,
                                    TextView destination2TextView, ImageButton destination2Button){
        //display appropriate destinations
        if (destinationsList.size() == 0){
            removeDestination(destination1TextView, destination1Button);
            removeDestination(destination2TextView, destination2Button);
        } else if (destinationsList.size() == 1){
            destination1TextView.setText(destinationsList.get(0));
            removeDestination(destination2TextView, destination2Button);
        } else {
            destination1TextView.setText(destinationsList.get(0));
            destination2TextView.setText(destinationsList.get(1));
        }

        displayNotifications();

    }

    /*
     * This method removes the corresponding destination from home page.
     */
    private void removeDestination(TextView destinationTextView, ImageButton destinationButton){
        destinationTextView.setText("");
        destinationButton.setVisibility(View.GONE);
        destinationTextView.setVisibility(View.GONE);
    }

    /*
     * This method displays the appropriate notifications on each destination.
     */
    private void displayNotifications(){

        //Content views
        final ImageView inballoonstagenotif1 = findViewById(R.id.inballoonstagenotif1);
        final ImageView inballoonstagenotif2 = findViewById(R.id.inballoonstagenotif2);
        final ImageView ingamestagenotif1 = findViewById(R.id.ingamestagenotif1);
        final ImageButton destination1Button = findViewById(R.id.destination1);
        final ImageButton x1Button = findViewById(R.id.x1);
        final ImageButton x2Button = findViewById(R.id.x2);
        final TextView destination1TextView = findViewById(R.id.destination1Text);
        final TextView destination2TextView = findViewById(R.id.destination2Text);

        //clear all x
        x1Button.setVisibility(View.GONE);
        x2Button.setVisibility(View.GONE);

        //clear all ellipses
        inballoonstagenotif1.setVisibility(View.GONE);
        inballoonstagenotif2.setVisibility(View.GONE);

        //add ellipses where appropriate
        if(destination1TextView.getText().toString().equals("San Francisco")){
            inballoonstagenotif1.setVisibility(View.VISIBLE);
        }
        if(destination2TextView.getText().toString().equals("San Francisco")){
            inballoonstagenotif2.setVisibility(View.VISIBLE);
        }

        //make user-input destination clickable
        if(ingamestagenotif1.getVisibility() == View.VISIBLE){
            destination1Button.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    Intent intent = new Intent(getApplicationContext(), PopBalloonActivity.class);
                    startActivity(intent);
                }
            });
            destination1TextView.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    Intent intent = new Intent(getApplicationContext(), PopBalloonActivity.class);
                    startActivity(intent);
                }
            });
        }
    }

    /*
     * This method saves the destinations in the homepage
     */
    private void saveHomepageState(TextView destination1TextView, TextView destination2TextView){

        //destinations on home page
        String destination1 = destination1TextView.getText().toString();
        String destination2 = destination2TextView.getText().toString();

        //save state
        destinationsList.clear();
        if(!destination1.equals("")) destinationsList.add(destination1);
        if(!destination2.equals("")) destinationsList.add(destination2);

        //notify other pages of destination list change
        UpdatedHomePage.destinationsList = destinationsList;
        DestinationSetting.destinationsList = destinationsList;

    }

}


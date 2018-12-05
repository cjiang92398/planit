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

public class UpdatedHomePage extends AppCompatActivity {

    public static boolean updatedHomeStarted = false;          //if user has set destination
    public static String newDestination = "";          //user input destination
    public static ArrayList<String> destinationsList;          //list of destinations on home page

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updated_home_page);

        //user has set destination
        updatedHomeStarted = true;

        //Content views
        final Button gamesButton = findViewById(R.id.games);
        final ImageButton destination1Button = findViewById(R.id.destination1);
        final ImageButton destination2Button = findViewById(R.id.destination2);
        final ImageButton destination3Button = findViewById(R.id.destination3);
        final ImageButton x1Button = findViewById(R.id.x1);
        final ImageButton x2Button = findViewById(R.id.x2);
        final ImageButton x3Button = findViewById(R.id.x3);
        final TextView destination1TextView = findViewById(R.id.destination1Text);
        final TextView destination2TextView = findViewById(R.id.destination2Text);
        final TextView destination3TextView = findViewById(R.id.destination3Text);

        initializeHomePage(destination1TextView, destination1Button,
                destination2TextView, destination2Button,
                destination3TextView, destination3Button);

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

        //handle destination3 button hold
        //shows delete option
        destination3Button.setOnLongClickListener(new View.OnLongClickListener(){
            public boolean onLongClick(View v){
                x3Button.setVisibility(View.VISIBLE);
                return true;
            }
        });
        destination3TextView.setOnLongClickListener(new View.OnLongClickListener(){
            public boolean onLongClick(View v){
                x3Button.setVisibility(View.VISIBLE);
                return true;
            }
        });

        //handle x1 button click
        x1Button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                //update destinations
                if (destination2Button.getVisibility() != View.GONE &&
                        destination3Button.getVisibility() != View.GONE) {
                    destination1TextView.setText(destination2TextView.getText());
                    destination2TextView.setText(destination3TextView.getText());
                    removeDestination(destination3TextView, destination3Button);
                } else if(destination2Button.getVisibility() != View.GONE) {
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
                if(destination3Button.getVisibility() != View.GONE) {
                    destination2TextView.setText(destination3TextView.getText());
                    removeDestination(destination3TextView, destination3Button);
                } else {
                    removeDestination(destination2TextView, destination2Button);
                }

                displayNotifications();
            }
        });

        //handle x3 button click
        x3Button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                removeDestination(destination3TextView, destination3Button);
                displayNotifications();
            }
        });

        //handle games button click
        gamesButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                saveHomepageState(destination1TextView, destination2TextView, destination3TextView);
                startActivity(new Intent(UpdatedHomePage.this,
                        OngoingGamesActivity.class));
            }
        });

    }

    /*
    * This method initializes the home page by displaying the appropriate destinations and
    * notifications.
     */
    private void initializeHomePage(TextView destination1TextView, ImageButton destination1Button,
                                    TextView destination2TextView, ImageButton destination2Button,
                                    TextView destination3TextView, ImageButton destination3Button){

        //display appropriate destinations
        if (destinationsList.size() == 0) {
            removeDestination(destination1TextView, destination1Button);
            removeDestination(destination2TextView, destination2Button);
            removeDestination(destination3TextView, destination3Button);
        } else if (destinationsList.size() == 1) {
            destination1TextView.setText(destinationsList.get(0));
            removeDestination(destination2TextView, destination2Button);
            removeDestination(destination3TextView, destination3Button);
        } else if (destinationsList.size() == 2) {
            destination1TextView.setText(destinationsList.get(0));
            destination2TextView.setText(destinationsList.get(1));
            removeDestination(destination3TextView, destination3Button);
        } else {
            destination1TextView.setText(destinationsList.get(0));
            destination2TextView.setText(destinationsList.get(1));
            destination3TextView.setText(destinationsList.get(2));
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
        final ImageView inballoonstagenotif3 = findViewById(R.id.inballoonstagenotif3);
        final ImageView ingamestagenotif1 = findViewById(R.id.ingamestagenotif1);
        final ImageButton x1Button = findViewById(R.id.x1);
        final ImageButton x2Button = findViewById(R.id.x2);
        final ImageButton x3Button = findViewById(R.id.x3);
        final TextView destination1TextView = findViewById(R.id.destination1Text);
        final TextView destination2TextView = findViewById(R.id.destination2Text);

        //clear all x
        x1Button.setVisibility(View.GONE);
        x2Button.setVisibility(View.GONE);
        x3Button.setVisibility(View.GONE);

        //clear all ellipses
        inballoonstagenotif1.setVisibility(View.GONE);
        inballoonstagenotif2.setVisibility(View.GONE);
        inballoonstagenotif3.setVisibility(View.GONE);

        //add ellipses where appropriate
        if(destination1TextView.getText().toString().equals("Paris")){
            inballoonstagenotif1.setVisibility(View.VISIBLE);
        }
        if(destination2TextView.getText().toString().equals("Paris")){
            inballoonstagenotif2.setVisibility(View.VISIBLE);
        }

        //add exclamations where appropriate
        if(destination1TextView.getText().toString().equals(newDestination)) {
            ingamestagenotif1.setVisibility(View.VISIBLE);
        } else {
            ingamestagenotif1.setVisibility(View.GONE);
        }

        setClickForBalloonPopping();
    }

    private void setClickForBalloonPopping() {
        final ImageButton destination1Button = findViewById(R.id.destination1);
        final ImageButton destination2Button = findViewById(R.id.destination2);
        final ImageView inballoonstagenotif1 = findViewById(R.id.inballoonstagenotif1);
        final ImageView inballoonstagenotif2 = findViewById(R.id.inballoonstagenotif2);
        final TextView destination1TextView = findViewById(R.id.destination1Text);
        final TextView destination2TextView = findViewById(R.id.destination2Text);

        destination2Button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if (inballoonstagenotif2.getVisibility() == View.VISIBLE) {
                    Intent intent = new Intent(getApplicationContext(), PopBalloonActivity.class);
                    startActivity(intent);
                }
            }
        });
        destination2TextView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if (inballoonstagenotif2.getVisibility() == View.VISIBLE) {
                    Intent intent = new Intent(getApplicationContext(), PopBalloonActivity.class);
                    startActivity(intent);
                }
            }
        });
        destination1Button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if (inballoonstagenotif1.getVisibility() == View.VISIBLE) {
                    Intent intent = new Intent(getApplicationContext(), PopBalloonActivity.class);
                    startActivity(intent);
                }
            }
        });
        destination1TextView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if (inballoonstagenotif1.getVisibility() == View.VISIBLE) {
                    Intent intent = new Intent(getApplicationContext(), PopBalloonActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    /*
    * This method saves the destinations in the homepage
     */
    private void saveHomepageState(TextView destination1TextView, TextView destination2TextView,
                                   TextView destination3TextView){

        //destinations on home page
        String destination1 = destination1TextView.getText().toString();
        String destination2 = destination2TextView.getText().toString();
        String destination3 = destination3TextView.getText().toString();

        //save state
        destinationsList.clear();
        if(!destination1.equals("")) destinationsList.add(destination1);
        if(!destination2.equals("")) destinationsList.add(destination2);
        if(!destination3.equals("")) destinationsList.add(destination3);

    }
}

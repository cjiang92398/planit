/*
* This activity represents the Destination Setting Page. Users input a destination
* and select friends to add to the game.
 */
package edu.stanford.cs147.planit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class DestinationSetting extends AppCompatActivity {

    //list of destinations on home page
    public static ArrayList<String> destinationsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination_setting);

        //Content views
        final Button elizaSmithButton = findViewById(R.id.elizaSmithButton);
        final Button johnDoeButton = findViewById(R.id.johnDoeButton);
        final Button requestIdeasButton = findViewById(R.id.requestIdeasButton);
        final Button returnHomeButton = findViewById(R.id.returnHome);
        final Button sarahRogersButton = findViewById(R.id.sarahRogersButton);
        final Button selectAllButton = findViewById(R.id.selectAll);
        final EditText destinationEditText = findViewById(R.id.destination);
        final ImageView elizaSmithCheck = findViewById(R.id.elizaSmithCheck);
        final ImageView johnDoeCheck = findViewById(R.id.johnDoeCheck);
        final ImageView sarahRogersCheck = findViewById(R.id.sarahRogersCheck);

        deselectAllFriends(johnDoeCheck, sarahRogersCheck, elizaSmithCheck);

        //handle selectAll button click
        //selects all friends in list
        selectAllButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(johnDoeCheck.getVisibility() == View.INVISIBLE){
                    clickJohnDoe(johnDoeCheck);
                }
                if(sarahRogersCheck.getVisibility() == View.INVISIBLE){
                    clickSarahRogers(sarahRogersCheck);
                }
                if(elizaSmithCheck.getVisibility() == View.INVISIBLE){
                    clickElizaSmith(elizaSmithCheck);
                }
            }
        });

        //handle johnDoe button click
        //selects/deselects JohnDoe based on previous state of checkbox
        johnDoeButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                clickJohnDoe(johnDoeCheck);
            }
        });

        //handle sarahRogers button click
        //selects/deselects Sarah Rogers based on previous state of checkbox
        sarahRogersButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                System.out.println("request made");
                clickSarahRogers(sarahRogersCheck);
            }
        });

        //handle elizaSmith button click
        //selects/deselects Eliza Smith based on previous state of checkbox
        elizaSmithButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                clickElizaSmith(elizaSmithCheck);
            }
        });

        //handle requestIdeasButton button click
        requestIdeasButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                //user input destination
                String destination = destinationEditText.getText().toString();

                //set up toast
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.custom_toast,
                        (ViewGroup) findViewById(R.id.custom_toast_container));
                TextView text = (TextView) layout.findViewById(R.id.text);

                //error checking: no destination entered
                if(destination.equals(""))  {
                    text.setText("Please enter a destination.");
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setView(layout);
                    toast.show();
                }

                //error checking: destination already on home page
                else if (destinationsList.size() == 1 &&
                        destination.toLowerCase().equals(destinationsList.get(0).toLowerCase())) {
                    text.setText("Please enter a NEW destination.");
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setView(layout);
                    toast.show();
                }

                //error checking: destination already on home page
                else if (destinationsList.size() == 2 &&
                        (destination.toLowerCase().equals(destinationsList.get(0).toLowerCase()) ||
                        destination.toLowerCase().equals(destinationsList.get(1).toLowerCase()))){
                    text.setText("Please enter a NEW destination.");
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setView(layout);
                    toast.show();
                }

                //error checking: no friends invited
                else if (johnDoeCheck.getVisibility() == View.INVISIBLE &&
                    sarahRogersCheck.getVisibility() == View.INVISIBLE &&
                        elizaSmithCheck.getVisibility() == View.INVISIBLE) {
                    text.setText("Please invite a friend.");
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setView(layout);
                    toast.show();
                }

                //no errors in user input
                else {
                    destinationsList.add(0, destination);
                    UpdatedHomePage.newDestination= destination;
                    startActivity(new Intent(DestinationSetting.this,
                            CustomizeRequestActivity.class));
                }

            }
        });



        //handle return home button click
        returnHomeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                returnHome();
            }
        });

    }

    /*
     * This method controls selecting/deselecting John Doe on the destination setting page.
     */
    private void clickJohnDoe(ImageView johnDoeCheck){
        if(johnDoeCheck.getVisibility() == View.VISIBLE){
            johnDoeCheck.setVisibility(View.INVISIBLE);
        }
        else johnDoeCheck.setVisibility(View.VISIBLE);
    }

    /*
     * This method controls selecting/deselecting SaraRogers on the destination setting page.
     */
    private void clickSarahRogers(ImageView sarahRogersCheck){
        System.out.println("request fulfilled");
        if(sarahRogersCheck.getVisibility() == View.VISIBLE){
            sarahRogersCheck.setVisibility(View.INVISIBLE);
        }
        else sarahRogersCheck.setVisibility(View.VISIBLE);
    }

    /*
    * This method controls selecting/deselecting Eliza Smith on the destination setting page.
     */
    private void clickElizaSmith(ImageView elizaSmithCheck){
        if(elizaSmithCheck.getVisibility() == View.VISIBLE){
            elizaSmithCheck.setVisibility(View.INVISIBLE);
        }
        else elizaSmithCheck.setVisibility(View.VISIBLE);
    }

    /*
    * This method deselects all friends on the destination setting page.
     */
    private void deselectAllFriends(ImageView johnDoeCheck, ImageView sarahRogersCheck,
                                    ImageView elizaSmithCheck){
        johnDoeCheck.setVisibility(View.INVISIBLE);
        sarahRogersCheck.setVisibility(View.INVISIBLE);
        elizaSmithCheck.setVisibility(View.INVISIBLE);
    }

    /*
    * This method brings the user to the correct home page.
     */
    private void returnHome(){
        if (Boolean.toString(UpdatedHomePage.updatedHomeStarted).equals("false")) {
            startActivity(new Intent(DestinationSetting.this, Home.class));
        } else {
            startActivity(new Intent(DestinationSetting.this, UpdatedHomePage.class));
        }
    }

}

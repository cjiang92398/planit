package edu.stanford.cs147.planit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.View;
import android.widget.ImageView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DestinationSetting extends AppCompatActivity {

    private static ArrayList<String> newDestinationsList;
    public static void setDestinationsList(ArrayList<String> destinationsList) {
        newDestinationsList = destinationsList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination_setting);

        //text in destination EditText
        final EditText destinationEditText = findViewById(R.id.destination);

        //checks and buttons in destination setting page
        final Button selectAllButton = findViewById(R.id.selectAll);
        final Button johnDoeButton = findViewById(R.id.johnDoeButton);
        final Button sarahRogersButton = findViewById(R.id.sarahRogersButton);
        final Button elizaSmithButton = findViewById(R.id.elizaSmithButton);
        final ImageView johnDoeCheck = findViewById(R.id.johnDoeCheck);
        final ImageView sarahRogersCheck = findViewById(R.id.sarahRogersCheck);
        final ImageView elizaSmithCheck = findViewById(R.id.elizaSmithCheck);
        final Button requestIdeasButton = findViewById(R.id.requestIdeasButton);

        //deselect all names in list
        johnDoeCheck.setVisibility(View.INVISIBLE);
        sarahRogersCheck.setVisibility(View.INVISIBLE);
        elizaSmithCheck.setVisibility(View.INVISIBLE);

        //handle selectAll button click
        //selects all names in list
        selectAllButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(johnDoeCheck.getVisibility() == View.INVISIBLE) clickJohnDoe(johnDoeCheck);
                if(sarahRogersCheck.getVisibility() == View.INVISIBLE) clickSarahRogers(sarahRogersCheck);
                if(elizaSmithCheck.getVisibility() == View.INVISIBLE) clickElizaSmith(elizaSmithCheck);
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
        //selects/deselects JohnDoe based on previous state of checkbox
        sarahRogersButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                clickSarahRogers(sarahRogersCheck);
            }
        });

        //handle elizaSmith button click
        //selects/deselects JohnDoe based on previous state of checkbox
        elizaSmithButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                clickElizaSmith(elizaSmithCheck);
            }
        });

        //handle requestIdeasButton button click
        requestIdeasButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String destination = destinationEditText.getText().toString();

                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.custom_toast,
                        (ViewGroup) findViewById(R.id.custom_toast_container));
                TextView text = (TextView) layout.findViewById(R.id.text);
                if(destination.equals(""))  {
                    text.setText("Please enter a destination.");
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setView(layout);
                    toast.show();
                } else if (newDestinationsList.size() == 1 && destination.toLowerCase().equals(newDestinationsList.get(0).toLowerCase())) {
                    text.setText("Please enter a NEW destination.");
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setView(layout);
                    toast.show();
                } else if (newDestinationsList.size() == 2 &&
                        (destination.toLowerCase().equals(newDestinationsList.get(0).toLowerCase()) ||
                        destination.toLowerCase().equals(newDestinationsList.get(1).toLowerCase()))){
                    text.setText("Please enter a NEW destination.");
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setView(layout);
                    toast.show();
                } else {
                    UpdatedHomePage.setDestination(destination);
                    startActivity(new Intent(DestinationSetting.this, UpdatedHomePage.class));
                }

            }
        });



        //handle return home button click
        final Button returnHomeButton = findViewById(R.id.returnHome);
        returnHomeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (Boolean.toString(UpdatedHomePage.updatedHomeStarted).equals("false")) {
                    startActivity(new Intent(DestinationSetting.this, Home.class));
                } else {
                    startActivity(new Intent(DestinationSetting.this, UpdatedHomePage.class));
                }
            }
        });


    }

    private void clickJohnDoe(ImageView johnDoeCheck){
        if(johnDoeCheck.getVisibility() == View.VISIBLE) johnDoeCheck.setVisibility(View.INVISIBLE);
        else johnDoeCheck.setVisibility(View.VISIBLE);
    }

    private void clickSarahRogers(ImageView sarahRogersCheck){
        if(sarahRogersCheck.getVisibility() == View.VISIBLE) sarahRogersCheck.setVisibility(View.INVISIBLE);
        else sarahRogersCheck.setVisibility(View.VISIBLE);
    }

    private void clickElizaSmith(ImageView elizaSmithCheck){
        if(elizaSmithCheck.getVisibility() == View.VISIBLE) elizaSmithCheck.setVisibility(View.INVISIBLE);
        else elizaSmithCheck.setVisibility(View.VISIBLE);
    }






}

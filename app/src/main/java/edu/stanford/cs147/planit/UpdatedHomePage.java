package edu.stanford.cs147.planit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class UpdatedHomePage extends AppCompatActivity {

    public static boolean updatedHomeStarted = false;

    private static String newDestination = "";
    public static void setDestination(String destination) {
        newDestination = destination;
    }

    private static ArrayList<String> newDestinationsList;
    public static void setDestinationsList(ArrayList<String> destinationsList) {
        newDestinationsList = destinationsList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updated_home_page);
        updatedHomeStarted = true;

        //annotations
        final ImageView exclamation1Annotation = findViewById(R.id.exclamation1);
        final ImageView ellipses1Annotation = findViewById(R.id.ellipses1);
        final ImageView ellipses2Annotation = findViewById(R.id.ellipses2);
        final ImageView ellipses3Annotation = findViewById(R.id.ellipses3);

        //handle destination1 button hold
        //shows delete option
        final ImageButton destination1Button = findViewById(R.id.destination1);
        final ImageButton x1Button = findViewById(R.id.x1);
        final TextView destination1TextView = findViewById(R.id.destination1Text);
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


        //handle destination1 button hold
        //shows delete option
        final ImageButton destination2Button = findViewById(R.id.destination2);
        final ImageButton x2Button = findViewById(R.id.x2);
        final TextView destination2TextView = findViewById(R.id.destination2Text);
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

        //handle destination1 button hold
        //shows delete option
        final ImageButton destination3Button = findViewById(R.id.destination3);
        final ImageButton x3Button = findViewById(R.id.x3);
        final TextView destination3TextView = findViewById(R.id.destination3Text);
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
                if (destination2Button.getVisibility() != View.GONE && destination3Button.getVisibility() != View.GONE) {
                    //adopt group 2 destination name
                    destination1TextView.setText(destination2TextView.getText());

                    //adopt group 3 destination name
                    destination2TextView.setText(destination3TextView.getText());

                    //remove group 3
                    destination3TextView.setText("");
                    destination3Button.setVisibility(View.GONE);
                    destination3TextView.setVisibility(View.GONE);

                } else if(destination2Button.getVisibility() != View.GONE) {
                    //adopt group 2 destination name
                    destination1TextView.setText(destination2TextView.getText());

                    //remove group 2
                    destination2TextView.setText("");
                    destination2Button.setVisibility(View.GONE);
                    destination2TextView.setVisibility(View.GONE);

                } else {
                    //remove group 1
                    destination1TextView.setText("");
                    destination1Button.setVisibility(View.GONE);
                    destination1TextView.setVisibility(View.GONE);
                }


                x1Button.setVisibility(View.GONE);
                x2Button.setVisibility(View.GONE);
                x3Button.setVisibility(View.GONE);
                ellipses1Annotation.setVisibility(View.GONE);
                ellipses2Annotation.setVisibility(View.GONE);
                ellipses3Annotation.setVisibility(View.GONE);
                if(destination1TextView.getText().toString().equals("Paris")) ellipses1Annotation.setVisibility(View.VISIBLE);
                if(destination2TextView.getText().toString().equals("Paris")) ellipses2Annotation.setVisibility(View.VISIBLE);
                if(destination3TextView.getText().toString().equals("Paris")) ellipses3Annotation.setVisibility(View.VISIBLE);

                if(!destination1TextView.getText().toString().equals(newDestination)) exclamation1Annotation.setVisibility(View.GONE);

            }
        });

        //handle x2 button click
        x2Button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                if(destination3Button.getVisibility() != View.GONE) {
                    //adopt group 3 destination name
                    destination2TextView.setText(destination3TextView.getText());

                    //remove group 3
                    destination3TextView.setText("");
                    destination3Button.setVisibility(View.GONE);
                    destination3TextView.setVisibility(View.GONE);
                } else {
                    //remove group 2
                    destination2TextView.setText("");
                    destination2Button.setVisibility(View.GONE);
                    destination2TextView.setVisibility(View.GONE);
                }

                x1Button.setVisibility(View.GONE);
                x2Button.setVisibility(View.GONE);
                x3Button.setVisibility(View.GONE);
                ellipses1Annotation.setVisibility(View.GONE);
                ellipses2Annotation.setVisibility(View.GONE);
                ellipses3Annotation.setVisibility(View.GONE);
                if(destination1TextView.getText().toString().equals("Paris")) ellipses1Annotation.setVisibility(View.VISIBLE);
                if(destination2TextView.getText().toString().equals("Paris")) ellipses2Annotation.setVisibility(View.VISIBLE);
                if(destination3TextView.getText().toString().equals("Paris")) ellipses3Annotation.setVisibility(View.VISIBLE);

                if(!destination1TextView.getText().toString().equals(newDestination)) exclamation1Annotation.setVisibility(View.GONE);
            }
        });

        //handle x3 button click
        x3Button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                destination3Button.setVisibility(View.GONE);
                destination3TextView.setVisibility(View.GONE);
                destination3TextView.setText("");

                x1Button.setVisibility(View.GONE);
                x2Button.setVisibility(View.GONE);
                x3Button.setVisibility(View.GONE);
                ellipses1Annotation.setVisibility(View.GONE);
                ellipses2Annotation.setVisibility(View.GONE);
                ellipses3Annotation.setVisibility(View.GONE);
                if(destination1TextView.getText().toString().equals("Paris")) ellipses1Annotation.setVisibility(View.VISIBLE);
                if(destination2TextView.getText().toString().equals("Paris")) ellipses2Annotation.setVisibility(View.VISIBLE);
                if(destination3TextView.getText().toString().equals("Paris")) ellipses3Annotation.setVisibility(View.VISIBLE);

                if(!destination1TextView.getText().toString().equals(newDestination)) exclamation1Annotation.setVisibility(View.GONE);
            }
        });


        if (newDestinationsList.size() == 0) {
            //0 items in last home page state
            destination2TextView.setText("");
            destination2Button.setVisibility(View.GONE);
            destination2TextView.setVisibility(View.GONE);

            destination3TextView.setText("");
            destination3Button.setVisibility(View.GONE);
            destination3TextView.setVisibility(View.GONE);
        } else if (newDestinationsList.size() == 1) {
            //1 item in last home page state
            destination2TextView.setText(newDestinationsList.get(0));

            destination3Button.setVisibility(View.GONE);
            destination3TextView.setVisibility(View.GONE);
            destination3TextView.setText("");
        } else {
            //2 items in last home page state
            destination2TextView.setText(newDestinationsList.get(0));
            destination3TextView.setText(newDestinationsList.get(1));
        }

        //handle games button click
        //transports user to ongoing games page
        final Button gamesButton = findViewById(R.id.games);
        gamesButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(UpdatedHomePage.this, OngoingGamesActivity.class));
            }
        });

        if(destination2TextView.getText().toString().equals("Paris")) ellipses2Annotation.setVisibility(View.VISIBLE);
        if(destination3TextView.getText().toString().equals("Paris")) ellipses3Annotation.setVisibility(View.VISIBLE);

        exclamation1Annotation.setVisibility(View.VISIBLE);

        destination1TextView.setText(newDestination);

    }


}


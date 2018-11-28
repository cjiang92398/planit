package edu.stanford.cs147.planit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ImageView;
import java.util.ArrayList;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //annotations
        final ImageView exclamation1Annotation = findViewById(R.id.exclamation1);
        final ImageView ellipses1Annotation = findViewById(R.id.ellipses1);
        final ImageView exclamation2Annotation = findViewById(R.id.exclamation2);
        final ImageView ellipses2Annotation = findViewById(R.id.ellipses2);

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

        //handle x1 button click
        x1Button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                if(destination2Button.getVisibility() != View.GONE) {
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
                exclamation1Annotation.setVisibility(View.GONE);
                ellipses1Annotation.setVisibility(View.GONE);
                exclamation2Annotation.setVisibility(View.GONE);
                ellipses2Annotation.setVisibility(View.GONE);
                if(destination1TextView.getText().toString().equals("Paris")) ellipses1Annotation.setVisibility(View.VISIBLE);
                if(destination2TextView.getText().toString().equals("Paris")) ellipses2Annotation.setVisibility(View.VISIBLE);
            }
        });

        //handle x2 button click
        x2Button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //remove group 2
                destination2TextView.setText("");
                destination2Button.setVisibility(View.GONE);
                destination2TextView.setVisibility(View.GONE);

                x1Button.setVisibility(View.GONE);
                x2Button.setVisibility(View.GONE);
                exclamation1Annotation.setVisibility(View.GONE);
                ellipses1Annotation.setVisibility(View.GONE);
                exclamation2Annotation.setVisibility(View.GONE);
                ellipses2Annotation.setVisibility(View.GONE);
                if(destination1TextView.getText().toString().equals("Paris")) ellipses1Annotation.setVisibility(View.VISIBLE);
                if(destination2TextView.getText().toString().equals("Paris")) ellipses2Annotation.setVisibility(View.VISIBLE);
            }
        });

        //handle addGame button click
        //transports user to destination setting view
        final Button addGameButton = findViewById(R.id.addGame);
        addGameButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //save homepage state
                ArrayList<String> destinationsList = new ArrayList<>();
                if(!destination1TextView.getText().toString().equals("")) {
                    destinationsList.add(destination1TextView.getText().toString());
                }
                if(!destination2TextView.getText().toString().equals("")) {
                    destinationsList. add(destination2TextView.getText().toString());
                }
                UpdatedHomePage.setDestinationsList(destinationsList);
                DestinationSetting.setDestinationsList(destinationsList);

                //start next activity
                startActivity(new Intent(Home.this, DestinationSetting.class));
            }
        });

        if(destination1TextView.getText().toString().equals("Paris")) ellipses1Annotation.setVisibility(View.VISIBLE);
        if(destination2TextView.getText().toString().equals("Paris")) ellipses2Annotation.setVisibility(View.VISIBLE);

    }


}


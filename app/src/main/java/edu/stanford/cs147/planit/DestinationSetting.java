package edu.stanford.cs147.planit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.ImageView;

public class DestinationSetting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination_setting);

        //checks and buttons in destination setting page
        final Button selectAllButton = findViewById(R.id.selectAll);
        final Button johnDoeButton = findViewById(R.id.johnDoeButton);
        final Button sarahRogersButton = findViewById(R.id.sarahRogersButton);
        final Button elizaSmithButton = findViewById(R.id.elizaSmithButton);
        final ImageView johnDoeCheck = findViewById(R.id.johnDoeCheck);
        final ImageView sarahRogersCheck = findViewById(R.id.sarahRogersCheck);
        final ImageView elizaSmithCheck = findViewById(R.id.elizaSmithCheck);

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

    }

    public void clickJohnDoe(ImageView johnDoeCheck){
        if(johnDoeCheck.getVisibility() == View.VISIBLE) johnDoeCheck.setVisibility(View.INVISIBLE);
        else johnDoeCheck.setVisibility(View.VISIBLE);
    }

    public void clickSarahRogers(ImageView sarahRogersCheck){
        if(sarahRogersCheck.getVisibility() == View.VISIBLE) sarahRogersCheck.setVisibility(View.INVISIBLE);
        else sarahRogersCheck.setVisibility(View.VISIBLE);
    }

    public void clickElizaSmith(ImageView elizaSmithCheck){
        if(elizaSmithCheck.getVisibility() == View.VISIBLE) elizaSmithCheck.setVisibility(View.INVISIBLE);
        else elizaSmithCheck.setVisibility(View.VISIBLE);
    }
}

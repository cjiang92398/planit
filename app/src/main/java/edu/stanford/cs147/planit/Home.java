package edu.stanford.cs147.planit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //handle addGame button click
        //transports user to destination setting view
        final Button addGameButton = findViewById(R.id.addGame);
        addGameButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(Home.this, DestinationSetting.class));
            }
        });
    }


}


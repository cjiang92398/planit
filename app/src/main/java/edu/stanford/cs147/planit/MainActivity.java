/*
* This activity represents the login page. Users log in to their accounts by
* inputting username and password and clicking login.
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
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game_2);
        startActivity(new Intent(MainActivity.this, PlayGameActivity2.class));

//        //handle login button click
//        final Button loginButton = findViewById(R.id.login);
//        loginButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//
//                //error checking: no username or password entered
//                final String usernameEntry = ((EditText) findViewById(R.id.username)).getText().toString();
//                final String passwordEntry = ((EditText) findViewById(R.id.password)).getText().toString();
//                if(usernameEntry.equals("") || passwordEntry.equals("")) {
//
//                    //display custom toast with "Please enter your username and password."
//                    LayoutInflater inflater = getLayoutInflater();
//                    View layout = inflater.inflate(R.layout.custom_toast,
//                            (ViewGroup) findViewById(R.id.custom_toast_container));
//                    TextView text = (TextView) layout.findViewById(R.id.text);
//                    text.setText("Please enter your username and password.");
//                    Toast toast = new Toast(getApplicationContext());
//                    toast.setDuration(Toast.LENGTH_SHORT);
//                    toast.setView(layout);
//                    toast.show();
//
//                }
//
//                //no errors in user input
//                else {
//
//                    //start app with 2 pre-added locations
//                    Home.destinationsList.add("Paris");
//                    Home.destinationsList.add("Tokyo");
//
//                    //transport user to home page
//                    startActivity(new Intent(MainActivity.this, Home.class));
//
//                    //startActivity(new Intent(MainActivity.this, FinalPlan.class));
//                }
//
//
//            }
//        });
    }
}

package edu.stanford.cs147.planit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class CustomizeRequestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize_request);
    }

    public void cancel(View view) {
        // go back to destination/friend setting once Lisa finishes
    }

    public void send(View view) {
        // go to GoHomeActivity once Lisa finishes
        Intent intent = new Intent(this, MainActivity.class); // main activity for now, replace with home
        startActivity(intent);
        Toast toast = Toast.makeText(getApplicationContext(), "Your invite has been sent!", Toast.LENGTH_LONG);
        toast.show();
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
}
package edu.stanford.cs147.planit;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), AlertDialog.THEME_HOLO_LIGHT,this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        month++; // adjust for zero index
        Toast toast = Toast.makeText(getContext(), "Deadline " + month + "/" +
                day + "/" + year + " set", Toast.LENGTH_SHORT);
        toast.show();
    }
}
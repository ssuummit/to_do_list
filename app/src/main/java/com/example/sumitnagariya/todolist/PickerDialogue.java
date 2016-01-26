package com.example.sumitnagariya.todolist;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by sumit.nagariya on 24/01/16.
 */
public class PickerDialogue extends android.support.v4.app.DialogFragment implements DatePickerDialog.OnDateSetListener {

    TextView txtDate;

    public PickerDialogue(View view)
    {
        txtDate = (TextView)view;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(),this,year,month,day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

        String date = dayOfMonth+"-"+monthOfYear+"-"+year;
        txtDate.setText(date);

    }
}

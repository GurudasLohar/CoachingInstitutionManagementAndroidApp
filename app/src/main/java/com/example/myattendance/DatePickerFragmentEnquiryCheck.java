package com.example.myattendance;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class DatePickerFragmentEnquiryCheck extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    TextView txtEnquiryDateCheck;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(),this,year,month,day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        txtEnquiryDateCheck = getActivity().findViewById(R.id.txtEnquiryDateCheck);
        String endate = dayOfMonth+"/"+(month+1)+"/"+year;
        txtEnquiryDateCheck.setText(endate);
    }
}

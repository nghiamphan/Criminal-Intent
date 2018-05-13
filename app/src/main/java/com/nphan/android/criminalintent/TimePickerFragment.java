package com.nphan.android.criminalintent;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimePickerFragment extends DialogFragment {

    public static final String EXTRA_TIME = "com.nphan.android.criminalintent.time";

    private static final String ARG_TIME = "time";

    Calendar mCalendar = Calendar.getInstance();

    private TimePicker mTimePicker;

    public static TimePickerFragment newInstance(Date date) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_TIME, date);

        TimePickerFragment fragment = new TimePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Date date = (Date) getArguments().getSerializable(ARG_TIME);

        mCalendar.setTime(date);

        int hour = mCalendar.get(Calendar.HOUR);
        int minute = mCalendar.get(Calendar.MINUTE);

        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_time, null);

        mTimePicker = v.findViewById(R.id.dialog_time_picker);
        if (Build.VERSION.SDK_INT >= 23) {
            mTimePicker.setHour(hour);
            mTimePicker.setMinute(minute);
        }
        else {
            mTimePicker.setCurrentHour(hour);
            mTimePicker.setCurrentMinute(minute);
        }

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.time_picker_title)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int year =mCalendar.get(Calendar.YEAR);
                        int month = mCalendar.get(Calendar.MONTH);
                        int day = mCalendar.get(Calendar.DAY_OF_MONTH);
                        int hour;
                        int minute;
                        if (Build.VERSION.SDK_INT >= 23) {
                            hour = mTimePicker.getHour();
                            minute = mTimePicker.getMinute();
                        }
                        else {
                            hour = mTimePicker.getChildCount();
                            minute = mTimePicker.getChildCount();
                        }
                        Date date = new GregorianCalendar(year, month, day, hour, minute).getTime();
                        sendResult(Activity.RESULT_OK, date);
                    }
                })
                .create();
    }

    private void sendResult(int resultCode, Date date) {
        if (getTargetFragment() == null) {
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(EXTRA_TIME, date);

        getTargetFragment()
                .onActivityResult(getTargetRequestCode(), resultCode, intent);
    }
}
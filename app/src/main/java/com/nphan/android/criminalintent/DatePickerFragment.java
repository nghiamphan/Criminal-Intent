package com.nphan.android.criminalintent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.Date;
import java.util.GregorianCalendar;

public class DatePickerFragment extends DialogFragment {

    public static final String EXTRA_DATE = "com.nphan.android.criminalintent.date";

    //private static final String ARG_DATE = "date";

    private DatePicker mDatePicker;
    private Button mOkButton;

    /*public static DatePickerFragment newInstance(Date date) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATE, date);

        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }*/

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.date_picker_fragment, container, false);

        mDatePicker = v.findViewById(R.id.date_picker);
        int[] dateArray = getActivity().getIntent()
                .getIntArrayExtra(DatePickerActivity.EXTRA_CRIME_DATE);
        int year = dateArray[0];
        int month = dateArray[1];
        int day = dateArray[2];
        final int hour = dateArray[3];
        final int minute = dateArray[4];
        mDatePicker.init(year, month, day, null);

        //setTargetFragment(CrimeFragment, CrimeFragment.REQUEST_DATE);

        mOkButton = v.findViewById(R.id.date_picker_ok_button);
        mOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int year = mDatePicker.getYear();
                int month = mDatePicker.getMonth();
                int day = mDatePicker.getDayOfMonth();
                Date date = new GregorianCalendar(year, month, day, hour, minute).getTime();
                sendResult(Activity.RESULT_OK, date);
            }
        });

        return v;
    }

    private void sendResult(int resultCode, Date date) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_DATE, date);
        getActivity().setResult(resultCode, intent);
        getActivity().finish();
    }
}

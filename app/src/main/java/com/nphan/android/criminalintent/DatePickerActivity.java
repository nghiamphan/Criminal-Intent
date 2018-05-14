package com.nphan.android.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DatePickerActivity extends SingleFragmentActivity {

    public static final String EXTRA_CRIME_DATE = "com.nphan.android.criminalintent.crime_date";

    public static Intent newIntent(Context packageContext, int[] dateArray) {
        Intent intent = new Intent(packageContext, DatePickerActivity.class);
        intent.putExtra(EXTRA_CRIME_DATE, dateArray);
        return intent;
    }

    @Override
    protected Fragment createFragment() {

        int[] dateArray = getIntent().getIntArrayExtra(EXTRA_CRIME_DATE);
        int year = dateArray[0];
        int month = dateArray[1];
        int day = dateArray[2];
        final int hour = dateArray[3];
        final int minute = dateArray[4];
        Date date = new GregorianCalendar(year, month, day, hour, minute).getTime();

        return DatePickerFragment.newInstance(date);
    }
}
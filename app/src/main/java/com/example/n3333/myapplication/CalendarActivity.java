package com.example.n3333.myapplication;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CalendarActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private TextView txt;
    private Button btnCalendar;
    private int mYear, mMonth, mDay;
    private String selectedDate, msSelectedDate;
    Calendar cal = Calendar.getInstance();
    Calendar c = Calendar.getInstance();
    private EditText mEtSelectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        txt = findViewById(R.id.tv);
        mEtSelectedDate = findViewById(R.id.et_calendar);


        Date c = cal.getTime();
        SimpleDateFormat df = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            df = new SimpleDateFormat("yyyy-MM-dd");
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            selectedDate = df.format(c);
        }

        mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH);
        mDay = cal.get(Calendar.DAY_OF_MONTH);

        btnCalendar = findViewById(R.id.btn_calendar);
        msSelectedDate = mEtSelectedDate.getText().toString();
        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
//                DialogFragment datePicker = new GalleryFr();
//                datePicker.show(getSupportFragmentManager(), "date picker");


//                option2();
                SharedPreferences getData = getApplicationContext().getSharedPreferences("Hello", MODE_PRIVATE); // 0 - for private mode

            }
        });

        mEtSelectedDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!editable.toString().isEmpty()) {
                    msSelectedDate = mEtSelectedDate.getText().toString();
                }
            }
        });
    }

    private void showDatePicker() {

        long timeInMillis = getmilifromdate(txt.getText().toString());
        c.setTimeInMillis(timeInMillis);

        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        //2018-08-15
//                        Calendar class index is showing 0 to 11 Month in onDateSet callback.
                        selectedDate = year + "-" + ((monthOfYear + 1)) + "-" + (dayOfMonth);
                        Log.i("TAGS", "msSelectedDate = " + msSelectedDate);

                        txt.setText(selectedDate);
                    }
                }, mYear, mMonth, mDay);


        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
 /*          Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        txt.setText(currentDateString);
*/
    }

    public long getmilifromdate(String datei) // 2015-11-21
    {
        Date date = null;
        try {
            SimpleDateFormat sdf = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                sdf = new SimpleDateFormat("yyyy-MM-dd");
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                date = sdf.parse(datei);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date == null ? System.currentTimeMillis() : date.getTime();
    }

    private void option2() {
        cal.set(Calendar.MONTH, mMonth);
        cal.set(Calendar.DATE, mDay);
        cal.set(Calendar.YEAR, mYear);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        mMonth = monthOfYear;
                        mDay = dayOfMonth;
                        mYear = year;

                        //2018-08-15
                        selectedDate = year + "-" + ((monthOfYear + 1)) + "-" + (dayOfMonth);
                        txt.setText(selectedDate);
                    }
                }, mYear, mMonth, mDay);

        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.show();
    }

    public static Date convertStringToDate(String sDateInString, String sDateFormat) {

        DateFormat format = new java.text.SimpleDateFormat(sDateFormat, Locale.US);
        format.setLenient(false);
        Date date = null;
        try {
            date = format.parse(sDateInString);
        } catch (ParseException e) {
            Log.i("TAG", "convertStringToDate Error :" + e.toString());
            return null;
        }
        return date;
    }
}

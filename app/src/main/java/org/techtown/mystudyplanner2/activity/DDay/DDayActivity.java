package org.techtown.mystudyplanner2.activity.DDay;

import android.app.DatePickerDialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import org.techtown.mystudyplanner2.R;

import java.util.Calendar;
import java.util.GregorianCalendar;


public class DDayActivity extends AppCompatActivity {

    EditText nameEdit;
    TextView dateText;
    TextView ddayText;

    SQLiteDatabase database;

    GregorianCalendar calendar = new GregorianCalendar();
    int year_ = calendar.get(Calendar.YEAR);
    int month_ = calendar.get(Calendar.MONTH);
    int day_ = calendar.get(Calendar.DAY_OF_MONTH);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dday);

        nameEdit = findViewById(R.id.nameEdit);
        dateText = findViewById(R.id.dateText);
        ddayText = findViewById(R.id.ddayText);


        database = openOrCreateDatabase("dday.db", MODE_PRIVATE, null);
        database.execSQL("create table if not exists dday("
        + " _id integer PRIMARY KEY autoincrement, "
        + " name text, "
        + " year int, "
        + " month int, "
        + " day int)");


        dateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(DDayActivity.this, dateSetListener, year_, month_, day_).show();
            }
        });



        Button storeButton = findViewById(R.id.storeButton);
        storeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEdit.getText().toString();

                database.execSQL("insert into dday"
                + "(name, year, month, day) "
                + " values "
                + "('" + name + "', '" + year_ + "', '" + month_ + "', '" + day_ + "')");

                finish();

            }
        });






    }

    // datePicker (set testName)
    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateText.setText(year + "년 " + (monthOfYear+1) + "월 " + dayOfMonth + "일");

            year_ = year;
            month_ = monthOfYear + 1;
            day_ = dayOfMonth;

        }
    };
}

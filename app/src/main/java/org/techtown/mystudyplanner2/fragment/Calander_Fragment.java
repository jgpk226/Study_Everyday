package org.techtown.mystudyplanner2.fragment;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.techtown.mystudyplanner2.R;
import org.techtown.mystudyplanner2.activity.PlannerDataInput.PlannerActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;


public class Calander_Fragment extends Fragment {

    int sel_year;
    int sel_month;
    int sel_day;

    TextView dateText;
    TextView nowTimeText;
    TextView ddayText;
    EditText doitEdit;

    SQLiteDatabase contentDatabase;
    SQLiteDatabase ddayDatabase;
    SQLiteDatabase doitDatabase;


    // onResume

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.calander_fragment, container, false);

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);


        dateText = rootView.findViewById(R.id.dateText);
        nowTimeText = rootView.findViewById(R.id.nowTimeText);
        ddayText = rootView.findViewById(R.id.ddayText);
        doitEdit = rootView.findViewById(R.id.doitEdit);

        final Button doitSaveButton = rootView.findViewById(R.id.doitSaveButton);



        // set Today's date
        GregorianCalendar toDayMan = new GregorianCalendar();
        sel_year = toDayMan.get(toDayMan.YEAR);
        sel_month = toDayMan.get(toDayMan.MONTH) + 1; // calendar month starts from 0 in api
        sel_day = toDayMan.get(toDayMan.DAY_OF_MONTH);

        final String today = "p" + sel_year + sel_month + sel_day;
        dateText.setText(sel_year + "년 " + sel_month + "월 " + sel_day + "일");



        // 현재까지 공부시간 계산해서 설정하기
        contentDatabase = getActivity().openOrCreateDatabase("planner.db", Context.MODE_PRIVATE, null);
        contentDatabase.execSQL("create table if not exists " + today + "("
                + "_id integer PRIMARY KEY autoincrement, "
                + " subject text, "
                + " content text, "
                + " hour int, "
                + " min int, "
                + " sec int, "
                + " dDay text, "
                + " diary text)");

        Cursor cursor1 = contentDatabase.rawQuery("select _id, subject, content, hour, min, sec, dDay, diary from " + today, null);
        int recordCount1 = cursor1.getCount();
        int sum_hour = 0, sum_min = 0, sum_sec = 0;

            for(int i=0; i<recordCount1; i++) {
                cursor1.moveToNext();

                int new_id = cursor1.getInt(0);
                String new_subject = cursor1.getString(1);
                String new_content = cursor1.getString(2);
                int new_hour = cursor1.getInt(3);
                int new_min = cursor1.getInt(4);
                int new_sec = cursor1.getInt(5);
                String dDay = cursor1.getString(6);
                String diary = cursor1.getString(7);

                sum_hour += new_hour;
                sum_min += new_min;
                sum_sec += new_sec;

                if(sum_sec >= 60){
                    sum_sec -= 60;
                    sum_min += 1;
                }
                if(sum_min >= 60){
                    sum_min -= 60;
                    sum_hour +=1;
                }

            }



            nowTimeText.setText("현재까지 " + sum_hour + "h " + sum_min + "m ");



        // 오늘날짜 디데이 계산하기
        ddayDatabase = getActivity().openOrCreateDatabase("dday.db",android.content.Context.MODE_PRIVATE ,null);
        ddayDatabase.execSQL("create table if not exists dday("
                + " _id integer PRIMARY KEY autoincrement, "
                + " name text, "
                + " year int, "
                + " month int, "
                + " day int)");

        Cursor cursor = ddayDatabase.rawQuery("select _id, name, year, month, day from dday", null);
        int recordCount = cursor.getCount();

        if(recordCount != 0){
            cursor.moveToLast();

            int new_id = cursor.getInt(0);
            String new_name = cursor.getString(1);
            int new_year = cursor.getInt(2);
            int new_month = cursor.getInt(3);
            int new_day = cursor.getInt(4);

            String firstDate = new_year + "-" + new_month + "-" + new_day;
            String secondDate = sel_year + "-" + sel_month + "-" + sel_day;
            calculateDday(firstDate, secondDate, new_name);
        }
        else {
            ddayText.setText("D-Day를 설정해주세요.");
        }



        // 두잇 데이터베이스 만들고 조회하기
        doitDatabase = getActivity().openOrCreateDatabase("doit.db", Context.MODE_PRIVATE, null);
        doitDatabase.execSQL("create table if not exists " + today + "("
        + " _id integer PRIMARY KEY autoincrement, "
        + " content text)");

        Cursor cursor2 = doitDatabase.rawQuery("select _id, content from " + today, null);
        int recordCount2 = cursor2.getCount();

        cursor2.moveToLast();

        if(recordCount2 != 0){
            int new_id = cursor2.getInt(0);
            String new_content = cursor2.getString(1);

            doitEdit.setText(new_content);
        }
        else {
            doitEdit.setText(null);
        }



        doitSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String doit = doitEdit.getText().toString();

                // 데이터베이스에 저장하기
                doitDatabase.execSQL("DELETE FROM " + today + " WHERE _id = '" + 1 + "';");
                doitDatabase.execSQL("insert into " + today
                        +"(content) "
                        + " values "
                        + "('" + doit + "')");

                Toast.makeText(getContext(), "저장되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });




        // calendarView clicked
        CalendarView calendarView = (CalendarView) rootView.findViewById(R.id.calanderItem);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                sel_year = year;
                sel_month = month + 1;
                sel_day = dayOfMonth;
                final String sel_date = "p" + sel_year + sel_month + sel_day;

                dateText.setText(sel_year + "년 " + sel_month + "월 " + sel_day + "일");

                // 선택된 날짜 디데이 계산하기
                ddayDatabase = getActivity().openOrCreateDatabase("dday.db",android.content.Context.MODE_PRIVATE ,null);
                ddayDatabase.execSQL("create table if not exists dday("
                        + " _id integer PRIMARY KEY autoincrement, "
                        + " name text, "
                        + " year int, "
                        + " month int, "
                        + " day int)");

                Cursor cursor = ddayDatabase.rawQuery("select _id, name, year, month, day from dday", null);
                int recordCount = cursor.getCount();


                // 지정 디데이의 아이디를 천으로 하면 과연 마지막이 될 것인가?!
                if(recordCount != 0){
                    cursor.moveToLast();

                    int new_id = cursor.getInt(0);
                    String new_name = cursor.getString(1);
                    int new_year = cursor.getInt(2);
                    int new_month = cursor.getInt(3);
                    int new_day = cursor.getInt(4);

                    String firstDate = new_year + "-" + new_month + "-" + new_day;
                    String secondDate = sel_year + "-" + sel_month + "-" + sel_day;
                    calculateDday(firstDate, secondDate, new_name);
                }


                // 선택 날짜 공부시간 계산해서 설정하기
                contentDatabase = getActivity().openOrCreateDatabase("planner.db", Context.MODE_PRIVATE, null);
                contentDatabase.execSQL("create table if not exists " + sel_date + "("
                        + "_id integer PRIMARY KEY autoincrement, "
                        + " subject text, "
                        + " content text, "
                        + " hour int, "
                        + " min int, "
                        + " sec int, "
                        + " dDay text, "
                        + " diary text)");

                Cursor cursor1 = contentDatabase.rawQuery("select _id, subject, content, hour, min, sec, dDay, diary from " + sel_date, null);
                int recordCount1 = cursor1.getCount();
                int sum_hour = 0, sum_min = 0, sum_sec = 0;

                for(int i=0; i<recordCount1; i++) {
                    cursor1.moveToNext();

                    int new_id = cursor1.getInt(0);
                    String new_subject = cursor1.getString(1);
                    String new_content = cursor1.getString(2);
                    int new_hour = cursor1.getInt(3);
                    int new_min = cursor1.getInt(4);
                    int new_sec = cursor1.getInt(5);
                    String dDay = cursor1.getString(6);
                    String diary = cursor1.getString(7);


                    sum_hour += new_hour;
                    sum_min += new_min;
                    sum_sec += new_sec;

                    if(sum_sec >= 60){
                        sum_sec -= 60;
                        sum_min += 1;
                    }
                    if(sum_min >= 60){
                        sum_min -= 60;
                        sum_hour +=1;
                    }
                }


                nowTimeText.setText("현재까지 " + sum_hour + "h " + sum_min + "m ");



                // 두잇 데이터 조회하기 (데이터 용량을 위해 기존을 삭제하고 한개만 생성 삭제 하기)
                doitDatabase = getActivity().openOrCreateDatabase("doit.db", Context.MODE_PRIVATE, null);
                doitDatabase.execSQL("create table if not exists " + sel_date + "("
                        + " _id integer PRIMARY KEY autoincrement, "
                        + " content text)");


                Cursor cursor3 = doitDatabase.rawQuery("select _id, content from " + sel_date, null);
                int recordCount3 = cursor3.getCount();

                for(int i=0; i<recordCount3; i++){
                    cursor3.moveToNext();

                    int new_id = cursor3.getInt(0);
                    String new_content = cursor3.getString(1);

                    doitEdit.setText(new_content);
                }

                if(recordCount3 == 0){
                    doitEdit.setText(null);
                }



                // 버튼 누르면 인서트하기
                doitSaveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String doit = doitEdit.getText().toString();

                        // 데이터베이스에 저장하기
                        doitDatabase.execSQL("DELETE FROM " + sel_date + " WHERE _id = '" + 0 + "';");
                        doitDatabase.execSQL("insert into " + sel_date
                                +"(content) "
                                + " values "
                                + "('" + doit + "')");

                        Toast.makeText(getContext(), "저장되었습니다.", Toast.LENGTH_LONG).show();
                    }
                });

            }
        });



        // plannerButton clicked
        Button plannerButton = rootView.findViewById(R.id.plannerButton);
        plannerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // go to PlannerActivity with Intent(date)
                Intent intent = new Intent(getContext(), PlannerActivity.class);

                intent.putExtra("year", sel_year);
                intent.putExtra("month", sel_month);
                intent.putExtra("day", sel_day);

                startActivityForResult(intent, 101);
            }
        });

        return rootView;
    }



    private void calculateDday(String firstDate, String secondDate, String name){

        try{ // String Type을 Date Type으로 캐스팅하면서 생기는 예외로 인해 여기서 예외처리 해주지 않으면 컴파일러에서 에러가 발생해서 컴파일을 할 수 없다.
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            // date1, date2 두 날짜를 parse()를 통해 Date형으로 변환.
            Date FirstDate = format.parse(firstDate);
            Date SecondDate = format.parse(secondDate);

            // Date로 변환된 두 날짜를 계산한 뒤 그 리턴값으로 long type 변수를 초기화 하고 있다.
            // 연산결과 -950400000. long type 으로 return 된다.
            long calDate = FirstDate.getTime() - SecondDate.getTime();

            // Date.getTime() 은 해당날짜를 기준으로1970년 00:00:00 부터 몇 초가 흘렀는지를 반환해준다.
            // 이제 24*60*60*1000(각 시간값에 따른 차이점) 을 나눠주면 일수가 나온다.
            long calDateDays = calDate / ( 24*60*60*1000);

            calDateDays = Math.abs(calDateDays);


            if(calDate < 0){
                ddayText.setText("D + " + calDateDays + " " + name);
            }
            else if(calDate == 0){
                ddayText.setText("D - Day" + " " + name);
            }
            else if(calDate > 0){
                ddayText.setText("D - " + calDateDays + " " + name);
            }

        }
        catch(ParseException e)
        {
            // 예외 처리
        }

    }

}

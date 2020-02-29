package org.techtown.mystudyplanner2.activity.PlannerDataInput;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.techtown.mystudyplanner2.R;
import org.techtown.mystudyplanner2.etc.StudyContent.OnStudyContentClickListener;
import org.techtown.mystudyplanner2.etc.StudyContent.StudyContent;
import org.techtown.mystudyplanner2.etc.StudyContent.StudyContentAdapter;


public class PlannerActivity extends AppCompatActivity {

    TextView dayTextView;

    EditText diaryEdit;
    Button saveButton;
    TextView totalTimeText;

    SQLiteDatabase database;
    SQLiteDatabase database2;
    StudyContentAdapter adapter;


    // apply result as insert (same with onCreate method)
    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_planner);

        int sum_Hour = 0;
        int sum_Min = 0;
        int sum_Sec = 0;



        // get intent from Calendar_Fragment && set date in dayTextView
        Intent receivedIntent = getIntent();
        int year = receivedIntent.getIntExtra("year", 0);
        int month = receivedIntent.getIntExtra("month", 0);
        int day = receivedIntent.getIntExtra("day", 0);

        dayTextView = findViewById(R.id.dayTextView);
        dayTextView.setText(year + "년 " + month + "월 " + day + "일");
        //


        // make Database - Table name
        String text_year = Integer.toString(year);
        String  text_month = Integer.toString(month);
        String text_day = Integer.toString(day);
        final String date = "p" + text_year + text_month + text_day;
        final String diary_date = "d" + text_year + text_month + text_day;
        //


        // create or get Database for planner as soon as PlannerActivity start
        createDatabase("planner.db");
        createTable(date);


        // create db for diary
        openOrCreateDatabase("planner.db", MODE_PRIVATE, null);
        database.execSQL("create table if not exists " + diary_date + "("
                + "_id integer PRIMARY KEY autoincrement, "
                + " diary text)");


        // make RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new StudyContentAdapter();


        // view records created by Timer and Enter Activity && create totalTimeText
        database = openOrCreateDatabase("planner.db", MODE_PRIVATE, null);
        Cursor cursor = database.rawQuery("select _id, subject, content, hour, min, sec, dDay, diary from " + date, null);
        int recordCount = cursor.getCount();


        for(int i=0; i<recordCount; i++){
            cursor.moveToNext();
            int new_id = cursor.getInt(0);
            String new_subject = cursor.getString(1);
            String new_content = cursor.getString(2);
            int new_hour = cursor.getInt(3);
            int new_min = cursor.getInt(4);
            int new_sec = cursor.getInt(5);
            String new_dDay = cursor.getString(6);
            String new_diary = cursor.getString(7);

            adapter.addItem(new StudyContent(new_id, new_subject, new_content, new_hour, new_min, new_sec));

            // for totalTime
            sum_Hour += new_hour;
            sum_Min += new_min;
            sum_Sec += new_sec;

            if(sum_Sec >= 60){
                sum_Sec -= 60;
                sum_Min += 1;
            }

            if(sum_Min >= 60){
                sum_Min -= 60;
                sum_Hour += 1;
            }

        }

        //sum_Hour /= 2;
        //sum_Min /= 2;
        //sum_Sec /= 2;

        totalTimeText = findViewById(R.id.totalTimeText);
        totalTimeText.setText("Total Time : " + sum_Hour + "H " + sum_Min + "M " + sum_Sec + "S");

        recyclerView.setAdapter(adapter);


        // get planner db
        diaryEdit = findViewById(R.id.diaryEdit);
        database2 = openOrCreateDatabase("planner.db", MODE_PRIVATE, null);
        Cursor cursor2 = database2.rawQuery("select _id, diary from " + diary_date, null);
        int recordCount2 = cursor2.getCount();

        for(int i=0; i<recordCount2; i++){
            cursor2.moveToNext();
            int new_id2 = cursor2.getInt(0);
            String new_diary2 = cursor2.getString(1);

            diaryEdit.setText(new_diary2);
        }


        // recyclerView click event (for delete)
        adapter.setOnItemClickListener(new OnStudyContentClickListener() {
            @Override
            public void onItemClick(StudyContentAdapter.ViewHolder holder, View view, int position) {
                StudyContent item = adapter.getItem(position);
                final int get_id = item.get_id();

                new AlertDialog.Builder(PlannerActivity.this)
                        .setMessage("삭제하시겠습니까?")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                database.delete(date, "_id" + "=" + get_id, null);

                                Toast.makeText(getApplicationContext(), "삭제되었습니다", Toast.LENGTH_LONG).show();
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();
            }
        });


        // newContent button clicked (make AlertDialog Timer or Enter)
        final Button newContent = findViewById(R.id.newContent);
        newContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] items = new String[] {"타이머", "직접입력"};

                AlertDialog.Builder dialog = new AlertDialog.Builder(PlannerActivity.this);
                dialog.setTitle("입력 방법을 선택하세요")
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(items[which].equals("타이머")){
                                    // go to TimerActivity
                                    Intent intent = new Intent(getApplicationContext(), TimerActivity.class);
                                    intent.putExtra("date", date);

                                    startActivityForResult(intent, 101);
                                }
                                if(items[which].equals("직접입력")){
                                    // go to EnterActivity
                                    Intent intent = new Intent(getApplicationContext(), EnterActivity.class);
                                    intent.putExtra("date", date);

                                    startActivityForResult(intent, 101);
                                }
                            }
                        }).create().show();
            }
        });


        // storeButton clicked (diary)
        saveButton = findViewById(R.id.saveButton);
        diaryEdit = findViewById(R.id.diaryEdit);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t_diary = diaryEdit.getText().toString();

                database.delete(diary_date, "_id" + "=" + 1 , null);

                database.execSQL("insert into " + diary_date
                        +"(diary) "
                        + " values "
                        + "('" + t_diary + "')");

                Toast.makeText(getApplicationContext(), "저장되었습니다.", Toast.LENGTH_LONG).show();
            }
        });


    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planner);

        int sum_Hour = 0;
        int sum_Min = 0;
        int sum_Sec = 0;



        // get intent from Calendar_Fragment && set date in dayTextView
        Intent receivedIntent = getIntent();
        int year = receivedIntent.getIntExtra("year", 0);
        int month = receivedIntent.getIntExtra("month", 0);
        int day = receivedIntent.getIntExtra("day", 0);

        dayTextView = findViewById(R.id.dayTextView);
        dayTextView.setText(year + "년 " + month + "월 " + day + "일");
        //


        // make Database - Table name
        String text_year = Integer.toString(year);
        String  text_month = Integer.toString(month);
        String text_day = Integer.toString(day);
        final String date = "p" + text_year + text_month + text_day;
        final String diary_date = "d" + text_year + text_month + text_day;
        //


        // create or get Database for planner as soon as PlannerActivity start
        createDatabase("planner.db");
        createTable(date);


        // create db for diary
        openOrCreateDatabase("planner.db", MODE_PRIVATE, null);
        database.execSQL("create table if not exists " + diary_date + "("
                + "_id integer PRIMARY KEY autoincrement, "
                + " diary text)");


        // make RecyclerView
        final RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new StudyContentAdapter();
        recyclerView.setAdapter(adapter);


        // view records created by Timer and Enter Activity && create totalTimeText
        database = openOrCreateDatabase("planner.db", MODE_PRIVATE, null);
        Cursor cursor = database.rawQuery("select _id, subject, content, hour, min, sec, dDay, diary from " + date, null);
        int recordCount = cursor.getCount();


        for(int i=0; i<recordCount; i++){
            cursor.moveToNext();
            int new_id = cursor.getInt(0);
            String new_subject = cursor.getString(1);
            String new_content = cursor.getString(2);
            int new_hour = cursor.getInt(3);
            int new_min = cursor.getInt(4);
            int new_sec = cursor.getInt(5);
            String new_dDay = cursor.getString(6);
            String new_diary = cursor.getString(7);

            adapter.addItem(new StudyContent(new_id, new_subject, new_content, new_hour, new_min, new_sec));


            // for totalTime
            sum_Hour += new_hour;
            sum_Min += new_min;
            sum_Sec += new_sec;

            if(sum_Sec >= 60){
                sum_Sec -= 60;
                sum_Min += 1;
            }

            if(sum_Min >= 60){
                sum_Min -= 60;
                sum_Hour += 1;
            }


        }

        totalTimeText = findViewById(R.id.totalTimeText);
        totalTimeText.setText("Total Time : " + sum_Hour + "H " + sum_Min + "M " + sum_Sec + "S");

        recyclerView.setAdapter(adapter);


        // get planner db
        diaryEdit = findViewById(R.id.diaryEdit);
        database2 = openOrCreateDatabase("planner.db", MODE_PRIVATE, null);
        Cursor cursor2 = database2.rawQuery("select _id, diary from " + diary_date, null);
        int recordCount2 = cursor2.getCount();

        for(int i=0; i<recordCount2; i++){
            cursor2.moveToNext();
            int new_id2 = cursor2.getInt(0);
            String new_diary2 = cursor2.getString(1);

            diaryEdit.setText(new_diary2);
        }


        // recyclerView click event (for delete)
        adapter.setOnItemClickListener(new OnStudyContentClickListener() {
            @Override
            public void onItemClick(StudyContentAdapter.ViewHolder holder, View view, int position) {
                StudyContent item = adapter.getItem(position);
                final int get_id = item.get_id();

                new AlertDialog.Builder(PlannerActivity.this)
                        .setMessage("삭제하시겠습니까?")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                database.delete(date, "_id" + "=" + get_id, null);

                                Toast.makeText(getApplicationContext(), "삭제되었습니다", Toast.LENGTH_LONG).show();

                                recyclerView.setAdapter(adapter);
                                adapter.notifyDataSetChanged();

                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();

            }
        });


        // newContent button clicked (make AlertDialog Timer or Enter)
        final Button newContent = findViewById(R.id.newContent);
        newContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] items = new String[] {"타이머", "직접입력"};

                AlertDialog.Builder dialog = new AlertDialog.Builder(PlannerActivity.this);
                dialog.setTitle("공부 방법을 선택하세요")
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(items[which].equals("타이머")){
                                    // go to TimerActivity
                                    Intent intent = new Intent(getApplicationContext(), TimerActivity.class);
                                    intent.putExtra("date", date);

                                    startActivityForResult(intent, 101);
                                }
                                if(items[which].equals("직접입력")){
                                    // go to EnterActivity
                                    Intent intent = new Intent(getApplicationContext(), EnterActivity.class);
                                    intent.putExtra("date", date);

                                    startActivityForResult(intent, 101);
                                }
                            }
                        }).create().show();
            }
        });


        // storeButton clicked (diary)
        saveButton = findViewById(R.id.saveButton);
        diaryEdit = findViewById(R.id.diaryEdit);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t_diary = diaryEdit.getText().toString();

                database.delete(diary_date, "_id" + "=" + 1 , null);

                database.execSQL("insert into " + diary_date
                        +"(diary) "
                        + " values "
                        + "('" + t_diary + "')");

                Toast.makeText(getApplicationContext(), "저장되었습니다.", Toast.LENGTH_LONG).show();
            }
        });


    }



    // create Database method
    private void createDatabase(String name){
        database = openOrCreateDatabase(name, MODE_PRIVATE, null);
    }

    // 테이블 생성 함수
    private void createTable(String name){
        if(database == null){
            return;
        }

        database.execSQL("create table if not exists " + name + "("
                + "_id integer PRIMARY KEY autoincrement, "
                + " subject text, "
                + " content text, "
                + " hour int, "
                + " min int, "
                + " sec int, "
                + " dDay text, "
                + " diary text)");
    }

    // create record
    private void insertRecord(String tableName, String subject, String content, int hour, int min, int sec, String dDay, String diary){

        SQLiteDatabase database;

        database = openOrCreateDatabase("planner.db", MODE_PRIVATE, null);
        database.execSQL("insert into " + tableName
                +"(subject, content, hour, min, sec, dDay, diary) "
                + " values "
                + "('" + subject + "', '" + content + "', '" + hour + "', '" + min + "', '" + sec + "', '" + dDay + "', '" + diary + "')");

    }


}
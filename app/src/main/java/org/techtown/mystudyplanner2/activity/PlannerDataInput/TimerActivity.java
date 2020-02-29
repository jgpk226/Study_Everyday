package org.techtown.mystudyplanner2.activity.PlannerDataInput;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.techtown.mystudyplanner2.R;

public class TimerActivity extends AppCompatActivity {

    LinearLayout all;
    TextView hourText;
    TextView f;
    TextView minText;
    TextView s;
    TextView secText;
    TextView subject;
    EditText studyContent;

    Thread thread = null;
    Boolean isRunning = true;
    boolean first = true;

    int r_hour = 0;
    int r_min = 0;
    int r_sec = 0;

    SQLiteDatabase database;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        all = findViewById(R.id.all);

        hourText = findViewById(R.id.hour);
        f = findViewById(R.id.first);
        minText = findViewById(R.id.min);
        s = findViewById(R.id.second);
        secText = findViewById(R.id.sec);

        subject = findViewById(R.id.subject);
        studyContent = findViewById(R.id.studyContent);
        final Button btn_startpause = findViewById(R.id.btn_startpause);
        final Button btn_complete = findViewById(R.id.btn_complete);


        // subjectText clicked (choose subject)
        subject = findViewById(R.id.subject);
        subject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int[] selectedIndex = {0};

                database = openOrCreateDatabase("subject.db", MODE_PRIVATE, null);
                database.execSQL("create table if not exists subject("
                        + " _id integer PRIMARY KEY autoincrement, "
                        + " subject text)");

                Cursor cursor = database.rawQuery("select _id, subject from subject", null);
                int recordCount = cursor.getCount();

                final String[] items = new String[recordCount];


                if(recordCount != 0) {

                    for (int i = 0; i < recordCount; i++) {
                        cursor.moveToNext();

                        int new_id = cursor.getInt(0);
                        String new_subject = cursor.getString(1);

                        items[i] = new_subject;
                    }

                    AlertDialog.Builder dialog = new AlertDialog.Builder(TimerActivity.this);
                    dialog.setTitle("과목을 선택하세요.")
                            .setSingleChoiceItems(items,
                                    0,
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            selectedIndex[0] = which;
                                        }
                                    })
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    subject.setText(items[selectedIndex[0]]);
                                }
                            }).create().show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "설정탭에서 과목을 한 개 이상 설정해주세요.", Toast.LENGTH_LONG).show();
                }


            }
        });


        // start/pause Button clicked
        btn_startpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (first) {
                    btn_startpause.setText("중지");
                    first = false;

                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);


                    all.setBackgroundColor(getResources().getColor(R.color.black));

                    subject.setTextColor(getResources().getColor(R.color.snow));
                    studyContent.setTextColor(getResources().getColor(R.color.snow));
                    subject.setClickable(false);
                    studyContent.setFocusable(false);
                    studyContent.setClickable(false);

                    hourText.setTextColor(getResources().getColor(R.color.cyan));
                    f.setTextColor(getResources().getColor(R.color.cyan));
                    minText.setTextColor(getResources().getColor(R.color.cyan));
                    s.setTextColor(getResources().getColor(R.color.cyan));
                    secText.setTextColor(getResources().getColor(R.color.cyan));

                    btn_complete.setVisibility(View.INVISIBLE);


                    thread = new Thread(new timeThread());
                    thread.start();
                }

                else if(isRunning){
                    btn_startpause.setText("시작");

                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);


                    all.setBackgroundColor(getResources().getColor(R.color.white));

                    subject.setTextColor(getResources().getColor(R.color.black));
                    studyContent.setTextColor(getResources().getColor(R.color.black));
                    subject.setClickable(true);
                    studyContent.isFocusable();
                    studyContent.setFocusableInTouchMode(true);
                    studyContent.isClickable();
                    studyContent.setClickable(true);


                    hourText.setTextColor(getResources().getColor(R.color.black));
                    f.setTextColor(getResources().getColor(R.color.black));
                    minText.setTextColor(getResources().getColor(R.color.black));
                    s.setTextColor(getResources().getColor(R.color.black));
                    secText.setTextColor(getResources().getColor(R.color.black));

                    btn_complete.setVisibility(View.VISIBLE);

                    isRunning = !isRunning;

                }
                else if (!isRunning){
                    btn_startpause.setText("일시정지");

                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                    //requestWindowFeature(Window.FEATURE_NO_TITLE);

                    all.setBackgroundColor(getResources().getColor(R.color.black));

                    subject.setTextColor(getResources().getColor(R.color.white));
                    studyContent.setTextColor(getResources().getColor(R.color.white));
                    subject.setClickable(false);
                    studyContent.setFocusable(false);
                    studyContent.setClickable(false);

                    hourText.setTextColor(getResources().getColor(R.color.cyan));
                    f.setTextColor(getResources().getColor(R.color.cyan));
                    minText.setTextColor(getResources().getColor(R.color.cyan));
                    s.setTextColor(getResources().getColor(R.color.cyan));
                    secText.setTextColor(getResources().getColor(R.color.cyan));

                    btn_complete.setVisibility(View.INVISIBLE);

                    isRunning = !isRunning;
                }
            }
        });




        // complete Button clicked
        btn_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text_subject = subject.getText().toString();
                String text_content = studyContent.getText().toString();
                int new_hour = r_hour;
                int new_min = r_min;
                int new_sec = r_sec;
                String text_dDay = null;
                String text_diary = null;


                if(new_hour == 0 && new_min == 0 && new_sec == 0){
                    Toast.makeText(getApplicationContext(), "입력할 공부 시간이 없습니다.", Toast.LENGTH_LONG).show();
                }

                if(text_content.equals("")){
                    Toast.makeText(getApplicationContext(), "공부 내용을 입력하세요.", Toast.LENGTH_LONG).show();
                }

                if(text_subject.equals("")){
                    Toast.makeText(getApplicationContext(), "과목을 선택해주세요.", Toast.LENGTH_LONG).show();
                }


                if((r_hour != 0 || r_min != 0 || r_sec != 0) && !studyContent.equals("") && !subject.equals("")){

                    // 데이터베이스의 테이블의 레코드에 저장
                    Intent receivedIntent = getIntent();
                    String date = receivedIntent.getStringExtra("date");

                    insertRecord(date, text_subject, text_content, new_hour, new_min, new_sec, text_dDay, text_diary);

                    finish();
                }

            }
        });

    }


    // help timer goes
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // 시간 format :
            int sec = (msg.arg1 / 100) % 60;
            int min = (msg.arg1 / 100) / 60;
            int hour = (msg.arg1 / 100) / 3600;

            while (min >= 60){
                min -= 60;
            }

            String result_hour = String.format("%02d", hour);
            String result_min = String.format("%02d", min);
            String result_sec = String.format("%02d", sec);

            hourText.setText(result_hour);
            minText.setText(result_min);
            secText.setText(result_sec);

            r_hour = hour;
            r_min = min;
            r_sec = sec;
        }
    };



    // help timer goes & stop
    public class timeThread implements Runnable {
        @Override
        public void run() {
            int i = 0;

            while (true) {
                while (isRunning) { //일시정지를 누르면 멈추도록
                    Message msg = new Message();
                    msg.arg1 = i++;
                    handler.sendMessage(msg);

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        return; // 인터럽트 받을 경우 return됨
                    }
                }
            }
        }
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

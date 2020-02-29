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

import org.techtown.mystudyplanner2.R;

// datepicker 사용하기
public class EnterActivity extends AppCompatActivity {

    TextView subject;
    EditText content;
    EditText hour;
    EditText min;
    EditText sec;

    SQLiteDatabase database;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);


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

                    AlertDialog.Builder dialog = new AlertDialog.Builder(EnterActivity.this);
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

        content = findViewById(R.id.contentEdit);
        hour = findViewById(R.id.hourEdit);
        min = findViewById(R.id.minEdit);
        sec = findViewById(R.id.secEdit);

        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text_hour = hour.getText().toString();
                String text_min = min.getText().toString();
                String text_sec = sec.getText().toString();

                String text_subject = subject.getText().toString();
                String text_content = content.getText().toString();
                int int_hour = 0;
                int int_min = 0;
                int int_sec = 0;
                String text_dDay = null;
                String text_diary = null;

                if(!text_hour.equals("")){
                    int_hour = Integer.parseInt(text_hour);
                }
                if(!text_min.equals("")){
                    int_min = Integer.parseInt(text_min);
                }
                if (!text_sec.equals("")){
                    int_sec = Integer.parseInt(text_sec);
                }


                if(int_hour >= 24 || int_min >= 60 || int_sec >= 60){
                    Toast.makeText(getApplicationContext(), "시간 형식이 잘못 되었습니다.", Toast.LENGTH_LONG).show();
                }

                if(text_hour.equals("") && text_min.equals("") && text_sec.equals("")){
                    Toast.makeText(getApplicationContext(), "공부 시간을 입력해주세요.", Toast.LENGTH_LONG).show();
                }

                if(text_content.equals("")){
                    Toast.makeText(getApplicationContext(), "공부 내용을 입력하세요.", Toast.LENGTH_LONG).show();
                }

                if(text_subject.equals("")){
                    Toast.makeText(getApplicationContext(), "과목을 선택해주세요.", Toast.LENGTH_LONG).show();
                }


                if((int_hour != 0 || int_min != 0 || int_sec != 0) && !text_content.equals("") && !text_subject.equals("") && (int_hour <24 && int_min < 60 && int_sec <60)){

                    // 데이터베이스의 테이블의 레코드에 저장

                    Intent receivedIntent = getIntent();
                    String date = receivedIntent.getStringExtra("date");

                    insertRecord(date, text_subject, text_content, int_hour, int_min, int_sec, text_dDay, text_diary);

                    finish();
                }


            }
        });


    }

    private void insertRecord(String tableName, String subject, String content, int hour, int min, int sec, String dDay, String diary){

        SQLiteDatabase database;

        database = openOrCreateDatabase("planner.db", MODE_PRIVATE, null);
        database.execSQL("insert into " + tableName
                +"(subject, content, hour, min, sec, dDay, diary) "
                + " values "
                + "('" + subject + "', '" + content + "', '" + hour + "', '" + min + "', '" + sec + "', '" + dDay + "', '" + diary + "')");

    }


}

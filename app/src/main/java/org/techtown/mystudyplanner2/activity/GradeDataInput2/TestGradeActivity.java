package org.techtown.mystudyplanner2.activity.GradeDataInput2;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.techtown.mystudyplanner2.R;
import org.techtown.mystudyplanner2.etc.TestGrade.OnTGradeItemClickListener;
import org.techtown.mystudyplanner2.etc.TestGrade.TGrade;
import org.techtown.mystudyplanner2.etc.TestGrade.TGradeAdapter;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class TestGradeActivity extends AppCompatActivity {

    EditText testName;
    TextView dateText;
    EditText subject;
    EditText score;
    EditText perfect;

    SQLiteDatabase database;

    GregorianCalendar calendar = new GregorianCalendar();
    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH);
    int day= calendar.get(Calendar.DAY_OF_MONTH);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testgrade);

        testName = findViewById(R.id.testName);
        dateText = findViewById(R.id.dateText);
        subject = findViewById(R.id.subject);
        score = findViewById(R.id.score);
        perfect = findViewById(R.id.perfect);


        // db와 테이블 만들기
        database = openOrCreateDatabase("test.db", MODE_PRIVATE, null);
        database.execSQL("create table if not exists " + "test("
                + " _id integer PRIMARY KEY autoincrement, "
                + " testName text, "
                + " date text, "
                + " subject text, "
                + " score int, "
                + " perfect int)");


        // 리사이클러뷰 만들기
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        final TGradeAdapter adapter = new TGradeAdapter();

        // db에서 가져와서 리사이클러뷰에 만들기
        database = openOrCreateDatabase("test.db", MODE_PRIVATE, null);
        Cursor cursor = database.rawQuery("select _id, testName, date, subject, score, perfect from test", null);
        int recordCount = cursor.getCount();

        for(int i=0; i<recordCount; i++) {
            cursor.moveToNext();
            int new_id = cursor.getInt(0);
            String new_testName = cursor.getString(1);
            String new_date = cursor.getString(2);
            String new_subject = cursor.getString(3);
            int new_score = cursor.getInt(4);
            int new_perfect = cursor.getInt(5);

            adapter.addItem(new TGrade(new_id, new_testName, new_date, new_subject, new_score, new_perfect));
        }

        recyclerView.setAdapter(adapter);


        // 날짜에 데이트피커 만들기
        dateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(TestGradeActivity.this, dateSetListener, year, month, day).show();
            }
        });


        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 저장 버튼 클릭 시
                String text_testName;
                String text_date;
                String text_subject;
                int text_score;
                int text_perfect;

                String t_perfect = perfect.getText().toString();
                if(t_perfect.equals("")){
                    Toast.makeText(getApplicationContext(), "만점을 입력해주세요.", Toast.LENGTH_LONG).show();
                    text_perfect = 0;
                }
                else {
                    text_perfect = Integer.parseInt(t_perfect);
                }

                String t_score = score.getText().toString();
                if(t_score.equals("")){
                    Toast.makeText(getApplicationContext(), "점수를 입력해주세요.", Toast.LENGTH_LONG).show();
                    text_score = 0;
                }
                else {
                    text_score = Integer.parseInt(t_score);
                }

                String t_subject = subject.getText().toString();
                if(t_subject.equals("")){
                    Toast.makeText(getApplicationContext(), "과목명을 입력해주세요", Toast.LENGTH_LONG).show();
                    text_subject = "";
                }
                else {
                    text_subject = t_subject;
                }

                String t_date = dateText.getText().toString();
                if(t_date.equals("00.00.00.")){
                    Toast.makeText(getApplicationContext(), "시험날짜를 입력해주세요.", Toast.LENGTH_LONG).show();
                    text_date = "";
                }
                else {
                    text_date = t_date;
                }

                String t_testName = testName.getText().toString();
                if(t_testName.equals("")){
                    Toast.makeText(getApplicationContext(), "시험명을 입력해주세요.",Toast.LENGTH_LONG).show();
                    text_testName = "";
                }
                else {
                    text_testName = t_testName;
                }


                if(text_score > text_perfect){
                    Toast.makeText(getApplicationContext(), "성적이 만점보다 높을 수 없습니다.", Toast.LENGTH_LONG).show();
                }





                if(!text_testName.equals("") && !text_date.equals("00.00.00.") && !text_subject.equals("") && text_score != 0 && text_perfect != 0 && text_score <= text_perfect){

                    // db에 저장하기
                    // db에 저장
                    database = openOrCreateDatabase("test.db", MODE_PRIVATE, null);
                    database.execSQL("insert into test"
                            + "(testName, date, subject, score, perfect) "
                            + " values "
                            + "('" + text_testName + "', '" + text_date + "', '" + text_subject + "', '" + text_score + "', '" + text_perfect +  "')");


                    // 새로고침
                    database = openOrCreateDatabase("test.db", MODE_PRIVATE, null);
                    Cursor cursor = database.rawQuery("select _id, testName, date, subject, score, perfect from test", null);
                    int recordCount = cursor.getCount();

                    cursor.moveToLast();

                    int new_id = cursor.getInt(0);
                    String new_testName = cursor.getString(1);
                    String new_date = cursor.getString(2);
                    String new_subject = cursor.getString(3);
                    int new_score = cursor.getInt(4);
                    int new_perfect = cursor.getInt(5);

                    adapter.addItem(new TGrade(new_id, new_testName, new_date, new_subject, new_score, new_perfect));


                    testName.setText(null);
                    dateText.setText("00.00.00.");
                    subject.setText(null);
                    score.setText(null);
                    perfect.setText(null);

                    adapter.notifyDataSetChanged();

                }

            }
        });



        // 클릭 시 삭제 만들기
        adapter.setOnItemClickListener(new OnTGradeItemClickListener() {
            @Override
            public void onItemClick(TGradeAdapter.ViewHolder holder, View view, int position) {
                TGrade item = adapter.getItem(position);
                final int get_id = item.get_id();

                // delete Database if clicked
                new AlertDialog.Builder(TestGradeActivity.this)
                        .setMessage("삭제하시겠습니까?")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                database.delete("test", "_id" + "=" + get_id, null);

                                Toast.makeText(getApplicationContext(), "삭제되었습니다", Toast.LENGTH_LONG).show();
                                // 삭제 시 바로 반영 불가능?

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


        Button completeButton = findViewById(R.id.completeButton);
        completeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }







    // datePicker (set testName)
    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateText.setText(year%100 + "." + (monthOfYear + 1) + "." + dayOfMonth + ".");
        }
    };
}

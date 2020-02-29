package org.techtown.mystudyplanner2.activity.GradeDataInput;

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

import org.techtown.mystudyplanner2.etc.NationGrade.NGrade;
import org.techtown.mystudyplanner2.etc.NationGrade.NGradeAdapter;
import org.techtown.mystudyplanner2.etc.NationGrade.OnNGradeItemClickListener;

import java.util.Calendar;
import java.util.GregorianCalendar;


public class NationGradeActivity extends AppCompatActivity {

    TextView testName;
    TextView subjectClassification;
    EditText subject;
    EditText originalScore;
    EditText standardScore;
    EditText grade;
    EditText percentage;
    Button saveButton;

    SQLiteDatabase database;

    GregorianCalendar calendar = new GregorianCalendar();
    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH);
    int day= calendar.get(Calendar.DAY_OF_MONTH);



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nationgrade);


        testName = findViewById(R.id.testName);
        subjectClassification = findViewById(R.id.subjectClassification);
        subject = findViewById(R.id.subject);
        originalScore = findViewById(R.id.originalScore);
        standardScore = findViewById(R.id.standardScore);
        grade = findViewById(R.id.grade);
        percentage = findViewById(R.id.percentage);
        saveButton = findViewById(R.id.saveButton);


        // db와 테이블 만들기
        database = openOrCreateDatabase("grade.db", MODE_PRIVATE, null);
        database.execSQL("create table if not exists " + "nation("
                + " _id integer PRIMARY KEY autoincrement, "
                + " testName text, "
                + " subjectClassification text, "
                + " subject text, "
                + " originalScore int, "
                + " standardScore int, "
                + " grade int, "
                + " percentage float)");



        // 리사이클러뷰 만들기
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        final NGradeAdapter adapter = new NGradeAdapter();

        // db에서 가져와서 리사이클러뷰에 만들기
        database = openOrCreateDatabase("grade.db", MODE_PRIVATE, null);
        Cursor cursor = database.rawQuery("select _id, testName, subjectClassification, subject, originalScore, standardScore, grade, percentage from nation", null);
        int recordCount = cursor.getCount();

        for(int i=0; i<recordCount; i++) {
            cursor.moveToNext();
            int new_id = cursor.getInt(0);
            String new_testName = cursor.getString(1);
            String new_subjectClassification = cursor.getString(2);
            String new_subject = cursor.getString(3);
            int new_originalScore = cursor.getInt(4);
            int new_standardScroe = cursor.getInt(5);
            int new_grade = cursor.getInt(6);
            float new_percentage = cursor.getFloat(7);

            adapter.addItem(new NGrade(new_id, new_testName, new_subjectClassification, new_subject, new_originalScore, new_standardScroe, new_grade, new_percentage));

        }

        recyclerView.setAdapter(adapter);



        // 시험명에 데이트피커 만들기
        testName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(NationGradeActivity.this, dateSetListener, year, month, day).show();
            }
        });


        // 과목클래시피케이션 만들기
        subjectClassification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] items = new String[] {"국어", "수학", "영어", "한국사", "사회", "과학", "제2외국어"};
                final int[] selectIndex = {0};

                AlertDialog.Builder dialog = new AlertDialog.Builder(NationGradeActivity.this);
                dialog.setTitle("과목분류를 선택하세요.")
                        .setSingleChoiceItems(items,
                                0,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        selectIndex[0] = which;
                                    }
                                })
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                subjectClassification.setText(items[selectIndex[0]]);
                                if(items[selectIndex[0]].equals("영어") || items[selectIndex[0]].equals("한국사")){
                                    standardScore.setText("000");
                                    percentage.setText("00.0");
                                }
                            }
                        }).create().show();
            }
        });



        // 저장 버튼 클릭 시
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int text_originalScore;
                int text_standardScore;
                int text_grade;
                float text_percentage;

                String t_percentage = percentage.getText().toString();
                if(t_percentage.equals("")){
                    Toast.makeText(getApplicationContext(), "백분위를 입력해주세요.", Toast.LENGTH_LONG).show();
                    text_percentage = 9999;
                }
                else {
                    text_percentage = Float.parseFloat(percentage.getText().toString());
                }

                String t_grade = grade.getText().toString();
                if(t_grade.equals("")){
                    Toast.makeText(getApplicationContext(), "등급을 입력해주세요.", Toast.LENGTH_LONG).show();
                    text_grade = 0;
                }
                else {
                    text_grade = Integer.parseInt(grade.getText().toString());
                }

                String t_standardScroe = standardScore.getText().toString();
                if(t_standardScroe.equals("")){
                    Toast.makeText(getApplicationContext(), "표준점수를 입력해주세요.", Toast.LENGTH_LONG).show();
                    text_standardScore = 9999;
                }
                else {
                    text_standardScore = Integer.parseInt(standardScore.getText().toString());
                }

                String t_origianlScore = originalScore.getText().toString();
                if(t_origianlScore.equals("")){
                    Toast.makeText(getApplicationContext(), "원점수를 입력해주세요.", Toast.LENGTH_LONG).show();
                    text_originalScore = 0;
                }
                else {
                    text_originalScore = Integer.parseInt(originalScore.getText().toString());
                }


                String text_subject = subject.getText().toString();
                if(text_subject.equals("")){
                    Toast.makeText(getApplicationContext(), "과목명을 입력해주세요.", Toast.LENGTH_LONG).show();
                }


                String text_subjectClassification = subjectClassification.getText().toString();
                if(text_subjectClassification.equals("")){
                    Toast.makeText(getApplicationContext(), "과목분류를 선택해주세요.", Toast.LENGTH_LONG).show();
                }

                String text_testName = testName.getText().toString();
                if(text_testName.equals("")){
                    Toast.makeText(getApplicationContext(), "시험날짜를 선택하세요.", Toast.LENGTH_LONG).show();
                }



                if(!text_testName.equals("") && !text_subjectClassification.equals("") && !text_subject.equals("") && text_originalScore != 0 && text_standardScore != 9999 && text_grade != 0 && text_percentage != 9999){
                    // db에 저장
                    database.execSQL("insert into nation"
                            + "(testName, subjectClassification, subject, originalScore, standardScore, grade, percentage) "
                            + " values "
                            + "('" + text_testName + "', '" + text_subjectClassification + "', '" + text_subject + "', '" + text_originalScore + "', '" + text_standardScore + "', '" + text_grade + "', '" + text_percentage + "')");

                    Toast.makeText(getApplicationContext(), "입력되었습니다", Toast.LENGTH_SHORT).show();

                    // 새로고침
                    database = openOrCreateDatabase("grade.db", MODE_PRIVATE, null);
                    Cursor cursor = database.rawQuery("select _id, testName, subjectClassification, subject, originalScore, standardScore, grade, percentage from nation", null);
                    int recordCount = cursor.getCount();

                    cursor.moveToLast();
                    int new_id = cursor.getInt(0);
                    String new_testName = cursor.getString(1);
                    String new_subjectClassification = cursor.getString(2);
                    String new_subject = cursor.getString(3);
                    int new_originalScore = cursor.getInt(4);
                    int new_standardScore = cursor.getInt(5);
                    int new_grade = cursor.getInt(6);
                    float new_percentage = cursor.getFloat(7);

                    adapter.addItem(new NGrade(new_id, new_testName, new_subjectClassification, new_subject, new_originalScore, new_standardScore, new_grade, new_percentage));


                    testName.setText(null);
                    subjectClassification.setText("과목");
                    subject.setText(null);
                    originalScore.setText(null);
                    standardScore.setText(null);
                    grade.setText(null);
                    percentage.setText(null);

                    adapter.notifyDataSetChanged();
                }

            }
        });



        adapter.setOnItemClickListener(new OnNGradeItemClickListener() {
            @Override
            public void onItemClick(NGradeAdapter.ViewHolder holder, View view, int position) {
                NGrade item = adapter.getItem(position);
                final int get_id = item.get_id();

                // delete Database if clicked
                new AlertDialog.Builder(NationGradeActivity.this)
                        .setMessage("삭제하시겠습니까?")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                database.delete("nation", "_id" + "=" + get_id, null);

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
            testName.setText(year % 100 + "." + (monthOfYear + 1) + "." + dayOfMonth + ".");
        }
    };


}

package org.techtown.mystudyplanner2.activity.GradeDataInput;

import android.content.DialogInterface;
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
import org.techtown.mystudyplanner2.etc.SchoolGrade.Grade;
import org.techtown.mystudyplanner2.etc.SchoolGrade.GradeAdapter;
import org.techtown.mystudyplanner2.etc.SchoolGrade.OnGradeItemClickListener;

public class SchoolGradeActivity extends AppCompatActivity {

    TextView newSemester;
    TextView newSubjectClassification;
    EditText newSubject;
    EditText newLearnTime;
    EditText newScore;
    EditText newAccomplishment;
    EditText newGrade;
    EditText newAverage;
    EditText newDiviation;
    Button saveButton;

    SQLiteDatabase database;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schoolgrade);

        newSemester = findViewById(R.id.newSemester);
        newSubjectClassification = findViewById(R.id.newSubjectClassification);
        newSubject = findViewById(R.id.newSubject);
        newLearnTime = findViewById(R.id.newLearnTime);
        newScore = findViewById(R.id.newScore);
        newAccomplishment = findViewById(R.id.newAccomplishment);
        newGrade = findViewById(R.id.newGrade);
        newAverage = findViewById(R.id.newAverage);
        newDiviation = findViewById(R.id.newDiviation);
        saveButton = findViewById(R.id.saveButton);


        // 리사이클러뷰 만들기
        final RecyclerView recyclerView  = findViewById(R.id.recyclerView);
        final GradeAdapter adapter = new GradeAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);


        // get record from Database
        database = openOrCreateDatabase("grade.db", MODE_PRIVATE, null);
        Cursor cursor = database.rawQuery("select _id, semester, subjectClassification, subject, learnTime, score, accomplishment, grade, average, diviation from school", null);
        int recordCount = cursor.getCount();

        for(int i=0; i<recordCount; i++) {
            cursor.moveToNext();
            int new_id = cursor.getInt(0);
            String new_semester = cursor.getString(1);
            String new_subjectClassification = cursor.getString(2);
            String new_subject = cursor.getString(3);
            int new_learnTime = cursor.getInt(4);
            float new_score = cursor.getFloat(5);
            String new_accomplishment = cursor.getString(6);
            int new_grade = cursor.getInt(7);
            float new_average = cursor.getFloat(8);
            float new_diviation = cursor.getFloat(9);

            adapter.addItem(new Grade(new_id, new_semester, new_subjectClassification, new_subject, new_learnTime, new_score, new_accomplishment, new_grade, new_average, new_diviation));
        }

        recyclerView.setAdapter(adapter);



        // set semester
        newSemester.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] items = new String[] {"1-1", "1-2", "2-1", "2-2", "3-1", "3-2"};
                final  int[] selectedIndex = {0};

                AlertDialog.Builder dialog = new AlertDialog.Builder(SchoolGradeActivity.this);
                dialog.setTitle("학기를 선택하세요.")
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
                                newSemester.setText(items[selectedIndex[0]]);
                            }
                        }).create().show();
            }
        });


        // set subjectClassification
        newSubjectClassification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] items = new String[] {"국어", "수학", "영어", "사회", "과학", "기타"};
                final int[] selectIndex = {0};

                AlertDialog.Builder dialog = new AlertDialog.Builder(SchoolGradeActivity.this);
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
                                newSubjectClassification.setText(items[selectIndex[0]]);
                            }
                        }).create().show();
            }
        });



        // saveButton clicked event, save to Database
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String semester;
                String subjectClassification;
                String subject;
                int learnTime;
                int score;
                String accomplishment;
                int grade;
                float average;
                float diviation;


                // 내용입력 확인

                String t_diviation = newDiviation.getText().toString();
                if(t_diviation.equals("")){
                    Toast.makeText(getApplicationContext(), "표준편차를 입력하세요.", Toast.LENGTH_LONG).show();
                    diviation = 0;
                }
                else {
                    diviation = Float.parseFloat(newDiviation.getText().toString());
                }


                String t_average = newAverage.getText().toString();
                if(t_average.equals("")){
                    Toast.makeText(getApplicationContext(), "평균을 입력하세요", Toast.LENGTH_LONG).show();
                    average = 0;
                }
                else {
                    average = Float.parseFloat(newAverage.getText().toString());
                }


                String t_grade = newGrade.getText().toString();
                if(t_grade .equals("")){
                    Toast.makeText(getApplicationContext(), "등급을 입력하세요.", Toast.LENGTH_LONG).show();
                    grade = 0;
                }
                else {
                    grade = Integer.parseInt(newGrade.getText().toString());
                }


                String t_accomplishment = newAccomplishment.getText().toString();
                if(t_accomplishment.equals("")){
                    Toast.makeText(getApplicationContext(), "성취도를 입력하세요.", Toast.LENGTH_LONG).show();
                    accomplishment = null;
                }
                else {
                    accomplishment = newAccomplishment.getText().toString();
                }


                String t_score = newScore.getText().toString();
                if(t_score.equals("")){
                    Toast.makeText(getApplicationContext(), "점수를 입력하세요.", Toast.LENGTH_LONG).show();
                    score = 0;
                }
                else {
                    score = Integer.parseInt(newScore.getText().toString());
                }


                String t_learnTime = newLearnTime.getText().toString();
                if(t_learnTime.equals("")){
                    Toast.makeText(getApplicationContext(), "단위수를 입력하세요.", Toast.LENGTH_LONG).show();
                    learnTime = 0;
                }
                else {
                    learnTime = Integer.parseInt(newLearnTime.getText().toString());
                }


                String t_subject = newSubject.getText().toString();
                if(t_subject.equals("")){
                    Toast.makeText(getApplicationContext(), "과목명을 입력하세요.", Toast.LENGTH_LONG).show();
                    subject = null;
                }
                else {
                    subject = newSubject.getText().toString();
                }


                subjectClassification = newSubjectClassification.getText().toString();
                if(subjectClassification.equals("과목분류")){
                    Toast.makeText(getApplicationContext(), "과목분류를 선택하세요.", Toast.LENGTH_LONG).show();
                }


                semester = newSemester.getText().toString();
                if(semester.equals("학기")){
                    Toast.makeText(getApplicationContext(), "학기를 선택하세요.", Toast.LENGTH_LONG).show();
                }



                // 내용이 다 입력되었으면 db에 레코드 저장하기
                if(!semester.equals("학기") && !subjectClassification.equals("과목분류") && subject != null && learnTime != 0 && score != 0 && accomplishment != null && grade != 0 && average != 0 && diviation != 0){
                    database.execSQL("insert into school"
                            + "(semester, subjectClassification, subject, learnTime, score, accomplishment, grade, average, diviation) "
                            + " values "
                            + "('" + semester + "', '" + subjectClassification + "', '" + subject + "', '" + learnTime + "', '" + score + "', '" + accomplishment + "', '" + grade + "', '" + average + "', '" + diviation + "')");

                    Toast.makeText(getApplicationContext(), "입력되었습니다", Toast.LENGTH_SHORT).show();


                    database = openOrCreateDatabase("grade.db", MODE_PRIVATE, null);
                    Cursor cursor = database.rawQuery("select _id, semester, subjectClassification, subject, learnTime, score, accomplishment, grade, average, diviation from school", null);
                    int recordCount = cursor.getCount();

                    cursor.moveToLast();
                    int new_id = cursor.getInt(0);
                    String new_semester = cursor.getString(1);
                    String new_subjectClassification = cursor.getString(2);
                    String new_subject = cursor.getString(3);
                    int new_learnTime = cursor.getInt(4);
                    float new_score = cursor.getFloat(5);
                    String new_accomplishment = cursor.getString(6);
                    int new_grade = cursor.getInt(7);
                    float new_average = cursor.getFloat(8);
                    float new_diviation = cursor.getFloat(9);

                    adapter.addItem(new Grade(new_id, new_semester, new_subjectClassification, new_subject, new_learnTime, new_score, new_accomplishment, new_grade, new_average, new_diviation));

                    newSemester.setText("학기");
                    newSubjectClassification.setText("과목분류");
                    newSubject.setText(null);
                    newLearnTime.setText(null);
                    newScore.setText(null);
                    newAccomplishment.setText(null);
                    newGrade.setText(null);
                    newAverage.setText(null);
                    newDiviation.setText(null);

                    adapter.notifyDataSetChanged();
                }

            }
        });


        // create Database & table as soon as Activity starts
        database = openOrCreateDatabase("grade.db", MODE_PRIVATE, null);
        database.execSQL("create table if not exists " + "school("
                + " _id integer PRIMARY KEY autoincrement, "
                + " semester text, "
                + " subjectClassification text, "
                + "subject text, "
                + " learnTime int, "
                + " score float, "
                + " accomplishment text, "
                + " grade int, "
                + " average float, "
                + " diviation float)");



        // for delete data
        adapter.setOnItemClickListener(new OnGradeItemClickListener() {
            @Override
            public void onItemClick(GradeAdapter.ViewHolder holder, View view, int position) {
                final Grade item = adapter.getItem(position);
                final int get_id  = item.get_id();

                // delete Database if clicked
                new AlertDialog.Builder(SchoolGradeActivity.this)
                        .setMessage("삭제하시겠습니까?")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                database.delete("school", "_id" + "=" + get_id, null);

                                // 삭제 시 새로고침은..?

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

        Button completeButton = findViewById(R.id.completeButton);
        completeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}

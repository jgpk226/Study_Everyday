package org.techtown.mystudyplanner2.activity.Subject;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.techtown.mystudyplanner2.R;
import org.techtown.mystudyplanner2.etc.Subject.OnSubjectClickListener;
import org.techtown.mystudyplanner2.etc.Subject.Subject;
import org.techtown.mystudyplanner2.etc.Subject.SubjectAdapter;

// 버튼 클릭 시 새로고침
// 어플 사용 처음에 국영수사과 기타 추가
public class SubjectActivity extends AppCompatActivity {

    EditText subjectEdit;

    SQLiteDatabase database;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);

        subjectEdit = findViewById(R.id.subjectEdit);



        database = openOrCreateDatabase("subject.db", MODE_PRIVATE, null);
        database.execSQL("create table if not exists subject("
        + " _id integer PRIMARY KEY autoincrement, "
        + " subject text)");


        // 리사이클러뷰 만들기
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        final SubjectAdapter adapter = new SubjectAdapter();
        recyclerView.setAdapter(adapter);


        Cursor cursor = database.rawQuery("select _id, subject from subject", null);
        int recordCount = cursor.getCount();

        if(recordCount != 0){

            for(int i=0; i<recordCount; i++){
                cursor.moveToNext();

                int new_id = cursor.getInt(0);
                String new_subject = cursor.getString(1);

                adapter.addItem(new Subject(new_id, new_subject));
            }

        }




        // 클릭 시 삭제 만들기
        adapter.setOnItemClickListener(new OnSubjectClickListener() {
            @Override
            public void onItemClick(SubjectAdapter.ViewHolder holder, View view, int position) {
                Subject item = adapter.getItem(position);
                final int get_id = item.get_id();

                // delete Database if clicked
                new AlertDialog.Builder(SubjectActivity.this)
                        .setMessage("삭제하시겠습니까?")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                database.delete("subject", "_id" + "=" + get_id, null);

                                Toast.makeText(getApplicationContext(), "삭제되었습니다", Toast.LENGTH_LONG).show();
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();


                adapter.notifyDataSetChanged();
            }
        });


        // 과목추가버튼 클릭
        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = subjectEdit.getText().toString();

                boolean ok = true;

                Cursor cursor1 = database.rawQuery("select _id, subject from subject", null);
                int recordCount1 = cursor1.getCount();

                if(recordCount1 != 0){
                    for(int i=0; i<recordCount1; i++){
                        cursor1.moveToNext();

                        int new_id = cursor1.getInt(0);
                        String new_subject = cursor1.getString(1);

                        if(new_subject.equals(str)){
                            ok = false;
                        }
                    }
                }


                if(ok && !str.equals("")){
                    database.execSQL("insert into subject"
                            + "(subject) "
                            + " values "
                            + "('" + str + "')");

                    // 새로고침
                    Cursor cursor2 = database.rawQuery("select _id, subject from subject", null);
                    cursor2.moveToLast();
                    int new_id = cursor2.getInt(0);
                    String new_subject = cursor2.getString(1);

                    adapter.addItem(new Subject(new_id, new_subject));

                    Toast.makeText(getApplicationContext(), "과목이 추가되었습니다.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "해당 과목이 이미 존재합니다.", Toast.LENGTH_LONG).show();
                }

                if(str.equals("")){
                    Toast.makeText(getApplicationContext(), "과목명을 입력해주세요.", Toast.LENGTH_LONG).show();
                }



                subjectEdit.setText(null);

                adapter.notifyDataSetChanged();
            }
        });




    }
}

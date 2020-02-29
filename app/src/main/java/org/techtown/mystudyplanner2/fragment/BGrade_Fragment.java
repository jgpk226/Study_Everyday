package org.techtown.mystudyplanner2.fragment;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.techtown.mystudyplanner2.R;
import org.techtown.mystudyplanner2.activity.GradeDataInput2.TestGradeActivity;
import org.techtown.mystudyplanner2.etc.TestGrade.TGrade;
import org.techtown.mystudyplanner2.etc.TestGrade.TGradeAdapter;


public class BGrade_Fragment extends Fragment {
    EditText searchEdit;

    SQLiteDatabase database;


    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.bgrade_fragment, container, false);

        searchEdit = rootView.findViewById(R.id.searchEdit);

        database = getActivity().openOrCreateDatabase("test.db", Context.MODE_PRIVATE, null);
        database.execSQL("create table if not exists " + "test("
                + " _id integer PRIMARY KEY autoincrement, "
                + " testName text, "
                + " date text, "
                + " subject text, "
                + " score int, "
                + " perfect int)");


        // 리사이클러뷰 만들기
        final RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        final TGradeAdapter adapter = new TGradeAdapter();
        recyclerView.setAdapter(adapter);


        // 성적입력 버튼 클릭
        Button inputButton = rootView.findViewById(R.id.inputButton);
        inputButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), TestGradeActivity.class);

                startActivityForResult(intent, 101);
            }
        });


        // 조회버튼 클릭
        final Button searchButton = rootView.findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str;
                str = searchEdit.getText().toString();

                database = getActivity().openOrCreateDatabase("test.db", Context.MODE_PRIVATE, null);
                Cursor cursor = database.rawQuery("select _id, testName, date, subject, score, perfect from test", null);
                int recordCount = cursor.getCount();

                cursor.moveToLast();

                for(int i=0; i<recordCount; i++) {
                    int new_id = cursor.getInt(0);
                    String new_testName = cursor.getString(1);
                    String new_date = cursor.getString(2);
                    String new_subject = cursor.getString(3);
                    int new_score = cursor.getInt(4);
                    int new_perfect = cursor.getInt(5);

                    if(new_testName.equals(str) || new_subject.equals(str)){
                        adapter.addItem(new TGrade(new_id, new_testName, new_date, new_subject, new_score, new_perfect));
                    }

                    cursor.moveToPrevious();
                }
                adapter.notifyDataSetChanged();
            }
        });


        return rootView;
    }
}

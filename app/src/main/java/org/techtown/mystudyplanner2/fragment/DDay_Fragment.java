package org.techtown.mystudyplanner2.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.techtown.mystudyplanner2.R;
import org.techtown.mystudyplanner2.activity.DDay.DDayActivity;
import org.techtown.mystudyplanner2.etc.DDay.DDay;
import org.techtown.mystudyplanner2.etc.DDay.DDayAdapter;
import org.techtown.mystudyplanner2.etc.DDay.OnDDayClickListener;

// 디데이 오래 누르면 순서 변경

public class DDay_Fragment extends Fragment {

    SQLiteDatabase database;

    @Override
    public void onResume() {
        super.onResume();


        database = getActivity().openOrCreateDatabase("dday.db",android.content.Context.MODE_PRIVATE ,null);
        database.execSQL("create table if not exists dday("
                + " _id integer PRIMARY KEY autoincrement, "
                + " name text, "
                + " year int, "
                + " month int, "
                + " day int)");


        RecyclerView recyclerView = getActivity().findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        final DDayAdapter adapter = new DDayAdapter();

        // 추가
        Cursor cursor = database.rawQuery("select _id, name, year, month, day from dday", null);
        int recordCount = cursor.getCount();

        for(int i=0; i<recordCount; i++){
            cursor.moveToNext();

            int new_id = cursor.getInt(0);
            String new_name = cursor.getString(1);
            int new_year = cursor.getInt(2);
            int new_month = cursor.getInt(3);
            int new_day = cursor.getInt(4);

            adapter.addItem(new DDay(new_id, new_name, new_year, new_month, new_day));
        }

        recyclerView.setAdapter(adapter);


        adapter.setOnItemClickListener(new OnDDayClickListener() {
            @Override
            public void onItemClick(DDayAdapter.ViewHolder holder, View view, int position) {
                final DDay item = adapter.getItem(position);
                final int get_id = item.get_id();

                // delete Database if clicked
                new AlertDialog.Builder(getContext())
                        .setMessage("삭제하시겠습니까?")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                database.delete("dday", "_id" + "=" + get_id, null);

                                // 삭제 시 새로고침은..?
                                // onResume(); 은 안되지만 아이디어 사용해보자

                                Toast.makeText(getContext(), "삭제되었습니다", Toast.LENGTH_LONG).show();
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



        Button newButton = getActivity().findViewById(R.id.newButton);
        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DDayActivity.class);

                startActivityForResult(intent, 101);
            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.dday_fragment, container, false);

        database = getActivity().openOrCreateDatabase("dday.db",android.content.Context.MODE_PRIVATE ,null);
        database.execSQL("create table if not exists dday("
                + " _id integer PRIMARY KEY autoincrement, "
                + " name text, "
                + " year int, "
                + " month int, "
                + " day int)");


        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        final DDayAdapter adapter = new DDayAdapter();

        // 추가
        Cursor cursor = database.rawQuery("select _id, name, year, month, day from dday", null);
        int recordCount = cursor.getCount();

        for(int i=0; i<recordCount; i++){
            cursor.moveToNext();

            int new_id = cursor.getInt(0);
            String new_name = cursor.getString(1);
            int new_year = cursor.getInt(2);
            int new_month = cursor.getInt(3);
            int new_day = cursor.getInt(4);

            adapter.addItem(new DDay(new_id, new_name, new_year, new_month, new_day));
        }

        recyclerView.setAdapter(adapter);


        adapter.setOnItemClickListener(new OnDDayClickListener() {
            @Override
            public void onItemClick(DDayAdapter.ViewHolder holder, View view, int position) {
                final DDay item = adapter.getItem(position);
                final int get_id = item.get_id();

                // delete Database if clicked
                new AlertDialog.Builder(getContext())
                        .setMessage("삭제하시겠습니까?")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                database.delete("dday", "_id" + "=" + get_id, null);

                                // 삭제 시 새로고침은..?
                                // onResume(); 은 안되지만 아이디어 사용해보자

                                Toast.makeText(getContext(), "삭제되었습니다", Toast.LENGTH_LONG).show();
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



        Button newButton = rootView.findViewById(R.id.newButton);
        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DDayActivity.class);

                startActivityForResult(intent, 101);
            }
        });

        return rootView;
    }
}

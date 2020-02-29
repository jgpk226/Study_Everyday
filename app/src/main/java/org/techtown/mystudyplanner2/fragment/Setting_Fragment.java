package org.techtown.mystudyplanner2.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import org.techtown.mystudyplanner2.R;
import org.techtown.mystudyplanner2.activity.Subject.SubjectActivity;


public class Setting_Fragment extends Fragment {
    EditText editText;
    EditText editText2;
    TextView typeText;
    TextView subjectText;

    SQLiteDatabase goalDatabase;
    SQLiteDatabase typeDatabase;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.setting_fragment, container, false);

        editText = rootView.findViewById(R.id.editText);
        editText2 = rootView.findViewById(R.id.editText2);
        typeText = rootView.findViewById(R.id.typeText);
        subjectText = rootView.findViewById(R.id.subjectText);


        // 시작 시 목표 데이터 조회
        goalDatabase = getActivity().openOrCreateDatabase("goal.db", Context.MODE_PRIVATE, null);
        goalDatabase.execSQL("create table if not exists goal("
                + "_id integer PRIMARY KEY autoincrement, "
                + " str1 text, "
                + " str2 text)");

        typeDatabase = getActivity().openOrCreateDatabase("type.db", Context.MODE_PRIVATE, null);
        typeDatabase.execSQL("create table if not exists type("
        + " _id integer PRIMARY KEY autoincrement, "
        + " str text)");


        Cursor cursor = goalDatabase.rawQuery("select _id, str1, str2 from goal", null);
        int recordCount = cursor.getCount();

        cursor.moveToLast();

        if(recordCount != 0){
            int new_id = cursor.getInt(0);
            String new_str1 = cursor.getString(1);
            String new_str2 = cursor.getString(2);

            editText.setText(new_str1);
            editText2.setText(new_str2);
        }
        else {
            editText.setText(null);
            editText2.setText(null);
        }


        // 목표 저장 버튼 클릭
        Button saveButton = rootView.findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str1, str2;

                str1 = editText.getText().toString();
                str2 = editText2.getText().toString();


                if(!str1.equals("") && !str2.equals("")){
                    goalDatabase.execSQL("DELETE FROM goal WHERE _id = '" + 1 +"';");
                    goalDatabase.execSQL("insert into goal"
                            + " (str1, str2) "
                            + " values "
                            + "('" + str1 + "', '" + str2 + "')");

                    Toast.makeText(getContext(), "저장되었습니다.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getContext(), "내용을 입력해주세요.", Toast.LENGTH_SHORT).show();
                }



            }
        });


        // 신분선택 클릭 시
        typeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] items = new String[] {"중고등학생", "대학생 및 취업준비생"};

                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                dialog.setTitle("자신의 신분을 선택하세요. \n(성적화면 구성이 변합니다.)")
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(items[which].equals("중고등학생")){
                                    String str = "A";

                                    typeDatabase.execSQL("DELETE FROM type WHERE _id = '" + 1 + "';");
                                    typeDatabase.execSQL("insert into type"
                                    + " (str) "
                                    + " values "
                                    + "('" + str + "')");

                                    Toast.makeText(getContext(), "앱 재시작시부터 적용됩니다.", Toast.LENGTH_LONG).show();
                                }
                                if(items[which].equals("대학생 및 취업준비생")){
                                    String str = "B";

                                    typeDatabase.execSQL("DELETE FROM type WHERE _id = '" + 1 + "';");
                                    typeDatabase.execSQL("insert into type"
                                            + " (str) "
                                            + " values "
                                            + "('" + str + "')");

                                    Toast.makeText(getContext(), "앱 재시작시부터 적용됩니다.", Toast.LENGTH_LONG).show();
                                }
                            }
                        }).create().show();
            }
        });


        subjectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 액티비티 띄우기
                Intent intent = new Intent(getContext(), SubjectActivity.class);

                startActivityForResult(intent, 101);
            }
        });







        return rootView;
    }
}

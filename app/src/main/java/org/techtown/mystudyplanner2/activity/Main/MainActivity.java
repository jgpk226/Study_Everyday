package org.techtown.mystudyplanner2.activity.Main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.techtown.mystudyplanner2.fragment.BGrade_Fragment;
import org.techtown.mystudyplanner2.fragment.Calander_Fragment;
import org.techtown.mystudyplanner2.fragment.DDay_Fragment;
import org.techtown.mystudyplanner2.fragment.Grade_Fragment;
import org.techtown.mystudyplanner2.R;
import org.techtown.mystudyplanner2.fragment.Setting_Fragment;


public class MainActivity extends AppCompatActivity {

    Fragment fragment1;
    Fragment fragment2;
    Fragment fragment3;
    Fragment fragment4;
    Fragment fragment5;

    SQLiteDatabase typeDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment1 = new Calander_Fragment();
        fragment2 = new Grade_Fragment();
        fragment3 = new DDay_Fragment();
        fragment4 = new Setting_Fragment();

        fragment5 = new BGrade_Fragment();

        typeDatabase = openOrCreateDatabase("type.db", MODE_PRIVATE, null);
        typeDatabase.execSQL("create table if not exists type("
                + " _id integer PRIMARY KEY autoincrement, "
                + " str text)");

        Cursor cursor = typeDatabase.rawQuery("select _id, str from type", null);
        int recordCount = cursor.getCount();

        if(recordCount != 0){
            cursor.moveToLast();

            int new_id = cursor.getInt(0);
            String new_str = cursor.getString(1);
            if(new_str.equals("A")){
                fragment2 = new Grade_Fragment();
            }
            else if(new_str.equals("B")){
                fragment2 = new BGrade_Fragment();
            }

        }


        // 프래그먼트 올리기
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();

        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.tab1:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();
                        return true;
                    case R.id.tab2:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment2).commit();
                        return true;
                    case R.id.tab3:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment3).commit();
                        return true;
                    case R.id.tab4:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment4).commit();
                        return true;
                }
                return false;
            }
        });
    }

}

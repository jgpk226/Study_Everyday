package org.techtown.mystudyplanner2.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import org.techtown.mystudyplanner2.R;
import org.techtown.mystudyplanner2.activity.GradeDataInput.NationGradeActivity;
import org.techtown.mystudyplanner2.activity.GradeDataInput.SchoolGradeActivity;


public class Grade_Fragment extends Fragment {
    TextView allGradeText;
    TextView allZText;
    TextView kGradeText;
    TextView kZText;
    TextView mGradeText;
    TextView mZText;

    TextView TestName1, TestName2, TestName3, TestName4;
    TextView SubjectName1, SubjectName2, SubjectName3, SubjectName4;
    TextView Score1, Score2, Score3, Score4;
    TextView sScore1, sScore2, sScore3, sScore4;
    TextView Percent1, Percent2, Percent3, Percent4;
    TextView Grade1, Grade2, Grade3, Grade4;

    SQLiteDatabase sdatabase;
    SQLiteDatabase ndatabase;


    float sumForKME1_1 = 0, sumForSo1_1 = 0, sumForSi1_1 = 0, sumForPlus1_1 = 0, sumForKMEZ1_1 = 0, sumForSoZ1_1 = 0, sumForSiZ1_1 = 0, sumForPlusZ1_1 = 0, countForKME1_1 = 0, countForSo1_1 = 0, countForSi1_1 = 0, countForPlus1_1 = 0;
    float sumForKME1_2 = 0, sumForSo1_2 = 0, sumForSi1_2 = 0, sumForPlus1_2 = 0, sumForKMEZ1_2 = 0, sumForSoZ1_2 = 0, sumForSiZ1_2 = 0, sumForPlusZ1_2 = 0, countForKME1_2 = 0, countForSo1_2 = 0, countForSi1_2 = 0, countForPlus1_2 = 0;
    float sumForKME2_1 = 0, sumForSo2_1 = 0, sumForSi2_1 = 0, sumForPlus2_1 = 0, sumForKMEZ2_1 = 0, sumForSoZ2_1 = 0, sumForSiZ2_1 = 0, sumForPlusZ2_1 = 0, countForKME2_1 = 0, countForSo2_1 = 0, countForSi2_1 = 0, countForPlus2_1 = 0;
    float sumForKME2_2 = 0, sumForSo2_2 = 0, sumForSi2_2 = 0, sumForPlus2_2 = 0, sumForKMEZ2_2 = 0, sumForSoZ2_2 = 0, sumForSiZ2_2 = 0, sumForPlusZ2_2 = 0, countForKME2_2 = 0, countForSo2_2 = 0, countForSi2_2 = 0, countForPlus2_2 = 0;
    float sumForKME3_1 = 0, sumForSo3_1 = 0, sumForSi3_1 = 0, sumForPlus3_1 = 0, sumForKMEZ3_1 = 0, sumForSoZ3_1 = 0, sumForSiZ3_1 = 0, sumForPlusZ3_1 = 0, countForKME3_1 = 0, countForSo3_1 = 0, countForSi3_1 = 0, countForPlus3_1 = 0;
    float sumForKME3_2 = 0, sumForSo3_2 = 0, sumForSi3_2 = 0, sumForPlus3_2 = 0, sumForKMEZ3_2 = 0, sumForSoZ3_2 = 0, sumForSiZ3_2 = 0, sumForPlusZ3_2 = 0, countForKME3_2 = 0, countForSo3_2 = 0, countForSi3_2 = 0, countForPlus3_2 = 0;

    float All1_1 ,All1_2, All2_1, All2_2, All3_1, All3_2;
    float KMESo1_1, KMESi1_1, KMESo1_2, KMESi1_2, KMESo2_1, KMESi2_1, KMESo2_2, KMESi2_2, KMESo3_1, KMESi3_1, KMESo3_2, KMESi3_2;

    float AllZ1_1, AllZ1_2, AllZ2_1, AllZ2_2, AllZ3_1, AllZ3_2;
    float KMESoZ1_1, KMESoZ1_2, KMESoZ2_1, KMESoZ2_2, KMESoZ3_1, KMESoZ3_2, KMESiZ1_1, KMESiZ1_2, KMESiZ2_1, KMESiZ2_2, KMESiZ3_1, KMESiZ3_2;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.grade_fragment, container, false);

        setHasOptionsMenu(true);

        sumForKME1_1 = 0; sumForSo1_1 = 0; sumForSi1_1 = 0; sumForPlus1_1 = 0; sumForKMEZ1_1 = 0; sumForSoZ1_1 = 0; sumForSiZ1_1 = 0; sumForPlusZ1_1 = 0; countForKME1_1 = 0; countForSo1_1 = 0; countForSi1_1 = 0; countForPlus1_1 = 0;
        sumForKME1_2 = 0; sumForSo1_2 = 0; sumForSi1_2 = 0; sumForPlus1_2 = 0; sumForKMEZ1_2 = 0; sumForSoZ1_2 = 0; sumForSiZ1_2 = 0; sumForPlusZ1_2 = 0; countForKME1_2 = 0; countForSo1_2 = 0; countForSi1_2 = 0; countForPlus1_2 = 0;
        sumForKME2_1 = 0; sumForSo2_1 = 0; sumForSi2_1 = 0; sumForPlus2_1 = 0; sumForKMEZ2_1 = 0; sumForSoZ2_1 = 0; sumForSiZ2_1 = 0; sumForPlusZ2_1 = 0; countForKME2_1 = 0; countForSo2_1 = 0; countForSi2_1 = 0; countForPlus2_1 = 0;
        sumForKME2_2 = 0; sumForSo2_2 = 0; sumForSi2_2 = 0; sumForPlus2_2 = 0; sumForKMEZ2_2 = 0; sumForSoZ2_2 = 0; sumForSiZ2_2 = 0; sumForPlusZ2_2 = 0; countForKME2_2 = 0; countForSo2_2 = 0; countForSi2_2 = 0; countForPlus2_2 = 0;
        sumForKME3_1 = 0; sumForSo3_1 = 0; sumForSi3_1 = 0; sumForPlus3_1 = 0; sumForKMEZ3_1 = 0; sumForSoZ3_1 = 0; sumForSiZ3_1 = 0; sumForPlusZ3_1 = 0; countForKME3_1 = 0; countForSo3_1 = 0; countForSi3_1 = 0; countForPlus3_1 = 0;
        sumForKME3_2 = 0; sumForSo3_2 = 0; sumForSi3_2 = 0; sumForPlus3_2 = 0; sumForKMEZ3_2 = 0; sumForSoZ3_2 = 0; sumForSiZ3_2 = 0; sumForPlusZ3_2 = 0; countForKME3_2 = 0; countForSo3_2 = 0; countForSi3_2 = 0; countForPlus3_2 = 0;

        All1_1 = 0; All1_2 = 0; All2_1 = 0; All2_2 = 0; All3_1 = 0; All3_2 = 0;
        KMESo1_1 = 0; KMESi1_1 = 0; KMESo1_2 = 0; KMESi1_2 = 0; KMESo2_1 = 0; KMESi2_1 = 0; KMESo2_2 = 0; KMESi2_2 = 0; KMESo3_1 = 0; KMESi3_1 = 0; KMESo3_2 = 0; KMESi3_2 = 0;

        AllZ1_1 = 0; AllZ1_2 = 0; AllZ2_1 = 0; AllZ2_2 = 0; AllZ3_1 = 0; AllZ3_2 = 0;
        KMESoZ1_1 = 0; KMESoZ1_2 = 0; KMESoZ2_1 = 0; KMESoZ2_2 = 0; KMESoZ3_1 = 0; KMESoZ3_2 = 0; KMESiZ1_1 = 0; KMESiZ1_2 = 0; KMESiZ2_1 = 0; KMESiZ2_2 = 0; KMESiZ3_1 = 0; KMESiZ3_2 = 0;


        allGradeText = rootView.findViewById(R.id.allGradeText);
        allZText = rootView.findViewById(R.id.allZText);
        kGradeText = rootView.findViewById(R.id.kGradeText);
        kZText = rootView.findViewById(R.id.kZText);
        mGradeText = rootView.findViewById(R.id.mGradeText);
        mZText = rootView.findViewById(R.id.mZText);

        TestName1 = rootView.findViewById(R.id.TestName1);
        TestName2 = rootView.findViewById(R.id.TestName2);
        TestName3 = rootView.findViewById(R.id.TestName3);
        TestName4 = rootView.findViewById(R.id.TestName4);

        SubjectName1 = rootView.findViewById(R.id.SubjectName1);
        SubjectName2 = rootView.findViewById(R.id.SubjectName2);
        SubjectName3 = rootView.findViewById(R.id.SubjectName3);
        SubjectName4 = rootView.findViewById(R.id.SubjectName4);

        Score1 = rootView.findViewById(R.id.Score1);
        Score2 = rootView.findViewById(R.id.Score2);
        Score3 = rootView.findViewById(R.id.Score3);
        Score4 = rootView.findViewById(R.id.Score4);

        sScore1 = rootView.findViewById(R.id.sScore1);
        sScore2 = rootView.findViewById(R.id.sScore2);
        sScore3 = rootView.findViewById(R.id.sScore3);
        sScore4 = rootView.findViewById(R.id.sScore4);

        Percent1 = rootView.findViewById(R.id.Percent1);
        Percent2 = rootView.findViewById(R.id.Percent2);
        Percent3 = rootView.findViewById(R.id.Percent3);
        Percent4 = rootView.findViewById(R.id.Percent4);

        Grade1 = rootView.findViewById(R.id.Grade1);
        Grade2 = rootView.findViewById(R.id.Grade2);
        Grade3 = rootView.findViewById(R.id.Grade3);
        Grade4 = rootView.findViewById(R.id.Grade4);



        // 메인 액티비티에서 내신 데이터 베이스 조회하고 프래그먼트로 보내기
        sdatabase = getActivity().openOrCreateDatabase("grade.db", Context.MODE_PRIVATE, null);
        sdatabase.execSQL("create table if not exists " + "school("
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
        Cursor cursor = sdatabase.rawQuery("select _id, semester, subjectClassification, subject, learnTime, score, accomplishment, grade, average, diviation from school", null);
        int recordCount = cursor.getCount();

        for(int i=0; i<recordCount; i++){
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


            if(new_semester.equals("1-1")){
                if(new_subjectClassification.equals("국어") || new_subjectClassification.equals("수학") || new_subjectClassification.equals("영어")){
                    sumForKME1_1 += (new_grade * new_learnTime);
                    sumForKMEZ1_1 += ((new_score - new_average) / new_diviation) * new_learnTime;
                    countForKME1_1 += new_learnTime;
                }

                if(new_subjectClassification.equals("사회")){
                    sumForSo1_1 += (new_grade * new_learnTime);
                    sumForSoZ1_1 += ((new_score - new_average) / new_diviation) * new_learnTime;
                    countForSo1_1 += new_learnTime;
                }

                if(new_subjectClassification.equals("과학")){
                    sumForSi1_1 += (new_grade * new_learnTime);
                    sumForSiZ1_1 += ((new_score - new_average) / new_diviation) * new_learnTime;
                    countForSi1_1 += new_learnTime;
                }

                if(new_subjectClassification.equals("기타")){
                    sumForPlus1_1 += (new_grade * new_learnTime);
                    sumForPlusZ1_1 += ((new_score - new_average) / new_diviation) * new_learnTime;
                    countForPlus1_1 += new_learnTime;
                }
            }


            if(new_semester.equals("1-2")){
                if(new_subjectClassification.equals("국어") || new_subjectClassification.equals("수학") || new_subjectClassification.equals("영어")){
                    sumForKME1_2 += (new_grade * new_learnTime);
                    sumForKMEZ1_2 += ((new_score - new_average) / new_diviation) * new_learnTime;
                    countForKME1_2 += new_learnTime;
                }

                if(new_subjectClassification.equals("사회")){
                    sumForSo1_2 += (new_grade * new_learnTime);
                    sumForSoZ1_2 += ((new_score - new_average) / new_diviation) * new_learnTime;
                    countForSo1_2 += new_learnTime;
                }

                if(new_subjectClassification.equals("과학")){
                    sumForSi1_2 += (new_grade * new_learnTime);
                    sumForSiZ1_2 += ((new_score - new_average) / new_diviation) * new_learnTime;
                    countForSi1_2 += new_learnTime;
                }

                if(new_subjectClassification.equals("기타")){
                    sumForPlus1_2 += (new_grade * new_learnTime);
                    sumForPlusZ1_2 += ((new_score - new_average) / new_diviation) * new_learnTime;
                    countForPlus1_2 += new_learnTime;
                }
            }


            if(new_semester.equals("2-1")){
                if(new_subjectClassification.equals("국어") || new_subjectClassification.equals("수학") || new_subjectClassification.equals("영어")){
                    sumForKME2_1 += (new_grade * new_learnTime);
                    sumForKMEZ2_1 += ((new_score - new_average) / new_diviation) * new_learnTime;
                    countForKME2_1 += new_learnTime;
                }

                if(new_subjectClassification.equals("사회")){
                    sumForSo2_1 += (new_grade * new_learnTime);
                    sumForSoZ2_1 += ((new_score - new_average) / new_diviation) * new_learnTime;
                    countForSo2_1 += new_learnTime;
                }

                if(new_subjectClassification.equals("과학")){
                    sumForSi2_1 += (new_grade * new_learnTime);
                    sumForSiZ2_1 += ((new_score - new_average) / new_diviation) * new_learnTime;
                    countForSi2_1 += new_learnTime;
                }

                if(new_subjectClassification.equals("기타")){
                    sumForPlus2_1 += (new_grade * new_learnTime);
                    sumForPlusZ2_1 += ((new_score - new_average) / new_diviation) * new_learnTime;
                    countForPlus2_1 += new_learnTime;
                }
            }


            if(new_semester.equals("2-2")){
                if(new_subjectClassification.equals("국어") || new_subjectClassification.equals("수학") || new_subjectClassification.equals("영어")){
                    sumForKME2_2 += (new_grade * new_learnTime);
                    sumForKMEZ2_2 += ((new_score - new_average) / new_diviation) * new_learnTime;
                    countForKME2_2 += new_learnTime;
                }

                if(new_subjectClassification.equals("사회")){
                    sumForSo2_2 += (new_grade * new_learnTime);
                    sumForSoZ2_2 += ((new_score - new_average) / new_diviation) * new_learnTime;
                    countForSo2_2 += new_learnTime;
                }

                if(new_subjectClassification.equals("과학")){
                    sumForSi2_2 += (new_grade * new_learnTime);
                    sumForSiZ2_2 += ((new_score - new_average) / new_diviation) * new_learnTime;
                    countForSi2_2 += new_learnTime;
                }

                if(new_subjectClassification.equals("기타")){
                    sumForPlus2_2 += (new_grade * new_learnTime);
                    sumForPlusZ2_2 += ((new_score - new_average) / new_diviation) * new_learnTime;
                    countForPlus2_2 += new_learnTime;
                }
            }


            if(new_semester.equals("3-1")){
                if(new_subjectClassification.equals("국어") || new_subjectClassification.equals("수학") || new_subjectClassification.equals("영어")){
                    sumForKME3_1 += (new_grade * new_learnTime);
                    sumForKMEZ3_1 += ((new_score - new_average) / new_diviation) * new_learnTime;
                    countForKME3_1 += new_learnTime;
                }

                if(new_subjectClassification.equals("사회")){
                    sumForSo3_1 += (new_grade * new_learnTime);
                    sumForSoZ3_1 += ((new_score - new_average) / new_diviation) * new_learnTime;
                    countForSo3_1 += new_learnTime;
                }

                if(new_subjectClassification.equals("과학")){
                    sumForSi3_1 += (new_grade * new_learnTime);
                    sumForSiZ3_1 += ((new_score - new_average) / new_diviation) * new_learnTime;
                    countForSi3_1 += new_learnTime;
                }

                if(new_subjectClassification.equals("기타")){
                    sumForPlus3_1 += (new_grade * new_learnTime);
                    sumForPlusZ3_1 += ((new_score - new_average) / new_diviation) * new_learnTime;
                    countForPlus3_1 += new_learnTime;
                }
            }


            if(new_semester.equals("3-2")){
                if(new_subjectClassification.equals("국어") || new_subjectClassification.equals("수학") || new_subjectClassification.equals("영어")){
                    sumForKME3_2 += (new_grade * new_learnTime);
                    sumForKMEZ3_2 += ((new_score - new_average) / new_diviation) * new_learnTime;
                    countForKME3_2 += new_learnTime;
                }

                if(new_subjectClassification.equals("사회")){
                    sumForSo3_2 += (new_grade * new_learnTime);
                    sumForSoZ3_2 += ((new_score - new_average) / new_diviation) * new_learnTime;
                    countForSo3_2 += new_learnTime;
                }

                if(new_subjectClassification.equals("과학")){
                    sumForSi3_2 += (new_grade * new_learnTime);
                    sumForSiZ3_2 += ((new_score - new_average) / new_diviation) * new_learnTime;
                    countForSi3_2 += new_learnTime;
                }

                if(new_subjectClassification.equals("기타")){
                    sumForPlus3_2 += (new_grade * new_learnTime);
                    sumForPlusZ3_2 += ((new_score - new_average) / new_diviation) * new_learnTime;
                    countForPlus3_2 += new_learnTime;
                }
            }


        }


        // 통계 내주기
        // 1-1
        if((countForKME1_1 + countForSo1_1 + countForSi1_1 + countForPlus1_1) == 0){
            All1_1 = 0;
            AllZ1_1 = 0;
        }
        else {
            All1_1 = (sumForKME1_1 + sumForSi1_1 + sumForSo1_1 + sumForPlus1_1) / (countForKME1_1 + countForSo1_1 + countForSi1_1 + countForPlus1_1);
            AllZ1_1 = (sumForKMEZ1_1 + sumForSiZ1_1 + sumForSoZ1_1 + sumForPlusZ1_1) / (countForKME1_1 + countForSo1_1 + countForSi1_1 + countForPlus1_1);
        }
        if((countForKME1_1 + countForSo1_1 + countForSi1_1) == 0){
            KMESo1_1 = 0;
            KMESi1_1 = 0;
            KMESoZ1_1 = 0;
            KMESiZ1_1 = 0;
        }
        else {
            KMESo1_1 = (sumForKME1_1 + sumForSo1_1) / (countForKME1_1 + countForSo1_1);
            KMESi1_1 = (sumForKME1_1 + sumForSi1_1) / (countForKME1_1 + countForSi1_1);
            KMESoZ1_1 = (sumForKMEZ1_1 + sumForSoZ1_1) / (countForKME1_1 + countForSo1_1);
            KMESiZ1_1 = (sumForKMEZ1_1 + sumForSiZ1_1) / (countForKME1_1 + countForSi1_1);
        }


        // 1-2
        if((countForKME1_2 + countForSo1_2 + countForSi1_2 + countForPlus1_2) == 0){
            All1_2 = 0;
            AllZ1_2 = 0;
        }
        else {
            All1_2 = (sumForKME1_2 + sumForSi1_2 + sumForSo1_2 + sumForPlus1_2) / (countForKME1_2 + countForSo1_2 + countForSi1_2 + countForPlus1_2);
            AllZ1_2 = (sumForKMEZ1_2 + sumForSiZ1_2 + sumForSoZ1_2 + sumForPlusZ1_2) / (countForKME1_2 + countForSo1_2 + countForSi1_2 + countForPlus1_2);
        }
        if((countForKME1_2 + countForSo1_2) == 0){
            KMESo1_2 = 0;
            KMESi1_2 = 0;
            KMESoZ1_2 = 0;
            KMESiZ1_2 = 0;
        }
        else {
            KMESo1_2 = (sumForKME1_2 + sumForSo1_2) / (countForKME1_2 + countForSo1_2);
            KMESi1_2 = (sumForKME1_2 + sumForSi1_2) / (countForKME1_2 + countForSi1_2);
            KMESoZ1_2 = (sumForKMEZ1_2 + sumForSoZ1_2) / (countForKME1_2 + countForSo1_2);
            KMESiZ1_2 = (sumForKMEZ1_2 + sumForSiZ1_2) / (countForKME1_2 + countForSi1_2);
        }


        // 2-1
        if((countForKME2_1 + countForSo2_1 + countForSi2_1 + countForPlus2_1) == 0){
            All2_1 = 0;
            AllZ2_1 = 0;
        }
        else {
            All2_1 = (sumForKME2_1 + sumForSi2_1 + sumForSo2_1 + sumForPlus2_1) / (countForKME2_1 + countForSo2_1 + countForSi2_1 + countForPlus2_1);
            AllZ2_1 = (sumForKMEZ2_1 + sumForSiZ2_1 + sumForSoZ2_1 + sumForPlusZ2_1) / (countForKME2_1 + countForSo2_1 + countForSi2_1 + countForPlus2_1);
        }
        if((countForKME2_1 + countForSo2_1) == 0){
            KMESi2_1 = 0;
            KMESo2_1 = 0;
            KMESoZ2_1 = 0;
            KMESiZ2_1 = 0;
        }
        else {
            KMESo2_1 = (sumForKME2_1 + sumForSo2_1) / (countForKME2_1 + countForSo2_1);
            KMESi2_1 = (sumForKME2_1 + sumForSi2_1) / (countForKME2_1 + countForSi2_1);
            KMESoZ2_1 = (sumForKMEZ2_1 + sumForSoZ2_1) / (countForKME2_1 + countForSo2_1);
            KMESiZ2_1 = (sumForKMEZ2_1 + sumForSiZ2_1) / (countForKME2_1 + countForSi2_1);
        }


        // 2-2
        if((countForKME2_2 + countForSo2_2 + countForSi2_2 + countForPlus2_2) == 0){
            All2_2 = 0;
            AllZ2_2 = 0;
        }
        else {
            All2_2 = (sumForKME2_2 + sumForSi2_2 + sumForSo2_2 + sumForPlus2_2) / (countForKME2_2 + countForSo2_2 + countForSi2_2 + countForPlus2_2);
            AllZ2_2 = (sumForKMEZ2_2 + sumForSiZ2_2 + sumForSoZ2_2 + sumForPlusZ2_2) / (countForKME2_2 + countForSo2_2 + countForSi2_2 + countForPlus2_2);

        }
        if((countForKME2_2 + countForSo2_2) == 0){
            KMESo2_2 = 0;
            KMESi2_2 = 0;
            KMESoZ2_2 = 0;
            KMESiZ2_2 = 0;
        }
        else {
            KMESo2_2 = (sumForKME2_2 + sumForSo2_2) / (countForKME2_2 + countForSo2_2);
            KMESi2_2 = (sumForKME2_2 + sumForSi2_2) / (countForKME2_2 + countForSi2_2);
            KMESoZ2_2 = (sumForKMEZ2_2 + sumForSoZ2_2) / (countForKME2_2 + countForSo2_2);
            KMESiZ2_2 = (sumForKMEZ2_2 + sumForSiZ2_2) / (countForKME2_2 + countForSi2_2);
        }


        // 3-1
        if((countForKME3_1 + countForSo3_1 + countForSi3_1 + countForPlus3_1) == 0){
            All3_1 = 0;
            AllZ3_1 = 0;
        }
        else {
            All3_1 = (sumForKME3_1 + sumForSi3_1 + sumForSo3_1 + sumForPlus3_1) / (countForKME3_1 + countForSo3_1 + countForSi3_1 + countForPlus3_1);
            AllZ3_1 = (sumForKMEZ3_1 + sumForSiZ3_1 + sumForSoZ3_1 + sumForPlusZ3_1) / (countForKME3_1 + countForSo3_1 + countForSi3_1 + countForPlus3_1);

        }
        if ((countForKME3_1 + countForSo3_1) == 0){
            KMESi3_1 = 0;
            KMESo3_1 = 0;
            KMESoZ3_1 = 0;
            KMESiZ3_1 = 0;
        }
        else {
            KMESo3_1 = (sumForKME3_1 + sumForSo3_1) / (countForKME3_1 + countForSo3_1);
            KMESi3_1 = (sumForKME3_1 + sumForSi3_1) / (countForKME3_1 + countForSi3_1);
            KMESoZ3_1 = (sumForKMEZ3_1 + sumForSoZ3_1) / (countForKME3_1 + countForSo3_1);
            KMESiZ3_1 = (sumForKMEZ3_1 + sumForSiZ3_1) / (countForKME3_1 + countForSi3_1);
        }


        // 3-2
        if((countForKME3_2 + countForSo3_2 + countForSi3_2 + countForPlus3_2) == 0){
            All3_2 = 0;
            AllZ3_2 = 0;
        }
        else {
            All3_2 = (sumForKME3_2 + sumForSi3_2 + sumForSo3_2 + sumForPlus3_2) / (countForKME3_2 + countForSo3_2 + countForSi3_2 + countForPlus3_2);
            AllZ3_2 = (sumForKMEZ3_2 + sumForSiZ3_2 + sumForSoZ3_2 + sumForPlusZ3_2) / (countForKME3_2 + countForSo3_2 + countForSi3_2 + countForPlus3_2);

        }
        if((countForKME3_2 + countForSo3_2) == 0){
            KMESi3_2 = 0;
            KMESo3_2 = 0;
            KMESoZ3_2 = 0;
            KMESiZ3_2 = 0;
        }
        else {
            KMESo3_2 = (sumForKME3_2 + sumForSo3_2) / (countForKME3_2 + countForSo3_2);
            KMESi3_2 = (sumForKME3_2 + sumForSi3_2) / (countForKME3_2 + countForSi3_2);
            KMESoZ3_2 = (sumForKMEZ3_2 + sumForSoZ3_2) / (countForKME3_2 + countForSo3_2);
            KMESiZ3_2 = (sumForKMEZ3_2 + sumForSiZ3_2) / (countForKME3_2 + countForSi3_2);
        }



        int new_count = 0;
        if(All1_1 != 0){
            new_count++;
        }
        if(All1_2 != 0){
            new_count++;
        }
        if(All2_1 != 0){
            new_count++;
        }
        if(All2_2 != 0){
            new_count++;
        }
        if(All3_1 != 0){
            new_count++;
        }
        if(All3_2 != 0){
            new_count++;
        }


        if(new_count == 0){
            allGradeText.setText("null");
            kGradeText.setText("null");
            mGradeText.setText("null");
        }
        else {
            allGradeText.setText(String.format("%.2f", ((All1_1 + All1_2 + All2_1 + All2_2 + All3_1 + All3_2) / new_count)) + "");
            kGradeText.setText(String.format("%.2f", ((KMESo1_1 + KMESo1_2 + KMESo2_1 + KMESo2_2 + KMESo3_1 + KMESo3_2)/ new_count)) + "");
            mGradeText.setText(String.format("%.2f", ((KMESi1_1 + KMESi1_2 + KMESi2_1 + KMESi2_2 + KMESi3_1 + KMESi3_2)/ new_count)) + "");

            allZText.setText(String.format("%.2f", ((AllZ1_1 + AllZ1_2 + AllZ2_1 + AllZ2_2 + AllZ3_1 + AllZ3_2) / new_count)) + "");
            kZText.setText(String.format("%.2f", ((KMESoZ1_1 + KMESoZ1_2 + KMESoZ2_1 + KMESoZ2_2 + KMESoZ3_1 + KMESoZ3_2)/ new_count)) + "");
            mZText.setText(String.format("%.2f", ((KMESiZ1_1 + KMESiZ1_2 + KMESiZ2_1 + KMESiZ2_2 + KMESiZ3_1 + KMESiZ3_2)/ new_count)) + "");
        }



        Button setRatioButton = rootView.findViewById(R.id.setRatioButton);
        setRatioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 계산 후 성적 텍스트뷰 입력 바꾸기

                // 알라트 다이아로그 선택
                final String[] items = new String[] {"33:33:33", "20:40:40"};

                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                dialog.setTitle("학년 비율을 선택하세요.")
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(items[which].equals("33:33:33")){
                                    // 비율로 계산해서 성적 입력
                                    // 성적 입력이 일부 비어 있으면..?

                                    if(All1_1 == 0 || All1_2 == 0){
                                        Toast.makeText(getContext(), "1학년 성적을 한개 이상 입력하세요.", Toast.LENGTH_LONG).show();
                                    }

                                    if(All2_1 == 0 || All2_2 == 0){
                                        Toast.makeText(getContext(), "2학년 성적을 한개 이상 입력하세요.", Toast.LENGTH_LONG).show();
                                    }

                                    // 3-2만 비어있으면 (오류가 있다. 앤드 다른 학년 꽉 차 있을 때)
                                    if(All3_2 == 0 && All1_1 != 0 && All1_2 != 0 && All2_1 != 0 && All2_2 != 0 && All3_1 !=0){
                                        allGradeText.setText(String.format("%.2f", ((All1_1 + All1_2 + All2_1 + All2_2 + All3_1) / 5)) + "");
                                        kGradeText.setText(String.format("%.2f", ((KMESo1_1 + KMESo1_2 + KMESo2_1 + KMESo2_2 + KMESo3_1)/ 5)) + "");
                                        mGradeText.setText(String.format("%.2f", ((KMESi1_1 + KMESi1_2 + KMESi2_1 + KMESi2_2 + KMESi3_1)/ 5)) + "");

                                        allZText.setText(String.format("%.2f", ((AllZ1_1 + AllZ1_2 + AllZ2_1 + AllZ2_2 + AllZ3_1) / 5)) + "");
                                        kZText.setText(String.format("%.2f", ((KMESoZ1_1 + KMESoZ1_2 + KMESoZ2_1 + KMESoZ2_2 + KMESoZ3_1)/ 5)) + "");
                                        mZText.setText(String.format("%.2f", ((KMESiZ1_1 + KMESiZ1_2 + KMESiZ2_1 + KMESiZ2_2 + KMESiZ3_1)/ 5)) + "");

                                        Toast.makeText(getContext(), "3-2 제외한 성적입니다.", Toast.LENGTH_LONG).show();
                                    }
                                    // 모두 채워져 있으면
                                    if(All1_1 != 0 && All1_2 != 0 && All2_1 != 0 && All2_2 != 0 && All3_1 !=0 && All3_2 != 0) {
                                        allGradeText.setText(String.format("%.2f", ((All1_1 + All1_2 + All2_1 + All2_2 + All3_1 + All3_2) / 6)) + "");
                                        kGradeText.setText(String.format("%.2f", ((KMESo1_1 + KMESo1_2 + KMESo2_1 + KMESo2_2 + KMESo3_1 + KMESo3_2)/ 6)) + "");
                                        mGradeText.setText(String.format("%.2f", ((KMESi1_1 + KMESi1_2 + KMESi2_1 + KMESi2_2 + KMESi3_1 + KMESi3_2)/ 6)) + "");

                                        allZText.setText(String.format("%.2f", ((AllZ1_1 + AllZ1_2 + AllZ2_1 + AllZ2_2 + AllZ3_1 + AllZ3_2) / 6)) + "");
                                        kZText.setText(String.format("%.2f", ((KMESoZ1_1 + KMESoZ1_2 + KMESoZ2_1 + KMESoZ2_2 + KMESoZ3_1 + KMESoZ3_2)/ 6)) + "");
                                        mZText.setText(String.format("%.2f", ((KMESiZ1_1 + KMESiZ1_2 + KMESiZ2_1 + KMESiZ2_2 + KMESiZ3_1 + KMESiZ3_2)/ 6)) + "");
                                    }

                                }


                                if(items[which].equals("20:40:40")){
                                    // 비율로 계산해서 성적 입력

                                    if(All1_1 == 0 || All1_2 == 0){
                                        Toast.makeText(getContext(), "1학년 성적을 한개 이상 입력하세요.", Toast.LENGTH_LONG).show();
                                    }

                                    if(All2_1 == 0 || All2_2 == 0){
                                        Toast.makeText(getContext(), "2학년 성적을 한개 이상 입력하세요.", Toast.LENGTH_LONG).show();
                                    }

                                    // 3-2만 비어있으면 (오류가 있다. 앤드 다른 학년 꽉 차 있을 때)
                                    if(All3_2 == 0 && All1_1 != 0 && All1_2 != 0 && All2_1 != 0 && All2_2 != 0 && All3_1 !=0){
                                        allGradeText.setText(String.format("%.2f", (((All1_1 + All1_2)/2 * 2 + (All2_1 + All2_2)/2 * 4 + All3_1 * 4)/10)) + "");
                                        kGradeText.setText(String.format("%.2f", (((KMESo1_1 + KMESo1_2)/2 * 2 + (KMESo2_1 + KMESo2_2)/2 * 4 +KMESo3_1 * 4)/10))  + "");
                                        mGradeText.setText(String.format("%.2f", (((KMESi1_1 + KMESi1_2)/2 * 2 + (KMESi2_1 + KMESi2_2)/2 * 4 + KMESi3_1 * 4)/10)) + "");

                                        allZText.setText(String.format("%.2f", (((AllZ1_1 + AllZ1_2)/2 * 2 + (AllZ2_1 + AllZ2_2)/2 * 4 + AllZ3_1 * 4)/10)) + "");
                                        kZText.setText(String.format("%.2f", (((KMESoZ1_1 + KMESoZ1_2)/2 * 2 + (KMESoZ2_1 + KMESoZ2_2)/2 * 4 + KMESoZ3_1 * 4)/10))  + "");
                                        mZText.setText(String.format("%.2f", (((KMESiZ1_1 + KMESiZ1_2)/2 * 2 + (KMESiZ2_1 + KMESiZ2_2)/2 * 4 + KMESiZ3_1 * 4)/10)) + "");

                                        Toast.makeText(getContext(), "3-2 제외한 성적입니다.", Toast.LENGTH_LONG).show();
                                    }
                                    // 모두 채워져 있으면
                                    if(All1_1 != 0 && All1_2 != 0 && All2_1 != 0 && All2_2 != 0 && All3_1 !=0 && All3_2 != 0) {
                                        allGradeText.setText(String.format("%.2f", (((All1_1 + All1_2)/2 * 2 + (All2_1 + All2_2)/2 *4 + (All3_1 + All3_2)/2 * 4) / 10)) + "");
                                        kGradeText.setText(String.format("%.2f", (((KMESo1_1 + KMESo1_2)/2 * 2 + (KMESo2_1 + KMESo2_2)/2 * 4 + (KMESo3_1 + KMESo3_2)/2 *4)/ 10)) + "");
                                        mGradeText.setText(String.format("%.2f", (((KMESi1_1 + KMESi1_2)/2 * 2 + (KMESi2_1 + KMESi2_2)/2 * 4 + (KMESi3_1 + KMESi3_2)/2 * 4)/ 10)) + "");

                                        allZText.setText(String.format("%.2f", (((AllZ1_1 + AllZ1_2)/2 * 2 + (AllZ2_1 + AllZ2_2)/2 *4 + (AllZ3_1 + AllZ3_2)/2 * 4) / 10)) + "");
                                        kZText.setText(String.format("%.2f", (((KMESoZ1_1 + KMESoZ1_2)/2 * 2 + (KMESoZ2_1 + KMESoZ2_2)/2 * 4 + (KMESoZ3_1 + KMESoZ3_2)/2 *4)/ 10)) + "");
                                        mZText.setText(String.format("%.2f", (((KMESiZ1_1 + KMESiZ1_2)/2 * 2 + (KMESiZ2_1 + KMESiZ2_2)/2 * 4 + (KMESiZ3_1 + KMESiZ3_2)/2 * 4)/ 10)) + "");
                                    }


                                }
                            }
                        }).create().show();


            }
        });






        ndatabase = getActivity().openOrCreateDatabase("grade.db",android.content.Context.MODE_PRIVATE ,null);
        ndatabase.execSQL("create table if not exists " + "nation("
                + " _id integer PRIMARY KEY autoincrement, "
                + " testName text, "
                + " subjectClassification text, "
                + " subject text, "
                + " originalScore int, "
                + " standardScore int, "
                + " grade int, "
                + " percentage float)");




        // 과목 선택시 최근 4개 성적 가져와서 화면에 표시해줌
        final Button setSubjectButton = rootView.findViewById(R.id.setSubjectButton);
        setSubjectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String[] items = new String[] {"국어", "수학", "영어", "한국사", "사회", "과학", "제2외국어"};

                TestName1.setText(null); TestName2.setText(null); TestName3.setText(null); TestName4.setText(null);
                SubjectName1.setText(null); SubjectName2.setText(null); SubjectName3.setText(null); SubjectName4.setText(null);
                Score1.setText(null); Score2.setText(null); Score3.setText(null); Score4.setText(null);
                sScore1.setText(null); sScore2.setText(null); sScore3.setText(null); sScore4.setText(null);
                Percent1.setText(null); Percent2.setText(null); Percent3.setText(null); Percent4.setText(null);
                Grade1.setText(null); Grade2.setText(null); Grade3.setText(null); Grade4.setText(null);


                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                dialog.setTitle("선택 과목의 최근 성적을 \n4개까지 불러옵니다.")
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(items[which].equals("국어")){

                                    setSubjectButton.setText("국어");

                                    Cursor cursor1 = ndatabase.rawQuery("select _id, testName, subjectClassification, subject, originalScore, standardScore, grade, percentage from nation", null);
                                    int recordCount1 = cursor1.getCount();
                                    int count = 0;

                                    if(recordCount1 == 0){
                                        Toast.makeText(getContext(), "국어 성적을 한 개 이상 입력하세요.", Toast.LENGTH_LONG).show();
                                    }

                                    cursor1.moveToLast();

                                    for(int i=0; i<recordCount1; i++){

                                        int new_n_id = cursor1.getInt(0);
                                        String new_ntestName = cursor1.getString(1);
                                        String new_nsubjectClassification = cursor1.getString(2);
                                        String new_nsubject = cursor1.getString(3);
                                        int new_noriginalScore = cursor1.getInt(4);
                                        int new_nstandardScore = cursor1.getInt(5);
                                        int new_ngrade = cursor1.getInt(6);
                                        float new_npercentage = cursor1.getFloat(7);


                                        if(new_nsubjectClassification.equals("국어")){
                                            if(count == 4){
                                                break;
                                            }
                                            if(count == 3){
                                                TestName4.setText(new_ntestName);
                                                SubjectName4.setText(new_nsubject);
                                                Score4.setText(new_noriginalScore + "점");
                                                sScore4.setText(new_nstandardScore + "점");
                                                Percent4.setText(new_npercentage + "");
                                                Grade4.setText(new_ngrade + "등급");
                                                count++;
                                            }
                                            if(count == 2){
                                                TestName3.setText(new_ntestName);
                                                SubjectName3.setText(new_nsubject);
                                                Score3.setText(new_noriginalScore + "점");
                                                sScore3.setText(new_nstandardScore + "점");
                                                Percent3.setText(new_npercentage + "");
                                                Grade3.setText(new_ngrade + "등급");
                                                count++;
                                            }
                                            if(count == 1){
                                                TestName2.setText(new_ntestName);
                                                SubjectName2.setText(new_nsubject);
                                                Score2.setText(new_noriginalScore + "점");
                                                sScore2.setText(new_nstandardScore + "점");
                                                Percent2.setText(new_npercentage + "");
                                                Grade2.setText(new_ngrade + "등급");
                                                count++;
                                            }
                                            if(count == 0){
                                                TestName1.setText(new_ntestName);
                                                SubjectName1.setText(new_nsubject);
                                                Score1.setText(new_noriginalScore + "점");
                                                sScore1.setText(new_nstandardScore + "점");
                                                Percent1.setText(new_npercentage + "");
                                                Grade1.setText(new_ngrade + "등급");
                                                count++;
                                            }

                                        }

                                        cursor1.moveToPrevious();
                                    }

                                }
                                if(items[which].equals("수학")){

                                    setSubjectButton.setText("수학");

                                    Cursor cursor1 = ndatabase.rawQuery("select _id, testName, subjectClassification, subject, originalScore, standardScore, grade, percentage from nation", null);
                                    int recordCount1 = cursor1.getCount();
                                    int count = 0;

                                    if(recordCount1 == 0){
                                        Toast.makeText(getContext(), "수학 성적을 한 개 이상 입력하세요.", Toast.LENGTH_LONG).show();
                                    }

                                    cursor1.moveToLast();

                                    for(int i=0; i<recordCount1; i++){

                                        int new_n_id = cursor1.getInt(0);
                                        String new_ntestName = cursor1.getString(1);
                                        String new_nsubjectClassification = cursor1.getString(2);
                                        String new_nsubject = cursor1.getString(3);
                                        int new_noriginalScore = cursor1.getInt(4);
                                        int new_nstandardScore = cursor1.getInt(5);
                                        int new_ngrade = cursor1.getInt(6);
                                        float new_npercentage = cursor1.getFloat(7);


                                        if(new_nsubjectClassification.equals("수학")){
                                            if(count == 4){
                                                break;
                                            }
                                            if(count == 3){
                                                TestName4.setText(new_ntestName);
                                                SubjectName4.setText(new_nsubject);
                                                Score4.setText(new_noriginalScore + "점");
                                                sScore4.setText(new_nstandardScore + "점");
                                                Percent4.setText(new_npercentage + "");
                                                Grade4.setText(new_ngrade + "등급");
                                                count++;
                                            }
                                            if(count == 2){
                                                TestName3.setText(new_ntestName);
                                                SubjectName3.setText(new_nsubject);
                                                Score3.setText(new_noriginalScore + "점");
                                                sScore3.setText(new_nstandardScore + "점");
                                                Percent3.setText(new_npercentage + "");
                                                Grade3.setText(new_ngrade + "등급");
                                                count++;
                                            }
                                            if(count == 1){
                                                TestName2.setText(new_ntestName);
                                                SubjectName2.setText(new_nsubject);
                                                Score2.setText(new_noriginalScore + "점");
                                                sScore2.setText(new_nstandardScore + "점");
                                                Percent2.setText(new_npercentage + "");
                                                Grade2.setText(new_ngrade + "등급");
                                                count++;
                                            }
                                            if(count == 0){
                                                TestName1.setText(new_ntestName);
                                                SubjectName1.setText(new_nsubject);
                                                Score1.setText(new_noriginalScore + "점");
                                                sScore1.setText(new_nstandardScore + "점");
                                                Percent1.setText(new_npercentage + "");
                                                Grade1.setText(new_ngrade + "등급");
                                                count++;
                                            }
                                        }

                                        cursor1.moveToPrevious();
                                    }

                                }
                                if(items[which].equals("영어")){

                                    setSubjectButton.setText("영어");

                                    Cursor cursor1 = ndatabase.rawQuery("select _id, testName, subjectClassification, subject, originalScore, standardScore, grade, percentage from nation", null);
                                    int recordCount1 = cursor1.getCount();
                                    int count = 0;

                                    if(recordCount1 == 0){
                                        Toast.makeText(getContext(), "영어 성적을 한 개 이상 입력하세요.", Toast.LENGTH_LONG).show();
                                    }

                                    cursor1.moveToLast();

                                    for(int i=0; i<recordCount1; i++){

                                        int new_n_id = cursor1.getInt(0);
                                        String new_ntestName = cursor1.getString(1);
                                        String new_nsubjectClassification = cursor1.getString(2);
                                        String new_nsubject = cursor1.getString(3);
                                        int new_noriginalScore = cursor1.getInt(4);
                                        int new_nstandardScore = cursor1.getInt(5);
                                        int new_ngrade = cursor1.getInt(6);
                                        float new_npercentage = cursor1.getFloat(7);


                                        if(new_nsubjectClassification.equals("영어")){
                                            if(count == 4){
                                                break;
                                            }
                                            if(count == 3){
                                                TestName4.setText(new_ntestName);
                                                SubjectName4.setText(new_nsubject);
                                                Score4.setText(new_noriginalScore + "점");
                                                sScore4.setText("null");
                                                Percent4.setText("null");
                                                Grade4.setText(new_ngrade + "등급");
                                                count++;
                                            }
                                            if(count == 2){
                                                TestName3.setText(new_ntestName);
                                                SubjectName3.setText(new_nsubject);
                                                Score3.setText(new_noriginalScore + "점");
                                                sScore3.setText("null");
                                                Percent3.setText("null");
                                                Grade3.setText(new_ngrade + "등급");
                                                count++;
                                            }
                                            if(count == 1){
                                                TestName2.setText(new_ntestName);
                                                SubjectName2.setText(new_nsubject);
                                                Score2.setText(new_noriginalScore + "점");
                                                sScore2.setText("null");
                                                Percent2.setText("null");
                                                Grade2.setText(new_ngrade + "등급");
                                                count++;
                                            }
                                            if(count == 0){
                                                TestName1.setText(new_ntestName);
                                                SubjectName1.setText(new_nsubject);
                                                Score1.setText(new_noriginalScore + "점");
                                                sScore1.setText("null");
                                                Percent1.setText("null");
                                                Grade1.setText(new_ngrade + "등급");
                                                count++;
                                            }
                                        }

                                        cursor1.moveToPrevious();
                                    }

                                }
                                if(items[which].equals("한국사")){

                                    setSubjectButton.setText("한국사");

                                    Cursor cursor1 = ndatabase.rawQuery("select _id, testName, subjectClassification, subject, originalScore, standardScore, grade, percentage from nation", null);
                                    int recordCount1 = cursor1.getCount();
                                    int count = 0;

                                    if(recordCount1 == 0){
                                        Toast.makeText(getContext(), "한국사 성적을 한 개 이상 입력하세요.", Toast.LENGTH_LONG).show();
                                    }

                                    cursor1.moveToLast();

                                    for(int i=0; i<recordCount1; i++){

                                        int new_n_id = cursor1.getInt(0);
                                        String new_ntestName = cursor1.getString(1);
                                        String new_nsubjectClassification = cursor1.getString(2);
                                        String new_nsubject = cursor1.getString(3);
                                        int new_noriginalScore = cursor1.getInt(4);
                                        int new_nstandardScore = cursor1.getInt(5);
                                        int new_ngrade = cursor1.getInt(6);
                                        float new_npercentage = cursor1.getFloat(7);

                                        if(new_nsubjectClassification.equals("한국사")){
                                            if(count == 4){
                                                break;
                                            }
                                            if(count == 3){
                                                TestName4.setText(new_ntestName);
                                                SubjectName4.setText(new_nsubject);
                                                Score4.setText(new_noriginalScore + "점");
                                                sScore4.setText("null");
                                                Percent4.setText("null");
                                                Grade4.setText(new_ngrade + "등급");
                                                count++;
                                            }
                                            if(count == 2){
                                                TestName3.setText(new_ntestName);
                                                SubjectName3.setText(new_nsubject);
                                                Score3.setText(new_noriginalScore + "점");
                                                sScore3.setText("null");
                                                Percent3.setText("null");
                                                Grade3.setText(new_ngrade + "등급");
                                                count++;
                                            }
                                            if(count == 1){
                                                TestName2.setText(new_ntestName);
                                                SubjectName2.setText(new_nsubject);
                                                Score2.setText(new_noriginalScore + "점");
                                                sScore2.setText("null");
                                                Percent2.setText("null");
                                                Grade2.setText(new_ngrade + "등급");
                                                count++;
                                            }
                                            if(count == 0){
                                                TestName1.setText(new_ntestName);
                                                SubjectName1.setText(new_nsubject);
                                                Score1.setText(new_noriginalScore + "점");
                                                sScore1.setText("null");
                                                Percent1.setText("null");
                                                Grade1.setText(new_ngrade + "등급");
                                                count++;
                                            }
                                        }

                                        cursor1.moveToPrevious();
                                    }

                                }
                                if(items[which].equals("사회")){

                                    setSubjectButton.setText("사회");

                                    Cursor cursor1 = ndatabase.rawQuery("select _id, testName, subjectClassification, subject, originalScore, standardScore, grade, percentage from nation", null);
                                    int recordCount1 = cursor1.getCount();
                                    int count = 0;

                                    if(recordCount1 == 0){
                                        Toast.makeText(getContext(), "사회 성적을 한 개 이상 입력하세요.", Toast.LENGTH_LONG).show();
                                    }

                                    cursor1.moveToLast();

                                    for(int i=0; i<recordCount1; i++){

                                        int new_n_id = cursor1.getInt(0);
                                        String new_ntestName = cursor1.getString(1);
                                        String new_nsubjectClassification = cursor1.getString(2);
                                        String new_nsubject = cursor1.getString(3);
                                        int new_noriginalScore = cursor1.getInt(4);
                                        int new_nstandardScore = cursor1.getInt(5);
                                        int new_ngrade = cursor1.getInt(6);
                                        float new_npercentage = cursor1.getFloat(7);

                                        if(new_nsubjectClassification.equals("사회")){
                                            if(count == 4){
                                                break;
                                            }
                                            if(count == 3){
                                                TestName4.setText(new_ntestName);
                                                SubjectName4.setText(new_nsubject);
                                                Score4.setText(new_noriginalScore + "점");
                                                sScore4.setText(new_nstandardScore + "점");
                                                Percent4.setText(new_npercentage + "");
                                                Grade4.setText(new_ngrade + "등급");
                                                count++;
                                            }
                                            if(count == 2){
                                                TestName3.setText(new_ntestName);
                                                SubjectName3.setText(new_nsubject);
                                                Score3.setText(new_noriginalScore + "점");
                                                sScore3.setText(new_nstandardScore + "점");
                                                Percent3.setText(new_npercentage + "");
                                                Grade3.setText(new_ngrade + "등급");
                                                count++;
                                            }
                                            if(count == 1){
                                                TestName2.setText(new_ntestName);
                                                SubjectName2.setText(new_nsubject);
                                                Score2.setText(new_noriginalScore + "점");
                                                sScore2.setText(new_nstandardScore + "점");
                                                Percent2.setText(new_npercentage + "");
                                                Grade2.setText(new_ngrade + "등급");
                                                count++;
                                            }
                                            if(count == 0){
                                                TestName1.setText(new_ntestName);
                                                SubjectName1.setText(new_nsubject);
                                                Score1.setText(new_noriginalScore + "점");
                                                sScore1.setText(new_nstandardScore + "점");
                                                Percent1.setText(new_npercentage + "");
                                                Grade1.setText(new_ngrade + "등급");
                                                count++;
                                            }
                                        }

                                        cursor1.moveToPrevious();
                                    }

                                }
                                if(items[which].equals("과학")){

                                    setSubjectButton.setText("과학");

                                    Cursor cursor1 = ndatabase.rawQuery("select _id, testName, subjectClassification, subject, originalScore, standardScore, grade, percentage from nation", null);
                                    int recordCount1 = cursor1.getCount();
                                    int count = 0;

                                    if(recordCount1 == 0){
                                        Toast.makeText(getContext(), "과학 성적을 한 개 이상 입력하세요.", Toast.LENGTH_LONG).show();
                                    }

                                    cursor1.moveToLast();

                                    for(int i=0; i<recordCount1; i++){

                                        int new_n_id = cursor1.getInt(0);
                                        String new_ntestName = cursor1.getString(1);
                                        String new_nsubjectClassification = cursor1.getString(2);
                                        String new_nsubject = cursor1.getString(3);
                                        int new_noriginalScore = cursor1.getInt(4);
                                        int new_nstandardScore = cursor1.getInt(5);
                                        int new_ngrade = cursor1.getInt(6);
                                        float new_npercentage = cursor1.getFloat(7);

                                        if(new_nsubjectClassification.equals("과학")){
                                            if(count == 4){
                                                break;
                                            }
                                            if(count == 3){
                                                TestName4.setText(new_ntestName);
                                                SubjectName4.setText(new_nsubject);
                                                Score4.setText(new_noriginalScore + "점");
                                                sScore4.setText(new_nstandardScore + "점");
                                                Percent4.setText(new_npercentage + "");
                                                Grade4.setText(new_ngrade + "등급");
                                                count++;
                                            }
                                            if(count == 2){
                                                TestName3.setText(new_ntestName);
                                                SubjectName3.setText(new_nsubject);
                                                Score3.setText(new_noriginalScore + "점");
                                                sScore3.setText(new_nstandardScore + "점");
                                                Percent3.setText(new_npercentage + "");
                                                Grade3.setText(new_ngrade + "등급");
                                                count++;
                                            }
                                            if(count == 1){
                                                TestName2.setText(new_ntestName);
                                                SubjectName2.setText(new_nsubject);
                                                Score2.setText(new_noriginalScore + "점");
                                                sScore2.setText(new_nstandardScore + "점");
                                                Percent2.setText(new_npercentage + "");
                                                Grade2.setText(new_ngrade + "등급");
                                                count++;
                                            }
                                            if(count == 0){
                                                TestName1.setText(new_ntestName);
                                                SubjectName1.setText(new_nsubject);
                                                Score1.setText(new_noriginalScore + "점");
                                                sScore1.setText(new_nstandardScore + "점");
                                                Percent1.setText(new_npercentage + "");
                                                Grade1.setText(new_ngrade + "등급");
                                                count++;
                                            }
                                        }

                                        cursor1.moveToPrevious();
                                    }

                                }
                                if(items[which].equals("기타")){

                                    setSubjectButton.setText("기타");

                                    Cursor cursor1 = ndatabase.rawQuery("select _id, testName, subjectClassification, subject, originalScore, standardScore, grade, percentage from nation", null);
                                    int recordCount1 = cursor1.getCount();
                                    int count = 0;

                                    if(recordCount1 == 0){
                                        Toast.makeText(getContext(), "기타과목 성적을 한 개 이상 입력하세요.", Toast.LENGTH_LONG).show();
                                    }

                                    cursor1.moveToLast();

                                    for(int i=0; i<recordCount1; i++){

                                        int new_n_id = cursor1.getInt(0);
                                        String new_ntestName = cursor1.getString(1);
                                        String new_nsubjectClassification = cursor1.getString(2);
                                        String new_nsubject = cursor1.getString(3);
                                        int new_noriginalScore = cursor1.getInt(4);
                                        int new_nstandardScore = cursor1.getInt(5);
                                        int new_ngrade = cursor1.getInt(6);
                                        float new_npercentage = cursor1.getFloat(7);

                                        if(new_nsubjectClassification.equals("제2외국어")){
                                            if(count == 4){
                                                break;
                                            }
                                            if(count == 3){
                                                TestName4.setText(new_ntestName);
                                                SubjectName4.setText(new_nsubject);
                                                Score4.setText(new_noriginalScore + "점");
                                                sScore4.setText(new_nstandardScore + "점");
                                                Percent4.setText(new_npercentage + "");
                                                Grade4.setText(new_ngrade + "등급");
                                                count++;
                                            }
                                            if(count == 2){
                                                TestName3.setText(new_ntestName);
                                                SubjectName3.setText(new_nsubject);
                                                Score3.setText(new_noriginalScore + "점");
                                                sScore3.setText(new_nstandardScore + "점");
                                                Percent3.setText(new_npercentage + "");
                                                Grade3.setText(new_ngrade + "등급");
                                                count++;
                                            }
                                            if(count == 1){
                                                TestName2.setText(new_ntestName);
                                                SubjectName2.setText(new_nsubject);
                                                Score2.setText(new_noriginalScore + "점");
                                                sScore2.setText(new_nstandardScore + "점");
                                                Percent2.setText(new_npercentage + "");
                                                Grade2.setText(new_ngrade + "등급");
                                                count++;
                                            }
                                            if(count == 0){
                                                TestName1.setText(new_ntestName);
                                                SubjectName1.setText(new_nsubject);
                                                Score1.setText(new_noriginalScore + "점");
                                                sScore1.setText(new_nstandardScore + "점");
                                                Percent1.setText(new_npercentage + "");
                                                Grade1.setText(new_ngrade + "등급");
                                                count++;
                                            }
                                        }

                                        cursor1.moveToPrevious();
                                    }

                                }


                            }
                        }).create().show();
            }
        });


        Button schoolGradeButton = rootView.findViewById(R.id.schoolGradeButton);
        schoolGradeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SchoolGradeActivity.class);

                startActivityForResult(intent, 101);
            }
        });



        Button nationGradeButton = rootView.findViewById(R.id.nationGradeButton);
        nationGradeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), NationGradeActivity.class);

                startActivityForResult(intent, 101);
            }
        });


        return rootView;
    }





}

package org.techtown.mystudyplanner2.etc.SchoolGrade;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.techtown.mystudyplanner2.R;

import java.util.ArrayList;

public class GradeAdapter extends RecyclerView.Adapter<GradeAdapter.ViewHolder>
implements OnGradeItemClickListener {
    ArrayList<Grade> items = new ArrayList<Grade>();
    OnGradeItemClickListener listener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.grade_item, viewGroup, false);

        return new ViewHolder(itemView,this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Grade item = items.get(position);
        viewHolder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Grade item){
        items.add(item);
    }

    public void setItems(ArrayList<Grade> items){
        this.items = items;
    }

    public Grade getItem(int position){
        return items.get(position);
    }

    public void setItem(int position, Grade item){
        items.set(position, item);
    }

    public void setOnItemClickListener(OnGradeItemClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onItemClick(ViewHolder holder, View view, int position){
        if(listener != null){
            listener.onItemClick(holder, view, position);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView semester;
        TextView subjectAndLearnTime;
        TextView score;
        TextView accomplishment;
        TextView grade;
        TextView average;
        TextView diviation;

        public ViewHolder(final View itemView, final OnGradeItemClickListener listener){
            super(itemView);

            semester = itemView.findViewById(R.id.semester);
            subjectAndLearnTime = itemView.findViewById(R.id.subjectAndLearnTime);
            score = itemView.findViewById(R.id.score);
            accomplishment = itemView.findViewById(R.id.accomplishment);
            grade = itemView.findViewById(R.id.grade);
            average = itemView.findViewById(R.id.average);
            diviation = itemView.findViewById(R.id.diviation);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    if(listener != null){
                        listener.onItemClick(ViewHolder.this, itemView, position);
                    }
                }
            });
        }

        public void setItem(Grade item){
            semester.setText(item.getSemester());
            subjectAndLearnTime.setText(item.getSubject() + " (" + item.getLearnTime() + ")");
            score.setText(item.getScore() + "점");
            accomplishment.setText(item.getAccomplishment());
            grade.setText(item.getGrade() + "등급");
            average.setText(item.getAverage() + "");
            diviation.setText(item.getDiviation() + "");
        }
    }
}

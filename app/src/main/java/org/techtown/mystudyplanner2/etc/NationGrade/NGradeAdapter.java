package org.techtown.mystudyplanner2.etc.NationGrade;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.techtown.mystudyplanner2.R;

import java.util.ArrayList;

public class NGradeAdapter extends RecyclerView.Adapter<NGradeAdapter.ViewHolder>
implements OnNGradeItemClickListener {
    ArrayList<NGrade> items = new ArrayList<NGrade>();
    OnNGradeItemClickListener listener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.ngrade_item, viewGroup, false);

        return new ViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        NGrade item = items.get(position);
        viewHolder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(NGrade item){
        items.add(item);
    }

    public void setItems(ArrayList<NGrade> items){
        this.items = items;
    }

    public NGrade getItem(int position){
        return items.get(position);
    }

    public void setItem(int position, NGrade item){
        items.set(position, item);
    }

    public void setOnItemClickListener(OnNGradeItemClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onItemClick(ViewHolder holder, View view, int position){
        if(listener != null){
            listener.onItemClick(holder, view, position);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView testName;
        TextView subject;
        TextView originalScore;
        TextView standardScore;
        TextView grade;
        TextView percentage;

        public ViewHolder(final View itemView, final OnNGradeItemClickListener listener){
            super(itemView);

            testName = itemView.findViewById(R.id.testName);
            subject = itemView.findViewById(R.id.subject);
            originalScore = itemView.findViewById(R.id.originalScore);
            standardScore = itemView.findViewById(R.id.standardScore);
            grade = itemView.findViewById(R.id.grade);
            percentage = itemView.findViewById(R.id.percentage);

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

        public void setItem(NGrade item){
            testName.setText(item.getTestName());
            subject.setText(item.getSubject());
            originalScore.setText(item.getOriginalScore() + "점");
            if(item.getStandardScore() == 0){
                standardScore.setText("null");
            }
            else {
                standardScore.setText(item.getStandardScore() + "점");
            }
            grade.setText(item.getGrade() + "등급");
            if(item.getPercentage() == 0){
                percentage.setText("null");
            }
            else {
                percentage.setText(item.getPercentage() + "");
            }
        }
    }
}

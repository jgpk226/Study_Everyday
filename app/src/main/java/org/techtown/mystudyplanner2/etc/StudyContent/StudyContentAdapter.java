package org.techtown.mystudyplanner2.etc.StudyContent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.techtown.mystudyplanner2.R;

import java.util.ArrayList;

public class StudyContentAdapter extends RecyclerView.Adapter<StudyContentAdapter.ViewHolder>
implements OnStudyContentClickListener {
    ArrayList<StudyContent> items = new ArrayList<StudyContent>();
    OnStudyContentClickListener listener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.studycontent_item, viewGroup, false);

        return new ViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        StudyContent item = items.get(position);
        viewHolder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(StudyContent item){
        items.add(item);
    }

    public void setItems(ArrayList<StudyContent> items){
        this.items = items;
    }

    public StudyContent getItem(int position){
        return items.get(position);
    }

    public void setItem(int positiion, StudyContent item){
        items.set(positiion, item);
    }


    // recyclerView click event
    public void setOnItemClickListener(OnStudyContentClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onItemClick(ViewHolder holder, View view, int position){
        if(listener != null){
            listener.onItemClick(holder, view, position);
        }
    }

    public  static class ViewHolder extends RecyclerView.ViewHolder{
        TextView subject;
        TextView studyContent;
        TextView time;

        public ViewHolder(final View itemView, final OnStudyContentClickListener listener){
            super(itemView);

            subject = itemView.findViewById(R.id.subject);
            studyContent = itemView.findViewById(R.id.studyContent);
            time = itemView.findViewById(R.id.time);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if(listener != null){
                        listener.onItemClick(ViewHolder.this, view, position);
                    }

                }
            });
        }

        public void setItem(StudyContent item){
            subject.setText(item.getSubject());
            studyContent.setText(item.getContent());
            time.setText(item.getHour() + "h " + item.getMin() + "m " + item.getSec() + "s");
        }
    }
}

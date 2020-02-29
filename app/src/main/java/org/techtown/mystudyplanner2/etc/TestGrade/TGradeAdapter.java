package org.techtown.mystudyplanner2.etc.TestGrade;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.techtown.mystudyplanner2.R;

import java.util.ArrayList;

public class TGradeAdapter extends RecyclerView.Adapter<TGradeAdapter.ViewHolder> implements OnTGradeItemClickListener{
    ArrayList<TGrade> items = new ArrayList<TGrade>();
    OnTGradeItemClickListener listener;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.testgrade_item, viewGroup, false);

        return new ViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        TGrade item = items.get(position);
        viewHolder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(TGrade item){
        items.add(item);
    }

    public void setItems(ArrayList<TGrade> items){
        this.items = items;
    }

    public TGrade getItem(int position){
        return items.get(position);
    }

    public void setItem(int position, TGrade item){
        items.set(position, item);
    }

    public void setOnItemClickListener(OnTGradeItemClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onItemClick(ViewHolder holder, View view, int position){
        if(listener != null){
            listener.onItemClick(holder, view, position);
        }
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        TextView textView2;
        TextView textView3;

        public ViewHolder(final View itemView, final OnTGradeItemClickListener listener){
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    if(listener != null){
                        listener.onItemClick(ViewHolder.this, v, position);
                    }
                }
            });

            textView = itemView.findViewById(R.id.textView);
            textView2 = itemView.findViewById(R.id.textView2);
            textView3 = itemView.findViewById(R.id.textView3);
        }

        public void setItem(TGrade item){
            textView.setText(item.getTestName() + "(" + item.getDate() + ")");
            textView2.setText(item.getSubject());
            textView3.setText(item.getScore() + " / " + item.getPerfect() +"점");
        }
    }
}

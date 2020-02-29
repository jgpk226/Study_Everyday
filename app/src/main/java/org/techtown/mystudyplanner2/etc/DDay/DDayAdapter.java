package org.techtown.mystudyplanner2.etc.DDay;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.techtown.mystudyplanner2.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class DDayAdapter extends RecyclerView.Adapter<DDayAdapter.ViewHolder> implements OnDDayClickListener{
    ArrayList<DDay> items = new ArrayList<DDay>();
    OnDDayClickListener listener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.dday_item, viewGroup, false);

        return new ViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        DDay item = items.get(position);
        viewHolder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(DDay item){
        items.add(item);
    }

    public void setItems(ArrayList<DDay> items){
        this.items = items;
    }

    public DDay getItem(int position){
        return items.get(position);
    }

    public void setItem(int position, DDay item){
        items.set(position, item);
    }

    public void setOnItemClickListener(OnDDayClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onItemClick(ViewHolder holder, View view, int position){
        if(listener != null){
            listener.onItemClick(holder, view, position);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView nameText;
        TextView dateText;
        TextView ddayText;

        public ViewHolder(final View itemView, final OnDDayClickListener listener){
            super(itemView);

            nameText = itemView.findViewById(R.id.nameText);
            dateText = itemView.findViewById(R.id.dateText);
            ddayText = itemView.findViewById(R.id.ddayText);

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

        public void setItem(DDay item){

            nameText.setText(item.getName());
            dateText.setText(item.getYear() % 100 + "." + item.getMonth() + "." + item.getDay() + ".");

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
            Date date = new Date();
            String currentDate = formatter.format(date);

            String ddayDate = item.getYear() + "-" + item.getMonth() + "-" + item.getDay();

            try{ // String Type을 Date Type으로 캐스팅하면서 생기는 예외로 인해 여기서 예외처리 해주지 않으면 컴파일러에서 에러가 발생해서 컴파일을 할 수 없다.
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                // date1, date2 두 날짜를 parse()를 통해 Date형으로 변환.
                Date FirstDate = format.parse(ddayDate);
                Date SecondDate = format.parse(currentDate);

                // Date로 변환된 두 날짜를 계산한 뒤 그 리턴값으로 long type 변수를 초기화 하고 있다.
                // 연산결과 -950400000. long type 으로 return 된다.
                long calDate = FirstDate.getTime() - SecondDate.getTime();

                // Date.getTime() 은 해당날짜를 기준으로1970년 00:00:00 부터 몇 초가 흘렀는지를 반환해준다.
                // 이제 24*60*60*1000(각 시간값에 따른 차이점) 을 나눠주면 일수가 나온다.
                long calDateDays = calDate / ( 24*60*60*1000);

                calDateDays = Math.abs(calDateDays);


                if(calDate < 0){
                    ddayText.setText("D + " + calDateDays);
                }
                else if(calDate == 0){
                    ddayText.setText("D - Day");
                }
                else if(calDate > 0){
                    ddayText.setText("D - " + calDateDays);
                }

            }
            catch(ParseException e)
            {
                // 예외 처리
            }



        }
    }
}

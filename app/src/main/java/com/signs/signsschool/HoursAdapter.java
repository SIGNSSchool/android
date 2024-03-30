package com.signs.signsschool;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.signs.signsschool.models.ModelDate;
import com.signs.signsschool.models.Hours;

import java.util.ArrayList;
import java.util.List;

public class HoursAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context activityContext;
    private final List<Hours> list;
    private final List<ModelDate> dateList;
    private final ArrayList<Hours> arrayList;
    private final ArrayList<ModelDate> dateArrayList;
    //public static final String TAG = "ListView";


    public HoursAdapter(Context context, List<Hours> list, List<ModelDate> dateList){
        this.activityContext = context;
        this.list = list;
        this.dateList = dateList;
        this.arrayList = (ArrayList<Hours>) list;
        this.dateArrayList = (ArrayList<ModelDate>) dateList;
    }

    int num;



    @Override
    public int getItemViewType(int position) {

        if ((position == 0) || (position == 11) || (position == 22) || (position == 33) || (position == 44) || (position == 55) || (position == 66) || (position == 77) || (position == 88) || (position == 99) || (position == 110) || (position == 121) || (position == 132) || (position == 143) || (position == 154) || (position == 165)) {
            num =  1;
        } else {

            num = 0;
        }

        return num;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch (viewType) {

            case 0:

                return new ViewHolder0(LayoutInflater.from(parent.getContext()).inflate(R.layout.hourscell, parent, false));


            case 1:

                return new ViewHolder1(LayoutInflater.from(parent.getContext()).inflate(R.layout.datecell, parent, false));

        }
        return null;
    }





    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        Hours recyclerData = arrayList.get(position);


       switch (holder.getItemViewType()) {

           case 0:

            ViewHolder0 viewHolder = (ViewHolder0) holder;

            viewHolder.SubjectTV.setText(recyclerData.getSubject());

            viewHolder.itemView.setOnClickListener(viewHolder);

            System.out.println("position" + position);


            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(activityContext, StundenPopOver.class);
                    intent.putExtra("Subject", recyclerData.getSubject());
                    intent.putExtra("Room", recyclerData.getRoom());
                    intent.putExtra("Day", recyclerData.getDay());
                    intent.putExtra("Hour", recyclerData.getHour());
                    intent.putExtra("Date", recyclerData.getDate());
                    intent.putExtra("Teacher", recyclerData.getTeachername());


                    System.out.println("clicked" + recyclerData.getSubject());

                    //if ((!Objects.equals(model.getSubject(), "blank")) && (!Objects.equals(model.getSubject(), " "))) {

                        activityContext.startActivity(intent);
                    //}
                }
            });

            break;

            case 1:

                ModelDate dateData = dateArrayList.get(position);

            ViewHolder1 viewHolder1 = (ViewHolder1) holder;
            viewHolder1.DateTV.setText(dateData.getDate());

            break;

        }

    }




    @Override
    public int getItemCount() {
        return arrayList.size();
    }



    class ViewHolder0 extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView SubjectTV;

        public ViewHolder0(@NonNull View itemView) {
            super(itemView);

            SubjectTV = itemView.findViewById(R.id.hoursSubjectTextView);

        }

        @Override
        public void onClick(View view) {

        }
    }

    class ViewHolder1 extends RecyclerView.ViewHolder {

        TextView DateTV;

        public ViewHolder1(@NonNull View itemView) {
            super(itemView);

            DateTV = itemView.findViewById(R.id.dateTVhours);

        }
    }

}

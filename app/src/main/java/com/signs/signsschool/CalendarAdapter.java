package com.signs.signsschool;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.signs.signsschool.models.Calendar;

import java.util.ArrayList;
import java.util.List;

public class CalendarAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context activityContext;
    private final List<Calendar> list;
    ArrayList<Integer> indexes = new ArrayList<>();
    ArrayList<Integer> selectedDates = new ArrayList<>();

    public CalendarAdapter(Context context, List<Calendar> list) {
        this.activityContext = context;
        this.list = list;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.datecell_calender, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {


        ViewHolder viewHolder = (ViewHolder) holder;

        Calendar model = list.get(position);

        viewHolder.date.setText(String.valueOf(model.getDate()));

        viewHolder.itemView.setOnClickListener(viewHolder);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Integer index = indexes.indexOf(holder.getAdapterPosition());


                if (viewHolder.date.getCurrentTextColor() == Color.BLUE) {
                    indexes.remove(index);
                    selectedDates.remove(model.getDate());

                     viewHolder.date.setTextColor(Color.BLACK);
                     viewHolder.date.setTextSize(20);
                } else if (viewHolder.date.getCurrentTextColor() == Color.BLACK) {
                    indexes.add(index);
                    selectedDates.add(model.getDate());

                    viewHolder.date.setTextColor(Color.BLUE);
                    viewHolder.date.setTextSize(25);

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        View background;
        TextView date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            background = itemView.findViewById(R.id.backgroundCalendar);
            date = itemView.findViewById(R.id.dateTextViewCalender);
        }
        @Override
        public void onClick(View view) {
        }
    }
}

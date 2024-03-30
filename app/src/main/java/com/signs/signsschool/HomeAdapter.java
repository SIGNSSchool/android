package com.signs.signsschool;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Filterable;
import android.widget.ListView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.signs.signsschool.absences.RecentAbsences;
import com.signs.signsschool.absences.Absences;
import com.signs.signsschool.absences.FehlstundenS;
import com.signs.signsschool.models.Home;
import com.signs.signsschool.news.ModelNews;
import com.signs.signsschool.news.News;
import com.signs.signsschool.news.NewsHomeAdapter;
import com.signs.signsschool.news.NewsService;
import com.signs.signsschool.signs.ListAdapterSigns;
import com.signs.signsschool.signs.Signs;
import com.signs.signsschool.signs.SignsModel;

import java.util.ArrayList;
import java.util.Objects;

public class HomeAdapter extends ArrayAdapter<Home> implements Filterable {

    private final Context activityContext;
    private final ArrayList<Home> list;
    ArrayList<SignsModel> signs_list;
    ArrayList<ModelNews> news_list;
    SharedPreferences preferences;

    public HomeAdapter(Context context, ArrayList<Home> list) {
        super(context, R.layout.fehlstundensrow, list);
        this.activityContext = context;
        this.list = list;

        NewsService.getNews(context);
        news_list = NewsService.news;

        SignsService.getSigns(context);
        signs_list = SignsService.signs;

      }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        final HomeAdapter.ViewHolder viewHolder;


        preferences = PreferenceManager.getDefaultSharedPreferences(activityContext);
        String account = preferences.getString("account", "error");


        switch (list.get(position).feature) {
            case "news":

                if (account.equals("School")) {
                    view = LayoutInflater.from(activityContext).inflate(R.layout.news_home_school_widget, null);
                    viewHolder = new HomeAdapter.ViewHolder();

                } else {
                    view = LayoutInflater.from(activityContext).inflate(R.layout.news_home_widget, null);
                    viewHolder = new HomeAdapter.ViewHolder();
                    viewHolder.recyclerView = view.findViewById(R.id.recyclerViewNewsHome);

                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(activityContext, 1, GridLayoutManager.HORIZONTAL, false);
                    viewHolder.recyclerView.setLayoutManager(layoutManager);

                    NewsHomeAdapter adapter = new NewsHomeAdapter(activityContext, news_list);
                    viewHolder.recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                    viewHolder.recyclerView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            activityContext.startActivity(new Intent(activityContext, News.class));

                        }
                    });
                }

                view.setTag(viewHolder);

                break;
            case "aufgaben":
                view = LayoutInflater.from(activityContext).inflate(R.layout.home_aufgaben_widget, null);
                viewHolder = new HomeAdapter.ViewHolder();
                view.setTag(viewHolder);

                break;
            case "stunden":
                view = LayoutInflater.from(activityContext).inflate(R.layout.home_stunden_widget, null);
                viewHolder = new HomeAdapter.ViewHolder();
                view.setTag(viewHolder);

                break;
            case "signs":
                view = LayoutInflater.from(activityContext).inflate(R.layout.home_signs_widget, null);
                viewHolder = new HomeAdapter.ViewHolder();
                viewHolder.listView = view.findViewById(R.id.listView_home_signs);

                ListAdapterSigns signsAdapter = new ListAdapterSigns(activityContext, signs_list);
                viewHolder.listView.setAdapter(signsAdapter);

                if (!signs_list.isEmpty()) {
                    signsAdapter.notifyDataSetChanged();
                }

                viewHolder.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        activityContext.startActivity(new Intent(activityContext, Signs.class));
                    }
                });
                view.setTag(viewHolder);

                break;
            case "rooms":
                view = LayoutInflater.from(activityContext).inflate(R.layout.home_rooms_widget, null);
                viewHolder = new HomeAdapter.ViewHolder();
                viewHolder.primaryButton = view.findViewById(R.id.home_rooms_reserve_button);

                viewHolder.primaryButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        activityContext.startActivity(new Intent(activityContext, Rooms.class));
                    }
                });
                view.setTag(viewHolder);

                break;
            case "fehlstunden":
                view = LayoutInflater.from(activityContext).inflate(R.layout.home_fehlstunden_widget, null);
                viewHolder = new HomeAdapter.ViewHolder();

                viewHolder.secondaryButton = view.findViewById(R.id.home_fehlstunden_first_button);
                viewHolder.primaryButton = view.findViewById(R.id.home_fehlstunden_second_button);

                viewHolder.primaryButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (account) {
                            case "Student":
                                activityContext.startActivity(new Intent(activityContext, TokenK.class));
                                break;
                            case "Teacher":
                                activityContext.startActivity(new Intent(activityContext, RecentAbsences.class));
                                break;
                        }
                    }
                });
                viewHolder.secondaryButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (account) {
                            case "Student":
                                activityContext.startActivity(new Intent(activityContext, FehlstundenS.class));
                                break;
                            case "Teacher":
                            case "School":
                                activityContext.startActivity(new Intent(activityContext, Absences.class));
                                break;
                        }
                    }
                });

                switch (account) {
                    case "Student":
                        viewHolder.primaryButton.setText("Krankmelden");
                        viewHolder.secondaryButton.setText("Aktuelle Fehlstunden");
                        break;
                    case "Teacher":
                        viewHolder.primaryButton.setText("Aktuelle Fehlstunden");
                        viewHolder.secondaryButton.setText("Einsicht");
                        break;
                    case "School":
                        viewHolder.primaryButton.setVisibility(View.GONE);
                        viewHolder.primaryButton.setEnabled(false);
                        viewHolder.secondaryButton.setText("Alle Fehlstunden");
                        break;
                }

                view.setTag(viewHolder);

                break;
            case "kurse":
                view = LayoutInflater.from(activityContext).inflate(R.layout.home_courses_widget, null);
                viewHolder = new HomeAdapter.ViewHolder();
                viewHolder.primaryButton = view.findViewById(R.id.home_courses_show_button);

                viewHolder.primaryButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(activityContext, Courses.class);
                        intent.putExtra("Grade", "Fünfter");
                        activityContext.startActivity(intent);
                    }
                });


                view.setTag(viewHolder);

                break;
        }

        return view;
    }

    private static class ViewHolder {
        ListView listView;
        Button primaryButton;
        Button secondaryButton;
        RecyclerView recyclerView;
    }
}

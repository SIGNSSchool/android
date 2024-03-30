package com.signs.signsschool;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.signs.signsschool.absences.Absences;
import com.signs.signsschool.absences.RecentAbsences;
import com.signs.signsschool.absences.FehlstundenS;
import com.signs.signsschool.assignments.AufgabenEvaluations;
import com.signs.signsschool.assignments.Learning;
import com.signs.signsschool.common.Common;
import com.signs.signsschool.models.Course;
import com.signs.signsschool.news.News;
import com.signs.signsschool.news.NewsSubmissions;
import com.signs.signsschool.signs.Signs;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class Home extends AppCompatActivity {
    TextView greetingLabel, statusLabel;
    ImageButton homeButton, idButton, settingsButton;
    ListView listView;
    SharedPreferences preferences;
    String account;
    ArrayList<String> list = new ArrayList();
    HomeService homeService = new HomeService();

    public static void setTimeout(Runnable runnable, int delay) {
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            } catch (Exception e) {
                System.err.println(e);
            }
        }).start();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        idButton = findViewById(R.id.home_idButton);
        settingsButton = findViewById(R.id.home_settingsButton);
        listView = findViewById(R.id.home_listView);
        account = preferences.getString("account", "error");

        greetingLabel = findViewById(R.id.home_greetingLabel);
        statusLabel = findViewById(R.id.home_statusLabel);

        Common.getCoursesByTeacherId(this);
        ArrayList<Course> courses = new Common().courses;

        /*String check = preferences.getString("check", "error");
        if (!Objects.equals(check, "GOOD")) {
            startActivity(new Intent(Home.this, Login.class));
        }*/
        checkBlocked();

        statusLabel.setVisibility(View.GONE);
        HomeAdapter adapterHome = new HomeAdapter(listView.getContext(), styleByAccount());
        listView.setAdapter(adapterHome);

        setTimeout(() -> adapterHome.notifyDataSetChanged(), 1000);

        UserService.storeToken(Home.this);
        displayGreeting(preferences.getString("firstName", ""));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // change on account (also in cell), etc.,
                System.out.println("position" + position + "account" + account);

                switch (account) {
                    case "Student":
                        switch (position) {
                            case 0:
                                startActivity(new Intent(Home.this, News.class));
                                break;
                            case 1:
                                startActivity(new Intent(Home.this, Learning.class));
                                break;
                            case 2:
                                startActivity(new Intent(Home.this, hours.class));
                                break;
                            case 3:
                                startActivity(new Intent(Home.this, Signs.class));
                                break;
                            case 4:
                                startActivity(new Intent(Home.this, FehlstundenS.class));
                                break;
                        }

                        break;
                    case "Teacher":
                        switch (position) {
                            case 0:
                                Intent intent = new Intent(Home.this, AufgabenEvaluations.class);
                                if (courses.get(0).getCourseId() != null) {
                                    intent.putExtra("courseId", courses.get(0).getCourseId());
                                }
                                startActivity(intent);
                                break;
                            case 1:
                                startActivity(new Intent(Home.this, hours.class));
                                break;
                            case 2:
                                startActivity(new Intent(Home.this, Signs.class));
                                break;
                            case 3:
                                startActivity(new Intent(Home.this, Rooms.class));
                                break;
                            case 4:
                                Intent cIntent = new Intent(Home.this, Courses.class);
                                cIntent.putExtra("Grade", "Fünfter");
                                startActivity(cIntent);
                                break;
                            case 5:
                                startActivity(new Intent(Home.this, RecentAbsences.class));
                                break;
                        }

                        break;
                    case "School":
                        switch (position) {
                            case 0:
                                startActivity(new Intent(Home.this, NewsSubmissions.class));
                                break;
                            case 1:
                                startActivity(new Intent(Home.this, hoursEdit.class));
                                break;
                            case 2:
                                startActivity(new Intent(Home.this, Signs.class));
                                break;
                            case 3:

                                break;
                            case 4:
                                startActivity(new Intent(Home.this, Absences.class));

                                break;
                        }

                        break;
                    case "Parent":
                        startActivity(new Intent(Home.this, Signs.class));
                        break;
                }
            }
        });

        idButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, newID.class));
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, Account.class));
            }
        });
    }

    public void checkBlocked() {
        if (Objects.equals(preferences.getBoolean("activated", true), true)) {
            listView.setVisibility(View.VISIBLE);
            statusLabel.setVisibility(View.INVISIBLE);
            idButton.setVisibility(View.VISIBLE);
            idButton.setEnabled(true);
        } else {
            listView.setVisibility(View.INVISIBLE);
            statusLabel.setVisibility(View.VISIBLE);
            idButton.setVisibility(View.INVISIBLE);
            idButton.setEnabled(false);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void displayGreeting(String name) {
        int hour = ZonedDateTime.now(ZoneId.of("Europe/Berlin")).getHour();
        System.out.println("time is" + hour);
        if (3 < hour && hour < 12) {
            greetingLabel.setText("Guten Morgen");
        } else if (12 < hour && hour < 15) {
            greetingLabel.setText("Guten Mittag");
        } else if (15 < hour && hour < 18) {
            greetingLabel.setText("Guten Nachmittag");
        } else if (18 < hour && hour < 24) {
            greetingLabel.setText("Guten Abend");
        } else {
            greetingLabel.setText("Hallo, " + name);
        }
    }

    public ArrayList<com.signs.signsschool.models.Home> styleByAccount() {
        ArrayList<com.signs.signsschool.models.Home> result = new ArrayList<>();

        System.out.println("styling with: " + account);
        switch (account) {
            case "Student":
                result.add(new com.signs.signsschool.models.Home("news"));
                result.add(new com.signs.signsschool.models.Home("aufgaben"));
                result.add(new com.signs.signsschool.models.Home("stunden"));
                result.add(new com.signs.signsschool.models.Home("signs"));
                result.add(new com.signs.signsschool.models.Home("fehlstunden"));

                return result;
            case "Teacher":
                result.add(new com.signs.signsschool.models.Home("aufgaben"));
                result.add(new com.signs.signsschool.models.Home("stunden"));
                result.add(new com.signs.signsschool.models.Home("signs"));
                result.add(new com.signs.signsschool.models.Home("rooms"));
                result.add(new com.signs.signsschool.models.Home("fehlstunden"));

                return result;

            case "School":
                result.add(new com.signs.signsschool.models.Home("news"));
                result.add(new com.signs.signsschool.models.Home("stunden"));
                result.add(new com.signs.signsschool.models.Home("signs"));
                //result.add(new ModelHome("rooms"));
                result.add(new com.signs.signsschool.models.Home("fehlstunden"));
                result.add(new com.signs.signsschool.models.Home("kurse"));

                return result;
            case "Parent":
                result.add(new com.signs.signsschool.models.Home("signs"));

                return result;
        }

        return result;
    }
}


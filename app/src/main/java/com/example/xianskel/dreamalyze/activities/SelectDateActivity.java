package com.example.xianskel.dreamalyze.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import com.example.xianskel.dreamalyze.pojos.Dream;
import com.example.xianskel.dreamalyze.R;

import java.util.Calendar;

public class SelectDateActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private CalendarView calendar;
    private Button select_date_button;
    private Calendar c = Calendar.getInstance();
    private int selectedYear = c.get(Calendar.YEAR);
    private String selectedMonth = "0"+(c.get(Calendar.MONTH)+1);
    private int selectedDay = c.get(Calendar.DATE);
    private String date = selectedDay+"/"+selectedMonth+"/"+selectedYear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_date);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        calendar = (CalendarView) findViewById(R.id.calendarView);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view,
                                            int year, int month, int dayOfMonth) {
                selectedYear = year;
                selectedMonth = String.valueOf(month+=1);
                if(Integer.parseInt(selectedMonth)<10){
                    selectedMonth = "0"+month;
                }
                selectedDay = dayOfMonth;
            }
        });
        select_date_button = (Button) findViewById(R.id.select_date_btn);

        select_date_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                date = selectedDay+"/"+selectedMonth+"/"+selectedYear;

                // Start NewActivity.class
                Intent myIntent = new Intent(SelectDateActivity.this,
                        DateDreamActivity.class);
                myIntent.putExtra("date", date);
                startActivity(myIntent);
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public  boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.action_contact){
            // Start NewActivity.class
            Intent myIntent = new Intent(SelectDateActivity.this,
                    ContactActivity.class);
            startActivity(myIntent);
        }
        else if(id == R.id.action_about){
            // Start NewActivity.class
            Intent myIntent = new Intent(SelectDateActivity.this,
                    AboutActivity.class);
            startActivity(myIntent);
        }
        else if(id == R.id.action_clear_logs){
            Context context = getApplicationContext();
            Dream.clearAllDreams(context);
            Toast.makeText(getApplicationContext(), "All Dreams have been deleted", Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }

}
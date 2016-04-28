package com.example.xianskel.dreamalyze.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.xianskel.dreamalyze.pojos.Dream;
import com.example.xianskel.dreamalyze.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.data.Entry;
import java.util.ArrayList;

public class DateDreamActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private String dreamText;

    private PieChart chart;
    private float[] amounts = {5,10,20,30,35};
    private String[] labels = {"Angry", "Sad", "Nervous", "Happy", "Anxious"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_dream_info);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        Bundle b = getIntent().getExtras();
        String date = b.getString("date");
        System.out.println(date);

        Context context = getApplicationContext();
        dreamText = Dream.getDreamByDate(date, context);

        TextView dreamTextView = (TextView)findViewById(R.id.dream_text);

        dreamTextView.setText(dreamText);

        chart = (PieChart) findViewById(R.id.day_chart);

        chart.setUsePercentValues(true);
        chart.setDrawHoleEnabled(false);
        chart.setRotationEnabled(true);
        chart.setDescription("Moods");
        chart.setDescriptionColor(Color.WHITE);
        chart.setDescriptionTextSize(15f);


        chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int i, Highlight h) {

                if (e == null)
                    return;

                Toast.makeText(DateDreamActivity.this, labels[e.getXIndex()] + " = " + e.getVal() + "%", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });

        addData();

        Legend l = chart.getLegend();
        l.setPosition(Legend.LegendPosition.ABOVE_CHART_CENTER);
        l.setTextColor(Color.WHITE);
        l.setTextSize(15f);
        l.setForm(Legend.LegendForm.CIRCLE);
    }

    private void addData(){
        ArrayList<Entry> amounts1 = new ArrayList<Entry>();

        for(int i=0; i < amounts.length; i++){
            amounts1.add(new Entry(amounts[i], i));
        }

        ArrayList<String> labels1 = new ArrayList<String>();

        for(int i=0; i < labels.length; i++){
            labels1.add(labels[i]);
        }

        PieDataSet dataSet = new PieDataSet(amounts1, "");
        dataSet.setSliceSpace(2);

        ArrayList<Integer> colors = new ArrayList<Integer>();
        for(int c : ColorTemplate.VORDIPLOM_COLORS){
            colors.add(c);
        }

        dataSet.setColors(colors);

        PieData data = new PieData(labels1, dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(13f);
        data.setValueTextColor(Color.BLACK);

        chart.setData(data);

        chart.highlightValues(null);
        chart.invalidate();
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
            Intent myIntent = new Intent(DateDreamActivity.this,
                    ContactActivity.class);
            startActivity(myIntent);
        }
        else if(id == R.id.action_about){
            // Start NewActivity.class
            Intent myIntent = new Intent(DateDreamActivity.this,
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

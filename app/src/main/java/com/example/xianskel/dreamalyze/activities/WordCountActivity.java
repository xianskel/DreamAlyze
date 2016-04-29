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
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WordCountActivity extends AppCompatActivity{

    private Toolbar toolbar;
    private BarChart chart;
    private Context context;
    private List<Map.Entry<String, Integer>> wordCount;
    private ArrayList<Integer> amounts = new ArrayList<>();
    private ArrayList<String> labels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_count);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        context = getApplicationContext();
        wordCount = Dream.wordCount(context);

        TextView dreamTextView1 = (TextView)findViewById(R.id.dreamGraphText1);
        String topDreams1 = "";
        for(int i=0; i<Math.min(wordCount.size(), 5); i++){
             topDreams1 += wordCount.get(i).getKey()+"  "+wordCount.get(i).getValue()+"\n";
        }
        dreamTextView1.setText(topDreams1);

        TextView dreamTextView2 = (TextView)findViewById(R.id.dreamGraphText2);
        String topDreams2 = "";
        for(int i=5; i<Math.min(wordCount.size(), 10); i++){
            topDreams2 += wordCount.get(i).getKey()+"  "+wordCount.get(i).getValue()+"\n";
        }
        dreamTextView2.setText(topDreams2);

        TextView dreamTextView3 = (TextView)findViewById(R.id.dreamGraphText3);
        String topDreams3 = "";
        for(int i=10; i<Math.min(wordCount.size(), 15); i++){
            topDreams3 += wordCount.get(i).getKey()+"  "+wordCount.get(i).getValue()+"\n";
        }
        dreamTextView3.setText(topDreams3);

        for(int i=0; i<Math.min(wordCount.size(), 5); i++){
            labels.add(wordCount.get(i).getKey());
            amounts.add(wordCount.get(i).getValue());
        }

        chart = (BarChart) findViewById(R.id.chart);

        chart.setDescription("");
        chart.getLegend().setEnabled(false);
        chart.getXAxis().setTextColor(Color.WHITE);
        chart.getXAxis().setTextSize(10f);
        chart.getAxisLeft().setEnabled(false);
        chart.getAxisRight().setTextColor(Color.WHITE);

        addData();
        chart.invalidate();
    }

    private void addData(){
        List<BarEntry> amounts1 = new ArrayList<>();

        for(int i=0; i < amounts.size(); i++){
            amounts1.add(new BarEntry(amounts.get(i), i));
        }

        ArrayList<String> labels1 = new ArrayList<>();

        for(int i=0; i < labels.size(); i++){
            labels1.add(labels.get(i));
        }

        BarDataSet dataSet = new BarDataSet(amounts1, "");

        ArrayList<Integer> colors = new ArrayList<>();
        for(int c : ColorTemplate.VORDIPLOM_COLORS){
            colors.add(c);
        }

        dataSet.setColors(colors);

        BarData data = new BarData(labels1, dataSet);
        data.setValueTextSize(13f);
        data.setValueTextColor(Color.WHITE);


        chart.setData(data);
        chart.highlightValues(null);
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
            Intent myIntent = new Intent(WordCountActivity.this,
                    ContactActivity.class);
            startActivity(myIntent);
        }
        else if(id == R.id.action_about){
            // Start NewActivity.class
            Intent myIntent = new Intent(WordCountActivity.this,
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

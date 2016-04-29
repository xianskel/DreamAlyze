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
import com.example.xianskel.dreamalyze.model.API;
import com.example.xianskel.dreamalyze.model.Callback;
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
<<<<<<< HEAD
<<<<<<< HEAD
import org.json.JSONArray;
import org.json.JSONException;
=======
=======
>>>>>>> origin/master

<<<<<<< HEAD
=======
import org.json.JSONArray;
import org.json.JSONException;
>>>>>>> 7317a7f740d0d6e8ce3d08be9889f417b9620450
<<<<<<< HEAD
>>>>>>> origin/master
=======
>>>>>>> origin/master
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
<<<<<<< HEAD
<<<<<<< HEAD
=======
<<<<<<< HEAD
import java.util.Map;
=======
>>>>>>> 7317a7f740d0d6e8ce3d08be9889f417b9620450
>>>>>>> origin/master
=======
import java.util.Map;
=======
>>>>>>> 7317a7f740d0d6e8ce3d08be9889f417b9620450
>>>>>>> origin/master

public class DateDreamActivity extends AppCompatActivity{

    private Toolbar toolbar;
    private String dreamText;
    private PieChart chart;
    private JSONObject response;
    private ArrayList<Float> amounts = new ArrayList<>();
    private ArrayList<String> labels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_dream_info);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        Bundle b = getIntent().getExtras();
        String date = b.getString("date");

        TextView dateText = (TextView)findViewById(R.id.date);
        dateText.setText("Date of dream: "+date);

        Context context = getApplicationContext();
        dreamText = Dream.getDreamByDate(date, context);

        TextView dreamTextView = (TextView)findViewById(R.id.dream_text);

        dreamTextView.setText(dreamText);

        context = getApplicationContext();
<<<<<<< HEAD
        API.makeRequest(context, date, new Callback() {
            @Override
            public void onSuccess(JSONObject newresponse) {
                response = newresponse;
                List<String> catLabels = new ArrayList<>();
                List<Double> scores = new ArrayList<>();


                try {
                    JSONArray categoryLabels = (JSONArray) response.get("categories");

                    for (int i = 0; i < categoryLabels.length(); i++) {
                        JSONObject singlecat = (JSONObject) categoryLabels.get(i);
                        catLabels.add((String)singlecat.get("label"));
                        scores.add((Double)singlecat.get("score"));
                    }
                    for(int i=0; i<Math.min(catLabels.size(), 5); i++){
                        labels.add(catLabels.get(i));
                        double d = scores.get(i);
                        float f = (float) d;
                        amounts.add(f);
                    }
                    addData();
                    chart.invalidate();
                } catch (JSONException j) {
                    j.printStackTrace();
                }
            }
        });
=======
        response = API.makeRequest(context, "classify/iab-qag");

<<<<<<< HEAD
        wordCount = Dream.wordCount(context);

       for(int i=0; i<5; i++){
            labels[i] = wordCount.get(i).getKey();
            amounts[i] = wordCount.get(i).getValue();
       }

        System.out.println(wordCount.get(0).getKey());
        System.out.println(wordCount);
=======
        List<String> catLabels = new ArrayList<>();

       try{
            //store categories in a JSON array
           System.out.println(response.get("text"));
           JSONArray categoryLabels = (JSONArray)response.get("categories");

            for(int i = 0; i < categoryLabels.length(); i++){
                JSONObject singlecat = (JSONObject)categoryLabels.get(i);
                catLabels.add((String)singlecat.get("label"));
            }
        }
        catch(JSONException j){
            j.printStackTrace();
        }
>>>>>>> 7317a7f740d0d6e8ce3d08be9889f417b9620450
        System.out.println(response);

        //System.out.println(catLabels);
<<<<<<< HEAD
>>>>>>> origin/master
=======
>>>>>>> origin/master

        chart = (PieChart) findViewById(R.id.day_chart);

        chart.setUsePercentValues(true);
        chart.setDrawHoleEnabled(false);
        chart.setRotationEnabled(true);
        chart.setDescription("Dream Topics");
        chart.setDescriptionColor(Color.WHITE);
        chart.setDescriptionTextSize(15f);


        chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int i, Highlight h) {

                if (e == null)
                    return;

                Toast.makeText(DateDreamActivity.this, labels.get(e.getXIndex()) + " = " + e.getVal() + "%", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });

        Legend l = chart.getLegend();
        l.setPosition(Legend.LegendPosition.ABOVE_CHART_CENTER);
        l.setTextColor(Color.WHITE);
        l.setTextSize(15f);
        l.setForm(Legend.LegendForm.CIRCLE);


    }

    private void addData(){
        ArrayList<Entry> amounts1 = new ArrayList<Entry>();

        for(int i=0; i < amounts.size(); i++){
            amounts1.add(new Entry(amounts.get(i), i));
        }

        ArrayList<String> labels1 = new ArrayList<String>();

        for(int i=0; i < labels.size(); i++){
            labels1.add(labels.get(i));
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
       // chart.invalidate();
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

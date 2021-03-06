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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xianskel.dreamalyze.pojos.Dream;
import com.example.xianskel.dreamalyze.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RecordDreamActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Button record_dream_button;
    private TextView dreamText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_dream);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        record_dream_button = (Button) findViewById(R.id.record_dream_btn);
        dreamText = (EditText)findViewById(R.id.dream_text);

        // Capture button clicks
        record_dream_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Calendar c = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                final String formattedDate = df.format(c.getTime());
                String dream = dreamText.getText().toString();

                if(dream.length()<140){
                    Toast.makeText(getApplicationContext(), "Dreams must be at least 140 characters", Toast.LENGTH_LONG).show();
                }

                else if(dream.length()>500){
                    Toast.makeText(getApplicationContext(), "Dreams must be shorter than 500 characters", Toast.LENGTH_LONG).show();
                }

                else{
                    Context context = getApplicationContext();
                    try{
                        Dream.addDream(formattedDate, dream, context);
                        Toast.makeText(getApplicationContext(), "Dream added", Toast.LENGTH_LONG).show();
                        System.out.println("Dream:" + dream);
                        System.out.println(formattedDate);

                        // Start NewActivity.class
                        Intent myIntent = new Intent(RecordDreamActivity.this,
                                DateDreamActivity.class);
                        myIntent.putExtra("date", formattedDate);
                        startActivity(myIntent);


                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }

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
            Intent myIntent = new Intent(RecordDreamActivity.this,
                    ContactActivity.class);
            startActivity(myIntent);
        }
        else if(id == R.id.action_about){
            // Start NewActivity.class
            Intent myIntent = new Intent(RecordDreamActivity.this,
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
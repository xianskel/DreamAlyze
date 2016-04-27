package com.example.xianskel.dreamalyze;

//API KEY IS c9708cb2f12b7e678d807d443ba61ca4b6890145

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Button record_view_button;
    private Button view_logs_button;
    private Button dream_graph_view_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        // Locate the button in activity_main.xml
        record_view_button = (Button) findViewById(R.id.record_dream_view_btn);
        view_logs_button = (Button) findViewById(R.id.view_logs_btn);
        dream_graph_view_button = (Button) findViewById(R.id.dream_graph_view_btn);


        // Capture button clicks
        record_view_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(MainActivity.this,
                        RecordDreamActivity.class);
                startActivity(myIntent);
            }
        });

        view_logs_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(MainActivity.this,
                        SelectDateActivity.class);
                startActivity(myIntent);
            }
        });

        dream_graph_view_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(MainActivity.this,
                        DreamGraphActivity.class);
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
           Intent myIntent = new Intent(MainActivity.this,
                   ContactActivity.class);
           startActivity(myIntent);
       }
       else if(id == R.id.action_about){
           // Start NewActivity.class
           Intent myIntent = new Intent(MainActivity.this,
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

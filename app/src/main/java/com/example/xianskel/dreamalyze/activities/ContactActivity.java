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
import android.widget.Toast;

import com.example.xianskel.dreamalyze.pojos.Dream;
import com.example.xianskel.dreamalyze.R;

public class ContactActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Button submit_contact_button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        submit_contact_button = (Button) findViewById(R.id.submit_contact_btn);

        submit_contact_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent contactIntent = new Intent(ContactActivity.this,
                        MainActivity.class);

                startActivity(contactIntent);
                Toast.makeText(getApplicationContext(), "Your Message has been Submitted", Toast.LENGTH_LONG).show();

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
            Intent myIntent = new Intent(ContactActivity.this,
                    ContactActivity.class);
            startActivity(myIntent);
        }
        else if(id == R.id.action_about){
            // Start NewActivity.class
            Intent myIntent = new Intent(ContactActivity.this,
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
package com.example.simonescaboro.passwords;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Arrays;

public class StartActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mButton1;
    private Button mButton2;
    private Button mButton3;
    private Button mButton4;
    private TextView ctii;
    private TextView counter;
    private String combination = "";
    SharedPreferences shp;
    SharedPreferences.Editor shpEditor;
    private String password;
    int i = 0;

    public void snak()
    {
        Toast.makeText(this, "Wrong password", Toast.LENGTH_SHORT).show();
    }

    private void check()
    {
        if(combination.length() == password.length())
        {
            if (!combination.equals(password))
            {
                snak();
                combination = "";
                i = 0;
            }
            else
                openActivity();
        }
        else
            i++;
        counter.setText(combination);
    }
    private void openActivity() {
        startActivity(new Intent(StartActivity.this, MainActivity.class));
        finish();
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);

        ctii = (TextView) findViewById(R.id.ctii);
        shp = getSharedPreferences("myPreferences", MODE_PRIVATE);
        password = shp.getString("password", "0.00");

        counter = (TextView) findViewById(R.id.counter);
        mButton1 = (Button) findViewById(R.id.button1);
        mButton2 = (Button) findViewById(R.id.button2);
        mButton3 = (Button) findViewById(R.id.button3);
        mButton4 = (Button) findViewById(R.id.button4);

        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton3.setOnClickListener(this);
        mButton4.setOnClickListener(this);

    }
    @Override
    public void onClick (View v) {
        switch (v.getId()) {
            case R.id.button1:
                combination = combination + "1";
                check();
                break;
            case R.id.button2:
                combination = combination + "2";
                check();
                break;
            case R.id.button3:
                combination = combination + "3";
                check();
                break;
            case R.id.button4:
                combination = combination + "4";
                check();
                break;
        }
    }
}

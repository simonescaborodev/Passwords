package com.example.simonescaboro.passwords;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by simonescaboro on 14/09/16.
 */

public class SetFirstActivity extends Activity implements View.OnClickListener {
    private Button mButton1;
    private Button mButton2;
    private Button mButton3;
    private Button mButton4;
    private TextView insert;
    private Button reset;
    private Button save;
    String newPassword = "";
    SharedPreferences shp;
    SharedPreferences.Editor shpEditor;

    private void snak()
    {
        Toast.makeText(this,"Password Saved",Toast.LENGTH_SHORT).show();
    }

    private void null_string()
    {
        Toast.makeText(this,"Invalid password",Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.real_start_activity);


            mButton1 = (Button) findViewById(R.id.button1);
            mButton2 = (Button) findViewById(R.id.button2);
            mButton3 = (Button) findViewById(R.id.button3);
            mButton4 = (Button) findViewById(R.id.button4);
            insert = (TextView) findViewById(R.id.insert);
            reset = (Button) findViewById(R.id.reset);
            save = (Button) findViewById(R.id.save);
            mButton1.setOnClickListener(this);
            mButton2.setOnClickListener(this);
            mButton3.setOnClickListener(this);
            mButton4.setOnClickListener(this);

            reset.setOnClickListener(this);
            save.setOnClickListener(this);
        }
        @Override
        public void onClick (View v) {
            switch (v.getId()) {
                case R.id.button1:
                    newPassword = newPassword + "1";
                    insert.setText(newPassword);
                    break;
                case R.id.button2:
                    newPassword = newPassword + "2";
                    insert.setText(newPassword);
                    break;
                case R.id.button3:
                    newPassword = newPassword + "3";
                    insert.setText(newPassword);
                    break;
                case R.id.button4:
                    newPassword = newPassword + "4";
                    insert.setText(newPassword);
                    break;
                case R.id.save:
                    if(newPassword.equals(""))
                        null_string();
                    else
                    {
                        shp = getSharedPreferences("myPreferences", MODE_PRIVATE);
                        shpEditor  = shp.edit();
                        shpEditor.putString("password", newPassword);
                        shpEditor.commit();
                        snak();
                        startActivity(new Intent(SetFirstActivity.this, MainActivity.class));
                        shpEditor.putBoolean("first", true);
                        shpEditor.commit();
                    }
                    break;
                case R.id.reset:
                    newPassword = "";
                    insert.setText(newPassword);
                    break;
            }
        }


    }

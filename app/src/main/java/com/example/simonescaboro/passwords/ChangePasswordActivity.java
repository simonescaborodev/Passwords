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
 * Created by simonescaboro on 13/09/16.
 */

public class ChangePasswordActivity extends Activity implements View.OnClickListener {
    private Button mButton1;
    private Button mButton2;
    private Button mButton3;
    private Button mButton4;
    String newPassword = "";
    private TextView ct;
    private Button BtnSave;
    private TextView cti;
    SharedPreferences shp;
    SharedPreferences.Editor shpEditor;
    public void snak() {
//        Snackbar.make(this,"Wrong Password - Retry", Snackbar.LENGTH_SHORT);
        Toast.makeText(this, "Wrong password", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_password_layout);

        mButton1 = (Button) findViewById(R.id.button1);
        mButton2 = (Button) findViewById(R.id.button2);
        mButton3 = (Button) findViewById(R.id.button3);
        mButton4 = (Button) findViewById(R.id.button4);
        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton3.setOnClickListener(this);
        mButton4.setOnClickListener(this);
        cti = (TextView) findViewById(R.id.cti);
        ct = (TextView) findViewById(R.id.ct);
        BtnSave = (Button)  findViewById(R.id.add);
        BtnSave.setOnClickListener(this);
    }
    @Override
    public void onClick (View v) {
        switch (v.getId()) {
            case R.id.button1:
                newPassword = newPassword + "1";
                ct.setText(newPassword);
                break;
            case R.id.button2:
                newPassword = newPassword + "2";
                ct.setText(newPassword);
                break;
            case R.id.button3:
                newPassword = newPassword + "3";
                ct.setText(newPassword);
                break;
            case R.id.button4:
                newPassword = newPassword + "4";
                ct.setText(newPassword);
                break;
            case R.id.add:
                shp = getSharedPreferences("myPreferences", MODE_PRIVATE);
                shpEditor  = shp.edit();
                String name = shp.getString("password", "0.00");
                if(newPassword.equals(name))
                    startActivity(new Intent(ChangePasswordActivity.this, SetFirstActivity.class));
                else {
                    snak();
                    newPassword = "";
                }
//                shp = getSharedPreferences("myPreferences", MODE_PRIVATE);
//                shpEditor  = shp.edit();
//                shpEditor.putString("password",newPassword);
//                shpEditor.commit();
//                String name = shp.getString("password", "0.00");
//                cti.setText(name);
                break;
        }
    }
}

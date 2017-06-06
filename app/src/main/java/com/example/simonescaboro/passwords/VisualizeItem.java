package com.example.simonescaboro.passwords;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by simonescaboro on 27/10/16.
 */

public class VisualizeItem extends Activity {

    private SharedPreferences pass;
    private SharedPreferences.Editor passEdit;
    private TextView emailTextView;
    private TextView usernameTextView;
    private TextView passwordTextView;
    private TextView siteTextView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visualize_layout);

        pass = getSharedPreferences("myPassage", MODE_PRIVATE);
        passEdit  = pass.edit();
        String site = pass.getString("site", "0.00");
        String username = pass.getString("username", "0.00");
        String email = pass.getString("email", "0.00");
        String password = pass.getString("password", "0.00");
        setTitle(site);
        passwordTextView = (TextView) findViewById(R.id.vis_password);
        passwordTextView.setText(password);

        emailTextView = (TextView) findViewById(R.id.vis_email);
        emailTextView.setText(email);
        usernameTextView = (TextView) findViewById(R.id.vis_username);
        usernameTextView.setText(username);

        passEdit.commit();

    }
}

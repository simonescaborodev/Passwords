package com.example.simonescaboro.passwords;

/**
 * Created by simonescaboro on 04/09/16.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity implements OnClickListener {
    private Button addTodoBtn;
    private SQLController dbController;

    private EditText siteEditText;
    private EditText usernameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private TextInputLayout TLUserName;

    protected void weNeedSite() {
        Toast.makeText(this,"Insert valide site",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Add Record");

        setContentView(R.layout.add_layout);
        usernameEditText = (EditText) findViewById(R.id.usernamex);
        emailEditText = (EditText) findViewById(R.id.emailx);
        passwordEditText = (EditText) findViewById(R.id.passwordx);
        siteEditText = (EditText) findViewById(R.id.sitex);

        addTodoBtn = (Button) findViewById(R.id.add_button);
        dbController = new SQLController(this);
        dbController.open();

        addTodoBtn.setOnClickListener(this);
    }
        @Override
        public void onClick (View v){
            switch (v.getId()) {
                case R.id.add_button:

                    final String username = usernameEditText.getText().toString();
                    final String email = emailEditText.getText().toString();
                    final String password = passwordEditText.getText().toString();
                    final String site = siteEditText.getText().toString();
                    if(site.equals(""))
                    {
                        weNeedSite();
                    }
                    else {
                        dbController.insert(username, password, email, site);

                        Intent main = new Intent(AddActivity.this,
                                MainActivity.class)
                                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(main);
                    }
                    break;
                default:
                    break;
            }
        }
    }
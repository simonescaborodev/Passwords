package com.example.simonescaboro.passwords;

/**
 * Created by simonescaboro on 04/09/16.
 */

        import android.app.Activity;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.os.Bundle;
        import android.os.StrictMode;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.widget.EditText;
        import android.widget.ImageButton;
        import android.widget.TextView;

public class ModifyActivity extends Activity implements OnClickListener {
    private EditText usernameText, emailText, passwordText;
    private TextView siteText;
    private ImageButton updateBtn, saveBtn;
    private long _id;
    private SQLController dbController;
    SharedPreferences pass;

    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(),
                MainActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Modify Record");
        setContentView(R.layout.modify_layout);

        dbController = new SQLController(this);
        dbController.open();

        usernameText = (EditText) findViewById(R.id.username_edittext);
        emailText = (EditText) findViewById(R.id.email_edittext);
        passwordText = (EditText) findViewById(R.id.password_edittext);
        siteText = (TextView) findViewById(R.id.site_textview);
        updateBtn = (ImageButton) findViewById(R.id.button_edit);

        pass = getSharedPreferences("myPassage", MODE_PRIVATE);

        String id = pass.getString("id", "0.00");
        String site = pass.getString("site", "0.00");
        String username = pass.getString("username", "0.00");
        String email = pass.getString("email", "0.00");
        String password = pass.getString("password", "0.00");

        setTitle(site);

        _id = Long.parseLong(id);
        usernameText.setText(username);
        emailText.setText(email);
        passwordText.setText(password);
        siteText.setText(site);
        siteText.setTextSize(24);

        updateBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_edit:

                String username = usernameText.getText().toString();
                String email = emailText.getText().toString();
                String password = passwordText.getText().toString();

                dbController.update(_id, username, email, password);

                this.returnHome();
                break;
        }
    }

}
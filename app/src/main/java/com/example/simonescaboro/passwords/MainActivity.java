package com.example.simonescaboro.passwords;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SimpleCursorAdapter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public void cambio() {
        Intent add_mem = new Intent(this, AddActivity.class);
        startActivity(add_mem);
    }

    public void visualizzo() {
        Intent add_mem = new Intent(this, VisualizeItem.class);
        startActivity(add_mem);
    }

    public void modifica() {
        Intent add_mem = new Intent(this, ModifyActivity.class);
        startActivity(add_mem);
    }
    public void back() {
        Intent add_mem = new Intent(this, MainActivity.class);
        startActivity(add_mem);
    }

    SharedPreferences pass;
    SharedPreferences.Editor passEdit;


    private SQLController dbManager;

    private ListView listView;

    private SimpleCursorAdapter adapter;

    final String[] from = new String[] { DBHelper._ID,
            DBHelper.PSSW_USERNAME, DBHelper.PSSW_EMAIL, DBHelper.PSSW_PASSWORD, DBHelper.PSSW_SITE };

    final int[] to = new int[] { R.id.id, R.id.username, R.id.email, R.id.password, R.id.site };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_layout);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);



        dbManager = new SQLController(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();

        listView = (ListView) findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));

        adapter = new SimpleCursorAdapter(this, R.layout.item_layout, cursor, from, to, 0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);

        FloatingActionButton btnAdd = (FloatingActionButton) findViewById(R.id.fab);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cambio();
            }
        });


                // OnCLickListiner For List Items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {

                TextView idTextView = (TextView) view.findViewById(R.id.id);
                TextView usernameTextView = (TextView) view.findViewById(R.id.username);
                TextView passwordTextView = (TextView) view.findViewById(R.id.password);
                TextView emailTextVew = (TextView) view.findViewById(R.id.email);
                TextView siteTextView = (TextView) view.findViewById(R.id.site);

                String id = idTextView.getText().toString();
                String username = usernameTextView.getText().toString();
                String password = passwordTextView.getText().toString();
                String email = emailTextVew.getText().toString();
                String site = siteTextView.getText().toString();

                pass = getSharedPreferences("myPassage", MODE_PRIVATE);
                passEdit  = pass.edit();
                passEdit.putString("id",id);
                passEdit.putString("username", username);
                passEdit.putString("password", password);
                passEdit.putString("email", email);
                passEdit.putString("site", site);
                passEdit.commit();

                visualizzo();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long viewId) {


                TextView idTextView = (TextView) view.findViewById(R.id.id);
                TextView usernameTextView = (TextView) view.findViewById(R.id.username);
                TextView passwordTextView = (TextView) view.findViewById(R.id.password);
                TextView emailTextVew = (TextView) view.findViewById(R.id.email);
                TextView siteTextView = (TextView) view.findViewById(R.id.site);

                final String id = idTextView.getText().toString();
                String username = usernameTextView.getText().toString();
                String password = passwordTextView.getText().toString();
                String email = emailTextVew.getText().toString();
                String site = siteTextView.getText().toString();

//                Intent modify_intent = new Intent(getApplicationContext(), DelModActivity.class);
//                modify_intent.putExtra("site", site);
//                modify_intent.putExtra("username", username);
//                modify_intent.putExtra("password", password);
//                modify_intent.putExtra("email", email);
//                modify_intent.putExtra("id", id);

                pass = getSharedPreferences("myPassage", MODE_PRIVATE);
                passEdit  = pass.edit();
                passEdit.putString("id",id);
                passEdit.putString("username", username);
                passEdit.putString("password", password);
                passEdit.putString("email", email);
                passEdit.putString("site", site);
                passEdit.commit();

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dbManager.delete(Long.parseLong(id));
                        back();
                    }
                });
                builder.setNegativeButton("Modify", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        modifica();
                    }
                });

//                builder.setItems(R.array.action, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        if(which==0)
//                        {
//                            secDel.Delete();
//                            Intent intent = new Intent(getActivity(), MainActivity.class);
//                            startActivity(intent);
//                        }
//                        else
//                        {
//                            Intent intent = new Intent(getActivity(), ModifyActivity.class);
//                            startActivity(intent);
//                        }
//                    }
//                });
                builder.setTitle("Modify or delete " + site + "?");
                AlertDialog dialog = builder.create();
                dialog.show();
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(MainActivity.this, ChangePasswordActivity.class));
            return true;
        }
        if (id == R.id.action_info) {
            startActivity(new Intent(MainActivity.this, info.class));
        return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
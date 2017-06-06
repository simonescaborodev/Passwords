package com.example.simonescaboro.passwords;
        import android.app.Activity;
        import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.graphics.Color;
        import android.os.Build;
        import android.os.Bundle;
        import android.support.v4.view.PagerAdapter;
        import android.support.v4.view.ViewPager;
        import android.text.Html;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.view.Window;
        import android.view.WindowManager;
        import android.widget.Button;
        import android.widget.LinearLayout;
        import android.widget.TextView;

public class Start extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_layout);
        SharedPreferences shp;
        SharedPreferences.Editor shpEditor;

        shp = getSharedPreferences("myPreferences", MODE_PRIVATE);
        Boolean name = shp.getBoolean("first", false);

        // Checking for first time launch - before calling setContentView()
        if (name == false) {
            startActivity(new Intent(Start.this, WelcomeActivity.class));
            finish();
        }
        else
        {
            startActivity(new Intent(Start.this, StartActivity.class));
            finish();
        }
    }
}


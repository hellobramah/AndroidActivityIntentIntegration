package com.hellobramah.android_activity_intent_integration;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AboutALCActivity extends AppCompatActivity {
    private ActionBar aboutActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_alc);
        aboutActionBar=getSupportActionBar();
        aboutActionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3359DE")));

    }
}

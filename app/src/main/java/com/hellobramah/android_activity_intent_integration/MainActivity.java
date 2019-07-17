package com.hellobramah.android_activity_intent_integration;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button buttonAbout, buttonProfile;
    private ActionBar mainActionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActionBar=getSupportActionBar();
        mainActionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3359DE")));
        buttonAbout = (Button) findViewById(R.id.btnAbout);
        buttonAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openActivityProfile();
            }
        });
        buttonProfile = (Button) findViewById(R.id.btnProfile);
        buttonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityAbout();
            }
        });
    }
    public void openActivityAbout() {
        Intent intent = new Intent(this, AboutALCActivity.class);
        startActivity(intent);
    }

    public void openActivityProfile() {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }
}

package com.smartcity.commonbase.widget.pagestatus.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(false);
        getSupportActionBar().setTitle("PageStatusHelper");
        setContentView(R.layout.activity_main);
    }


    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_simple1:

                startActivity(new Intent(this, SimpleActivity.class));
                break;
            case R.id.btn_simple2:

                startActivity(new Intent(this, SinglePageMoreStateActivity.class));
                break;
            case R.id.btn_simple3:

                startActivity(new Intent(this, Custom1Activity.class));
                break;

            case R.id.btn_simple6:

                startActivity(new Intent(this, CoordinatorLayoutActivity.class));
                break;
        }

    }

}

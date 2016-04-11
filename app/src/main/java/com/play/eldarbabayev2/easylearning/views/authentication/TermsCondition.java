package com.play.eldarbabayev2.easylearning.views.authentication;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.play.eldarbabayev2.easylearning.R;
import com.play.eldarbabayev2.easylearning.common.Utils;

import java.io.IOException;
import java.io.InputStream;

public class TermsCondition extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.terms_conditions);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_terms);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        TextView content = (TextView) findViewById(R.id.terms_and_conditions_text);
        TextView title = (TextView) toolbar.findViewById(R.id.toolbar_title);

        Utils.setTypefaceLight(content, this);
        Utils.setTypefaceMedium(title, this);


        readTextFromFile(content);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        finish();
        return true;
    }

    public void readTextFromFile(TextView content) {
        AssetManager assetManager = getAssets();
        InputStream input;
        try {
            input = assetManager.open("terms.txt");
            int size = input.available();
            byte[] buffer = new byte[size];
            input.read(buffer);
            input.close();
            String text = new String(buffer);
            content.setText(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeTerms(View v) {
        finish();
    }
}

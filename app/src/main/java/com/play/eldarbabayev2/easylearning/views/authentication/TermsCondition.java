package com.play.eldarbabayev2.easylearning.views.authentication;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.play.eldarbabayev2.easylearning.R;
import com.play.eldarbabayev2.easylearning.common.Utils;

import java.io.IOException;
import java.io.InputStream;

public class TermsCondition extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.terms_conditions);

        TextView content = (TextView) findViewById(R.id.terms_and_conditions_text);
        TextView head = (TextView) findViewById(R.id.terms_heading);

        Utils.setTypefaceLight(content, this);
        Utils.setTypefaceMedium(head, this);

        readTextFromFile(content);
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

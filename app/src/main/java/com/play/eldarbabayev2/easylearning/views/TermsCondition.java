package com.play.eldarbabayev2.easylearning.views;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.play.eldarbabayev2.easylearning.R;

import java.io.IOException;
import java.io.InputStream;

public class TermsCondition extends Activity {

    private TextView mtext;
    private TextView mtexthead;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.terms_conditions);

        AssetManager assetManager = getAssets();

        Typeface typeFacebold = Typeface.createFromAsset(getAssets(), "fonts/AvenirLTStd-Medium.ttf");
        Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/AvenirLTStd-Light.ttf");

        mtext = (TextView) findViewById(R.id.terms_and_conditions_text);
        mtexthead = (TextView) findViewById(R.id.terms_heading);

        mtext.setTypeface(typeFace);
        mtexthead.setTypeface(typeFacebold);

        InputStream input;
        try {
            input = assetManager.open("terms.txt");
            int size = input.available();
            byte[] buffer = new byte[size];
            input.read(buffer);
            input.close();
            String text = new String(buffer);
            mtext.setText(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeTerms(View v) {
        finish();
    }
}

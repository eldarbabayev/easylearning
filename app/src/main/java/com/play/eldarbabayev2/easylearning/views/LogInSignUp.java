package com.play.eldarbabayev2.easylearning.views;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.play.eldarbabayev2.easylearning.R;
import com.play.eldarbabayev2.easylearning.common.GenericActivity;
import com.play.eldarbabayev2.easylearning.common.Utils;
import com.play.eldarbabayev2.easylearning.controllers.SignUpController;

import java.util.List;

public class LogInSignUp extends GenericActivity<SignUp,
        SignUpController,
        SignUpController> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in_sign_up);

        List<Integer> parametersList = Utils.getDisplayParameters(this);
        List<Float> displayParameters = Utils.displayParametersDP(this);

        Typeface typeFace= Typeface.createFromAsset(getAssets(), "fonts/AvenirLTStd-Medium.ttf");

        Button button = (Button) findViewById(R.id.log_in_button);
        Button button2 = (Button) findViewById(R.id.sign_up_button);

        button.setTypeface(typeFace);
        button2.setTypeface(typeFace);

        Drawable dr;
        ImageView img = (ImageView) findViewById(R.id.background_image);

        if (displayParameters.get(0) / displayParameters.get(1) > 1.62) {
            dr = getResources().getDrawable(R.drawable.image_small);
        }
        else {
            dr = getResources().getDrawable(R.drawable.image_large);
        }
        Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
        Drawable d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, parametersList.get(1), parametersList.get(0) - Utils.getStatusBarHeight(this), true));
        img.setImageDrawable(d);
    }

    public void LogInActivityCommand(View v) {
        Intent logInActivity = new Intent(LogInSignUp.this, LogIn.class);
        startActivity(logInActivity);
    }

    public void SignUpActivityCommand(View v) {
        Intent signUpActivity = new Intent(LogInSignUp.this, SignUp.class);
        startActivity(signUpActivity);
    }

}
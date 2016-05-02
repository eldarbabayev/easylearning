package com.play.eldarbabayev2.easylearning.views.main;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.play.eldarbabayev2.easylearning.R;
import com.play.eldarbabayev2.easylearning.common.GenericActivity;
import com.play.eldarbabayev2.easylearning.common.Utils;
import com.play.eldarbabayev2.easylearning.controllers.SignUpController;
import com.play.eldarbabayev2.easylearning.views.authentication.LogIn;
import com.play.eldarbabayev2.easylearning.views.authentication.SignUp;

import java.util.List;

public class Main extends GenericActivity<SignUp,
        SignUpController,
        SignUpController> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in_sign_up);

        setUpBackgroundImage();

        Button logInButton = (Button) findViewById(R.id.log_in_button);
        Button signUpButton = (Button) findViewById(R.id.sign_up_button);

        Utils.setTypefaceMedium(logInButton, signUpButton, this);

    }

    // Log in button onclick
    public void LogInActivityCommand(View v) {
        Intent logInActivity = new Intent(Main.this, LogIn.class);
        startActivity(logInActivity);
    }

    // Sign up button onclick
    public void SignUpActivityCommand(View v) {
        Intent signUpActivity = new Intent(Main.this, SignUp.class);
        startActivity(signUpActivity);
    }

    // scare background image and render
    private void setUpBackgroundImage() {

        List<Integer> parametersList = Utils.getDisplayParameters(this);
        List<Float> displayParameters = Utils.displayParametersDP(this);

        ImageView img = (ImageView) findViewById(R.id.background_image);

        Drawable dr;

        if (displayParameters.get(0) / displayParameters.get(1) > 1.62) {
            dr = getResources().getDrawable(R.drawable.small);
        }
        else {
            dr = getResources().getDrawable(R.drawable.large);
        }

        if (dr != null) {
            Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
            Drawable d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap,
                                                                                      parametersList.get(1),
                                                                                      parametersList.get(0) - Utils.getStatusBarHeight(this),
                                                                                      true));
            img.setImageDrawable(d);
        } else {
            Log.d(TAG, "drawable is null");
        }

    }

}
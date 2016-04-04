package com.play.eldarbabayev2.easylearning.views.authentication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.play.eldarbabayev2.easylearning.R;

import com.play.eldarbabayev2.easylearning.common.GenericActivity;
import com.play.eldarbabayev2.easylearning.common.Utils;
import com.play.eldarbabayev2.easylearning.controllers.LogInController;
import com.play.eldarbabayev2.easylearning.models.User;
import com.play.eldarbabayev2.easylearning.utils.Constants;
import com.play.eldarbabayev2.easylearning.views.search_classes.SearchClassesActivity;

public class LogIn extends GenericActivity<LogIn,
        LogInController,
        LogInController> {

    private Firebase ref;
    private EditText emailInput;
    private EditText passInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);

        // UI
        emailInput = (EditText) findViewById(R.id.email_log_in);
        passInput = (EditText) findViewById(R.id.password_log_in);
        TextView forgotPass = (TextView) findViewById(R.id.forgot_password);
        final Button logInButton = (Button) findViewById(R.id.log_in_button_inner);

        // Set avenir typeface
        Utils.setTypefaceMedium(logInButton, this);
        Utils.setTypefaceLight(emailInput, passInput, forgotPass, this);

        setUpInputForm();

        setUpPassForgot(forgotPass);

        ref = new Firebase(Constants.FIREBASE_URL);

        passInput.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View view, int keyCode, KeyEvent keyevent) {
                //If the keyevent is a key-down event on the "enter" button
                if ((keyevent.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    logInButton.performClick();
                    // Perform your action on key press here
                    // ...
                    return true;
                }
                return false;
            }
        });


        super.onCreate(LogInController.class,
                this);
    }

    private void setUpInputForm() {

        emailInput.setOnFocusChangeListener(
                new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (hasFocus) {
                            FrameLayout fm = (FrameLayout) findViewById(R.id.frame6);
                            fm.setBackgroundColor(getResources().getColor(R.color.log_in_pressed));
                        } else {
                            FrameLayout fm = (FrameLayout) findViewById(R.id.frame6);
                            fm.setBackgroundColor(getResources().getColor(R.color.log_in_pass));
                        }
                    }
                }
        );

        passInput.setOnFocusChangeListener(
                new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (hasFocus) {
                            FrameLayout fm = (FrameLayout) findViewById(R.id.frame7);
                            fm.setBackgroundColor(getResources().getColor(R.color.log_in_pressed));
                        } else {
                            FrameLayout fm = (FrameLayout) findViewById(R.id.frame7);
                            fm.setBackgroundColor(getResources().getColor(R.color.log_in_pass));
                        }
                    }
                }
        );

    }

    private void setUpPassForgot(TextView forgotPass) {

        SpannableString ss = new SpannableString("Forgot password?");
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                //open a fragment
            }
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
            }
        };

        ss.setSpan(clickableSpan, 0, 16, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        forgotPass.setText(ss);
        forgotPass.setMovementMethod(LinkMovementMethod.getInstance());
        forgotPass.setHighlightColor(Color.TRANSPARENT);

    }

    // log in on clicked. retrieve data, store in shared prefs and go to classes list.
    public void LogInButton(View v) {
        ref.authWithPassword(emailInput.getText().toString(), passInput.getText().toString(),
                new Firebase.AuthResultHandler() {
                    @Override
                    public void onAuthenticated(AuthData authData) {
                        Firebase childref = ref.child("users").child(Utils.escapeEmailAddress(emailInput.getText().toString()));

                        // Attach an listener to read the data at our posts reference
                        childref.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot snapshot) {

                                // get data from snapshot
                                String userFullname = snapshot.child("name").getValue().toString();
                                String userDateOfBirth = snapshot.child("birthday").getValue().toString();
                                String userGender = snapshot.child("gender").getValue().toString();
                                String userEmail = snapshot.child("email").getValue().toString();
                                String userCountry = snapshot.child("country").getValue().toString();


                                // put data into shared preferences
                                SharedPreferences prefs = getApplication().getSharedPreferences("UserPrefs", 0);
                                prefs.edit().putString("userFullname", userFullname).apply();
                                prefs.edit().putString("userDateOfBirth", userDateOfBirth).apply();
                                prefs.edit().putString("userGender", userGender).apply();
                                prefs.edit().putString("userEmail", userEmail).apply();
                                prefs.edit().putString("userCountry", userCountry).apply();

                            }

                            @Override
                            public void onCancelled(FirebaseError firebaseError) {
                                System.out.println("The read failed: " + firebaseError.getMessage());
                            }
                        });

                        // Go to search classes activity
                        Intent intent = new Intent(LogIn.this, SearchClassesActivity.class);
                        startActivity(intent);
                    }
                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {
                        System.out.println("Authentication failed: " + firebaseError.getMessage());
                    }
                });
    }

 }


package com.play.eldarbabayev2.easylearning.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.JsonReader;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Logger;
import com.firebase.client.ValueEventListener;
import com.google.gson.Gson;
import com.play.eldarbabayev2.easylearning.R;

import com.play.eldarbabayev2.easylearning.common.GenericActivity;
import com.play.eldarbabayev2.easylearning.common.Utils;
import com.play.eldarbabayev2.easylearning.controllers.LogInController;
import com.play.eldarbabayev2.easylearning.models.User;
import com.play.eldarbabayev2.easylearning.utils.Constants;

import java.io.IOException;

public class LogIn extends GenericActivity<LogIn,
        LogInController,
        LogInController> {

    private Firebase ref;

    private EditText email_input;
    private EditText pass_input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.log_in);

        Typeface typeFacebold = Typeface.createFromAsset(getAssets(), "fonts/AvenirLTStd-Medium.ttf");
        Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/AvenirLTStd-Light.ttf");

        Button button = (Button) findViewById(R.id.log_in_button_inner);
        button.setTypeface(typeFacebold);

        ref = new Firebase(Constants.FIREBASE_URL);


        email_input = (EditText) findViewById(R.id.email_log_in);
        pass_input = (EditText) findViewById(R.id.password_log_in);
        TextView forgot_pass = (TextView) findViewById(R.id.forgot_password);
        email_input.setTypeface(typeFace);
        pass_input.setTypeface(typeFace);
        forgot_pass.setTypeface(typeFace);
        email_input.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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

        pass_input.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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

        TextView textView = (TextView) findViewById(R.id.forgot_password);
        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setHighlightColor(Color.TRANSPARENT);

        super.onCreate(LogInController.class,
                this);
    }

    public void LogInButton(View v) {
        ref.authWithPassword(email_input.getText().toString(), pass_input.getText().toString(),
                            new Firebase.AuthResultHandler() {
                                @Override
                                public void onAuthenticated(AuthData authData) {
                                    Firebase newref = ref.child("userList").child(Utils.escapeEmailAddress(email_input.getText().toString()));

                                    // Attach an listener to read the data at our posts reference
                                    newref.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot snapshot) {
                                            Log.d(TAG, snapshot.getValue().toString());

                                            String userFullname = snapshot.child("userFullname").getValue().toString();
                                            String userDateOfBirth = snapshot.child("userDateOfBirth").getValue().toString();
                                            String userGender = snapshot.child("userGender").getValue().toString();
                                            String userEmail = snapshot.child("userEmail").getValue().toString();
                                            String userCountry = snapshot.child("userCountry").getValue().toString();

                                            User user = new User(userFullname,userDateOfBirth,userGender,userEmail,userCountry);
                                            SharedPreferences prefs = getApplication().getSharedPreferences("UserPrefs", 0);


                                                prefs.edit().putString("userFullname", user.getUserFullname()).apply();
                                                prefs.edit().putString("userDateOfBirth", user.getUserDateOfBirth()).apply();
                                                prefs.edit().putString("userGender", user.getUserGender()).apply();
                                                prefs.edit().putString("userEmail", user.getUserEmail()).apply();
                                                prefs.edit().putString("userCountry", user.getUserCountry()).apply();

                                        }

                                        @Override
                                        public void onCancelled(FirebaseError firebaseError) {
                                            System.out.println("The read failed: " + firebaseError.getMessage());
                                        }
                                    });

                                    Intent intent = new Intent(LogIn.this, GroupListActivity.class);
                                    startActivity(intent);
                                }
                                @Override
                                public void onAuthenticationError(FirebaseError firebaseError) {
                                }
                            });
    }
}


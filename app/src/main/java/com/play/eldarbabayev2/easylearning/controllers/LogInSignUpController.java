package com.play.eldarbabayev2.easylearning.controllers;

import com.play.eldarbabayev2.easylearning.common.GenericAsyncTask;
import com.play.eldarbabayev2.easylearning.common.GenericAsyncTaskOps;
import com.play.eldarbabayev2.easylearning.common.PresenterOps;
import com.play.eldarbabayev2.easylearning.views.LogInSignUp;
import com.play.eldarbabayev2.easylearning.views.SignUp;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

public class LogInSignUpController implements GenericAsyncTaskOps<ArrayList<Iterator>, Void, Integer>,
        PresenterOps<LogInSignUp> {

    private final static String TAG =
            LogInSignUpController.class.getCanonicalName();

    private WeakReference<LogInSignUp> mView;

    private GenericAsyncTask<ArrayList<Iterator>,
            Void,
            Integer,
            LogInSignUpController> mAsyncTask;

    public LogInSignUpController() {

    }

    @Override
    public void onCreate(LogInSignUp view) {
        mView = new WeakReference<>(view);
    }

    @Override
    public void onConfigurationChange(LogInSignUp view) {
        mView = new WeakReference<>(view);
    }

    @Override
    public void onDestroy(boolean isChangingConfigurations) {
        // No op.
    }

    @Override
    public Integer doInBackground(ArrayList<Iterator>... iterators) {

        return 0;
    }

    @Override
    public void onPostExecute(Integer integers) {

    }
}

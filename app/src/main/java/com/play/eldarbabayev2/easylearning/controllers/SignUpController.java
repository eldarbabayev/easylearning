package com.play.eldarbabayev2.easylearning.controllers;

import com.play.eldarbabayev2.easylearning.common.GenericAsyncTask;
import com.play.eldarbabayev2.easylearning.common.GenericAsyncTaskOps;
import com.play.eldarbabayev2.easylearning.common.PresenterOps;
import com.play.eldarbabayev2.easylearning.views.authentication.SignUp;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

public class SignUpController
        implements GenericAsyncTaskOps<ArrayList<Iterator>, Void, Integer>,
        PresenterOps<SignUp> {

    private final static String TAG =
            SignUpController.class.getCanonicalName();

    private WeakReference<SignUp> mView;

    private GenericAsyncTask<ArrayList<Iterator>,
            Void,
            Integer,
            SignUpController> mAsyncTask;

    public SignUpController() {

    }

    @Override
    public void onCreate(SignUp view) {
        mView = new WeakReference<>(view);
    }

    @Override
    public void onConfigurationChange(SignUp view) {
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



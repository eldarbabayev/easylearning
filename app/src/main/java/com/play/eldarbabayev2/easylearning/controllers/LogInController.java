package com.play.eldarbabayev2.easylearning.controllers;

import com.play.eldarbabayev2.easylearning.common.GenericAsyncTask;
import com.play.eldarbabayev2.easylearning.common.GenericAsyncTaskOps;
import com.play.eldarbabayev2.easylearning.common.PresenterOps;
import com.play.eldarbabayev2.easylearning.views.authentication.LogIn;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

public class LogInController implements GenericAsyncTaskOps<ArrayList<Iterator>, Void, Integer>,
        PresenterOps<LogIn> {

        private final static String TAG =
                LogInController.class.getCanonicalName();

        private WeakReference<LogIn> mView;

        private GenericAsyncTask<ArrayList<Iterator>,
                Void,
                Integer,
                LogInController> mAsyncTask;

        public LogInController() {

        }

        @Override
        public void onCreate(LogIn view) {
                mView = new WeakReference<>(view);
        }

        @Override
        public void onConfigurationChange(LogIn view) {
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

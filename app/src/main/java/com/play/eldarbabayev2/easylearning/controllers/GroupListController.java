package com.play.eldarbabayev2.easylearning.controllers;

import com.play.eldarbabayev2.easylearning.common.GenericAsyncTask;
import com.play.eldarbabayev2.easylearning.common.GenericAsyncTaskOps;
import com.play.eldarbabayev2.easylearning.common.PresenterOps;
import com.play.eldarbabayev2.easylearning.views.search_classes.SearchClassesActivity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

public class GroupListController implements GenericAsyncTaskOps<ArrayList<Iterator>, Void, Integer>,
        PresenterOps<SearchClassesActivity> {


    private final static String TAG =
            GroupListController.class.getCanonicalName();

    private WeakReference<SearchClassesActivity> mView;

    private GenericAsyncTask<ArrayList<Iterator>,
            Void,
            Integer,
            GroupListController> mAsyncTask;

    public GroupListController() {

    }

    @Override
    public void onCreate(SearchClassesActivity view) {
        mView = new WeakReference<>(view);
    }

    @Override
    public void onConfigurationChange(SearchClassesActivity view) {
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

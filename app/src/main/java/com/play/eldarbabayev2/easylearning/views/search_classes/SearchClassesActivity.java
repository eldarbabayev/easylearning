package com.play.eldarbabayev2.easylearning.views.search_classes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.firebase.client.Firebase;
import com.play.eldarbabayev2.easylearning.R;
import com.play.eldarbabayev2.easylearning.common.GenericActivity;
import com.play.eldarbabayev2.easylearning.controllers.GroupListController;
import com.play.eldarbabayev2.easylearning.models.Group;
import com.play.eldarbabayev2.easylearning.utils.Constants;
import com.play.eldarbabayev2.easylearning.views.classes.MessengerSwipe;
import com.play.eldarbabayev2.easylearning.views.search_classes.adapters.ActiveListAdapter;

public class SearchClassesActivity extends GenericActivity<SearchClassesActivity,
        GroupListController,
        GroupListController> {

    private ActiveListAdapter classesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_list);

        ListView listOfClasses = (ListView) findViewById(R.id.group_list);

        listOfClasses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SearchClassesActivity.this, MessengerSwipe.class);
                startActivity(intent);
            }
        });


        Firebase ref = new Firebase(Constants.FIREBASE_URL).child("groupList");

        classesAdapter = new ActiveListAdapter(this, Group.class, R.layout.group_item, ref);

        listOfClasses.setAdapter(classesAdapter);

        super.onCreate(GroupListController.class,
                this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        classesAdapter.cleanup();
    }
}
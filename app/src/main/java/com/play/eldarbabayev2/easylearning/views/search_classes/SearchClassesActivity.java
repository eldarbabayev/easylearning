package com.play.eldarbabayev2.easylearning.views.search_classes;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ServerValue;
import com.firebase.client.ValueEventListener;
import com.play.eldarbabayev2.easylearning.R;
import com.play.eldarbabayev2.easylearning.common.GenericActivity;
import com.play.eldarbabayev2.easylearning.common.Utils;
import com.play.eldarbabayev2.easylearning.controllers.GroupListController;
import com.play.eldarbabayev2.easylearning.models.Group;
import com.play.eldarbabayev2.easylearning.utils.Constants;
import com.play.eldarbabayev2.easylearning.views.classes.MessengerSwipe;
import com.play.eldarbabayev2.easylearning.views.main.Main;
import com.play.eldarbabayev2.easylearning.views.search_classes.adapters.ActiveListAdapter;
import com.play.eldarbabayev2.easylearning.views.search_classes.adapters.CustomAdapter;
import com.play.eldarbabayev2.easylearning.views.search_classes.adapters.OptionsAdapter;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SearchClassesActivity extends GenericActivity<SearchClassesActivity,
        GroupListController,
        GroupListController> {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ListView mDrawerList;
    private String[] mOptionsList;
    private RelativeLayout mDrawerLeft;
    private CharSequence mTitle;
    private TextView titleTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_list);

        final Firebase ref = new Firebase("https://easylearning.firebaseio.com");

        Button logOutButton = (Button) findViewById(R.id.log_out_button);
        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref.unauth();
                Intent intent = new Intent(SearchClassesActivity.this, Main.class);
                startActivity(intent);
            }
        });

        Utils.setTypefaceMedium(logOutButton, this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.search_group_toolbar);

        titleTextView = (TextView) toolbar.findViewById(R.id.search_classes_title);

        Utils.setTypefaceMedium(titleTextView, this);

        toolbar.setNavigationIcon(R.drawable.ic_action_menu);


        setSupportActionBar(toolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        mDrawerLeft = (RelativeLayout) findViewById(R.id.left_drawer);
        SharedPreferences prefs = getSharedPreferences("UserPrefs", 0);

        ((TextView) findViewById(R.id.left_drawer_full_name)).setText(prefs.getString("userFullname", null));
        ((TextView) findViewById(R.id.left_drawer_email)).setText(prefs.getString("userEmail", null));

        Utils.setTypefaceMedium(((TextView) findViewById(R.id.left_drawer_full_name)), this);
        Utils.setTypefaceMedium(((TextView) findViewById(R.id.left_drawer_email)), this);

        mOptionsList = getResources().getStringArray(R.array.options_array);
        mDrawerList = (ListView) findViewById(R.id.left_drawer_options);

        List<String> list = new ArrayList<>();
        list.add(mOptionsList[0]);
//        list.add(mOptionsList[1]);
        // set up the drawer's list view with items and click listener
        mDrawerList.setAdapter(new OptionsAdapter(this, list, R.layout.drawer_list_item));


        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        // enable ActionBar app icon to behave as action to toggle nav drawer
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                toolbar,               /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
        ) {
            public void onDrawerClosed(View view) {
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            selectItem(0);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle(mOptionsList[0]);
            }
        }

        super.onCreate(GroupListController.class,
                this);
    }

    private void selectItem(int position) {
        // update the main content by replacing fragments
        Fragment fragment = new CustomFragment();
        Bundle args = new Bundle();
        args.putInt(CustomFragment.ARG_OPTIONS_NUMBER, position);
        fragment.setArguments(args);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        // update selected item and title, then close the drawer
        mDrawerList.setItemChecked(position, true);
        setTitle(mOptionsList[position]);
        mDrawerLayout.closeDrawer(mDrawerLeft);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        titleTextView.setText(mTitle);
    }


    /* The click listner for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    /**
     * Fragment that appears in the "content_frame", shows a list of groups
     */
    public static class CustomFragment extends Fragment {
        public static final String ARG_OPTIONS_NUMBER = "options_number";

        private ActiveListAdapter classesAdapter;

        private CustomAdapter myGroupAdapter;
        public CustomFragment() {
            // Empty constructor required for fragment subclasses
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            final View rootView = inflater.inflate(R.layout.group_list, container, false);

            Firebase ref = new Firebase(Constants.FIREBASE_URL).child("groups");
            classesAdapter = new ActiveListAdapter(getActivity(), Group.class, R.layout.group_item, ref);

            int i = getArguments().getInt(ARG_OPTIONS_NUMBER);
            Log.d(TAG, " " + i);

            String option = getResources().getStringArray(R.array.options_array)[i];

            // What the user selects in navigation
            if (option.equals("Find Courses")) {
                // Populate list with groups
                ((ListView) rootView.findViewById(R.id.group_list)).setAdapter(classesAdapter);

                Log.d(TAG, "I am here");
                // When user chooses the group
                ((ListView) rootView.findViewById(R.id.group_list)).setOnItemClickListener(
                        new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(final AdapterView<?> parent, View view, final int position, long id) {

                                // Get the class id the user pressed
                                Firebase newref = classesAdapter.getRef(position);
                                final String groupKey = newref.getKey();

                                // Get the size of the class
                                final Firebase refSize = new Firebase(Constants.FIREBASE_URL).
                                        child("groups").
                                        child(groupKey);


                                refSize.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot snapshot) {
                                        final long currentSize = (long) snapshot.child("size").getValue();
                                        final String groupName = (String) snapshot.child("name").getValue();

                                            SharedPreferences prefs = getActivity().getSharedPreferences("UserPrefs", 0);
                                            String email = prefs.getString("userEmail", null);

                                            final Firebase ref = new Firebase(Constants.FIREBASE_URL).child("users").child(Utils.escapeEmailAddress(email)).child("groups").child(groupKey);
                                            final Firebase refMember = new Firebase(Constants.FIREBASE_URL).child("members").child(groupKey).child(Utils.escapeEmailAddress(email));

                                            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(DataSnapshot snapshot) {

                                                    if (snapshot.getValue() == null) {
                                                        Log.d(TAG, "I am Here2");

                                                        refSize.child("size").setValue(currentSize + 1);

                                                        // add a new group to user group list and go to class
                                                        ref.setValue(ServerValue.TIMESTAMP);

                                                        // add the user to the membership of this group
                                                        refMember.setValue(ServerValue.TIMESTAMP);

                                                        // go to class
                                                        Intent intent = new Intent(getActivity(), MessengerSwipe.class);
                                                        intent.putExtra("groupKey", groupKey);
                                                        intent.putExtra("groupName", groupName);
                                                        startActivity(intent);

                                                    } else {
                                                        // don't add the group to user since it exists, go to class
                                                        Log.d(TAG, "I am Here");
                                                        Intent intent = new Intent(getActivity(), MessengerSwipe.class);
                                                        intent.putExtra("groupKey", groupKey);
                                                        intent.putExtra("groupName", groupName);
                                                        startActivity(intent);
                                                    }

                                                }

                                                @Override
                                                public void onCancelled(FirebaseError firebaseError) {
                                                    System.out.println("The read failed: " + firebaseError.getMessage());
                                                }
                                            });

                                    }

                                    @Override
                                    public void onCancelled(FirebaseError firebaseError) {
                                        System.out.println("The read failed: " + firebaseError.getMessage());
                                    }
                                });

                            }
                        });

            }

            /*else if (option.equals("My Courses")) {

                // get current user's group list
                SharedPreferences prefs = getActivity().getSharedPreferences("UserPrefs", 0);
                String email = prefs.getString("userEmail", null);
                final Firebase groupsRef = new Firebase(Constants.FIREBASE_URL).child("users").child(Utils.escapeEmailAddress(email)).child("groups");

                final List<Group> userGroupList = new ArrayList<>();

                groupsRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            final String key = postSnapshot.getKey();

                            final Firebase groupsRef = new Firebase(Constants.FIREBASE_URL).child("groups").child(key);

                            groupsRef.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    String description = (String) dataSnapshot.child("description").getValue();
                                    String name = (String) dataSnapshot.child("name").getValue();
                                    String teacher = (String) dataSnapshot.child("teacher").getValue();



                                    userGroupList.add(new Group(name, teacher, description, key));

                                    if (getActivity() != null) {
                                        myGroupAdapter = new CustomAdapter(getActivity(), userGroupList, R.layout.group_item);

                                        ((ListView) rootView.findViewById(R.id.group_list)).setAdapter(myGroupAdapter);

                                        ((ListView) rootView.findViewById(R.id.group_list)).setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                Group group = (Group) parent.getItemAtPosition(position);
                                                String group_key = group.getGroupId();
                                                String group_name = group.getName();

                                                Intent intent = new Intent(getActivity(), MessengerSwipe.class);

                                                intent.putExtra("groupKey", group_key);
                                                intent.putExtra("groupName", group_name);
                                                startActivity(intent);
                                            }
                                        });

                                    } else {


                                    }


                                }

                                @Override
                                public void onCancelled(FirebaseError firebaseError) {

                                }
                            });

                        }
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });

            }*/


            return rootView;
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            classesAdapter.cleanup();
        }

    }

    @Override
    public void onBackPressed() {

    }
}
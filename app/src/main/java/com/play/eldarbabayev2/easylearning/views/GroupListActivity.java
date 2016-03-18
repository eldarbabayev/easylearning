package com.play.eldarbabayev2.easylearning.views;

import android.content.Intent;
import android.graphics.Typeface;
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
import com.play.eldarbabayev2.easylearning.views.ui.ActiveListAdapter;

public class GroupListActivity extends GenericActivity<GroupListActivity,
        GroupListController,
        GroupListController> {

   // private GroupDetailsDialogFragment df;
    private ActiveListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_list);

        Typeface typeFace= Typeface.createFromAsset(getAssets(), "fonts/AvenirLTStd-Medium.ttf");

        ListView grouplist = (ListView) findViewById(R.id.group_list);

       // df = new GroupDetailsDialogFragment();
        //Button button = (Button) findViewById(R.id.more_groups_button);

        grouplist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(GroupListActivity.this, MessengerSwipe.class);
                startActivity(intent);
            }
        });

       /** button.setTypeface(typeFace);
        button.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                            df.show(getFragmentManager(), "group details");
                                      }
                                  }
        );

        */
        Firebase ref = new Firebase(Constants.FIREBASE_URL).child("groupList");

        adapter = new ActiveListAdapter(this, Group.class, R.layout.group_item, ref);
        grouplist.setAdapter(adapter);

        super.onCreate(GroupListController.class,
                this);

    }

  /**  public static class GroupDetailsDialogFragment extends DialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            LayoutInflater inflater = getActivity().getLayoutInflater();
            builder.setView(inflater.inflate(R.layout.add_new_group, null))
                    .setPositiveButton("Add group", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            EditText title = (EditText) getDialog().findViewById(R.id.group_title_dialog);
                            String stringtitle = title.getText().toString();

                            EditText description = (EditText) getDialog().findViewById(R.id.group_description_dialog);
                            String descriptionstring = description.getText().toString();

                            EditText teacher = (EditText) getDialog().findViewById(R.id.group_teacher_dialog);
                            String teacherstring = teacher.getText().toString();

                            Firebase ref = new Firebase(Constants.FIREBASE_URL);

                            Firebase postRef = ref.child("groupList");

                            Map<String, Object> post = new HashMap<>();

                            post.put("groupName", stringtitle);
                            post.put("groupTeacher", teacherstring);
                            post.put("groupDescription", descriptionstring);
                            postRef.push().setValue(post);


                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            GroupDetailsDialogFragment.this.getDialog().cancel();
                        }
                    });
            return builder.create();
        }
    }*/

    @Override
    public void onDestroy() {
        super.onDestroy();
        adapter.cleanup();
    }
}
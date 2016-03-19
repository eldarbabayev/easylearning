package com.play.eldarbabayev2.easylearning.views.search_classes.adapters;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.Query;
import com.firebase.ui.FirebaseListAdapter;
import com.play.eldarbabayev2.easylearning.R;
import com.play.eldarbabayev2.easylearning.models.Group;

public class ActiveListAdapter extends FirebaseListAdapter<Group> {

    private Typeface typeFace;

    public ActiveListAdapter(Activity activity, Class<Group> modelClass, int modelLayout,
                             Query ref) {
        super(activity, modelClass, modelLayout, ref);
        this.mActivity = activity;
        typeFace = Typeface.createFromAsset(activity.getAssets(), "fonts/AvenirLTStd-Light.ttf");

    }


    @Override
    protected void populateView(View view, Group group) {

        TextView nametext = (TextView) view.findViewById(R.id.group_name_item);
        TextView descrtext = (TextView) view.findViewById(R.id.group_description_item);
        TextView teachertext = (TextView) view.findViewById(R.id.group_teacher_item);

        nametext.setText(group.getGroupName());
        descrtext.setText(group.getGroupDescription());
        teachertext.setText(group.getGroupTeacher());

        nametext.setTypeface(typeFace);
        descrtext.setTypeface(typeFace);
        teachertext.setTypeface(typeFace);

    }
}
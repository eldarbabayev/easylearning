package com.play.eldarbabayev2.easylearning.views.search_classes.adapters;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.play.eldarbabayev2.easylearning.R;
import com.play.eldarbabayev2.easylearning.models.Group;

import java.util.List;


public class CustomAdapter extends BaseAdapter {

    private Typeface typeFace;
    private Typeface typeFace_bold;
    private List<Group> groups;
    private LayoutInflater mInflater;
    private int mLayout;

    public CustomAdapter(Activity activity, List<Group> groups, int layout) {

        this.groups = groups;
        typeFace = Typeface.createFromAsset(activity.getAssets(), "fonts/AvenirLTStd-Light.ttf");
        typeFace_bold = Typeface.createFromAsset(activity.getAssets(), "fonts/AvenirLTStd-Medium.ttf");
        this.mLayout = layout;
        mInflater = activity.getLayoutInflater();


    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null) {
            view = mInflater.inflate(mLayout, viewGroup, false);
        }

        TextView nametext = (TextView) view.findViewById(R.id.group_name_item);
        TextView descrtext = (TextView) view.findViewById(R.id.group_description_item);
        TextView teachertext = (TextView) view.findViewById(R.id.group_teacher_item);


        nametext.setText(groups.get(i).getName());
        descrtext.setText(groups.get(i).getDescription());
        teachertext.setText(groups.get(i).getTeacher());

        nametext.setTypeface(typeFace_bold);
        descrtext.setTypeface(typeFace);
        teachertext.setTypeface(typeFace);

        return view;
    }

    @Override
    public int getCount() {
        return groups.size();
    }

    @Override
    public Object getItem(int i) {
        return groups.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

}

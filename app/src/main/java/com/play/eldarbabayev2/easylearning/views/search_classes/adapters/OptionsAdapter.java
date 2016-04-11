package com.play.eldarbabayev2.easylearning.views.search_classes.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.play.eldarbabayev2.easylearning.R;
import com.play.eldarbabayev2.easylearning.common.Utils;

import java.util.List;

public class OptionsAdapter extends BaseAdapter {

    private List<String> objects; // obviously don't use object, use whatever you really want
    private int mLayout;
    private LayoutInflater mInflater;

    private Typeface typeFace;

    public OptionsAdapter(Activity activity, List<String> objects, int layout) {
        this.objects = objects;
        typeFace = Typeface.createFromAsset(activity.getAssets(), "fonts/AvenirLTStd-Light.ttf");
        this.mLayout = layout;
        mInflater = activity.getLayoutInflater();

    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if (view == null) {
            view = mInflater.inflate(mLayout, viewGroup, false);
        }

        TextView optionItem = (TextView) view.findViewById(R.id.options);

        // set whatever typeface you want here as well
        optionItem.setTypeface(typeFace);
        optionItem.setText(objects.get(position));
        return optionItem;
    }
}

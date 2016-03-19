package com.play.eldarbabayev2.easylearning.views.classes.adapters;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

import com.firebase.client.Query;
import com.firebase.ui.FirebaseListAdapter;
import com.play.eldarbabayev2.easylearning.R;
import com.play.eldarbabayev2.easylearning.models.Chat;

public class MessengerListAdapter extends FirebaseListAdapter<Chat> {

    private String mUsername;

    public MessengerListAdapter(Activity activity, Class<Chat> modelClass, int modelLayout,
                           Query ref) {
        super(activity, modelClass, modelLayout, ref);
        this.mUsername = mUsername;
    }


    @Override
    protected void populateView(View view, Chat chat) {

        String author = chat.getAuthor();
        SharedPreferences prefs = mActivity.getSharedPreferences("UserPrefs", 0);

        TextView tv = (TextView) view.findViewById(R.id.messageitem);
        String username = prefs.getString("userFullname", null);
        Log.d("USERNAME ", author + username);
        LayoutParams lp = (LayoutParams) tv.getLayoutParams();
        Typeface typeFace = Typeface.createFromAsset(mActivity.getAssets(), "fonts/AvenirLTStd-Light.ttf");
        tv.setText(chat.getMessage());
        tv.setTypeface(typeFace);

        if (username.equals(author)) {
            lp.gravity = Gravity.RIGHT;

            tv.setBackgroundDrawable(ContextCompat.getDrawable(mActivity,R.drawable.chat_bubble_me_middle));

        } else {
            lp.gravity = Gravity.LEFT;
            tv.setBackgroundDrawable(ContextCompat.getDrawable(mActivity,R.drawable.chat_bubble_friend_middle));

        }

        tv.setLayoutParams(lp);
    }

}

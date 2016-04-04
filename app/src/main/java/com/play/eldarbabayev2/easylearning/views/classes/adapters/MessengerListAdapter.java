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
import com.play.eldarbabayev2.easylearning.common.PresenterOps;
import com.play.eldarbabayev2.easylearning.common.Utils;
import com.play.eldarbabayev2.easylearning.models.Chat;

public class MessengerListAdapter extends FirebaseListAdapter<Chat> {

    public MessengerListAdapter(Activity activity, Class<Chat> modelClass, int modelLayout,
                           Query ref) {
        super(activity, modelClass, modelLayout, ref);
    }


    @Override
    protected void populateView(View view, Chat chat) {
        SharedPreferences prefs = mActivity.getSharedPreferences("UserPrefs", 0);

        TextView userNameTextBox = (TextView) view.findViewById(R.id.user_name);

        TextView contentTextBox = (TextView) view.findViewById(R.id.user_message_content);

        String author = chat.getAuthor();
        String username = prefs.getString("userFullname", null);
        Typeface typeFace = Typeface.createFromAsset(mActivity.getAssets(), "fonts/AvenirLTStd-Light.ttf");
        userNameTextBox.setText(author);
        contentTextBox.setText(chat.getMessage());
        userNameTextBox.setTypeface(typeFace);
        contentTextBox.setTypeface(typeFace);

        if (username.equals(author)) {
            userNameTextBox.setTextColor(view.getResources().getColor(R.color.blue));
        } else {
            userNameTextBox.setTextColor(view.getResources().getColor(R.color.pink));

        }
    }

}

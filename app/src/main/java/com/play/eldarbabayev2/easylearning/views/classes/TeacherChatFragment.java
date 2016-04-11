package com.play.eldarbabayev2.easylearning.views.classes;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.firebase.client.Firebase;
import com.github.curioustechizen.ago.RelativeTimeTextView;
import com.play.eldarbabayev2.easylearning.R;
import com.play.eldarbabayev2.easylearning.models.Chat;
import com.play.eldarbabayev2.easylearning.utils.Constants;
import com.play.eldarbabayev2.easylearning.views.classes.adapters.MessengerListAdapter;

import java.util.Date;

public class TeacherChatFragment extends Fragment implements View.OnClickListener {

    protected Activity mActivity;

    private Firebase ref;
    private MessengerListAdapter adapter;
    private String groupKey;


    @Override
    public void onAttach(Activity act)
    {
        super.onAttach(act);
        mActivity = act;
    }

    @Override
    public void onCreate(Bundle saveInstanceState)
    {
        super.onCreate(saveInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle saveInstanceState)
    {
        super.onActivityCreated(saveInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.teacher_chat, container, false);
        ListView teacherMessenger = (ListView) view.findViewById(R.id.teacher_messages);


        Bundle args = getArguments();
        groupKey = args.getString("groupKey");

        ref = new Firebase(Constants.FIREBASE_URL).child("teacherMessages").child(groupKey);
        adapter = new MessengerListAdapter(mActivity, Chat.class, R.layout.teacher_message_items, ref);

        teacherMessenger.setAdapter(adapter);

        return view;
    }

    @Override
    public void onClick(View v)
    {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        adapter.cleanup();

    }
}

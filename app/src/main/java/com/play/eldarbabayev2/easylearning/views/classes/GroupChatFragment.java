package com.play.eldarbabayev2.easylearning.views.classes;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.firebase.client.Firebase;
import com.play.eldarbabayev2.easylearning.R;
import com.play.eldarbabayev2.easylearning.common.Utils;
import com.play.eldarbabayev2.easylearning.models.Chat;
import com.play.eldarbabayev2.easylearning.utils.Constants;
import com.play.eldarbabayev2.easylearning.views.classes.adapters.MessengerListAdapter;

public class GroupChatFragment extends Fragment implements View.OnClickListener {

    protected Activity mActivity;
    private Firebase ref;
    private MessengerListAdapter classMessagesAdapter;
    private Animation bottomDown;
    private ViewGroup hiddenPanel;
    private Animation bottomUp;
    private String mUsername;
    private String groupKey;


    @Override
    public void onAttach(Activity act) {
        super.onAttach(act);
        mActivity = act;
    }

    @Override
    public void onCreate(Bundle saveInstanceState)
    {
        super.onCreate(saveInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle saveInstanceState) {
        super.onActivityCreated(saveInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.group_chat, container, false);
        EditText message = (EditText) view.findViewById(R.id.send_message);
        Button button = (Button) view.findViewById(R.id.send_message_button);
        Utils.setTypefaceLight(message, getActivity());
        Utils.setTypefaceMedium(button, getActivity());
        Bundle args = getArguments();
        groupKey = args.getString("groupKey");

        ref = new Firebase(Constants.FIREBASE_URL).child("userMessages").child(groupKey);

        ListView classMessages = (ListView) view.findViewById(R.id.group_chat_list);
        classMessagesAdapter = new MessengerListAdapter(mActivity, Chat.class, R.layout.group_messages_item, ref);

        classMessages.setAdapter(classMessagesAdapter);

        hiddenPanel = (ViewGroup) view.findViewById(R.id.editable);

        // send message on enter or arrow click
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });

        setupUsername();

        return view;
    }

    @Override
    public void onClick(View v) {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        classMessagesAdapter.cleanup();
    }

    private void sendMessage() {
        EditText inputText = (EditText) getView().findViewById(R.id.send_message);
        String input = inputText.getText().toString();
        if (!input.equals("")) {
            Chat chat = new Chat(input, mUsername);
            ref.push().setValue(chat);
            inputText.setText("");
        }
    }

    private void setupUsername() {
        SharedPreferences prefs = getActivity().getSharedPreferences("UserPrefs", 0);
        mUsername = prefs.getString("userFullname", null);
    }
}
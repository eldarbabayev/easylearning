package com.play.eldarbabayev2.easylearning.views.classes;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.firebase.client.Firebase;
import com.play.eldarbabayev2.easylearning.R;
import com.play.eldarbabayev2.easylearning.models.Chat;
import com.play.eldarbabayev2.easylearning.utils.Constants;
import com.play.eldarbabayev2.easylearning.views.classes.adapters.MessengerListAdapter;

public class GroupChatFragment extends Fragment implements View.OnClickListener {

    protected Activity mActivity;
    private Firebase ref;
    private MessengerListAdapter adapter;
    private View view;
    private Animation bottomDown;
    private ViewGroup hiddenPanel;
    private Animation bottomUp;
    private String mUsername;

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

        view = inflater.inflate(R.layout.group_chat, container, false);

        Typeface typeFace = Typeface.createFromAsset(mActivity.getAssets(), "fonts/AvenirLTStd-Light.ttf");

        setupUsername();

        EditText sendmess = (EditText) view.findViewById(R.id.send_message);

        sendmess.setTypeface(typeFace);

        ListView groupmessenger = (ListView) view.findViewById(R.id.group_chat_list);

        ref = new Firebase(Constants.FIREBASE_URL).child("chatGroupList");

        adapter = new MessengerListAdapter(mActivity, Chat.class, R.layout.messenger_item, ref);

        groupmessenger.setAdapter(adapter);

        ImageButton button = (ImageButton) view.findViewById(R.id.send_message_button);

        //bottomUp = AnimationUtils.loadAnimation(getContext(),
      //          R.anim.bottom_up);

  //      bottomDown = AnimationUtils.loadAnimation(getContext(),
    //            R.anim.bottom_down);

        hiddenPanel = (ViewGroup) view.findViewById(R.id.editable);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });

        return view;
    }

    @Override
    public void onClick(View v)
    {
    }


    @Override
    public void onResume() {
        super.onResume();
        if (!getUserVisibleHint())
        {
            hiddenPanel.setVisibility(View.INVISIBLE);
        } else {
            hiddenPanel.startAnimation(bottomUp);
            hiddenPanel.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isResumed()) {
            onResume();
        } else if (!isVisibleToUser && isResumed()) {
            onResume();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        adapter.cleanup();
    }

    private void sendMessage() {
        EditText inputText = (EditText) getView().findViewById(R.id.send_message);
        String input = inputText.getText().toString();
        if (!input.equals("")) {
            Chat chat = new Chat(mUsername + ": " + input, mUsername);
            ref.push().setValue(chat);
            inputText.setText("");
        }
    }

    private void setupUsername() {
        SharedPreferences prefs = getActivity().getSharedPreferences("UserPrefs", 0);
        mUsername = prefs.getString("userFullname", null);
    }
}
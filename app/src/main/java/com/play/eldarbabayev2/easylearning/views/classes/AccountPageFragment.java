package com.play.eldarbabayev2.easylearning.views.classes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.play.eldarbabayev2.easylearning.R;
import com.play.eldarbabayev2.easylearning.common.Utils;
import com.play.eldarbabayev2.easylearning.views.search_classes.SearchClassesActivity;

public class AccountPageFragment extends Fragment implements View.OnClickListener {

    protected Activity mActivity;

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
        View view = inflater.inflate(R.layout.account_page, container, false);

        Button leaveClassButton = (Button) view.findViewById(R.id.account_log_out);

        Utils.setTypefaceMedium(leaveClassButton, getActivity());

        leaveClassButton.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Intent intent = new Intent(getActivity(), SearchClassesActivity.class);
                                                startActivity(intent);
                                            }
                                        }
        );
        return view;
    }

    @Override
    public void onClick(View v)
    {
    }
}

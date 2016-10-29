package cn.picksomething.drawlayouttest.Mission_cook;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cn.picksomething.drawlayouttest.R;

/**
 * Created by tt791_000 on 2016/7/15.
 */
public class Mission_cook  extends android.support.v4.app.Fragment {
    private Button cooking;
    private Button activity;
    private Button health;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mission_cooking, container, false);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        cooking = (Button) getView().findViewById(R.id.cookingButton);
        activity = (Button)  getView().findViewById(R.id.activityButton);
        health = (Button) getView(). findViewById(R.id.healthButton);

        cooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), Mission_cook_cooking.class);
                startActivity(intent);
            }
        });
        activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), Mission_cook_party.class);
                startActivity(intent);

            }
        });
        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), Mission_cook_health.class);
                startActivity(intent);

            }
        });

    }

}

package cn.picksomething.drawlayouttest.Mission_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cn.picksomething.drawlayouttest.LastPage.MissionProjectLast;
import cn.picksomething.drawlayouttest.R;



/**
 * Created by tt791_000 on 2016/7/6.
 */
public class Mission_project  extends android.support.v4.app.Fragment {
    private Button holiday;
    private Button cleaning;
    private Button party;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mission_project, container, false);
        return view;
    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        holiday = (Button)  getView().findViewById(R.id.holiday);
        cleaning = (Button)  getView().findViewById(R.id.cleaning);
        party = (Button)  getView().findViewById(R.id.party);
        holiday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),MissionProjectLastHoliday.class);
                Bundle bundle = new Bundle();
                bundle.putString("Mission_project", "P1");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        cleaning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),MissionProjectLast.class);
                Bundle bundle = new Bundle();
                bundle.putString("Mission_project", "P2");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        party.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),MissionProjectLast.class);
                Bundle bundle = new Bundle();
                bundle.putString("Mission_project", "P3");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }
}

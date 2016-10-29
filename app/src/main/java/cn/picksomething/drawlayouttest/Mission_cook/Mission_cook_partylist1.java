package cn.picksomething.drawlayouttest.Mission_cook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cn.picksomething.drawlayouttest.LastPage.PartyLast;
import cn.picksomething.drawlayouttest.LastPage.PartyLast2;
import cn.picksomething.drawlayouttest.R;



/**
 * Created by tt791_000 on 2016/7/15.
 */
public class Mission_cook_partylist1 extends Fragment {
    private Button PartyButton1;
    private Button PartyButton2;
    private Button PartyButton3;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mission_cooking_partylist1, container,false);
        return view;
    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        PartyButton1 = (Button) getView().findViewById(R.id.PartyButton1);
        PartyButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PartyLast2.class);
                Bundle bundle = new Bundle();
                bundle.putString("_partyid","P1");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        PartyButton2 = (Button) getView().findViewById(R.id.PartyButton2);
        PartyButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PartyLast.class);
                Bundle bundle = new Bundle();
                bundle.putString("_partyid","P2");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        PartyButton3 = (Button) getView().findViewById(R.id.PartyButton3);
        PartyButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PartyLast.class);
                Bundle bundle = new Bundle();
                bundle.putString("_partyid","P3");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }


}

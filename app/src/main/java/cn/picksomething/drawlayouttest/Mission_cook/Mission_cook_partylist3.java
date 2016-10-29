package cn.picksomething.drawlayouttest.Mission_cook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cn.picksomething.drawlayouttest.LastPage.HealthLast1;
import cn.picksomething.drawlayouttest.LastPage.LastPage3;
import cn.picksomething.drawlayouttest.R;



/**
 * Created by tt791_000 on 2016/7/15.
 */
public class Mission_cook_partylist3 extends Fragment{
    private Button GoodButton1;
    private Button GoodButton2;
    private Button GoodButton3;
    private Button GoodButton4;
    private Button GoodButton5;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mission_cooking_partylist3, container,false);
        return view;
    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        GoodButton1 = (Button) getView().findViewById(R.id.GoodButton1);
        GoodButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HealthLast1.class);
                Bundle bundle = new Bundle();
                bundle.putString("_HealthPart","H1");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        GoodButton2 = (Button) getView().findViewById(R.id.GoodButton2);
        GoodButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LastPage3.class);
                startActivity(intent);
            }
        });
        GoodButton3 = (Button) getView().findViewById(R.id.GoodButton3);
        GoodButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HealthLast1.class);
                Bundle bundle = new Bundle();
                bundle.putString("_HealthPart","H3");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        GoodButton4 = (Button) getView().findViewById(R.id.GoodButton4);
        GoodButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HealthLast1.class);
                Bundle bundle = new Bundle();
                bundle.putString("_HealthPart","H4");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        GoodButton5 = (Button) getView().findViewById(R.id.GoodButton5);
        GoodButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HealthLast1.class);
                Bundle bundle = new Bundle();
                bundle.putString("_HealthPart","H5");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

}

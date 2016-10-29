package cn.picksomething.drawlayouttest.Mission_cook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cn.picksomething.drawlayouttest.LastPage.FestivalLast;
import cn.picksomething.drawlayouttest.R;



/**
 * Created by tt791_000 on 2016/7/15.
 */
public class Mission_cook_partylist2 extends Fragment {

    private Button FestivalButton1;
    private Button FestivalButton2;
    private Button FestivalButton3;
    private Button FestivalButton4;
    private Button FestivalButton5;
    private Button FestivalButton6;
    private Button FestivalButton7;
    private Button FestivalButton8;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mission_cooking_partylist2, container,false);
        return view;
    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FestivalButton1 = (Button) getView().findViewById(R.id.FestivalButton1);
        FestivalButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FestivalLast.class);
                Bundle bundle = new Bundle();
                bundle.putString("_festivalpart","K1");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        FestivalButton2 = (Button) getView().findViewById(R.id.FestivalButton2);
        FestivalButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FestivalLast.class);
                Bundle bundle = new Bundle();
                bundle.putString("_festivalpart","K2");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        FestivalButton3 = (Button) getView().findViewById(R.id.FestivalButton3);
        FestivalButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FestivalLast.class);
                Bundle bundle = new Bundle();
                bundle.putString("_festivalpart","K3");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        FestivalButton4 = (Button) getView().findViewById(R.id.FestivalButton4);
        FestivalButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FestivalLast.class);
                Bundle bundle = new Bundle();
                bundle.putString("_festivalpart","K4");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        FestivalButton5 = (Button) getView().findViewById(R.id.FestivalButton5);
        FestivalButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FestivalLast.class);
                Bundle bundle = new Bundle();
                bundle.putString("_festivalpart","K5");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        FestivalButton6 = (Button) getView().findViewById(R.id.FestivalButton6);
        FestivalButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FestivalLast.class);
                Bundle bundle = new Bundle();
                bundle.putString("_festivalpart","K6");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        FestivalButton7 = (Button) getView().findViewById(R.id.FestivalButton7);
        FestivalButton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FestivalLast.class);
                Bundle bundle = new Bundle();
                bundle.putString("_festivalpart","K7");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        FestivalButton8 = (Button) getView().findViewById(R.id.FestivalButton8);
        FestivalButton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FestivalLast.class);
                Bundle bundle = new Bundle();
                bundle.putString("_festivalpart","K8");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}

package cn.picksomething.drawlayouttest.Point_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cn.picksomething.drawlayouttest.LastPage.PointProjectLast;
import cn.picksomething.drawlayouttest.R;

/**
 * Created by tt791_000 on 2016/7/6.
 */
public class Point_project  extends android.support.v4.app.Fragment {
    private Button pp1;
    private Button pp2;
    private Button pp3;
    private Button pp4;
    private Button pp5;
    private Button pp6;
    private Button pp7;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.point_project, container, false);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        pp1= (Button) getView().findViewById(R.id.p_beer);
        pp2= (Button) getView().findViewById(R.id.p_clean);
        pp3= (Button) getView().findViewById(R.id.p_shower);
        pp4 = (Button) getView().findViewById(R.id.p_cooking);
        pp5= (Button)getView(). findViewById(R.id.p_drinking);
        pp6= (Button) getView().findViewById(R.id.p_eating);

        pp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),PointProjectLast.class);
                Bundle bundle = new Bundle();
                bundle.putString("_itemPart","PP1");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        pp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),PointProjectLast.class);
                Bundle bundle = new Bundle();
                bundle.putString("_itemPart","PP2");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        pp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PointProjectLast.class);
                Bundle bundle = new Bundle();
                bundle.putString("_itemPart","PP3");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        pp4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PointProjectLast.class);
                Bundle bundle = new Bundle();
                bundle.putString("_itemPart","PP4");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        pp5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),PointProjectLast.class);
                Bundle bundle = new Bundle();
                bundle.putString("_itemPart","PP5");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        pp6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PointProjectLast.class);
                Bundle bundle = new Bundle();
                bundle.putString("_itemPart","PP6");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


    }
}

package cn.picksomething.drawlayouttest.Mission_cook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cn.picksomething.drawlayouttest.LastPage.BodyCareLast;
import cn.picksomething.drawlayouttest.R;

/**
 * Created by tt791_000 on 2016/7/15.
 */
public class Mission_cook_healthlist3  extends Fragment {
    private Button HealtyThirdButton1;
    private Button HealtyThirdButton2;
    private Button HealtyThirdButton3;
    private Button HealtyThirdButton4;
    private Button HealtyThirdButton5;
    private Button HealtyThirdButton6;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mission_cooking_healthlist3, container,false);
        return view;
    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        HealtyThirdButton1 = (Button) getView().findViewById(R.id.mc_healththirdButton1);
        HealtyThirdButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BodyCareLast.class);
                Bundle bundle = new Bundle();
                bundle.putString("_bodycarePart","B1");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        HealtyThirdButton2 = (Button) getView().findViewById(R.id.mc_healththirdButton2);
        HealtyThirdButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BodyCareLast.class);
                Bundle bundle = new Bundle();
                bundle.putString("_bodycarePart","B2");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        HealtyThirdButton3 = (Button) getView().findViewById(R.id.mc_healththirdButton3);
        HealtyThirdButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BodyCareLast.class);
                Bundle bundle = new Bundle();
                bundle.putString("_bodycarePart","B3");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        HealtyThirdButton4 = (Button) getView().findViewById(R.id.mc_healththirdButton4);
        HealtyThirdButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BodyCareLast.class);
                Bundle bundle = new Bundle();
                bundle.putString("_bodycarePart","B4");
                intent.putExtras(bundle);
                startActivity(intent);;
            }
        });
        HealtyThirdButton5 = (Button) getView().findViewById(R.id.mc_healththirdButton5);
        HealtyThirdButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BodyCareLast.class);
                Bundle bundle = new Bundle();
                bundle.putString("_bodycarePart","B5");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        HealtyThirdButton6 = (Button) getView().findViewById(R.id.mc_healththirdButton6);
        HealtyThirdButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BodyCareLast.class);
                Bundle bundle = new Bundle();
                bundle.putString("_bodycarePart","B6");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

}

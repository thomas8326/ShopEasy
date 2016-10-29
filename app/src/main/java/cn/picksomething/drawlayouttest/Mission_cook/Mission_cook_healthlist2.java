package cn.picksomething.drawlayouttest.Mission_cook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cn.picksomething.drawlayouttest.LastPage.CookHealthLast;
import cn.picksomething.drawlayouttest.R;



/**
 * Created by tt791_000 on 2016/7/15.
 */
public class Mission_cook_healthlist2  extends Fragment {
    private Button HealtySecondButton1;
    private Button HealtySecondButton2;
    private Button HealtySecondButton3;
    private Button HealtySecondButton4;
    private Button HealtySecondButton5;
    private Button HealtySecondButton6;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mission_cooking_healthlist2, container,false);
        return view;
    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        HealtySecondButton1 = (Button) getView().findViewById(R.id.mc_healthsecondButton1);
        HealtySecondButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CookHealthLast.class);
                Bundle bundle = new Bundle();
                bundle.putString("_cookhealthPart","Y1");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        HealtySecondButton2 = (Button) getView().findViewById(R.id.mc_healthsecondButton2);
        HealtySecondButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CookHealthLast.class);
                Bundle bundle = new Bundle();
                bundle.putString("_cookhealthPart","Y2");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        HealtySecondButton3 = (Button) getView().findViewById(R.id.mc_healthsecondButton3);
        HealtySecondButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CookHealthLast.class);
                Bundle bundle = new Bundle();
                bundle.putString("_cookhealthPart","Y3");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        HealtySecondButton4 = (Button) getView().findViewById(R.id.mc_healthsecondButton4);
        HealtySecondButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CookHealthLast.class);
                Bundle bundle = new Bundle();
                bundle.putString("_cookhealthPart","Y4");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        HealtySecondButton5 = (Button) getView().findViewById(R.id.mc_healthsecondButton5);
        HealtySecondButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CookHealthLast.class);
                Bundle bundle = new Bundle();
                bundle.putString("_cookhealthPart","Y5");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        HealtySecondButton6 = (Button) getView().findViewById(R.id.mc_healthsecondButton6);
        HealtySecondButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CookHealthLast.class);
                Bundle bundle = new Bundle();
                bundle.putString("_cookhealthPart","Y6");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


    }
}

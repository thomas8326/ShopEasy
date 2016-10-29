package cn.picksomething.drawlayouttest.Mission_cook;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cn.picksomething.drawlayouttest.LastPage.exerciseLast;
import cn.picksomething.drawlayouttest.LastPage.exerciseLast2;
import cn.picksomething.drawlayouttest.R;
/**
 * Created by tt791_000 on 2016/7/15.
 */
public class Mission_cook_healthlist1 extends Fragment {
    private Button healthButton1;
    private Button healthButton2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mission_cooking_healthlist1, container,false);
        return view;
    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        healthButton1 = (Button) getView().findViewById(R.id.mc_healthfirstButton1);
        healthButton1.setBackgroundResource(R.drawable.button_ininhealth);
        healthButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                defaultFragment();
                healthButton1.setBackgroundResource(R.drawable.button_ininhealth);
                healthButton2.setBackgroundResource(R.drawable.button_inin);

            }
        });
        healthButton2 = (Button) getView().findViewById(R.id.mc_healthfirstButton2);
        healthButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment2();
                healthButton1.setBackgroundResource(R.drawable.button_inin);
                healthButton2.setBackgroundResource(R.drawable.button_ininhealth);
            }
        });
        defaultFragment();

    }
    public void defaultFragment()
    {
        exerciseLast exerciselast = new exerciseLast();
        Bundle bundle=new Bundle();
        bundle.putString("exercisePart", "w1");
        //向detailFragment传入参数
        exerciselast.setArguments(bundle);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.popBackStack();
        fragmentManager.beginTransaction().replace(R.id.health1fragment, exerciselast).commit();


    }
    private void Fragment2()
    {
        exerciseLast2 exerciselast2 = new exerciseLast2();
        Bundle bundle=new Bundle();
        bundle.putString("exercisePart", "w2");
        //向detailFragment传入参数
        exerciselast2.setArguments(bundle);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.popBackStack();
        fragmentManager.beginTransaction().replace(R.id.health1fragment, exerciselast2).commit();
    }
}

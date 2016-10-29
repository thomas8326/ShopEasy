package cn.picksomething.drawlayouttest.Toolbar;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cn.picksomething.drawlayouttest.R;

/**
 * Created by tt791_000 on 2016/7/15.
 */
public class FragmentCookbook_list1 extends Fragment {

    private Button btn1;
    private Button btn2;
    private Button btn3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.toolbar_cookbook_list1, container,false);
        return view;
    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentCookbook_list1fragmentall fragmentCookbook_list1fragmentall = new FragmentCookbook_list1fragmentall();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.popBackStack();
        fragmentManager.beginTransaction().replace(R.id.cookbookname1, fragmentCookbook_list1fragmentall).commit();
        initBtn();
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn1.setBackgroundResource(R.drawable.foodbookbtn2);
                btn2.setBackgroundResource(R.drawable.foodbookbtn);
                btn3.setBackgroundResource(R.drawable.foodbookbtn);
                FragmentCookbook_list1fragmentall fragmentCookbook_list1fragmentall = new FragmentCookbook_list1fragmentall();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.popBackStack();
                fragmentManager.beginTransaction().replace(R.id.cookbookname1, fragmentCookbook_list1fragmentall).commit();

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn2.setBackgroundResource(R.drawable.foodbookbtn2);
                btn1.setBackgroundResource(R.drawable.foodbookbtn);
                btn3 .setBackgroundResource(R.drawable.foodbookbtn);
                FragmentCookbook_list1fragment fragmentCookbook_list1fragment = new FragmentCookbook_list1fragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.popBackStack();
                fragmentManager.beginTransaction().replace(R.id.cookbookname1, fragmentCookbook_list1fragment).commit();

            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn3.setBackgroundResource(R.drawable.foodbookbtn2);
                btn2.setBackgroundResource(R.drawable.foodbookbtn);
                btn1 .setBackgroundResource(R.drawable.foodbookbtn);
                FragmentCookbook_list1fragmentforeign fragmentCookbook_list1fragmentforeign = new FragmentCookbook_list1fragmentforeign();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.popBackStack();
                fragmentManager.beginTransaction().replace(R.id.cookbookname1, fragmentCookbook_list1fragmentforeign).commit();

            }
        });

}
    public void initBtn()
    {
        btn1 = (Button) getView().findViewById(R.id.ck_all);
        btn2 = (Button) getView().findViewById(R.id.ck_chinese);
        btn3 = (Button) getView().findViewById(R.id.ck_foreign);

    }


}

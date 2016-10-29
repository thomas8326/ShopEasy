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
public class FragmentCookbook_list2 extends Fragment {
    private Button btn1;
    private Button btn2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.toolbar_cookbook_list2, container,false);
        return view;
    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentCookbook_list2fragmentbread fragmentCookbook_list2fragmentbread = new FragmentCookbook_list2fragmentbread();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.popBackStack();
        fragmentManager.beginTransaction().replace(R.id.cookbookname2, fragmentCookbook_list2fragmentbread).commit();
        initBtn();
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btn2.setBackgroundResource(R.drawable.foodbookbtn);
                btn1 .setBackgroundResource(R.drawable.foodbookbtn2);
                FragmentCookbook_list2fragmentbread fragmentCookbook_list2fragmentbread = new FragmentCookbook_list2fragmentbread();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.popBackStack();
                fragmentManager.beginTransaction().replace(R.id.cookbookname2, fragmentCookbook_list2fragmentbread).commit();

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn2.setBackgroundResource(R.drawable.foodbookbtn2);
                btn1 .setBackgroundResource(R.drawable.foodbookbtn);
                FragmentCookbook_list2fragmentdessert fragmentCookbook_list2fragmentdessert = new FragmentCookbook_list2fragmentdessert();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.popBackStack();
                fragmentManager.beginTransaction().replace(R.id.cookbookname2, fragmentCookbook_list2fragmentdessert).commit();
            }
        });
    }
    public void initBtn()
    {
        btn1 = (Button) getView().findViewById(R.id.ck_bread);
        btn2 = (Button) getView().findViewById(R.id.ck_dessert);

    }
}

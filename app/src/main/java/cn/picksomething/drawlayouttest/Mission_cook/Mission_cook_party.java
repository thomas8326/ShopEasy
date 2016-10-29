package cn.picksomething.drawlayouttest.Mission_cook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import cn.picksomething.drawlayouttest.MapButton;
import cn.picksomething.drawlayouttest.R;

/**
 * Created by tt791_000 on 2016/7/15.
 */

public class Mission_cook_party  extends FragmentActivity {
    //滑動換頁所需
    private Mission_cook_partylist1 mission_cook_partylist1;
    private Mission_cook_partylist2 mission_cook_partylist2;
    private Mission_cook_partylist3 mission_cook_partylist3;
    private ArrayList<Fragment> fragmentsList;
    public FragmentManager fManager;

    ViewPager mPager;
    MyAdapter mAdapter;
    //圓形圖示所需
    private ImageView TurnPageSign1;
    private ImageView TurnPageSign2;
    private ImageView TurnPageSign3;
    private RelativeLayout TurnPage1;
    private RelativeLayout TurnPage2;
    private RelativeLayout TurnPage3;
    public MyOnClick myclick;
    public MyPageChangeListener myPageChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mission_cooking_party);
        fManager =getSupportFragmentManager();
        TextView title;
        title = (TextView) findViewById(R.id.TOPtitle);
        title.setText("派對");
        initViewPager();
        mPager = (ViewPager) findViewById(R.id.PartyPage);
        mPager.setAdapter(mAdapter);
        initCircle();
        initState();
        init();
    }

    private void initViewPager()
    {
        fragmentsList = new ArrayList<Fragment>();
        mission_cook_partylist1 = new Mission_cook_partylist1();
        mission_cook_partylist2 = new Mission_cook_partylist2();
        mission_cook_partylist3 = new Mission_cook_partylist3();
        fragmentsList.add(mission_cook_partylist1);
        fragmentsList.add(mission_cook_partylist2);
        fragmentsList.add(mission_cook_partylist3);
        mAdapter = new MyAdapter(fManager,fragmentsList);
    }

    public static class MyAdapter extends FragmentStatePagerAdapter {
        private ArrayList<Fragment> fragmentsList;
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }
        public MyAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
            super(fm);
            this.fragmentsList = fragments;
        }
        @Override
        public int getCount() {
            return fragmentsList.size();
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentsList.get(position);
        }
    }

    //初始化小圓標圖示
    private void initCircle()
    {   myclick = new MyOnClick();
        myPageChange = new MyPageChangeListener();
        TurnPageSign1 = (ImageView) findViewById(R.id.TurnPageSign1);
        TurnPageSign2 = (ImageView) findViewById(R.id.TurnPageSign2);
        TurnPageSign3 = (ImageView) findViewById(R.id.TurnPageSign3);
        TurnPage1 = (RelativeLayout) findViewById(R.id.TurnPage1);
        TurnPage2 = (RelativeLayout) findViewById(R.id.TurnPage2);
        TurnPage3 = (RelativeLayout) findViewById(R.id.TurnPage3);
        mPager.setOnPageChangeListener(myPageChange);
        TurnPage1.setOnClickListener(myclick);
        TurnPage2.setOnClickListener(myclick);
        TurnPage3.setOnClickListener(myclick);

    }
    public class MyOnClick implements View.OnClickListener
    {
        @Override
        public void onClick(View view) {
            clearChioce();
            iconChange(view.getId());
        }
    }
    public void clearChioce()
    {
        TurnPageSign1.setImageResource(R.drawable.point_unfocused);
        TurnPageSign2.setImageResource(R.drawable.point_unfocused);
        TurnPageSign3.setImageResource(R.drawable.point_unfocused);
    }

    private void initState()
    {
        TurnPageSign1.setImageResource(R.drawable.point_focused);
        mPager.setCurrentItem(0);
    }
    //換頁後會傳值
    public class MyPageChangeListener implements ViewPager.OnPageChangeListener
    {

        @Override
        public void onPageScrollStateChanged(int arg0)
        {
            if(arg0 == 2)
            {
                int i = mPager.getCurrentItem();
                clearChioce();
                iconChange(i);
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {}

        @Override
        public void onPageSelected(int index){}

    }

    public void iconChange(int num)
    {    TextView title;
        switch (num) {
            case R.id.TurnPage1:case 0:
                TurnPageSign1.setImageResource(R.drawable.point_focused);
                mPager.setCurrentItem(0);
                title = (TextView) findViewById(R.id.TOPtitle);
                title.setText("派對");
                break;
            case R.id.TurnPage2:case 1:
                TurnPageSign2.setImageResource(R.drawable.point_focused);
                mPager.setCurrentItem(1);
                title = (TextView) findViewById(R.id.TOPtitle);
                title.setText("節慶");
                break;
            case  R.id.TurnPage3:case 2:
                TurnPageSign3.setImageResource(R.drawable.point_focused);
                mPager.setCurrentItem(2);
                title = (TextView) findViewById(R.id.TOPtitle);
                title.setText("進補");
                break;
        }
    }

    protected void init()
    {
        ImageView back;
        ImageView map;
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        map = (ImageView) findViewById(R.id.map);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Mission_cook_party.this,MapButton.class));
            }
        });

    }

}

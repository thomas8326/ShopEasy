package cn.picksomething.drawlayouttest.Mission_cook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
public class Mission_cook_health extends FragmentActivity {
    //滑動換頁所需
    private Mission_cook_healthlist1 mission_cook_healthlist1;
    private Mission_cook_healthlist2 mission_cook_healthlist2;
    private Mission_cook_healthlist3 mission_cook_healthlist3;
    private ArrayList<Fragment> fragmentsList;
    public FragmentManager fManager;

    //圓形圖示所需
    private ImageView TurnPageSign1;
    private ImageView TurnPageSign2;
    private ImageView TurnPageSign3;
    private RelativeLayout TurnPage1;
    private RelativeLayout TurnPage2;
    private RelativeLayout TurnPage3;
    public MyOnClick myclick;
    public MyPageChangeListener myPageChange;
    ViewPager mPager;
    MyAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mission_cooking_health);
        fManager =getSupportFragmentManager();
        initViewPager();
        TextView title;
        title = (TextView) findViewById(R.id.TOPtitle);
        title.setText("健康飲食");
        mPager = (ViewPager) findViewById(R.id.HealthPager);
        mPager.setAdapter(mAdapter);
        initCircle();
        initState();
        init();
    }

    private void initViewPager()
    {
        fragmentsList = new ArrayList<Fragment>();
        mission_cook_healthlist1 = new Mission_cook_healthlist1();
        mission_cook_healthlist2 = new Mission_cook_healthlist2();
        mission_cook_healthlist3 = new Mission_cook_healthlist3();
        fragmentsList.add(mission_cook_healthlist2);
        fragmentsList.add(mission_cook_healthlist3);
        fragmentsList.add(mission_cook_healthlist1);
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
    { TextView title;
        switch (num) {
            case R.id.TurnPage1:case 0:
                TurnPageSign1.setImageResource(R.drawable.point_focused);
                mPager.setCurrentItem(0);

                title = (TextView) findViewById(R.id.TOPtitle);
                title.setText("健康飲食");
                break;
            case R.id.TurnPage2:case 1:
                TurnPageSign2.setImageResource(R.drawable.point_focused);
                mPager.setCurrentItem(1);
                title = (TextView) findViewById(R.id.TOPtitle);
                title.setText("身體保健");
                break;
            case  R.id.TurnPage3:case 2:
                TurnPageSign3.setImageResource(R.drawable.point_focused);
                mPager.setCurrentItem(2);

                title = (TextView) findViewById(R.id.TOPtitle);
                title.setText("體重控制");
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.

        // Handle action buttons
        switch (item.getItemId()) {
            case R.id.action_map:
                // create intent to perform web search for this planet
                Intent intent = new Intent();
                intent.setClass(this,MapButton.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
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
                startActivity(new Intent(Mission_cook_health.this,MapButton.class));
            }
        });

    }

}

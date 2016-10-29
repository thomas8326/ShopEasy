package cn.picksomething.drawlayouttest.LastPage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.picksomething.drawlayouttest.Databases.TestDatabaseHelper;
import cn.picksomething.drawlayouttest.MapButton;
import cn.picksomething.drawlayouttest.R;
import cn.picksomething.drawlayouttest.TabFragment.HealthFourWeek1;
import cn.picksomething.drawlayouttest.TabFragment.HealthFourWeek2;
import cn.picksomething.drawlayouttest.TabFragment.HealthFourWeek3;
import cn.picksomething.drawlayouttest.TabFragment.HealthFourWeek4;

/**
 * 坐月子
 */
public class LastPage3 extends FragmentActivity {
    private TestDatabaseHelper myDB;
    private ViewPager mPager;
    private android.support.design.widget.TabLayout mTabs;
    private ArrayList<CharSequence> aryTab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_lastpagefourweek);

        myDB = new TestDatabaseHelper(this);
        myDB.OpenDB();
        initViewPager();
        initTitle();
        TextView title;
        title = (TextView)findViewById(R.id.TOPtitle);
        title.setText("坐月子");

    }



    private void initViewPager() {
        mTabs = (android.support.design.widget.TabLayout) findViewById(R.id.fourweekTabs);
        mPager = (ViewPager) findViewById(R.id.fourweekviewpage);

        aryTab =  new ArrayList<>();
        mTabs.addTab(mTabs.newTab().setText("第一週"));
        mTabs.addTab(mTabs.newTab().setText("第二週"));
        mTabs.addTab(mTabs.newTab().setText("第三週"));
        mTabs.addTab(mTabs.newTab().setText("第四週"));
        List<Fragment> fl=new ArrayList<Fragment>(); //填充要的Fragment頁卡
        for(int j = 0;j<mTabs.getTabCount();j++) {
            aryTab.add(mTabs.getTabAt(j).getText());
        }
        fl.add(new HealthFourWeek1());
        fl.add(new HealthFourWeek2());
        fl.add(new HealthFourWeek3());
        fl.add(new HealthFourWeek4());
        if (mPager != null) {
            mPager.setAdapter(new SamplePagerAdapter(getSupportFragmentManager(), fl , this));  //設定Adapter給viewPager
        }

        mTabs.setupWithViewPager(mPager);
        mPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabs));

    }
    private class SamplePagerAdapter extends FragmentStatePagerAdapter {


        private Context context;
        List<Fragment> fragments; //切換頁面的Fragments
        private android.support.design.widget.TabLayout Tabs;
        public SamplePagerAdapter(FragmentManager fm , List<Fragment> f, Context context) {
            super(fm);
            this.context=context;
            fragments=f;
        }

        @Override
        public int getCount() { //頁卡數量
            return fragments.size();
        }

        @Override
        public Fragment getItem(int position) { //回傳Frament頁卡
            return fragments.get(position); //從上方List<Fragment> fragments取得
        }

        @Override
        public CharSequence getPageTitle(int position) { //在此回傳Tab title string
            return  aryTab.get(position);
        }




    }

    protected void initTitle()
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
                startActivity(new Intent(LastPage3.this,MapButton.class));
            }
        });

    }

}



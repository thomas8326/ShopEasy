package cn.picksomething.drawlayouttest.LastPage;



import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.picksomething.drawlayouttest.Databases.DatabaseHelper;
import cn.picksomething.drawlayouttest.Databases.TestDatabaseHelper;
import cn.picksomething.drawlayouttest.MapButton;
import cn.picksomething.drawlayouttest.R;
import cn.picksomething.drawlayouttest.TabFragment.FoodbookFragment1;
import cn.picksomething.drawlayouttest.TabFragment.FoodbookFragment2;

/**
 * 舉辦活動+健康生活
 */

public class CookBooklast2 extends FragmentActivity {

    private Bundle bundle;
    private String mPart;
    private TextView Toptitle;
    private Cursor totlaID;
    private Cursor cursor;
    private ArrayList<String> setData1;
    private ArrayList<String> setData2;
    private ArrayList<String> setData3;
    private Cursor data;
    private Cursor data2;
    private Cursor data3;

    private String Foodname;
    private String mIG;
    private int mID;
    private TestDatabaseHelper mydb;
    private DatabaseHelper dbhelp;

    private ArrayList<CharSequence> aryTab;
    private android.support.design.widget.TabLayout mTabs;

    private ViewPager mViewPager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.foodbook_lastpage);
        bundle = getIntent().getExtras();
        mPart = bundle.getString("Part");
        mID = bundle.getInt("_ID");
        mIG = bundle.getString("_partid");
        dataconn();
        initViewPager();
        init();
    }

    protected void dataconn() {
        mydb = new TestDatabaseHelper(this);
        dbhelp = new DatabaseHelper(this);
        mydb.OpenDB();
        Toptitle = (TextView) findViewById(R.id.TOPtitle);
        if ("Lastpage".equals(mPart)) {
            mID = bundle.getInt("_ID");
            mIG = bundle.getString("_partid");

            totlaID = mydb.SelectID(mID, mIG);
            cursor = mydb.SelectDoLastPage(totlaID.getInt(0));
            data = mydb.SelectIGLastPage(totlaID.getInt(0));
            data2 = mydb.getLastPagePosition(totlaID.getInt(0));
            data3 = mydb.geLastPageClassName(totlaID.getInt(0));

        } else if ("HealthLast1".equals(mPart)) {
            mID = bundle.getInt("_ID");
            mIG = bundle.getString("_partid");
            totlaID = mydb.SelectIDHealth(mID, mIG);

            cursor = mydb.SelectDoHealth(totlaID.getInt(0));
            data = mydb.SelectIGHealth(totlaID.getInt(0));
            data2 = mydb.getHealthPosition(totlaID.getInt(0));
            data3 = mydb.getHealthClassName(totlaID.getInt(0));

        } else if ("bodycare".equals(mPart)) {
            mID = bundle.getInt("_ID");
            mIG = bundle.getString("_partid");
            totlaID = mydb.SelectIDBodycare(mID, mIG);

            cursor = mydb.SelectDoBodycare(totlaID.getInt(0));

            data = mydb.SelectIGBodycare(totlaID.getInt(0));
            data2 = mydb.getIGBodycarePosition(totlaID.getInt(0));
            data3 = mydb.getIGBodycareClassName(totlaID.getInt(0));

        } else if ("cookhealthPart".equals(mPart)) {
            mID = bundle.getInt("_ID");
            mIG = bundle.getString("_partid");

            totlaID = mydb.SelectIDCookhealth(mID, mIG);
            cursor = mydb.SelectDoCookhealth(totlaID.getInt(0));
            data = mydb.SelectIGCookhealth(totlaID.getInt(0));
            data2 = mydb.getIGCookhealthPosition(totlaID.getInt(0));
            data3 = mydb.getIGCookhealthClassName(totlaID.getInt(0));

        } else if ("exercise".equals(mPart)) {
            mID = bundle.getInt("_ID");
            mIG = bundle.getString("_partid");

            totlaID = mydb.SelectIDExercise(mID, mIG);
            cursor = mydb.SelectDoExercise(totlaID.getInt(0));
            data = mydb.SelectIGExercise(totlaID.getInt(0));
            data2 = mydb.getIGExercisePosition(totlaID.getInt(0));
            data3 = mydb.getIGExerciseClassName(totlaID.getInt(0));

        }
        else if ("Festival".equals(mPart)) {
            mID = bundle.getInt("_ID");
            mIG = bundle.getString("_partid");

            totlaID = mydb.SelectIDFestival(mID, mIG);
            cursor = mydb.SelectDoFestival(totlaID.getInt(0));
            data = mydb.SelectIGFestival(totlaID.getInt(0));
            data2 = mydb.getIGFestivalPosition(totlaID.getInt(0));
            data3 = mydb.getIGFestivalClassName(totlaID.getInt(0));
        }
        else if("Fourweek".equals(mPart)){
            mID = bundle.getInt("_ID");
            mIG = bundle.getString("_partid");

            totlaID = mydb.SelectIDFourweek(mID, mIG);
            cursor = mydb.SelectDoFourweek(totlaID.getInt(0));
            data = mydb.SelectIGFourweek(totlaID.getInt(0));
            data2 = mydb.getIGFourWeekPosition(totlaID.getInt(0));
            data3 = mydb.getIGFourWeekClassName(totlaID.getInt(0));
        }
        else if("Together".equals(mPart)){
            mID = bundle.getInt("_ID");
            mIG = bundle.getString("_partid");

            totlaID = mydb.SelectIDTogether(mID, mIG);
            cursor = mydb.SelectDoTogether(totlaID.getInt(0));
            data = mydb.SelectIGTogether(totlaID.getInt(0));
            data2 = mydb.getIGTogetherPosition(totlaID.getInt(0));
            data3 = mydb.getIGTogetherClassName(totlaID.getInt(0));
        }
         setData1 = new ArrayList<String>();
        setData2 = new ArrayList<String>();
        setData3 = new ArrayList<String>();
        int column = data.getColumnCount() - 1;
        for (int i = 1; i <= column; i++) {
            if(data.getString(i)!=null)
            {setData1.add(data.getString(i));
                setData2.add(data.getString(i));
                setData3.add(data.getString(i));
            }
            else
                break;
        }
        Foodname = cursor.getString(0);
        Toptitle.setText(Foodname);
        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"已將此料理將入菜單管理",Toast.LENGTH_SHORT).show();
                dbhelp.addDataFromCBToFavorite(Foodname,mID,mIG,mPart,setData1,setData2,setData3);
            }
        });
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
                startActivity(new Intent(CookBooklast2.this,MapButton.class));
            }
        });

    }
    private void initViewPager() {
        aryTab = new ArrayList<>();
        mTabs = (android.support.design.widget.TabLayout) findViewById(R.id.tabLayout);
        mTabs.addTab(mTabs.newTab().setText("食材"));
        aryTab.add(mTabs.getTabAt(0).getText());
        mTabs.addTab(mTabs.newTab().setText("步驟"));
        aryTab.add(mTabs.getTabAt(1).getText());
        List<Fragment> fl=new ArrayList<Fragment>(); //填充要的Fragment頁卡

        fl.add(new FoodbookFragment1());
        fl.add(new FoodbookFragment2());


        ViewPager mPager = (ViewPager) findViewById(R.id.viewPager);
        SamplePagerAdapter mPagerAdapter = new SamplePagerAdapter(getSupportFragmentManager(),fl,this);
        mPager.setAdapter(mPagerAdapter);
        mTabs.setupWithViewPager(mPager);
        // ViewPager切换时NestedScrollView滑动到顶部
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ((NestedScrollView) findViewById(R.id.nestedScrollView)).scrollTo(0, 0);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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
            if(position ==0)
                return FoodbookFragment1.newInstance(mPart,mID,mIG);//從上方List<Fragment> fragments取得
            else
                return FoodbookFragment2.newInstance(mPart,mID,mIG);//從上方List<Fragment> fragments取得
        }

        @Override
        public CharSequence getPageTitle(int position) { //在此回傳Tab title string
            return  aryTab.get(position);
        }




    }

}

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
import cn.picksomething.drawlayouttest.TabFragment.FoodbookFragmentPoint5;
import cn.picksomething.drawlayouttest.TabFragment.FoodbookFragmentPoint6;

/**
 * 烹飪料理
 */
public  class  CookBooklast extends FragmentActivity{


    private TextView Toptitle;
    private Cursor totlaID;
    private String mIG;
    private int mID;
    private TestDatabaseHelper mydb;
    private DatabaseHelper dbhelp;
    private List<String> setData1;
    private List<String> setData2;
    private List<String> setData3;
    private String Foodname;
    private ArrayList<CharSequence> aryTab;
    private android.support.design.widget.TabLayout mTabs;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.foodbook_lastpage);
        dataconn();
        initViewPager();
        init();
    }
    //資料庫相關語法 抓取 記下食材
    protected void dataconn() {

        mydb = new TestDatabaseHelper(this);
        dbhelp = new DatabaseHelper(this);
        mydb.OpenDB();

        Bundle bundle = getIntent().getExtras();
        mID = bundle.getInt("_ID");
        mIG = bundle.getString("_Ingredient");
        totlaID = mydb.SelectIDPC(mID,mIG);

        //菜餚名稱
        Cursor cursor = mydb.SelectDo(totlaID.getInt(0));
        Foodname = cursor.getString(0);
        //食材清單
        Cursor data = mydb.SelectIG(totlaID.getInt(0));
        Cursor data1 = mydb.SelectIGPosition(totlaID.getInt(0));
        Cursor data2 = mydb.SelectIGClassName(totlaID.getInt(0));
        setData1 = new ArrayList<String>();
        setData2 = new ArrayList<String>();
        setData3 = new ArrayList<String>();
        int column = data.getColumnCount() - 1;
        for (int i = 1; i <= column; i++) {
            if(data.getString(i)!=null)
            {
                setData1.add(data.getString(i));
                setData2.add(data.getString(i));
                setData3.add(data.getString(i));

            }
            else
                break;
        }

        Toptitle = (TextView) findViewById(R.id.TOPtitle);
        Toptitle.setText(Foodname);

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"已將此料理將入菜單管理",Toast.LENGTH_SHORT).show();
                dbhelp.addDataFromCBToFavorite(Foodname,mID,mIG,"PointCookLast",setData1,setData2,setData3);
            }
        });
    }

    //Title 返回 地圖
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
                startActivity(new Intent(CookBooklast.this,MapButton.class));
            }
        });

    }
    //準備初始化Tablayout
    private void initViewPager() {
        aryTab = new ArrayList<>();
        mTabs = (android.support.design.widget.TabLayout) findViewById(R.id.tabLayout);
        mTabs.addTab(mTabs.newTab().setText("食材"));
        aryTab.add(mTabs.getTabAt(0).getText());
        mTabs.addTab(mTabs.newTab().setText("步驟"));
        aryTab.add(mTabs.getTabAt(1).getText());
        List<Fragment> fl=new ArrayList<Fragment>(); //填充要的Fragment頁卡

        fl.add(new FoodbookFragmentPoint5());
        fl.add(new FoodbookFragmentPoint6());


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


    //Viewpager適配器
    private class SamplePagerAdapter extends FragmentStatePagerAdapter {


        private Context context;
        List<Fragment> fragments; //切換頁面的Fragments

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
                return FoodbookFragmentPoint5.newInstance(mID,mIG);//從上方List<Fragment> fragments取得
            else
                return FoodbookFragmentPoint6.newInstance(mID,mIG);//從上方List<Fragment> fragments取得
        }

        @Override
        public CharSequence getPageTitle(int position) { //在此回傳Tab title string
            return  aryTab.get(position);
        }




    }

}

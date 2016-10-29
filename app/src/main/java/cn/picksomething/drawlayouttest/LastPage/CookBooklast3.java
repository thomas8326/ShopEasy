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
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import cn.picksomething.drawlayouttest.Databases.DatabaseHelper;
import cn.picksomething.drawlayouttest.Databases.TestDatabaseHelper;
import cn.picksomething.drawlayouttest.MapButton;
import cn.picksomething.drawlayouttest.R;
import cn.picksomething.drawlayouttest.TabFragment.FoodbookFragmentCookBook3;
import cn.picksomething.drawlayouttest.TabFragment.FoodbookFragmentCookBook4;

/**
 * 食譜
 */
public class CookBooklast3   extends FragmentActivity {

    private ImageView backdrop;
    private Cursor cursorBck;
    private Bundle bundle;
    private TextView Toptitle;
    private Cursor cursor;
    private Cursor data;
    private Cursor data2;
    private Cursor data3;


    private TestDatabaseHelper mydb;
    private DatabaseHelper dbhelp;

    private  List<String> setData1;
    private  List<String> setData2;
    private  List<String> setData3;

    private String Foodname;

    private String foodbook;
    private String childrenpart;
    private String Sub;
    private int fid;
    private String all;
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
        //食譜傳值
        bundle = getIntent().getExtras();
        all = bundle.getString("part");
        foodbook = bundle.getString("foodbook");
        childrenpart = bundle.getString("childrenpart");
        Sub = childrenpart.substring(0,1);
        fid = bundle.getInt("id");

        //資料庫傳食譜的值
        if("all".equals(foodbook) || "A".equals(Sub)) {
            cursor = mydb.SelectDoall(fid);
            Foodname = cursor.getString(1);

            data = mydb.SelectIGall(cursor.getInt(0));
            data2 = mydb.getIGallPosition(cursor.getInt(0));
            data3 = mydb.getIGallClassName(cursor.getInt(0));
            setBackimg(cursor.getInt(0));

        }
        else if("chinese".equals(foodbook) || "R".equals(Sub)) {

            if ("all".equals(all)) {
                cursor = mydb.SelectDoChineseall(fid);
                Foodname = cursor.getString(1);


            } else {
                cursor = mydb.SelectDoChinese(fid, childrenpart);
                Foodname = cursor.getString(1);

            }
            setBackimg(cursor.getInt(0));
            data = mydb.SelectIGChinese(cursor.getInt(0));
            data2 = mydb.getIGChinesePosition(cursor.getInt(0));
            data3 = mydb.getIGChineseClassName(cursor.getInt(0));


        }
        else if("foreign".equals(foodbook) || "M".equals(Sub)) {
            if ("all".equals(all)) {
                cursor = mydb.SelectDoforeignall(fid);
                Foodname = cursor.getString(1);

            } else {
                cursor = mydb.SelectDoforeign(fid, childrenpart);
                Foodname = cursor.getString(1);

            }
            setBackimg(cursor.getInt(0));
            data = mydb.SelectIGforeign(cursor.getInt(0));
            data2 = mydb.getIGforeignPosition(cursor.getInt(0));
            data3 = mydb.getIGforeignClassName(cursor.getInt(0));

        }
        else if("bread".equals(foodbook) || "Y".equals(Sub)) {

            if ("all".equals(all)) {
                cursor = mydb.SelectDobreadall(fid);
                Foodname = cursor.getString(1);

            } else {
                cursor = mydb.SelectDobread(fid, childrenpart);
                Foodname = cursor.getString(1);

            }
            setBackimg(cursor.getInt(0));
            data = mydb.SelectIGbread(cursor.getInt(0));
            data2 = mydb.getIGbreadPosition(cursor.getInt(0));
            data3 = mydb.getIGbreadClassName(cursor.getInt(0));

        }
        else if("dessert".equals(foodbook) || "L".equals(Sub)) {
            if ("all".equals(all)) {
                cursor = mydb.SelectDodessertall(fid);
                Foodname = cursor.getString(1);

            } else {
                cursor = mydb.SelectDodessert(fid, childrenpart);
                Foodname = cursor.getString(1);

            }
            setBackimg(cursor.getInt(0));
            data = mydb.SelectIGdessert(cursor.getInt(0));
            data2 = mydb.getIGdessertPosition(cursor.getInt(0));
            data3 = mydb.getIGdessertClassName(cursor.getInt(0));

        }

        Toptitle = (TextView) findViewById(R.id.TOPtitle);
        Toptitle.setText(Foodname);
        setData1 = new ArrayList<String>();
        setData2 = new ArrayList<String>();
        setData3 = new ArrayList<String>();
        int column = data.getColumnCount();
        for (int i = 0; i < column; i++) {
            if(data.getString(i)!=null)
            {
                setData1.add(data.getString(i));
                setData2.add(data2.getString(i));
                setData3.add(data3.getString(i));
            }
        }
        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"已將此料理將入菜單管理",Toast.LENGTH_SHORT).show();
                dbhelp.addDataFromCBToFavorite(Foodname,fid,childrenpart,foodbook,setData1,setData2,setData3);
            }
        });
    }
    //加入背景圖
    protected void setBackimg(int id)
    {
        backdrop = (ImageView) findViewById(R.id.backdrop);
       // 資料庫傳食譜的值
     if(foodbook.equals("all")||Sub.equals("A")) {
         cursorBck = mydb.SelectallPathBck(id);
     }
     else if(foodbook.equals("chinese")||Sub.equals("R")) {
         cursorBck = mydb.SelectChinesePathBck(id);
     }
     else if(foodbook.equals("foreign")||Sub.equals("M")) {
         cursorBck = mydb.SelectForeignPathBck(id);
     }
     else if(foodbook.equals("bread")||Sub.equals("Y")) {
         cursorBck = mydb.SelectBreadPathBck(id);
     }
     else if(foodbook.equals("dessert")||Sub.equals("L")) {
         cursorBck = mydb.SelectDessertPathBck(id);
     }
     Class drawable = R.drawable.class;
     Field field;
     int r_id;
     if(cursorBck.getString(0)!=null) {
         try {
             field = drawable.getField(cursorBck.getString(0));
             r_id = field.getInt(field.getName());
             backdrop.setBackgroundResource(r_id);
         } catch (Exception e) {
             Log.e("ERROR", "PICTURE NOT FOUND！");
         }
     }


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
                startActivity(new Intent(CookBooklast3.this,MapButton.class));
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

        fl.add(new FoodbookFragmentCookBook3());
        fl.add(new FoodbookFragmentCookBook4());


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
                return FoodbookFragmentCookBook3.newInstance(foodbook,fid,childrenpart,all);//從上方List<Fragment> fragments取得
            else
                return FoodbookFragmentCookBook4.newInstance(foodbook,fid,childrenpart,all);//從上方List<Fragment> fragments取得
        }

        @Override
        public CharSequence getPageTitle(int position) { //在此回傳Tab title string
            return  aryTab.get(position);
        }




    }

}

package cn.picksomething.drawlayouttest.Toolbar;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.picksomething.drawlayouttest.Databases.DatabaseHelper;
import cn.picksomething.drawlayouttest.FragmentFavorite_item;
import cn.picksomething.drawlayouttest.R;

/**
 * Created by tt791_000 on 2016/7/11.
 */
public class FragmentFavorite extends Fragment {


    private android.support.v4.app.FragmentManager manager;
    private android.support.v4.app.FragmentTransaction ft;

    private DatabaseHelper myDB;

    private ArrayList<CharSequence> aryTab;
    private android.support.design.widget.TabLayout mTabs;

    private ViewPager mViewPager;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.toolbar_favorite, container, false);

        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        manager = getFragmentManager();
        initViewPager();

    }

    @Override
    public void onResume() {
        super.onResume();
        getFocus();
    }

    //主界面获取焦点
    private void getFocus() {
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    FragmentMain fm = new FragmentMain();
                    ft = manager.beginTransaction();
                    ft.replace(R.id.flContent, fm);
                    ft.addToBackStack(null);
                    ft.commit();
                    return true;
                }
                return false;
            }
        });
    }

    private void initViewPager() {
        mTabs = (android.support.design.widget.TabLayout) getView().findViewById(R.id.tabs);
        mTabs.addTab(mTabs.newTab().setText("我喜歡的"));
        myDB = new DatabaseHelper(getActivity());
        aryTab = new ArrayList<>();
        Cursor dataclass = myDB.favoritesupportDistinct();
        if (dataclass.getCount() == 0) {
            Toast.makeText(getActivity(), "尚無商品，選購商品加入菜單吧!", Toast.LENGTH_SHORT).show();
        } else {
            int l = 0;
            while (l < dataclass.getCount()) {
                if (dataclass.getString(0).equals("我喜歡的")) {
                } else {
                    mTabs.addTab(mTabs.newTab().setText(dataclass.getString(0)));
                }
                l++;
                dataclass.moveToNext();
            }
        }

//        mViewPager.setAdapter(new SamplePagerAdapter());
//        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabs));

        List<Fragment> fl = new ArrayList<Fragment>(); //填充要的Fragment頁卡
        for (int j = 0; j < mTabs.getTabCount(); j++) {
            fl.add(new FragmentFavorite_item());
        }

        mViewPager = (ViewPager) getView().findViewById(R.id.favoriteViewpager);
        if (mViewPager != null) {
            mViewPager.setAdapter(new SamplePagerAdapter(getFragmentManager(), fl, getActivity()));  //設定Adapter給viewPager
        }
        mTabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                FragmentFavorite_item.newInstance(mTabs.getTabAt(tab.getPosition()).getText());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        if (mTabs.getTabCount() < 5) {
            mTabs.setTabMode(TabLayout.MODE_FIXED);
            mTabs.setTabGravity(TabLayout.GRAVITY_FILL);
        } else
            mTabs.setTabMode(TabLayout.MODE_SCROLLABLE);
        for (int i = 0; i < mTabs.getTabCount(); i++)
            aryTab.add(mTabs.getTabAt(i).getText());
        mTabs.setupWithViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabs));


    }

    private class SamplePagerAdapter extends FragmentStatePagerAdapter {


        private Context context;
        List<Fragment> fragments; //切換頁面的Fragments
        private android.support.design.widget.TabLayout Tabs;

        public SamplePagerAdapter(FragmentManager fm, List<Fragment> f, Context context) {
            super(fm);
            this.context = context;
            fragments = f;
        }

        @Override
        public int getCount() { //頁卡數量
            return fragments.size();
        }

        @Override
        public Fragment getItem(int position) { //回傳Frament頁卡
            return FragmentFavorite_item.newInstance(mTabs.getTabAt(position).getText()); //從上方List<Fragment> fragments取得
        }

        @Override
        public CharSequence getPageTitle(int position) { //在此回傳Tab title string
            return aryTab.get(position);
        }


    }
}
package cn.picksomething.drawlayouttest.Toolbar;

import android.content.Context;
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

import java.util.ArrayList;
import java.util.List;

import cn.picksomething.drawlayouttest.R;
import cn.picksomething.drawlayouttest.TabFragment.TabHistory1;
import cn.picksomething.drawlayouttest.TabFragment.TabHistory2;

/**
 * Created by tt791_000 on 2016/7/11.
 */
public class FragmentHistory extends android.support.v4.app.Fragment {
    private android.support.design.widget.TabLayout mTabs;
    private ViewPager mPager;
    private SamplePagerAdapter adapter;
    private ArrayList<CharSequence> aryTab;

    private List<Fragment> fgs = null;
    private android.support.v4.app.FragmentManager manager;


    private  android.support.v4.app.FragmentTransaction ft;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.toolbar_list, container, false);


        mPager=(ViewPager)view.findViewById(R.id.list_pager);



        return view;
    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViewPager();
        manager = getFragmentManager();

    }
    private void initViewPager() {
        mTabs = (android.support.design.widget.TabLayout) getView().findViewById(R.id.list_tabs);
        aryTab =  new ArrayList<>();
        mTabs.addTab(mTabs.newTab().setText("食材排序"));
        mTabs.addTab(mTabs.newTab().setText("商品排序"));
        List<Fragment> fl=new ArrayList<Fragment>(); //填充要的Fragment頁卡
        for(int j = 0;j<mTabs.getTabCount();j++) {
            aryTab.add(mTabs.getTabAt(j).getText());
        }

        fl.add(new TabHistory1());
        fl.add(new TabHistory2());

        adapter = new SamplePagerAdapter(getFragmentManager(), fl , getActivity(),aryTab);

        if (mPager != null) {
            mPager.setAdapter(adapter);  //設定Adapter給viewPager
        }

        mTabs.setTabMode (TabLayout.MODE_FIXED);
        mTabs.setTabGravity(TabLayout.GRAVITY_FILL);
        mTabs.setupWithViewPager(mPager);
        mPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabs));
    }
    public SamplePagerAdapter getAdapter()
    {
        return adapter;
    }
    public static class SamplePagerAdapter extends FragmentStatePagerAdapter {
        private Context context;



        private ArrayList<CharSequence> aryTab;
        List<Fragment> fragments; //切換頁面的Fragments
        private android.support.design.widget.TabLayout Tabs;
        public SamplePagerAdapter(FragmentManager fm , List<Fragment> f, Context context, ArrayList<CharSequence> aryTab) {
            super(fm);
            this.context=context;

            this.aryTab = aryTab;
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
    //點擊返回回到fregmantMain
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
}

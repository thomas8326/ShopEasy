package cn.picksomething.drawlayouttest;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;


/**
 * Created by tt791_000 on 2016/7/31.
 */
public class ViewPagerAdapter  extends FragmentPagerAdapter {
    private final ArrayList<Fragment> mFragmentList = new ArrayList<>();

    Context context;
    ViewPager viewPager;

    public ViewPagerAdapter(FragmentManager manager, Context context, ViewPager viewPager) {
        super(manager);
        this.context = context;
        this.viewPager = viewPager;

    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFrag(Fragment fragment, Fragment fragment2) {
        mFragmentList.add(fragment);
        mFragmentList.add(fragment2);
    }
    //Overloading
    public void addFrag(Fragment fragment, Fragment fragment2,Fragment fragment3) {
        mFragmentList.add(fragment);
        mFragmentList.add(fragment2);
        mFragmentList.add(fragment3);
    }



    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

}



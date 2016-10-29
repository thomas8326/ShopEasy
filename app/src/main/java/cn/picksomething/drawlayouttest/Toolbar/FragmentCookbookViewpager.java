package cn.picksomething.drawlayouttest.Toolbar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import cn.picksomething.drawlayouttest.R;
import cn.picksomething.drawlayouttest.ViewPagerAdapter;

/**
 * Created by tt791_000 on 2016/9/1.
 */
public class FragmentCookbookViewpager  extends Fragment {

    private  android.support.v4.app.FragmentTransaction ft;
    private ArrayList<Fragment> fragmentsList;
    private android.support.v4.app.FragmentManager manager;
    private ViewPager mPager;
    private MyAdapter madapter;
    private ViewPagerAdapter adapter;
    //圓形圖示所需
    private ImageView TurnPageSign1;
    private ImageView TurnPageSign2;
    private ImageView TurnPageSign3;
    private RelativeLayout TurnPage1;
    private RelativeLayout TurnPage2;
    private RelativeLayout TurnPage3;
    public MyOnClick myclick;
    public MyPageChangeListener myPageChange;

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initCircle();
        initState();
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.toolbar_cookbook_viewpager, container, false);
        mPager=(ViewPager)view.findViewById(R.id.pager);
        addPage();
        mPager.setAdapter(madapter);
        return view;
    }

    public void addPage() {
        fragmentsList = new ArrayList<Fragment>();
        FragmentCookbook_list1 fragmentCookbook_list1 = new FragmentCookbook_list1();
        FragmentCookbook_list2 fragmentCookbook_list2 = new FragmentCookbook_list2();
        fragmentsList.add(fragmentCookbook_list1);
        fragmentsList.add(fragmentCookbook_list2);
        madapter = new MyAdapter(this.getChildFragmentManager(),fragmentsList);

    }
    public static class MyAdapter extends FragmentPagerAdapter {
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
    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        manager = getFragmentManager();
    }


    //初始化小圓標圖示
    private void initCircle()
    {   myclick = new MyOnClick();
        myPageChange = new MyPageChangeListener();
        TurnPageSign1 = (ImageView) getView().findViewById(R.id.TurnPageSign1);
        TurnPageSign2 = (ImageView)getView(). findViewById(R.id.TurnPageSign2);

        TurnPage1 = (RelativeLayout) getView().findViewById(R.id.TurnPage1);
        TurnPage2 = (RelativeLayout) getView().findViewById(R.id.TurnPage2);

        mPager.setOnPageChangeListener(myPageChange);
        TurnPage1.setOnClickListener(myclick);
        TurnPage2.setOnClickListener(myclick);


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
                break;
            case R.id.TurnPage2:case 1:
                TurnPageSign2.setImageResource(R.drawable.point_focused);
                mPager.setCurrentItem(1);
                break;

        }
    }
}

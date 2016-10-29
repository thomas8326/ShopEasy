package cn.picksomething.drawlayouttest.Toolbar;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
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

import java.util.ArrayList;
import java.util.List;

import cn.picksomething.drawlayouttest.Databases.DatabaseHelper;
import cn.picksomething.drawlayouttest.Mission_cook.Mission_cook;
import cn.picksomething.drawlayouttest.Mission_project.Mission_project;
import cn.picksomething.drawlayouttest.Point_cook.Point_cooking;
import cn.picksomething.drawlayouttest.Point_project.Point_project;
import cn.picksomething.drawlayouttest.R;

/**
 * Created by tt791_000 on 2016/7/11.
 */
public class FragmentMain extends android.support.v4.app.Fragment {

    private cn.picksomething.drawlayouttest.Databases.DatabaseHelper myDB;
    private Dialog dialogLeave;

    private ViewPager mPager;
    private android.support.design.widget.TabLayout mTabs;
    private ArrayList<CharSequence> aryTab;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity() .setTitle("ShopEasy");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_default, container,false);


    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        myDB= new DatabaseHelper(getActivity());
        initViewPager();
//

}

    private void initViewPager() {
        mTabs = (android.support.design.widget.TabLayout) getView().findViewById(R.id.default_tabs);
        mPager = (ViewPager) getView().findViewById(R.id.decontent_bottom);

        aryTab =  new ArrayList<>();
        mTabs.addTab(mTabs.newTab().setText("任務型食材"));
        mTabs.addTab(mTabs.newTab().setText("任務非食材"));
        mTabs.addTab(mTabs.newTab().setText("指定型食材"));
        mTabs.addTab(mTabs.newTab().setText("指定非食材"));
        List<Fragment> fl=new ArrayList<Fragment>(); //填充要的Fragment頁卡
        for(int j = 0;j<mTabs.getTabCount();j++) {
            aryTab.add(mTabs.getTabAt(j).getText());
        }
        fl.add(new Mission_cook());
        fl.add(new Mission_project());
        fl.add(new Point_cooking());
        fl.add(new Point_project());
        if (mPager != null) {
            mPager.setAdapter(new SamplePagerAdapter(getFragmentManager(), fl , getActivity()));  //設定Adapter給viewPager
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
//

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
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    dialogLeave =new AlertDialog.Builder(getActivity())
                            .setIcon(R.drawable.app_icon)
                            .setTitle("離開")
                            .setMessage("購物清單內的資料將會重製，確定要離開嗎?")
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    myDB.reHistory();
                                    Cursor cursor = myDB.getShoppinList2();
                                    int j=0,rows_num = cursor.getCount();
                                    if (cursor.getCount() == 0) {

                                    } else {
                                        while (j < rows_num) {
                                            myDB.addHisotry(cursor.getString(1),cursor.getString(2));
                                            // 未选中任何Item，position设置为-1
                                            j++;
                                            cursor.moveToNext();
                                        }
                                    }
                                    myDB.reList();
                                    System.exit(0);
                                    getActivity().finish();

                                }
                            }).show();
                }
                return false;
            }
        });
            }
}

package cn.picksomething.drawlayouttest.Toolbar;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import cn.picksomething.drawlayouttest.Databases.TestDatabaseHelper;
import cn.picksomething.drawlayouttest.R;

/**
 * Created by tt791_000 on 2016/7/11.
 */
public class FragmentCookbook  extends Fragment implements android.widget.SearchView.OnQueryTextListener{

    private  android.support.v4.app.FragmentTransaction ft;
    private android.support.v4.app.FragmentManager manager;
    private android.widget.SearchView sv;
    private ArrayList<String> food;
    ArrayList<Integer> ID ;
    ArrayList<String> IG ;
    private TestDatabaseHelper myDB;
    ArrayList<Integer> searchID;
    ArrayList<String> searchIG;


    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        defaultFragmentViewpager();
        sv = (android.widget.SearchView) getView().findViewById(R.id.cookbook_search);


        db();
        defaultSea();
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.toolbar_cookbook, container, false);

        return view;
    }
    protected  void defaultSea()
    {

        // 设置该SearchView默认是否自动缩小为图标
        sv.setIconifiedByDefault(false);
        // 为该SearchView组件设置事件监听器
        sv.setOnQueryTextListener(this);
        // 设置该SearchView显示搜索按钮
        sv.setSubmitButtonEnabled(true);
        // 设置该SearchView内默认显示的提示文本
        sv.setQueryHint("搜尋食譜關鍵字");

    }
    private void defaultFragmentViewpager()
    {
        FragmentCookbookViewpager f = new FragmentCookbookViewpager();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.popBackStack();
        fragmentManager.beginTransaction().replace(R.id.cookbook_frg, f).commit();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        manager = getFragmentManager();
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

    //輸入字符時，便呼叫這方法
    @Override
    public boolean onQueryTextChange(String newText) {
        Object[] obj = searchItem(newText);
        FragmentCookbookFragment f2 = new FragmentCookbookFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("obj",obj);
        bundle.putSerializable("_ID",searchID);
        bundle.putSerializable("_Ingredient",searchIG);
        f2.setArguments(bundle);

        FragmentManager fragmentManager = getFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.cookbook_frg, f2);
        fragmentTransaction.commit();


        return true;
    }
    private void db()
    {
        food = new ArrayList<String>();
        ID = new ArrayList<Integer>();
        IG = new ArrayList<String>();
        myDB = new TestDatabaseHelper(getActivity());
        myDB.OpenDB();
        Cursor cursor1 = myDB.SelectCookbookSearchChinese();
        Cursor cursor2=     myDB.SelectCookbookSearchForeign();
        Cursor cursor3 =      myDB.SelectCookbookSearchBread();
        Cursor cursor4=       myDB.SelectCookbookSearchDessert();
        int row1 = cursor1.getCount() + cursor2.getCount()+cursor3.getCount()+cursor4.getCount();
        int row2 = cursor1.getCount() + cursor2.getCount();
        int row3 = cursor1.getCount() + cursor2.getCount()+cursor3.getCount();

        for(int i = 0;i<row1;i++){
                if(i< cursor1.getCount()) {
                    food.add(cursor1.getString(0));
                    ID.add(cursor1.getInt(1));
                    IG.add(cursor1.getString(2));
                    cursor1.moveToNext();//將指標移至下一筆資料
                    }
                if(i>cursor1.getCount()&&i< row2)
                    {
                    food.add(cursor2.getString(0));
                    ID.add(cursor2.getInt(1));
                    IG.add(cursor2.getString(2));
                    cursor2.moveToNext();//將指標移至下一筆資料
                    }
                if(i>row2&&i< row3)
                {
                    food.add(cursor3.getString(0));
                    ID.add(cursor3.getInt(1));
                    IG.add(cursor3.getString(2));
                    cursor3.moveToNext();//將指標移至下一筆資料
                }
                if(i>row3&&i< row1)
                {
                        food.add(cursor4.getString(0));
                        ID.add(cursor4.getInt(1));
                        IG.add(cursor4.getString(2));
                        cursor4.moveToNext();//將指標移至下一筆資料
                }

        }

    }
    public Object[] searchItem(String name) {
        ArrayList<String> mSearchList = new ArrayList<String>();
        searchID = new ArrayList<Integer>();
        searchIG = new ArrayList<String>();
        for (int i = 0; i < food.size(); i++) {
            int index = food.get(i).indexOf(name);
            // 存在匹配的字串
            if (index != -1) {
                mSearchList.add(food.get(i));
                searchID.add(ID.get(i));
                searchIG.add(IG.get(i));
            }
        }
        return mSearchList.toArray();
    }

    // 點搜尋時會發生，不需要用到
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }



}

package cn.picksomething.drawlayouttest.Mission_cook;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import cn.picksomething.drawlayouttest.Databases.TestDatabaseHelper;
import cn.picksomething.drawlayouttest.MapButton;
import cn.picksomething.drawlayouttest.R;

/**
 * Created by tt791_000 on 2016/7/15.
 */
public class Mission_cook_cooking extends Activity implements android.widget.SearchView.OnQueryTextListener {

    private TextView msg;
    private android.widget.SearchView sv;
    private ArrayList<String> food;
    ArrayList<Integer> ID ;
    ArrayList<String> IG ;
    private TestDatabaseHelper myDB;
    ArrayList<Integer> searchID;
    ArrayList<String> searchIG;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mission_cooking_cook);
        init();
        sv = (android.widget.SearchView) findViewById(R.id.mc_CookSearch);
        db();
        defaultFrg();
        defaultSea();


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
            sv.setQueryHint("搜尋菜餚關鍵字");

        }


    protected  void defaultFrg()
    {
        Mission_cook_cooking_frg1 mission_cook_cooking_frg1 = new Mission_cook_cooking_frg1();
        android.app.FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.popBackStack();
        fragmentManager.beginTransaction().replace(R.id.mission_cook_frg, mission_cook_cooking_frg1).commit();


    }
    protected void init()
    {
        ImageView back;
        ImageView map;
        TextView title;
        title = (TextView) findViewById(R.id.TOPtitle);
        title.setText("烹飪料理");
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
                startActivity(new Intent(Mission_cook_cooking.this,MapButton.class));

            }
        });}

    //輸入字符時，便呼叫這方法
    @Override
    public boolean onQueryTextChange(String newText) {
        Object[] obj = searchItem(newText);
        Mission_cook_cooking_frg2 f2 = new Mission_cook_cooking_frg2();
        Bundle bundle = new Bundle();
        bundle.putSerializable("obj",obj);
        bundle.putSerializable("_ID",searchID);
        bundle.putSerializable("_Ingredient",searchIG);
        f2.setArguments(bundle);

        android.app.FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mission_cook_frg, f2);
        fragmentTransaction.commit();


        return true;
    }
    private void db()
    {
        food = new ArrayList<String>();
        ID = new ArrayList<Integer>();
        IG = new ArrayList<String>();
        myDB = new TestDatabaseHelper(this);
        myDB.OpenDB();
        Cursor cursor = myDB.SelectSearch();
        int row = cursor.getCount();
        for(int i = 0;i<row;i++){
            food.add(cursor.getString(0));
            ID.add(cursor.getInt(1));
            IG.add(cursor.getString(2));
            cursor.moveToNext();//將指標移至下一筆資料
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

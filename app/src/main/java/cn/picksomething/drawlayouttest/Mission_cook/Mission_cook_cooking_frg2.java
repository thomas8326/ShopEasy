package cn.picksomething.drawlayouttest.Mission_cook;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import cn.picksomething.drawlayouttest.Databases.TestDatabaseHelper;
import cn.picksomething.drawlayouttest.LastPage.CookBooklast;
import cn.picksomething.drawlayouttest.R;

/**
 * Created by tt791_000 on 2016/9/1.
 */
public class Mission_cook_cooking_frg2 extends Fragment{
    private ListView lv;
    private ArrayList<String> food;
    private TestDatabaseHelper myDB;
    private FragmentTransaction ft;
    public android.app.FragmentManager manager;
    private  ArrayList<Integer> mid;
    private  ArrayList<String> ig;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mission_cooking_cook_frg2, container,false);

        return view;
    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        food = new ArrayList<String>();
        manager =getFragmentManager();
        myDB = new TestDatabaseHelper(getActivity());
        myDB.OpenDB();
        Cursor cursor = myDB.SelectSearch();
        int row = cursor.getCount();
        for(int i = 0;i<row;i++){
            food.add(cursor.getString(0));
            cursor.moveToNext();//將指標移至下一筆資料
        }


        mid= new ArrayList<Integer>();
        ig = new ArrayList<String>();
        lv = (ListView)getView().findViewById(R.id.mc_lv);
        lv.setAdapter(new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1, food));
        Object[] str = (Object[])getArguments().get("obj");
        mid = (ArrayList<Integer>)getArguments().get("_ID");
        ig = (ArrayList<String>)getArguments().get("_Ingredient");
        updateLayout(str);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), CookBooklast.class);
                Bundle bundle = new Bundle();
                bundle.putInt("_ID",mid.get(position));
                bundle.putString("_Ingredient",ig.get(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
    //為了字串中任何字都能被搜尋
    public void updateLayout(Object[] obj) {
        lv.setAdapter(new ArrayAdapter<Object>(getActivity(),
                android.R.layout.simple_expandable_list_item_1, obj));
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
                    Mission_cook_cooking_frg1 fm = new Mission_cook_cooking_frg1();
                    ft = manager.beginTransaction();
                    ft.replace(R.id.mission_cook_frg, fm);
                    ft.addToBackStack(null);
                    ft.commit();
                    return true;
                }
                return false;
            }
        });
    }

}

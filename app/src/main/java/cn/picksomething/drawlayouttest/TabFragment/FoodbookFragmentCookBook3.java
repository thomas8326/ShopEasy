package cn.picksomething.drawlayouttest.TabFragment;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.picksomething.drawlayouttest.Databases.TestDatabaseHelper;
import cn.picksomething.drawlayouttest.NoScrollListView;
import cn.picksomething.drawlayouttest.R;

/**
 * Created by tt791_000 on 2016/9/8.
 */
public class FoodbookFragmentCookBook3  extends android.support.v4.app.Fragment{
    private NoScrollListView lv;
    private TestDatabaseHelper mydb;
    private List<String> setData1;
    private Cursor cursor;
    private Cursor data;
    private String foodbook;
    public static final String Part = "foodbook";
    public static final String id = "id";
    public static final String ig = "childrenpart";
    public static final String lots = "part";
    private String childrenpart;
    private String Sub;
    private String all;
    private int fid;
    public static FoodbookFragmentCookBook3 newInstance(String wPart,int wid,String wig,String wall) {
        FoodbookFragmentCookBook3 fragment = new FoodbookFragmentCookBook3();
        Bundle bundle = new Bundle();
        bundle.putString(Part, wPart);
        bundle.putInt(id, wid);
        bundle.putString(ig, wig);
        bundle.putString(lots, wall);
        fragment.setArguments(bundle);
        return fragment;
    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        foodbook = getArguments().getString(Part);
        fid = getArguments().getInt(id);
        childrenpart = getArguments().getString(ig);
        all = getArguments().getString(lots);
        dataconn();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.foodbook_ingredient, container, false);

        return view;
    }

    protected void dataconn()
    {
//
        mydb = new TestDatabaseHelper(getActivity());
        mydb.OpenDB();
        //食譜傳值
        lv = (NoScrollListView) getView().findViewById(R.id.foodbook_lastpagelistview);
        Sub = childrenpart.substring(0,1);


        //資料庫傳食譜的值
        if(foodbook.equals("all")||Sub.equals("A")) {
            cursor = mydb.SelectDoall(fid);
            data = mydb.SelectIGall(cursor.getInt(0));
        }
        else if(foodbook.equals("chinese")||Sub.equals("R")) {

            if (all.equals("all")) {
                cursor = mydb.SelectDoChineseall(fid);
            } else {
                cursor = mydb.SelectDoChinese(fid, childrenpart);
            }
            data = mydb.SelectIGChinese(cursor.getInt(0));
        }
        else if(foodbook.equals("foreign")||Sub.equals("M")) {
            if (all.equals("all")) {
                cursor = mydb.SelectDoforeignall(fid);
            } else {
                cursor = mydb.SelectDoforeign(fid, childrenpart);
            }
            data = mydb.SelectIGforeign(cursor.getInt(0));

        }
        else if(foodbook.equals("bread")||Sub.equals("Y")) {

            if (all.equals("all")) {
                cursor = mydb.SelectDobreadall(fid);

            } else {
                cursor = mydb.SelectDobread(fid, childrenpart);

            }
            data = mydb.SelectIGbread(cursor.getInt(0));

        }
        else if(foodbook.equals("dessert")||Sub.equals("L")) {
            if (all.equals("all")) {
                cursor = mydb.SelectDodessertall(fid);

            } else {
                cursor = mydb.SelectDodessert(fid, childrenpart);

            }
            data = mydb.SelectIGdessert(cursor.getInt(0));

        }

        setData1 = new ArrayList<String>();
        int column = data.getColumnCount() - 1;
        for (int i = 1; i <= column; i++) {
            if(data.getString(i)!=null)
                setData1.add(data.getString(i));
            else
                break;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1 ,setData1);
        lv.setAdapter(adapter);
    }


}
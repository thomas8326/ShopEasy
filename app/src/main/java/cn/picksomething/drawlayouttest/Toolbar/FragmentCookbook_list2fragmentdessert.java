package cn.picksomething.drawlayouttest.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.picksomething.drawlayouttest.Databases.TestDatabaseHelper;
import cn.picksomething.drawlayouttest.LastPage.CookBooklast3;
import cn.picksomething.drawlayouttest.R;

/**
 * Created by tt791_000 on 2016/8/23.
 */
public class FragmentCookbook_list2fragmentdessert extends Fragment {
    private GridView gridView;
    private TestDatabaseHelper mydb;
    private String[] imgText ;
    private Spinner spinner;
    private int pos;
    private ArrayAdapter<String> List;
    private Context mContext;
    private String[] dessertid = {"全部","L1", "L2", "L3", "L4", "L5", "L6", "L7", "L8", "L9"};
    private String[] dessert = {"全部", "水果", "巧克力、棉花糖", "布丁、果凍", "冰品、飲品", "各式甜湯、湯圓", "其他", "派塔、酥餅", "蛋糕", "優格、奶酪"
};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.toolbar_cookbook_listview, container,false);
        return view;
    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext = this.getContext();
        spinner = (Spinner) getView().findViewById(R.id.foodbookSp);
        List = new ArrayAdapter<String>(getActivity(), R.layout.myspiner, dessert);
        List.setDropDownViewResource(R.layout.myspiner);
        spinner.setAdapter(List);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                if(dessert[position] =="全部")
                    allcheck();
                else
                    onecheck(position);


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                allcheck();

            }
        });


    }
    private void allcheck(){
        mydb = new TestDatabaseHelper(getActivity());
        mydb.OpenDB();
        Cursor cursor = mydb.SelectdessertPath();
        int rownum = cursor.getCount();
        gridView = (GridView) getView().findViewById(R.id.cookbookgridview);
        imgText = new String[rownum];
        Class drawable = R.drawable.class;
        Field[] field = new Field[rownum];
        final int[] r_id = new int[rownum];
        for (int i = 0; i < rownum; i++) {
            imgText[i] = cursor.getString(0);
            try {
                field[i] = drawable.getField(cursor.getString(1));
                r_id[i] = field[i].getInt(field[i].getName());
                cursor.moveToNext();
            } catch (Exception e) {
                Log.e("ERROR", "PICTURE NOT FOUND！");
            }
        }

        java.util.List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < rownum; i++) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("image", r_id[i]);
            item.put("text", imgText[i]);
            items.add(item);
        }
        SimpleAdapter adapter = new SimpleAdapter(getActivity(),
                items, R.layout.toolbar_cookbook_imgitem, new String[]{"image", "text"},
                new int[]{R.id.imglistImg, R.id.imglistText});
        gridView.setNumColumns(2);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(),CookBooklast3.class);
                Bundle bundle = new Bundle();
                bundle.putString("foodbook","dessert");
                bundle.putString("childrenpart","all");
                bundle.putString("part","all");
                bundle.putInt("id",position+1);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });}

    private void onecheck(int sinenerpos){
        mydb = new TestDatabaseHelper(getActivity());
        mydb.OpenDB();
        pos = sinenerpos;
        Cursor cursor = mydb.SelectdessertPath(dessertid[sinenerpos]);
        int rownum = cursor.getCount();
        gridView = (GridView) getView().findViewById(R.id.cookbookgridview);
        imgText = new String[rownum];
        Class drawable = R.drawable.class;
        Field[] field = new Field[rownum];
        final int[] r_id = new int[rownum];
        for (int i = 0; i < rownum ; i++) {
            imgText[i]= cursor.getString(0);
            try {
                field[i] = drawable.getField(cursor.getString(1));
                r_id[i] = field[i].getInt(field[i].getName());
                cursor.moveToNext();
            } catch (Exception e) {
                Log.e("ERROR", "PICTURE NOT FOUND！");
            }
        }

        List<Map<String, Object>> items = new ArrayList<Map<String,Object>>();
        for (int i = 0; i < rownum ; i++) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("image", r_id[i]);
            item.put("text", imgText[i]);
            items.add(item);
        }
        SimpleAdapter adapter = new SimpleAdapter(getActivity(),
                items, R.layout.toolbar_cookbook_imgitem, new String[]{"image", "text"},
                new int[]{R.id.imglistImg, R.id.imglistText});
        gridView.setNumColumns(2);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(),CookBooklast3.class);
                Bundle bundle = new Bundle();
                bundle.putString("foodbook","dessert");
                bundle.putString("childrenpart",dessertid[pos]);
                bundle.putString("part","one");
                bundle.putInt("id",position+1);

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });}

}
package cn.picksomething.drawlayouttest.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.picksomething.drawlayouttest.Databases.TestDatabaseHelper;
import cn.picksomething.drawlayouttest.LastPage.CookBooklast3;
import cn.picksomething.drawlayouttest.R;

/**
 * Created by tt791_000 on 2016/8/22.
 */
public class FragmentCookbook_list1fragmentforeign extends Fragment {
    private GridView gridView;
    private TestDatabaseHelper mydb;
    private String[] imgText ;
    private Spinner spinner;
    private int pos;
    private ArrayAdapter<String> ChineseList;
    private Context mContext;
    private String[] foreignid = {"全部","M1", "M2", "M3", "M4", "M5", "M6"
            , "M7", "M8", "M9"};
    private String[] foreign = {"全部", "日式" ,"印度", "西式", "法式", "美式", "泰式", "越式", "義式", "韓式"
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
        ChineseList = new ArrayAdapter<String>(getActivity(), R.layout.myspiner, foreign);
        ChineseList.setDropDownViewResource(R.layout.myspiner);
        spinner.setAdapter(ChineseList);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                if(foreign[position] =="全部")
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
        Cursor cursor = mydb.SelectforeignPath();
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

        List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
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
                bundle.putString("foodbook","foreign");
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
        Cursor cursor = mydb.SelectforeignPath(foreignid[sinenerpos]);
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
            item.put("image",r_id[i]);
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
                bundle.putString("foodbook","foreign");
                bundle.putString("childrenpart",foreignid[pos]);
                bundle.putString("part","one");
                bundle.putInt("id",position+1);

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });}
    public static Bitmap readBitMap(Context context, int resId){
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        opt.inSampleSize = 4;
        //獲取資源圖片
        InputStream is = context.getResources().openRawResource(resId);
        return BitmapFactory.decodeStream(is,null,opt);
    }
}
package cn.picksomething.drawlayouttest.LastPage;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.picksomething.drawlayouttest.Databases.TestDatabaseHelper;
import cn.picksomething.drawlayouttest.ListAdapter;
import cn.picksomething.drawlayouttest.R;

/**
 * Created by tt791_000 on 2016/8/18.
 */
public class PartyLastF1 extends android.support.v4.app.Fragment {


    private ListView lv;
    //資料庫
    private TestDatabaseHelper myDB;

    private int mID;
    private String partyid;

    private List<String> group = new ArrayList<>();
    private ArrayList<List<String>> child = new  ArrayList<>();
    private ArrayList<List<String>> childName = new  ArrayList<>();
    private ArrayList<List<String>> childClass = new  ArrayList<>();
    //我的最愛日期
    private String dts;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listview_lastpagefrg, container, false);
        return view;
    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lv = (ListView) getView().findViewById(R.id.listview_lastpagelv);
        myDB = new TestDatabaseHelper(getActivity());
        myDB.OpenDB();

        initAdapter();

    }


    private void initAdapter() {//傳資料近來
        partyid = getArguments().getString("_partyid");
        Cursor cursor = myDB.getPartyList(partyid);
        Cursor cursor1 = myDB.getPartyListPosition(partyid);
        Cursor cursor2 = myDB.getPartyListClassName(partyid);

        int rows_num = cursor.getCount();
        //將資料庫資料一一取出來
        int j = 0;
        while (j<rows_num) {
            List<String> list = new ArrayList<>();
            List<String> list2 = new ArrayList<>();
            List<String> list3 = new ArrayList<>();
            group.add(cursor.getString(0));
            int column = cursor.getColumnCount() - 1;
            for (int i = 1; i <= column; i++) {
                if(cursor.getString(i)!=null)
                {
                    list.add(cursor.getString(i));
                    list2.add(cursor1.getString(i));
                    list3.add(cursor2.getString(i));
                }
                else
                    break;
            }
            child.add(list);//加進 某個rol裡面
            childName.add(list2);
            childClass.add(list3);
            j++;
            cursor.moveToNext();
        }

        lv.setAdapter(new ListAdapter(getActivity(),group,child,partyid,"Lastpage",childName,childClass));

    }



}

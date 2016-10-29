package cn.picksomething.drawlayouttest.TabFragment;

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
 * Created by tt791_000 on 2016/9/8.
 */
public class HealthFourWeek3 extends android.support.v4.app.Fragment{

    private TestDatabaseHelper myDB;
    private List<String> group = new ArrayList<>();
    private ArrayList<List<String>> child = new  ArrayList<>();
    private ArrayList<List<String>> childName = new  ArrayList<>();
    private ArrayList<List<String>> childClass = new  ArrayList<>();
    private ListView lv;

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        list();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listview_lastpagefrg, container, false);

        return view;
    }
    private void list()
    {
        lv = (ListView) getView().findViewById(R.id.listview_lastpagelv);
        myDB = new TestDatabaseHelper(getActivity());
        myDB.OpenDB();
        Cursor data = myDB.getFourWeek("FW3");
        Cursor data1 = myDB.getFourWeekPosition("FW3");
        Cursor data2 = myDB.getFourWeekClassName("FW3");

        int j=0,rows_num = data.getCount();

        while (j<rows_num) {
            List<String> list = new ArrayList<>();
            List<String> list2 = new ArrayList<>();
            List<String> list3 = new ArrayList<>();
            group.add(data.getString(0));
            int column = data.getColumnCount() - 1;
            for (int i = 1; i <= column; i++) {
                if(data.getString(i)!=null)
                {
                    list.add(data.getString(i));
                    list2.add(data1.getString(i));
                    list3.add(data2.getString(i));
                }
                else
                    break;
            }
            child.add(list);
            childName.add(list2);
            childClass.add(list3);
            j++;
            data.moveToNext();
        }
        lv.setAdapter(new ListAdapter(getActivity(),group,child,"FW3","Fourweek",childName,childClass));

    }

}

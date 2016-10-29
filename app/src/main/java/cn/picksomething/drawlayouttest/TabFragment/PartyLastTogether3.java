package cn.picksomething.drawlayouttest.TabFragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.picksomething.drawlayouttest.Databases.DatabaseHelper;
import cn.picksomething.drawlayouttest.Databases.TestDatabaseHelper;
import cn.picksomething.drawlayouttest.ListAdapter;
import cn.picksomething.drawlayouttest.R;

/**
 * Created by tt791_000 on 2016/9/11.
 */
public class PartyLastTogether3 extends android.support.v4.app.Fragment{
    private DatabaseHelper dbhelp;
    private TestDatabaseHelper myDB;
    private List<String> group ;
    private ArrayList<List<String>> child ;
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
        View view = inflater.inflate(R.layout.listview_lastpagefrgtogether, container, false);

        return view;
    }
    private void list()
    {group = new ArrayList<>();
        child = new  ArrayList<>();
        dbhelp = new DatabaseHelper(getActivity());
        lv = (ListView) getView().findViewById(R.id.listview_lastpagelv);
        myDB = new TestDatabaseHelper(getActivity());
        myDB.OpenDB();
        Cursor data = myDB.SelectTogether("C3");
        Cursor data1 = myDB.getTogetherPosition("C3");
        Cursor data2 = myDB.getTogetherClassName("C3");

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
        lv.setAdapter(new ListAdapter(getActivity(),group,child,"C3","Together",childName,childClass));
        Button fab = (Button) getView().findViewById(R.id.togetherBtn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog = new AlertDialog.Builder(getActivity())
                        .setTitle("要將這道菜單加入菜單管理嗎?")
                        .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                for(int i =0;i<group.size();i++)
                                    dbhelp.addDataFromTogetToFavorite(group.get(i),i,"C3","Together",child.get(i),"五福臨門慶團圓",childName.get(i),childClass.get(i));
                                Toast.makeText(getActivity(),"已將整套菜單加入菜單管理",Toast.LENGTH_SHORT).show();
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();

            }
        });

    }


}


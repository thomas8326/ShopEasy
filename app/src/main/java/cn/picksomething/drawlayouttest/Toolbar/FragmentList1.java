package cn.picksomething.drawlayouttest.Toolbar;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import cn.picksomething.drawlayouttest.CustomExpandSelectedAdapterFavorite;
import cn.picksomething.drawlayouttest.Databases.DatabaseHelper;
import cn.picksomething.drawlayouttest.MainExpandableAdapterFrovite;
import cn.picksomething.drawlayouttest.R;

/**
 * Created by tt791_000 on 2016/7/31.
 */
public class FragmentList1 extends Fragment implements ExpandableListView.OnChildClickListener, ExpandableListView.OnGroupClickListener,
        CustomExpandSelectedAdapterFavorite.ExpandCallBack, ExpandableListView.OnItemLongClickListener {
    private ExpandableListView lv;
    private DatabaseHelper myDB;
    private int[] str;
    private LinearLayout linearLayout;
    private List<String> group = new ArrayList<>();
    private List<List<String>> child = new ArrayList<>();
    private List<String> list;
    private MainExpandableAdapterFrovite myAdapter;// ListView的Adapter

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.toolbar_list1, container,false);
        return view;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDB = new DatabaseHelper(getActivity());
    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lv = (ExpandableListView) getView().findViewById(R.id.list1_explistview);
        linearLayout = (LinearLayout) getView().findViewById(R.id.ll1);


        Cursor cursor = myDB.getShoppinList1();
        int rows_num = cursor.getCount();
        int j = 0;
        while (j<rows_num) {
            List<String> list = new ArrayList<>();
            group.add(cursor.getString(0));
            int colnum = cursor.getColumnCount() - 1;
            for (int i = 1; i <= colnum; i++) {
                if(cursor.getString(i)!=null)
                    list.add(cursor.getString(i));
                else
                    break;
            }
            child.add(list);//加進 某個rol裡面
            j++;
            cursor.moveToNext();
        }
        myAdapter = new MainExpandableAdapterFrovite(getActivity(),group,child);
        lv.setAdapter(myAdapter);


        initListener();
    }

    @Override
    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
        return false;
    }

    private void initListener() {

        lv.setOnGroupClickListener(this);//點擊group動作
        lv.setOnChildClickListener(this);//點擊child動作
        lv.setOnItemLongClickListener(this);//點擊child動作
    }

    @Override
    public void setCheckedNum(int num) {

    }
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        final long packedPosition = lv.getExpandableListPosition(position);
        final int groupPosition = ExpandableListView.getPackedPositionGroup(packedPosition);
        final int childPosition = ExpandableListView.getPackedPositionChild(packedPosition);

        if (childPosition == -1) {//长按的是group，childPosition为-1
            linearLayout.setVisibility(View.VISIBLE);
            myAdapter.setShowChk(true);
            myAdapter.setItemCheck(group, child);
            myAdapter.setCallBack(this);
        }
        return true;
    }
    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

        myAdapter.toggleChild(groupPosition, childPosition);
        return true;
    }
}

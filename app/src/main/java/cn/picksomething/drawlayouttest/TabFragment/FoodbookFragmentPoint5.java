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
public class FoodbookFragmentPoint5 extends android.support.v4.app.Fragment{
    private NoScrollListView lv;
    private TestDatabaseHelper mydb;
    private List<String> setData1;
    private Cursor totlaID;


    public static final String id = "_ID";
    public static final String ig = "_Ingredient";

    private String mIG;
    private int mID;
    public static FoodbookFragmentPoint5 newInstance(int wid,String wig) {
        FoodbookFragmentPoint5 fragment = new FoodbookFragmentPoint5();
        Bundle bundle = new Bundle();

        bundle.putInt(id, wid);
        bundle.putString(ig, wig);

        fragment.setArguments(bundle);
        return fragment;
    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        mID = getArguments().getInt(id);
        mIG = getArguments().getString(ig);


        dataconn();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.foodbook_ingredient, container, false);

        return view;
    }

        private void dataconn()
    {
        mydb = new TestDatabaseHelper(getActivity());
        mydb.OpenDB();
        lv = (NoScrollListView) getView().findViewById(R.id.foodbook_lastpagelistview);

        totlaID = mydb.SelectIDPC(mID,mIG);
        Cursor data = mydb.SelectIG(totlaID.getInt(0));
        setData1 = new ArrayList<String>();
        int column = data.getColumnCount() - 1;
        for (int i = 1; i <= column; i++) {
            if(data.getString(i)!=null)
                setData1.add(data.getString(i));
            else
                break;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity() , android.R.layout.simple_list_item_1 ,setData1);
        lv.setAdapter(adapter);
}


}
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
public class FoodbookFragment1 extends android.support.v4.app.Fragment{
    private NoScrollListView lv;
    private TestDatabaseHelper mydb;
    private List<String> setData1;
    private Cursor data;
    private Cursor totlaID;
    private String mPart;
    public static final String Part = "Part";
    public static final String id = "_ID";
    public static final String ig = "_partid";
    private String mIG;
    private int mID;
    public static FoodbookFragment1 newInstance(String wPart,int wid,String wig) {
        FoodbookFragment1 fragment = new FoodbookFragment1();
        Bundle bundle = new Bundle();
        bundle.putString(Part, wPart);
        bundle.putInt(id, wid);
        bundle.putString(ig, wig);
        fragment.setArguments(bundle);
        return fragment;
    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPart = getArguments().getString(Part);
        mID = getArguments().getInt(id);
        mIG = getArguments().getString(ig);
        dataconn();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.foodbook_ingredient, container, false);

        return view;
    }

    protected void dataconn()
    {
//        dbtv = (TextView) findViewById(R.id.fooddoing);
        mydb = new TestDatabaseHelper(getActivity());
        lv = (NoScrollListView) getView().findViewById(R.id.foodbook_lastpagelistview);
//        dbhelp = new DatabaseHelper(this);
        mydb.OpenDB();
        if(mPart.equals("Lastpage"))
        {

            totlaID = mydb.SelectID(mID,mIG);
            data = mydb.SelectIGLastPage(totlaID.getInt(0));

        }
        else if(mPart.equals("HealthLast1"))
        {
            totlaID = mydb.SelectIDHealth(mID,mIG);

            data = mydb.SelectIGHealth(totlaID.getInt(0));

        }
        else if(mPart.equals("bodycare"))
        {
            totlaID = mydb.SelectIDBodycare(mID,mIG);


            data = mydb.SelectIGBodycare(totlaID.getInt(0));

        }
        else if(mPart.equals("cookhealthPart"))
        {

            totlaID = mydb.SelectIDCookhealth(mID,mIG);
            data = mydb.SelectIGCookhealth(totlaID.getInt(0));

        }
        else if(mPart.equals("exercise"))
        {

            totlaID = mydb.SelectIDExercise(mID,mIG);
            data = mydb.SelectIGExercise(totlaID.getInt(0));

        }
        if(mPart.equals("Festival"))
        {

            totlaID = mydb.SelectIDFestival(mID,mIG);
            data = mydb.SelectIGFestival(totlaID.getInt(0));
        }
        else if("Fourweek".equals(mPart)){
            totlaID = mydb.SelectIDFourweek(mID, mIG);
            data = mydb.SelectIGFourweek(totlaID.getInt(0));
        }
        else if("Together".equals(mPart)){
            totlaID = mydb.SelectIDTogether(mID, mIG);
            data = mydb.SelectIGTogether(totlaID.getInt(0));
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

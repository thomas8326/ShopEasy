package cn.picksomething.drawlayouttest.TabFragment;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.picksomething.drawlayouttest.Databases.TestDatabaseHelper;
import cn.picksomething.drawlayouttest.R;

/**
 * Created by tt791_000 on 2016/9/8.
 */
public class FoodbookFragment2 extends android.support.v4.app.Fragment {


    private TestDatabaseHelper mydb;
    private String mIG;
    private int mID;
    private TextView dbtv;
    private Cursor cursor;
    private Cursor totlaID;
    public static final String Part = "Part";
    public static final String id = "_ID";
    public static final String ig = "_partid";
    private String mPart;
    public static FoodbookFragment2 newInstance(String wPart,int wid,String wig) {
        FoodbookFragment2 fragment = new FoodbookFragment2();
        Bundle bundle = new Bundle();
        bundle.putString(Part, wPart);
        bundle.putInt(id, wid);
        bundle.putString(ig, wig);
        fragment.setArguments(bundle);
        return fragment;
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.foodbook_doing, container, false);

        return view;
    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPart = getArguments().getString(Part);
        mID = getArguments().getInt(id);
        mIG = getArguments().getString(ig);
        dataconn();
    }
    protected void dataconn()
    {
        dbtv = (TextView) getView().findViewById(R.id.foodbookdoing);
        mydb = new TestDatabaseHelper(getActivity());
        mydb.OpenDB();
        if(mPart.equals("Lastpage"))
        {
            totlaID = mydb.SelectID(mID,mIG);
            cursor = mydb.SelectDoLastPage(totlaID.getInt(0));
            dbtv.setText(cursor.getString(1));

        }
        else if(mPart.equals("HealthLast1"))
        {
            totlaID = mydb.SelectIDHealth(mID,mIG);
            cursor = mydb.SelectDoHealth(totlaID.getInt(0));
            dbtv.setText(cursor.getString(1));

        }
        else if(mPart.equals("bodycare"))
        {
            totlaID = mydb.SelectIDBodycare(mID,mIG);
            cursor = mydb.SelectDoBodycare(totlaID.getInt(0));
            dbtv.setText(cursor.getString(1));

        }
        else if(mPart.equals("cookhealthPart"))
        {
            totlaID = mydb.SelectIDCookhealth(mID,mIG);
            cursor = mydb.SelectDoCookhealth(totlaID.getInt(0));
            dbtv.setText(cursor.getString(1));
        }
        else if(mPart.equals("exercise"))
        {
            totlaID = mydb.SelectIDExercise(mID,mIG);
            cursor = mydb.SelectDoExercise(totlaID.getInt(0));
            dbtv.setText(cursor.getString(1));

        }
        if(mPart.equals("Festival"))
        {

            totlaID = mydb.SelectIDFestival(mID,mIG);
            cursor = mydb.SelectDoFestival(totlaID.getInt(0));
            dbtv.setText(cursor.getString(1));
        }
        else if("Fourweek".equals(mPart)){


            totlaID = mydb.SelectIDFourweek(mID, mIG);
            cursor = mydb.SelectDoFourweek(totlaID.getInt(0));
            dbtv.setText(cursor.getString(1));
        }
        else if("Together".equals(mPart)){
            totlaID = mydb.SelectIDTogether(mID, mIG);
            cursor = mydb.SelectDoTogether(totlaID.getInt(0));
            dbtv.setText(cursor.getString(1));
        }

    }
}

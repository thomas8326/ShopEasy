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
public class FoodbookFragmentPoint6 extends android.support.v4.app.Fragment {
    private TestDatabaseHelper mydb;
    private TextView dbtv;
    private Cursor totlaID;



    public static final String id = "_ID";
    public static final String ig = "_Ingredient";

    private String mIG;
    private int mID;

    public static FoodbookFragmentPoint6 newInstance(int wid, String wig) {
        FoodbookFragmentPoint6 fragment = new FoodbookFragmentPoint6();
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
        View view = inflater.inflate(R.layout.foodbook_doing, container, false);

        return view;
    }

    protected void dataconn() {

        dbtv = (TextView) getView().findViewById(R.id.foodbookdoing);

        mydb = new TestDatabaseHelper(getActivity());
        mydb.OpenDB();
        totlaID = mydb.SelectIDPC(mID,mIG);
        Cursor cursor = mydb.SelectDo(totlaID.getInt(0));

        dbtv.setText(cursor.getString(1));

        }

    }



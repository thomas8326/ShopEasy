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
public class FoodbookFragmentCookBook4 extends android.support.v4.app.Fragment {

    private TestDatabaseHelper mydb;
    private TextView dbtv;
    private Cursor cursor;
    private String foodbook;
    public static final String Part = "foodbook";
    public static final String id = "id";
    public static final String ig = "childrenpart";
    public static final String lots = "part";
    private String childrenpart;
    private String Sub;
    private String all;
    private int fid;

    public static FoodbookFragmentCookBook4 newInstance(String wPart, int wid, String wig, String wall) {
        FoodbookFragmentCookBook4 fragment = new FoodbookFragmentCookBook4();
        Bundle bundle = new Bundle();
        bundle.putString(Part, wPart);
        bundle.putInt(id, wid);
        bundle.putString(ig, wig);
        bundle.putString(lots, wall);
        fragment.setArguments(bundle);
        return fragment;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        foodbook = getArguments().getString(Part);
        fid = getArguments().getInt(id);
        childrenpart = getArguments().getString(ig);
        all = getArguments().getString(lots);
        dataconn();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.foodbook_doing, container, false);

        return view;
    }

    protected void dataconn() {

        mydb = new TestDatabaseHelper(getActivity());
        mydb.OpenDB();

        Sub = childrenpart.substring(0,1);
        dbtv = (TextView) getView().findViewById(R.id.foodbookdoing);
        //資料庫傳食譜的值
        if ("all".equals(foodbook) || "A".equals(Sub)) {
            cursor = mydb.SelectDoall(fid);

            dbtv.setText(cursor.getString(2));



        } else if ("chinese".equals(foodbook) || "R".equals(Sub)) {

            if ("all".equals(all)) {
                cursor = mydb.SelectDoChineseall(fid);
                dbtv.setText(cursor.getString(2));

            } else {
                cursor = mydb.SelectDoChinese(fid, childrenpart);
                dbtv.setText(cursor.getString(2));
            }


        } else if ("foreign".equals(foodbook) || "M".equals(Sub)) {
            if ("all".equals(all)) {
                cursor = mydb.SelectDoforeignall(fid);

                dbtv.setText(cursor.getString(2));
            } else {
                cursor = mydb.SelectDoforeign(fid, childrenpart);

                dbtv.setText(cursor.getString(2));
            }


        } else if ("bread".equals(foodbook) || "Y".equals(Sub)) {

            if ("all".equals(all)) {
                cursor = mydb.SelectDobreadall(fid);

                dbtv.setText(cursor.getString(2));
            } else {
                cursor = mydb.SelectDobread(fid, childrenpart);

                dbtv.setText(cursor.getString(2));
            }


        } else if ("dessert".equals(foodbook) || "L".equals(Sub)) {
            if ("all".equals(all)) {
                cursor = mydb.SelectDodessertall(fid);

                dbtv.setText(cursor.getString(2));
            } else {
                cursor = mydb.SelectDodessert(fid, childrenpart);

                dbtv.setText(cursor.getString(2));
            }


        }

    }


}
package cn.picksomething.drawlayouttest.LastPage;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.picksomething.drawlayouttest.Databases.DatabaseHelper;
import cn.picksomething.drawlayouttest.Databases.TestDatabaseHelper;
import cn.picksomething.drawlayouttest.ListAdapter;
import cn.picksomething.drawlayouttest.MapButton;
import cn.picksomething.drawlayouttest.R;

/**
 * Created by tt791_000 on 2016/8/16.
 */
public class BodyCareLast extends AppCompatActivity {
    private ListView lv;

    //資料庫
    private DatabaseHelper dbhelp;
    private TestDatabaseHelper myDB;
    private ArrayList<List<String>> child = new  ArrayList<>();
    private ArrayList<List<String>> childName = new  ArrayList<>();
    private ArrayList<List<String>> childClass = new  ArrayList<>();
    private int mID;
    private String bodycarePart;
    private List<String> group = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_lastpage);
        lv = (ListView) findViewById(R.id.listview_lastpagelv);
        myDB = new TestDatabaseHelper(this);
        myDB.OpenDB();
        dbhelp = new DatabaseHelper(this);

//        initLayout();

        initAdapter();
        initTitle();
        title();

    }


    private void initAdapter() {//傳資料近來
        //點擊冬令 傳H1與資料庫比對
        Bundle bundle = getIntent().getExtras();
        bodycarePart = bundle.getString("_bodycarePart");

        Cursor cursor = myDB.getBodyCareList(bodycarePart);
        Cursor cursor1 = myDB.getBodycarePosition(bodycarePart);
        Cursor cursor2 = myDB.getBodycareClassName(bodycarePart);
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
        lv.setAdapter(new ListAdapter(this,group,child,bodycarePart,"bodycare",childName,childClass));



    }



    protected void initTitle()//返回鍵與地圖
    {
        ImageView back;
        ImageView map;
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        map = (ImageView) findViewById(R.id.map);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BodyCareLast.this,MapButton.class));
            }
        });


    }

    private void title()
    {
        TextView title;
        title = (TextView) findViewById(R.id.TOPtitle);

         if(bodycarePart.equals("B1"))
            title.setText("眼睛");
        else if(bodycarePart.equals("B2"))
            title.setText("骨骼");
        else if(bodycarePart.equals("B3"))
            title.setText("大腦");
        else if(bodycarePart.equals("B4"))
            title.setText("元氣");
        else if(bodycarePart.equals("B5"))
            title.setText("肌肉");
        else if(bodycarePart.equals("B6"))
            title.setText("其他");

    }

}

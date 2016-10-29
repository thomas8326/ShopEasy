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

import cn.picksomething.drawlayouttest.Databases.TestDatabaseHelper;
import cn.picksomething.drawlayouttest.ListAdapter;
import cn.picksomething.drawlayouttest.MapButton;
import cn.picksomething.drawlayouttest.R;

/**
 * Created by tt791_000 on 2016/8/10.
 */
public class HealthLast1 extends AppCompatActivity{

    private  ListView lv;
    //資料庫
    private TestDatabaseHelper myDB;

    //食譜
    private int mID;
    private String healthPart;
    private ArrayList<List<String>> childName = new  ArrayList<>();
    private ArrayList<List<String>> childClass = new  ArrayList<>();
    private List<String> group = new ArrayList<>();
    private ArrayList<List<String>> child = new  ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_lastpage);
        lv = (ListView) findViewById(R.id.listview_lastpagelv);
        myDB = new TestDatabaseHelper(this);
        myDB.OpenDB();
        initAdapter();
        title();
        initTitle();
    }


    private void initAdapter() {//傳資料近來
        //點擊冬令 傳H1與資料庫比對
        Bundle bundle = getIntent().getExtras();
        healthPart = bundle.getString("_HealthPart");


        group = new ArrayList<>();
        child = new ArrayList<>();

        Cursor cursor = myDB.getHealthList(healthPart);
        Cursor cursor1 = myDB.getHealthListPosition(healthPart);
        Cursor cursor2 = myDB.getHealthListClassName(healthPart);
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

        lv.setAdapter(new ListAdapter(this,group,child,healthPart,"HealthLast1",childName,childClass));

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
                startActivity(new Intent(HealthLast1.this,MapButton.class));
            }
        });


    }

    private void title() {
        TextView title;
        title = (TextView) findViewById(R.id.TOPtitle);
         if (healthPart.equals("H1"))
            title.setText("冬令");
        else if (healthPart.equals("H2"))
            title.setText("坐月子");
        else if (healthPart.equals("H3"))
            title.setText("調理身體");
        else if (healthPart.equals("H4"))
            title.setText("養顏美容");
        else if (healthPart.equals("H5"))
            title.setText("生理期");
    }
}

package cn.picksomething.drawlayouttest.LastPage;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.picksomething.drawlayouttest.Databases.DatabaseHelper;
import cn.picksomething.drawlayouttest.Databases.TestDatabaseHelper;
import cn.picksomething.drawlayouttest.MapButton;
import cn.picksomething.drawlayouttest.R;

/**
 * Created by tt791_000 on 2016/9/10.
 */
public class PointProjectLast extends AppCompatActivity {

    private List<String> group = new ArrayList<>();
    private List<String> list = new ArrayList<>();
    private ArrayList<List<String>> child = new  ArrayList<>();

    //資料庫
    private TestDatabaseHelper myDB;
    private DatabaseHelper dbhelp;
    //我的最愛日期
    private ListView lv;

    private int mID;
    private String itemPart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_lastpage);
        lv = (ListView) findViewById(R.id.listview_lastpagelv);
        myDB = new TestDatabaseHelper(this);
        dbhelp = new DatabaseHelper(this);
        myDB.OpenDB();


        init();
        initAdapter();


    }

    private void initAdapter() {//傳資料近來
        //點擊冬令 傳H1與資料庫比對
        Bundle bundle = getIntent().getExtras();
        itemPart = bundle.getString("_itemPart");

        Cursor cursor = myDB.SelectPointProject(itemPart);

        int rows_num = cursor.getCount();
        //將資料庫資料一一取出來
        int j = 0;
        while (j < rows_num) {
            group.add(cursor.getString(3));
            list.add(cursor.getString(4));
            j++;
            cursor.moveToNext();
        }

        lv.setAdapter(new ListAdapter(this, group,list));
    }
    protected void init()
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
                startActivity(new Intent(PointProjectLast.this,MapButton.class));
            }
        });


    }
    public class ListAdapter extends BaseAdapter {

        private List<String> group;
        private List<String> list;
        private Context context;
        private DatabaseHelper dbhelp;

        public ListAdapter(Context context, List<String> group, List<String> list)
        {
            this.context = context;
            this.group = group;
            this.list = list;

        }

        @Override
        public int getCount()
        {
            //取得 ListView 列表 Item 的數量
            return group.size();
        }

        @Override
        public Object getItem(int position)
        {
            //取得 ListView 列表於 position 位置上的 Item
            return position;
        }

        @Override
        public long getItemId(int position)
        {
            //取得 ListView 列表於 position 位置上的 Item 的 ID
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent)
        {
            dbhelp = new DatabaseHelper(context);
            ViewHolder holder = null;
            if(convertView==null){
                convertView = LayoutInflater.from(context).inflate(R.layout.listview_item2, parent, false);
                holder = new ViewHolder((TextView) convertView.findViewById(R.id.listview_itemTextview), (Button) convertView.findViewById(R.id.listview_itemBtn));
                convertView.setTag(holder);
            }else{
                holder = (ViewHolder) convertView.getTag();
            }
            holder.txtTitle.setText(group.get(position));
            holder.txtTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
            holder.btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,"已加入購物清單",Toast.LENGTH_SHORT).show();
                    dbhelp.addlist(group.get(position),"project",list.get(position));
                    //存值進去
                }
            });


            return convertView;
        }
        private class ViewHolder {
            TextView txtTitle;
            Button btn;
            public ViewHolder(TextView txtTitle,  Button btn){
                this.txtTitle = txtTitle;
                this.btn = btn;
            }
        }
    }

}



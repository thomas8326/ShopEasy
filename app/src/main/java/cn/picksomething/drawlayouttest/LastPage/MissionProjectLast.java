package cn.picksomething.drawlayouttest.LastPage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
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
 * Created by tt791_000 on 2016/8/25.
 */
public class MissionProjectLast extends Activity {

    private List<String> group = new ArrayList<>();

    protected DatabaseHelper myDB;
    private ListView lv;
    private TestDatabaseHelper dbhelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.point_lastpage);
        lv = (ListView) findViewById(R.id.listview_lastpagelv);
        TextView title;
        title = (TextView) findViewById(R.id.TOPtitle);
        init();
        //將raw/sd1.db存入系統內以及將開啟資料庫
        dbhelp = new TestDatabaseHelper(this);
        dbhelp.OpenDB();

        //判斷從哪一個item點擊
        Bundle bundle = getIntent().getExtras();
        String mission_project = bundle.getString("Mission_project");
         if(mission_project.equals("P2"))
            title.setText("大掃除");
        else
            title.setText("聚會");

        Cursor cursor = dbhelp.SelectMissionProject(mission_project);

        int row = cursor.getCount();
        for(int i = 0;i<row;i++){
            group.add(cursor.getString(3));
            cursor.moveToNext();//將指標移至下一筆資料
        }

        lv.setAdapter(new ListAdapter(this,group));

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
                startActivity(new Intent(MissionProjectLast.this,MapButton.class));
            }
        });


    }
    public class ListAdapter extends BaseAdapter {

        private List<String> group;
        private ArrayList<List<String> > child;
        private Context context;
        private DatabaseHelper dbhelp;
        private String ig;
        private String part;
        public ListAdapter(Context context, List<String> group)
        {
            this.context = context;
            this.group = group;
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
                    dbhelp.addlist(group.get(position),"project");
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

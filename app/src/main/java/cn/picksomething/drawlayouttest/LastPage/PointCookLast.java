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
 * 烹飪料理
 */
public class PointCookLast  extends AppCompatActivity{

    private List<String> group = new ArrayList<>();
    private ArrayList<List<String>> child = new  ArrayList<>();
    private ArrayList<List<String>> childName = new  ArrayList<>();
    private ArrayList<List<String>> childClass = new  ArrayList<>();
    //資料庫
    private TestDatabaseHelper myDB;
    //我的最愛日期
    private ListView lv;

    private int mID;
    private String wtpart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_lastpage);
        lv = (ListView) findViewById(R.id.listview_lastpagelv);
        myDB = new TestDatabaseHelper(this);
        myDB.OpenDB();


        initTitle();
        initAdapter();


    }

    private void initAdapter() {//傳資料近來
        //點擊冬令 傳H1與資料庫比對
        Bundle bundle = getIntent().getExtras();
        int page = bundle.getInt("page");
        if(page ==1)
        {Intent intent = getIntent();
            Bundle bundl2 = intent.getExtras();
            wtpart =bundl2.getString("Ingredient");
            TextView title;
            title = (TextView) findViewById(R.id.TOPtitle);
            if(wtpart.equals("P1"))
                title.setText("牛");
            else if(wtpart.equals("P2"))
                title.setText("豬");
            else if(wtpart.equals("P3"))
                title.setText("雞");
            else if(wtpart.equals("P4"))
                title.setText("羊");
            else if(wtpart.equals("P5"))
                title.setText("鴨");
            else if(wtpart.equals("P6"))
                title.setText("鵝");
            else if(wtpart.equals("P7"))
                title.setText("魚");}
        else
        {   Intent intent2 = getIntent();
            Bundle bundle3 = intent2.getExtras();
            wtpart = bundle3.getString("Ingredient2");
            TextView title;
            title = (TextView) findViewById(R.id.TOPtitle);

         if(wtpart.equals("P8"))
            title.setText("海鮮");
        else if(wtpart.equals("P9"))
            title.setText("菇");
        else if(wtpart.equals("P10"))
            title.setText("豆");
        else if(wtpart.equals("P11"))
            title.setText("澱粉");
        else if(wtpart.equals("P12"))
            title.setText("葉菜根莖");
        else if(wtpart.equals("P13"))
            title.setText("果實");
        else if(wtpart.equals("P14"))
            title.setText("蛋");}

        Cursor cursor = myDB.Select(wtpart);
        Cursor cursor1 = myDB.getPosition(wtpart);
        Cursor cursor2 = myDB.getClassName(wtpart);


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

        lv.setAdapter(new ListAdapter(this,group,child,wtpart,"PointCookLast",childName,childClass));
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
                startActivity(new Intent(PointCookLast.this,MapButton.class));
            }
        });


    }
    public class ListAdapter extends BaseAdapter {

        private List<String> group;
        private ArrayList<List<String> > child;
        private ArrayList<List<String> > childName;
        private ArrayList<List<String> > childClass;
        private Context context;
        private DatabaseHelper dbhelp;
        private String ig;
        private String part;
        public ListAdapter(Context context, List<String> group,ArrayList<List<String>> child,String ig,String part,ArrayList<List<String>> childName,ArrayList<List<String>> childClass)
        {
            this.context = context;
            this.group = group;
            this.child = child;
            this.childName = childName;
            this.childClass = childClass;
            this.ig = ig;
            this.part = part;
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
                convertView = LayoutInflater.from(context).inflate(R.layout.listview_item, parent, false);
                holder = new ViewHolder((TextView) convertView.findViewById(R.id.listview_itemTextview), (Button) convertView.findViewById(R.id.listview_itemBtn));
                convertView.setTag(holder);
            }else{
                holder = (ViewHolder) convertView.getTag();
            }
            holder.txtTitle.setText(group.get(position));
            holder.txtTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(context,CookBooklast.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("Part", part);
                    bundle.putInt("_ID",position+1);//傳遞
                    bundle.putString("_Ingredient",ig);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
            holder.btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,"已加入收藏清單",Toast.LENGTH_SHORT).show();
                    //存值進去
                    dbhelp.addDataFromCBToFavorite(group.get(position),position+1,ig,part,child.get(position),childName.get(position),childClass.get(position));
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

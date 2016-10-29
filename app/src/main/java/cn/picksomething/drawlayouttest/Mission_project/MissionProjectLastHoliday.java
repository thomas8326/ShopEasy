package cn.picksomething.drawlayouttest.Mission_project;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import cn.picksomething.drawlayouttest.Databases.TestDatabaseHelper;
import cn.picksomething.drawlayouttest.LastPage.MissionProjectLast2;
import cn.picksomething.drawlayouttest.MapButton;
import cn.picksomething.drawlayouttest.R;
import cn.picksomething.drawlayouttest.pictureavoidOOM;

/**
 * Created by tt791_000 on 2016/9/10.
 */
public class MissionProjectLastHoliday  extends AppCompatActivity {
    private TestDatabaseHelper dbhelp;
    private Integer num = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mission_project_recycle);
        ArrayList<String> myDataset = new ArrayList<>();
        ArrayList<Bitmap> myDatasetimg = new ArrayList<>();
        ArrayList<String> myDatasetid = new ArrayList<>();
        ArrayList<String> list = new ArrayList<>();
        ArrayList<ArrayList<String> > myItem = new ArrayList<>();
        ArrayList<ArrayList<Bitmap> > childimg = new ArrayList<>();

        init();
        dbhelp = new TestDatabaseHelper(this);
        dbhelp.OpenDB();
        pictureavoidOOM pc = new pictureavoidOOM();
        Bitmap bm1 = readBitMap(this,R.drawable.holiday1);
        Bitmap bm2 = readBitMap(this,R.drawable.holiday2);
        Bitmap bm3 = readBitMap(this,R.drawable.holiday3);
        Bitmap bm4 = readBitMap(this,R.drawable.holiday4);
        Bitmap bm5 = readBitMap(this,R.drawable.holiday5);
        Bitmap bm6 = readBitMap(this,R.drawable.holiday6);
        Bitmap bm7 = readBitMap(this,R.drawable.holiday7);
        myDataset.add("春節");
        myDatasetimg.add(bm1);
        myDataset.add("元宵節");
        myDatasetimg.add(bm2);
        myDataset.add("端午節");
        myDatasetimg.add(bm3);
        myDataset.add("中秋節");
        myDatasetimg.add(bm4);
        myDataset.add("聖誕節");
        myDatasetimg.add(bm5);
        myDataset.add("萬聖節");
        myDatasetimg.add(bm6);
        myDataset.add("情人節");
        myDatasetimg.add(bm7);
        Bitmap[] bbm = new Bitmap[14];
        bbm[0] = readBitMap(this,R.drawable.ho_1);
        bbm[1] = readBitMap(this,R.drawable.ho_2);
        bbm[2]= readBitMap(this,R.drawable.ho_3);
        bbm[3] = readBitMap(this,R.drawable.ho_4);
        bbm[4]= readBitMap(this,R.drawable.ho_5);
        bbm[5] = readBitMap(this,R.drawable.ho_6);
        bbm[6] = readBitMap(this,R.drawable.ho_7);
        bbm[7] = readBitMap(this,R.drawable.ho_8);
        bbm[8] = readBitMap(this,R.drawable.ho_9);
        bbm[9] =readBitMap(this,R.drawable.ho_10);
        bbm[10] =readBitMap(this,R.drawable.ho_11);
        bbm[11] =readBitMap(this,R.drawable.ho_12);
        bbm[12] =readBitMap(this,R.drawable.ho_13);
        bbm[13] =readBitMap(this,R.drawable.ho_14);
        for(int j=1;j<myDataset.size()+1;j++) {
            Cursor cursor =  dbhelp.SelectMissionProject2("KK"+j);
            myDatasetid.add("KK"+j);
            list = new ArrayList<>();
            ArrayList<Bitmap> listimg = new ArrayList<>();
            for (int i = 0; i < 2; i++) {
                list.add(cursor.getString(3));
                listimg.add(bbm[num]);
                num++;
                cursor.moveToNext();
            }
            myItem.add(list);
            childimg.add(listimg);
        }

        MyAdapter myAdapter = new MyAdapter(myDataset,myDatasetid,myItem,myDatasetimg,childimg);
        RecyclerView mList = (RecyclerView) findViewById(R.id.mision_project_recycle);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mList.setLayoutManager(layoutManager);
        mList.setAdapter(myAdapter);
        myAdapter.setOnItemClickListener(new MyAdapter.OnRecyclerViewItemClickListener(){
            @Override
            public void onItemClick(View view , String data){
                Bundle bundle = new Bundle();
                bundle.putString("itemPart",data);
                Intent intent = new Intent(MissionProjectLastHoliday.this,MissionProjectLast2.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    public static Bitmap readBitMap(Context context, int resId){
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        opt.inSampleSize = 2;
        //獲取資源圖片
        InputStream is = context.getResources().openRawResource(resId);
        return BitmapFactory.decodeStream(is,null,opt);
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
                startActivity(new Intent(MissionProjectLastHoliday.this,MapButton.class));
            }
        });


    }
    public static class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> implements View.OnClickListener{
        private List<String> mData;
        private List<String> mData2;
        private ArrayList<ArrayList<String>> mItem;
        private ArrayList<Bitmap> myDatasetimg ;
        private ArrayList<ArrayList<Bitmap>> myDatasetbitimg;
        public class ViewHolder extends RecyclerView.ViewHolder {
            private LinearLayout lv1;
            private LinearLayout lv2;
            public TextView TitleItem;
            public TextView item1;
            public TextView item2;
            public TextView itemall;
            public ImageView img;
            public ImageView img2;
            public ImageView img3;
            public ViewHolder(View v) {
                super(v);
                TitleItem = (TextView) v.findViewById(R.id.info_text);
                item1 = (TextView) v.findViewById(R.id.mp_itemtext1);
                item2 = (TextView) v.findViewById(R.id.mp_itemtext2);
                itemall = (TextView) v.findViewById(R.id.mp_itemall);
                img = (ImageView) v.findViewById(R.id.img);
                img2 = (ImageView) v.findViewById(R.id.img2);
                img3 = (ImageView) v.findViewById(R.id.img3);
                lv1 = (LinearLayout) v.findViewById(R.id.layoutcard);
                lv2 = (LinearLayout) v.findViewById(R.id.layoutcard2);
            }
        }

        public MyAdapter(List<String> data,List<String> data2,ArrayList<ArrayList<String>> data3,ArrayList<Bitmap> data4,ArrayList<ArrayList<Bitmap>> data5) {
            mData = data;
            mData2 = data2;
            mItem = data3;
            myDatasetimg = data4;
            myDatasetbitimg = data5;
        }
        //define interface
        public  interface OnRecyclerViewItemClickListener {
            void onItemClick(View view , String data);
        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.mission_project_cardview, parent, false);
            ViewHolder vh = new ViewHolder(v);
            v.setOnClickListener(this);
            return vh;
        }
        private OnRecyclerViewItemClickListener mOnItemClickListener = null;
        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                //注意这里使用getTag方法获取数据
                mOnItemClickListener.onItemClick(v,(String)v.getTag());
            }
        }
        public void setColor(ViewHolder holder,int position)
        {
            if(position == 0)
            {
                holder.lv1.setBackgroundColor(0xFFF71D36);
                holder.lv2.setBackgroundColor(0xFFF71D36);
            }
            else if(position == 1)
            {
                holder.lv1.setBackgroundColor(0xFFffa837);
                holder.lv2.setBackgroundColor(0xFFffa837);
            }
            else if(position == 2)
            {
                holder.lv1.setBackgroundColor(0xFF80c7fb);
                holder.lv2.setBackgroundColor(0xFF80c7fb);
            }
            else if(position == 3)
            {
                holder.lv1.setBackgroundColor(0xFF4576f2);
                holder.lv2.setBackgroundColor(0xFF4576f2);
            }
            else if(position == 4)
            {
                holder.lv1.setBackgroundColor(0xFF8af9aa);
                holder.lv2.setBackgroundColor(0xFF8af9aa);
            }
            else if(position == 5)
            {   holder.lv1.setBackgroundColor(0xFFe382f3);
                holder.lv2.setBackgroundColor(0xFFe382f3);}
            else if(position == 6)
            {
                holder.lv1.setBackgroundColor(0xFFf88990);
                holder.lv2.setBackgroundColor(0xFFf88990);
            }


        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            holder.TitleItem.setText(mData.get(position));
            holder.img.setImageBitmap(myDatasetimg.get(position));
            holder.item1.setText(mItem.get(position).get(0));
            holder.img2.setImageBitmap(myDatasetbitimg.get(position).get(0));
            holder.item2.setText(mItem.get(position).get(1));
            holder.img3.setImageBitmap(myDatasetbitimg.get(position).get(1));
            holder.itemView.setTag(mData2.get(position));
            setColor(holder,position);
        }
        public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
            this.mOnItemClickListener = listener;
        }


        @Override
        public int getItemCount() {
            return mData.size();
        }
    }
}
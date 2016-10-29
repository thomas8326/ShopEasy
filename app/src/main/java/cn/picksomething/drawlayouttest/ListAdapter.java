package cn.picksomething.drawlayouttest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.picksomething.drawlayouttest.Databases.DatabaseHelper;
import cn.picksomething.drawlayouttest.LastPage.CookBooklast2;

/**
 * Created by tt791_000 on 2016/9/7.
 */
public class ListAdapter extends BaseAdapter {
    private LayoutInflater mLayInf;
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
    public ListAdapter(Context context, List<String> group,ArrayList<List<String>> child,String ig,String part)
    {
        this.context = context;
        this.group = group;
        this.child = child;

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
        final ViewHolder holder;
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

                Intent intent = new Intent(context,CookBooklast2.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("Part", part);
                    bundle.putInt("_ID",position+1);//傳遞
                    bundle.putString("_partid",ig);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
            }
        });
        holder.btn.setBackgroundResource(R.drawable.listviewbtn);
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"已加入菜單管理",Toast.LENGTH_SHORT).show();
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

package cn.picksomething.drawlayouttest.Toolbar;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.picksomething.drawlayouttest.Databases.DatabaseHelper;
import cn.picksomething.drawlayouttest.R;

/**
 * Created by tt791_000 on 2016/7/31.
 */
public class FragmentList3 extends Fragment implements View.OnClickListener{

    //長按批量刪除
    private static final int NOSELECT_STATE = -1;// 未選中任何資料
    private List<Integer> list_deleteid= new ArrayList<Integer>();
    private List<String> list_delete = new ArrayList<String>();// 需要刪除的資料
    private boolean isMultiSelect = false;// 是否處於多選

    private ListView listView;

    private DatabaseHelper myDB;
    private List<Integer> list1id;
    private List<String> list;
    private TextView toolbarlistid;
    private MyAdapter adapter;// ListView的Adapter
    private LinearLayout linearLayout;
    private Button bt_cancel, bt_delete;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.toolbar_list2, container,false);
        return view;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list = new ArrayList<String>();

        list1id = new ArrayList<>();

    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        linearLayout = (LinearLayout) getView().findViewById(R.id.ll1);

        listView = (ListView) getView().findViewById(R.id.list2_listview);

        bt_cancel = (Button) getView().findViewById(R.id.bt_cancel);
        bt_delete = (Button) getView().findViewById(R.id.bt_delete);

        bt_cancel.setOnClickListener(this);
        bt_delete.setOnClickListener(this);

        list();



    }
    private void list()
    {
        myDB = new DatabaseHelper(getActivity());
        Cursor data = myDB.getShoppinList3("project");
        int i=0,j=0,rows_num = data.getCount();
        while (j<rows_num) {
            list1id.add(data.getInt(0));
            list.add(data.getString(1));
            j++;
            data.moveToNext();
        }
        adapter = new MyAdapter(getActivity(), list,list1id,NOSELECT_STATE);
        listView.setAdapter(adapter);
    }
    public class MyAdapter extends BaseAdapter {
        //刪除
        private HashMap<Integer, Integer> isCheckBoxVisible;// 是否顯示checkbox
        private HashMap<Integer, Boolean> isChecked;// 是否被記錄

        private List<Integer> arraysid = null;
        private List<String> arrays = null;
        private Context mContext;
        public MyAdapter(Context mContext, List<String> arrays, List<Integer> arraysid,int position) {
            this.mContext = mContext;
            this.arrays = arrays;
            this.arraysid = arraysid;
            isCheckBoxVisible = new HashMap<Integer, Integer>();
            isChecked = new HashMap<Integer, Boolean>();
            if (isMultiSelect) {
                for (int i = 0; i < arrays.size(); i++) {
                    isCheckBoxVisible.put(i, CheckBox.VISIBLE);
                    isChecked.put(i, false);
                }
            } else {
                for (int i = 0; i < arrays.size(); i++) {
                    isCheckBoxVisible.put(i, CheckBox.INVISIBLE);
                    isChecked.put(i, false);
                }
            }



        }
        public int getCount() {
            return this.arrays.size();
        }
        public Object getItem(int position) {
            return null;
        }
        public long getItemId(int position) {
            return position;
        }
        public View getView(final int position, View view, ViewGroup arg2) {
            final ViewHolder viewHolder;
            if (view == null) {
                viewHolder = new ViewHolder();
                view = LayoutInflater.from(mContext).inflate(R.layout.toolbar_list_item, null);
                viewHolder.tvTitle = (TextView) view.findViewById(R.id.title);
                viewHolder.cb = (CheckBox) view.findViewById(R.id.cb_select);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            final String str = arrays.get(position);
            final int id = arraysid.get(position);
            viewHolder.tvTitle.setText(this.arrays.get(position));
            viewHolder.cb.setChecked(isChecked.get(position));
            viewHolder.cb.setVisibility(isCheckBoxVisible.get(position));
            // ListView每一个Item的长按事件
            view.setOnLongClickListener(new onMyLongClick(position, arrays,arraysid));
            view.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // 处于多选模式
                    if (isMultiSelect) {
                        if (viewHolder.cb.isChecked()) {
                            viewHolder.cb.setChecked(false);
                            list_delete.remove(str);
                            list_deleteid.add(id);

                        } else {
                            viewHolder.cb.setChecked(true);
                            list_delete.add(str);
                            list_deleteid.add(id);
                        }
                    }
                }
            });
            return view;

        }
        final class ViewHolder {
            TextView tvTitle;
            CheckBox cb;
        }
        class onMyLongClick implements View.OnLongClickListener {

            private int position;
            private List<String> list;
            private List<Integer> listid;
            // 获取数据，与长按Item的position
            public onMyLongClick(int position, List<String> list,List<Integer> listid) {
                this.position = position;
                this.list = list;
                this.listid = listid;
            }

            // 在长按监听时候，切记将监听事件返回ture
            @Override
            public boolean onLongClick(View v) {
                isMultiSelect = true;
                list_delete.clear();
                list_deleteid.clear();
                // 添加长按Item到删除数据list中
                linearLayout.setVisibility(View.VISIBLE);
                for (int i = 0; i < list.size(); i++) {
                    adapter.isCheckBoxVisible.put(i, CheckBox.VISIBLE);
                }
                // 根据position，设置ListView中对应的CheckBox为选中状态
                adapter = new MyAdapter(getActivity(), list,listid, position);
                listView.setAdapter(adapter);
                return true;
            }


        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 取消选择
            case R.id.bt_cancel:
                isMultiSelect = false;// 退出多选模式
                list_delete.clear();// 清楚选中的数据
                // 重新加载Adapter
                adapter = new MyAdapter(getActivity(), list,list1id, NOSELECT_STATE);
                listView.setAdapter(adapter);
                linearLayout.setVisibility(View.GONE);
                break;
            // 删除
            case R.id.bt_delete:
                isMultiSelect = false;
                // 将数据从list中移除
                for (int i = 0; i < list.size(); i++) {
                    for (int j = 0; j < list_delete.size(); j++) {
                        if (list1id.get(i)==list_deleteid.get(j)) {

                            myDB.dellist(list1id.get(i));

                        }
                    }
                }
                list_delete.clear();
                list_deleteid.clear();
                // 重新加载Adapter
                adapter = new MyAdapter(getActivity(), list,list1id,NOSELECT_STATE);
                listView.setAdapter(adapter);
                linearLayout.setVisibility(View.GONE);
                break;

            default:
                break;
        }
    }


}


package cn.picksomething.drawlayouttest;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.picksomething.drawlayouttest.Databases.DatabaseHelper;
import cn.picksomething.drawlayouttest.LastPage.CookBooklast;
import cn.picksomething.drawlayouttest.LastPage.CookBooklast2;
import cn.picksomething.drawlayouttest.LastPage.CookBooklast3;

/**
 * Created by tt791_000 on 2016/9/7.
 */
public class FragmentFavorite_item extends android.support.v4.app.Fragment  implements ExpandableListView.OnChildClickListener, ExpandableListView.OnGroupClickListener,
                    CustomExpandSelectedAdapterFavorite.ExpandCallBack, ExpandableListView.OnItemLongClickListener {
    private DatabaseHelper myDB;
    private Boolean haveMenu = false;
    private List<String> group = new ArrayList<>();
    private List<Integer> groupid = new ArrayList<>();
    private List<List<String>> child = new ArrayList<>();
    private List<List<String>> childName = new ArrayList<>();
    private List<List<String>> childClass = new ArrayList<>();
    private ExpandableListView lv;
    private MainExpandableAdapterFrovite adapter;
    private Button menubtn;
    private Button menubtn2;
    private Button menubtn3;
    public static final String MTAB = "mTabs";
    public CharSequence ft;
    final WindowManager.LayoutParams params = new WindowManager.LayoutParams();
    public static FragmentFavorite_item newInstance(CharSequence arg) {
        FragmentFavorite_item fragment = new FragmentFavorite_item();
        Bundle bundle = new Bundle();
        bundle.putCharSequence(MTAB, arg);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.toolbar_favorite_pageitem, container, false); //實體化佈局
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ft = getArguments().getString(MTAB);
        myDB = new DatabaseHelper(getActivity());
        menubtn = (Button) getView().findViewById(R.id.menubtn);
        menubtn2 = (Button) getView().findViewById(R.id.menubtn2);
        menubtn3 = (Button) getView().findViewById(R.id.menubtn3);
        lv = (ExpandableListView) getView().findViewById(R.id.treeview);


        Cursor data = myDB.favoritesupportDistinct(ft);
        Cursor data2 = myDB.favoritesupportDistinctPosition(ft);
        Cursor data3 = myDB.favoritesupportDistinctClass(ft);
            int rows_num = data.getCount();
            //將資料庫資料一一取出來
            int j = 0;
            while (j < rows_num) {
                List<String> list = new ArrayList<>();
                List<String> list2 = new ArrayList<>();
                List<String> list3 = new ArrayList<>();
                group.add(data.getString(1));
                groupid.add(data.getInt(0));
                int column = data.getColumnCount() - 1;
                for (int i = 2; i <= column; i++) {
                    if (data.getString(i) != null)
                    {
                        list.add(data.getString(i));
                        list2.add(data2.getString(i));
                        list3.add(data3.getString(i));

                    }
                    else
                        break;
                }
                child.add(list);//加進 某個rol裡面
                childName.add(list2);
                childClass.add(list3);
                j++;
                data.moveToNext();
            }

        adapter = new MainExpandableAdapterFrovite(getActivity(), group, child);
        lv.setAdapter(adapter);
        adapter.setShowChk(true);
        adapter.setItemCheck(group, child);
        adapter.setCallBack(this);
        addGroup();
        initListener();
    }


        private void addGroup() {

            Cursor dataclass = myDB.favoritesupportDistinct();
             final ArrayList<String> Tabs = new ArrayList<>();
            Tabs.add("我喜歡的");
            if (dataclass.getCount() == 0) {
                Toast.makeText(getActivity(), "尚無商品，選購商品加入最愛吧!", Toast.LENGTH_SHORT).show();
            } else {
                int l = 0;
                while (l < dataclass.getCount()) {
                    if (dataclass.getString(0).equals("我喜歡的")) {
                    } else {
                        Tabs.add(dataclass.getString(0));
                    }
                    l++;
                    dataclass.moveToNext();
                }
            }
            menubtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final View item = LayoutInflater.from(getActivity()).inflate(R.layout.edittext, null);

                     new AlertDialog.Builder(getActivity())
                            .setTitle("加入一個新的菜餚清單")
                                .setItems(Tabs.toArray(new String[Tabs.size()]), new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                            String name = Tabs.get(which);
                                        Boolean gptf;
                                        for (int i = 0; i < group.size(); i++) {
                                            gptf = adapter.getCheckeditemGP(i);
                                            if (gptf == true) {
                                                myDB.addDataFromExClass(groupid.get(i), name);
                                                haveMenu = true;
                                            }
//                                            Log.i("Test","group: "+groupid.get(i)+" T/F: "+gptf);
                                        }
                                        if(!haveMenu)
                                        {
                                            Toast.makeText(getActivity(), "請先勾選菜餚名稱 再加入清單", Toast.LENGTH_SHORT).show();
                                        }
                                        else
                                        {
                                            Toast.makeText(getActivity(), "加入新列表 " + name + " 成功", Toast.LENGTH_SHORT).show();
                                        }
                                        adapter.notifyDataSetChanged();
                                    }
                                })
                                .setPositiveButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                })
                                .setNegativeButton("新增菜餚清單", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Dialog builder = new AlertDialog.Builder(getActivity())
                                            .setTitle("新增菜餚清單").setView(item)
                                                   .setPositiveButton("取消", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                            }
                                        }).setPositiveButton("確定", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                EditText editText = (EditText) item.findViewById(R.id.editem);
                                                Boolean gptf;
                                                for (int i = 0; i < group.size(); i++) {
                                                    gptf = adapter.getCheckeditemGP(i);
                                                    if (gptf == true) {
                                                        myDB.addDataFromExClass(groupid.get(i), editText.getText().toString());
                                                        haveMenu = true;
                                                    }
                                                }
                                                if(!haveMenu)
                                                {
                                                    Toast.makeText(getActivity(), "請先勾選菜餚名稱 再加入菜餚清單", Toast.LENGTH_SHORT).show();
                                                }
                                                else
                                                {
                                                    Toast.makeText(getActivity(), "加入新列表 " + editText.getText().toString() + " 成功", Toast.LENGTH_SHORT).show();
                                                }
                                                adapter.notifyDataSetChanged();
                                            }
                                        }).show();
                                        WindowManager.LayoutParams params = builder.getWindow().getAttributes();
                                        params.y =600;
                                        builder.getWindow().setGravity(Gravity.CENTER|Gravity.TOP);
                                        builder.getWindow().setAttributes(params);
                                    }}).show();

                }

            });

            menubtn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(),"已加入購物清單",Toast.LENGTH_SHORT).show();
                    HashMap<String, ArrayList<String>> checkedItem= adapter.getCheckedItemName();
                    ArrayList<String> Food = new ArrayList<String>();
                    ArrayList<List<String>> Ingredients = new ArrayList<List<String>>();
                    ArrayList<List<String>> Name = new ArrayList<List<String>>();
                    ArrayList<List<String>> Class = new ArrayList<List<String>>();
                    ArrayList<String> keys = new ArrayList<String>(checkedItem.keySet());
                    for (int i =0; i < keys.size(); i++)
                    {
                        if(checkedItem.containsKey(keys.get(i)))
                        {
                            Food.add(keys.get(i));
                            Ingredients.add(checkedItem.get(keys.get(i)));
                            Name.add(childName.get(i));
                            Class.add(childClass.get(i));
                        }
                    }
                    myDB.addDataFromExToShoppingList(Food,Ingredients,Name,Class);

                }
            });
            menubtn3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dialog dialog = new AlertDialog.Builder(getActivity())
                            .setTitle("確定刪除選取資料?")
                            .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    for (int i = 0; i < group.size(); i++) {
                                        Boolean gptf;
                                        gptf = adapter.getCheckeditemGP(i);
                                        if (gptf == true)
                                        {myDB.delDataFromExClass(groupid.get(i));
                                            haveMenu = true;
                                        }
                                    }
                                    if(!haveMenu)
                                    {
                                        Toast.makeText(getActivity(), "請先勾選菜餚名稱 再刪除菜餚", Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                    {
                                        Toast.makeText(getActivity(), "已將您勾選的菜餚刪除", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            }).show();

                }
            });

        }

        @Override
        public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
            return false;
        }

        private void initListener() {

            lv.setOnGroupClickListener(this);//點擊group動作
            lv.setOnChildClickListener(this);//點擊child動作
            lv.setOnItemLongClickListener(this);//點擊child動作
        }

        @Override
        public void setCheckedNum(int num) {

        }
    @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

            final long packedPosition = lv.getExpandableListPosition(position);
            final int groupPosition = ExpandableListView.getPackedPositionGroup(packedPosition);
            final int childPosition = ExpandableListView.getPackedPositionChild(packedPosition);

        if (childPosition == -1) {//长按的是group，childPosition为-1
            Cursor cursor = myDB.favoritesupportCookBook(groupid.get(groupPosition));
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            String foodbook = cursor.getString(2);
            if(cursor.getString(2).equals("PointCookLast"))
            {
                intent.setClass(getActivity(), CookBooklast.class);
                bundle.putInt("_ID",cursor.getInt(0));
                bundle.putString("_Ingredient",cursor.getString(1));
                bundle.putString("Part",cursor.getString(2));
            }
            else  if(foodbook.equals("all")||foodbook.equals("chinese")||foodbook.equals("foreign")||foodbook.equals("bread")||foodbook.equals("dessert"))
            {
                intent.setClass(getActivity(), CookBooklast3.class);
                bundle.putInt("id",cursor.getInt(0));
                bundle.putString("childrenpart",cursor.getString(1));
                bundle.putString("foodbook",cursor.getString(2));
                bundle.putString("part","one");

            }
            else {
                 intent.setClass(getActivity(), CookBooklast2.class);
                bundle.putInt("_ID",cursor.getInt(0));
                bundle.putString("_partid",cursor.getString(1));
                bundle.putString("Part",cursor.getString(2));
            }
            intent.putExtras(bundle);
            startActivity(intent);
        }
        return true;
    }

        @Override
        public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

            adapter.toggleChild(groupPosition, childPosition);
            return true;
        }

}
package cn.picksomething.drawlayouttest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author yangh 2016/6/8 10:36
 * @version V1.0
 * @Description
 */
public class MainExpandableAdapterFrovite extends CustomExpandSelectedAdapterFavorite  {
    List<String> group = new ArrayList<>();
    List<List<String>> child = new ArrayList<>();
    Context context;

    public MainExpandableAdapterFrovite(Context context, List<String> group, List<List<String>> child) {
        this.context = context;
        this.group = group;
        this.child = child;
    }

    @Override
    public int getGroupCount() {
        return group.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return child.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return group.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return child.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.ex_parent_layout, parent, false);
            holder = new GroupHolder();
            holder.tv = (TextView) convertView.findViewById(R.id.gp_tv);
            holder.gp_cb = (CheckBox) convertView.findViewById(R.id.gp_cb);
            convertView.setTag(holder);
        } else {
            holder = (GroupHolder) convertView.getTag();
        }
        holder.tv.setText(group.get(groupPosition));
        setGroupChkView(groupPosition, holder.gp_cb);
        return convertView;
    }

    class GroupHolder {
        TextView tv;
        CheckBox gp_cb;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.ex_child_layout, parent, false);
            holder = new ChildHolder();
            holder.tv = (TextView) convertView.findViewById(R.id.cd_tv);
            holder.cd_cb = (CheckBox) convertView.findViewById(R.id.cd_cb);
            convertView.setTag(holder);
        } else {
            holder = (ChildHolder) convertView.getTag();
        }
        holder.tv.setText(child.get(groupPosition).get(childPosition));
        setChildChkView(groupPosition, childPosition, holder.cd_cb);
        return convertView;
    }

    class ChildHolder {
        TextView tv;
        CheckBox cd_cb;
    }

    public HashMap<String, ArrayList<String>> getCheckedItemName()
    {
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        ArrayList<Boolean> ifchecked = getCheckeditem();
        int checkedPosition = 0;
        int temp = -1;

        for (int i = 0; i < group.size(); i++)
        {
            ArrayList<String> names = new ArrayList<>();
            for (int j = 0; j < child.get(i).size(); j++)
            {
                if (ifchecked.get(checkedPosition))
                {
                    names.add(child.get(i).get(j));

                    if(temp != i)
                    {
                        map.put(group.get(i), names);
                        temp = i;
                    }

                }
                checkedPosition++;
            }

        }

//        names.add(""+child.size());
//        names.add(""+group.size());

        return map;
    }
    public  ArrayList<String> getCheckedItemNameFV() {
        ArrayList<Boolean> ifchecked = getCheckeditem();
        ArrayList<String> names = new ArrayList<>();
        int checkedPosition = 0;
        int temp = -1;

        for (int i = 0; i < group.size(); i++) {
            names = new ArrayList<>();
            for (int j = 0; j < child.get(i).size(); j++) {
                if (ifchecked.get(checkedPosition)) {
                    names.add(child.get(i).get(j));

                }
                checkedPosition++;
            }

        }
        return names;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


}

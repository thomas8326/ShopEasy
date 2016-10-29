package cn.picksomething.drawlayouttest;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yangh 2016/7/14 10:58
 * @version V1.0
 * @Description
 */
public class CustomExpandSelectedAdapterFavorite extends BaseExpandableListAdapter {
    private ExpandCallBack callBack;
    public Boolean check = false;
    public String passStr = null;
    public  int passId;
    public int num ;
    public int count=0;
    public int ChooseNum;
    public void setCallBack(ExpandCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public int getGroupCount() {
        return 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
    private boolean isShowChk = false;

    public boolean isShowChk() {
        return isShowChk;
    }

    public void setShowChk(boolean isShowChk) {
        this.isShowChk = isShowChk;
        this.notifyDataSetChanged();
    }

    private List<Map<Integer, Boolean>> gpList = new ArrayList<>();
    private List<List<Map<Integer, Boolean>>> cdList = new ArrayList<>();

    public <T> void setItemCheck(List<T> list1, List<List<T>> list2) {
        gpList = new ArrayList<>();
        cdList = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++) {
            Map<Integer, Boolean> m = new HashMap<>();//Integer 是 key，boolean是對應key的內容
            m.put(i, false);
            gpList.add(i, m);//group的選取狀態
            Map<Integer, Boolean> map = new HashMap<>();
            List<Map<Integer, Boolean>> list = new ArrayList<>();
            for (int j = 0; j < list2.get(i).size(); j++) {
                map.put(j, false);
                list.add(j, map);
            }
            cdList.add(i, list);
        }
    }

    public <T> void unCheckAll(List<T> list1, List<List<T>> list2) {
        setItemCheck(list1, list2);
        passStr = null;
        count = 0;
        this.notifyDataSetChanged();//// 通知資料被變動，更新顯示內容
    }
    public List<Integer> ChooseGroupName()
    {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < gpList.size(); i++) {
            if(gpList.get(i).get(i)== true)
            {
                list.add(i);
            }
        }
        return  list;

    }

    public <T> void checkAll(List<T> list1, List<List<T>> list2) {
        gpList = new ArrayList<>();
        cdList = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++) {
            Map<Integer, Boolean> m = new HashMap<>();
            m.put(i, true);
            gpList.add(i, m);
            Map<Integer, Boolean> map = new HashMap<>();
            List<Map<Integer, Boolean>> list = new ArrayList<>();
            for (int j = 0; j < list2.get(i).size(); j++) {
                map.put(j, true);
                list.add(j, map);
            }
            cdList.add(i, list);
        }
        this.notifyDataSetChanged();
    }

    public Boolean getCheckeditemGP(int row)
    {
        Boolean list = false;
        list = gpList.get(row).get(row);

        return  list;

    }
    public ArrayList<Boolean> getCheckeditem()
    {
        ArrayList<Boolean> list = new ArrayList<>();
        for (int i = 0; i < gpList.size(); i++) {
            for (int j = 0; j < cdList.get(i).size(); j++) {
                list.add(cdList.get(i).get(j).get(j));
            }
        }
        return  list;

    }
    public int getCheckedNum() {
        num = 0;
        for (int i = 0; i < cdList.size(); i++) {
            for (int j = 0; j < cdList.get(i).size(); j++) {
                if (cdList.get(i).get(j).get(j)) {
                    num++;
                }
            }
        }
        return num;
    }//得到選取個數

    public void toggleChild(int groupPos, int childPos) {
        if (isShowChk()) {
            if (cdList.get(groupPos).get(childPos).get(childPos)) {
                cdList.get(groupPos).get(childPos).put(childPos, false);
            } else {
                cdList.get(groupPos).get(childPos).put(childPos, true);
            }
            if (cdList.get(groupPos).get(childPos).containsValue(false)) {
                gpList.get(groupPos).put(groupPos, false);
            } else {
                gpList.get(groupPos).put(groupPos, true);
            }
            this.notifyDataSetChanged();
            callBack.setCheckedNum(getCheckedNum());
        }
    }///控制點擊狀態，沒選點了便選取，以精選了點擊變取消選擇
    public int ChooseOneGroup()
    {
        ArrayList<String> list = new ArrayList<>();
        ChooseNum = 0;
        int groupNum=0;
        for (int i = 0; i < gpList.size(); i++) {
            if(gpList.get(i).get(i)== true)
            {
                ChooseNum++;
                groupNum = i;
            }
        }
        return  groupNum+1;

    }
    public List<String> ChooseGroupName(List<String> group)
    {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < gpList.size(); i++) {
            if(gpList.get(i).get(i)== true)
            {
                list.add(group.get(i));
            }
        }
        return  list;

    }

    public ArrayList<Integer> ChooseLotsGroup()
    {
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < gpList.size(); i++) {
            if(gpList.get(i).get(i)== true)
            {
                list.add(i+1);
            }
        }
        return  list;
    }

    private void toggleGroup(int groupPos) {
        if (isShowChk()) {
            if (gpList.get(groupPos).get(groupPos)) {
                gpList.get(groupPos).put(groupPos, false);
            } else {
                gpList.get(groupPos).put(groupPos, true);
            }
            if (gpList.get(groupPos).get(groupPos)) {
                for (int i = 0; i < cdList.get(groupPos).size(); i++) {
                    cdList.get(groupPos).get(i).put(i, true);
                    check= true;
                }
            } else {
                for (int i = 0; i < cdList.get(groupPos).size(); i++) {
                    cdList.get(groupPos).get(i).put(i, false);
                    check = false;
                }
            }
            this.notifyDataSetChanged();
        }
    }

    protected void setChildChkView(int groupPos, int childPos, CheckBox checkBox) {
        if (isShowChk()) {
            checkBox.setVisibility(View.VISIBLE);
            checkBox.setFocusable(false);
            checkBox.setClickable(false);
            if (cdList.get(groupPos).get(childPos).get(childPos)) {
                checkBox.setChecked(true);
            } else {
                checkBox.setChecked(false);
            }
        } else {
            checkBox.setVisibility(View.GONE);
        }
    }

    protected void setGroupChkView(final int groupPos, CheckBox checkBox) {
        if (isShowChk()) {
            checkBox.setVisibility(View.VISIBLE);
            checkBox.setFocusable(false);
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toggleGroup(groupPos);
                    callBack.setCheckedNum(getCheckedNum());
                }
            });
            if (gpList.get(groupPos).get(groupPos)) {
                checkBox.setChecked(true);
            } else {
                checkBox.setChecked(false);
            }
        } else {
            checkBox.setVisibility(View.GONE);
        }
    }
    public interface ExpandCallBack{
        void setCheckedNum(int num);
    }

}

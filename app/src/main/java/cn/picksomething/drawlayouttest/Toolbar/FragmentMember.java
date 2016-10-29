package cn.picksomething.drawlayouttest.Toolbar;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.picksomething.drawlayouttest.R;

/**
 * Created by tt791_000 on 2016/7/11.
 */
public class FragmentMember extends android.support.v4.app.Fragment{
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.toolbar_member, container, false);
        return view;
    }
    //點擊返回回到fregmantMain
    private android.support.v4.app.FragmentManager manager;
    private  android.support.v4.app.FragmentTransaction ft;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        manager = getFragmentManager();

    }
    @Override
    public void onResume() {
        super.onResume();
        getFocus();
    }

    //主界面获取焦点
    private void getFocus() {
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    FragmentMain fm = new FragmentMain();
                    ft = manager.beginTransaction();
                    ft.replace(R.id.flContent, fm);
                    ft.addToBackStack(null);
                    ft.commit();
                    return true;
                }
                return false;
            }
        });
    }
}

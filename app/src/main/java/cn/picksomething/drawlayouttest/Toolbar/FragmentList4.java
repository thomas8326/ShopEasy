package cn.picksomething.drawlayouttest.Toolbar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.InputStream;

import cn.picksomething.drawlayouttest.R;

/**
 * Created by tt791_000 on 2016/7/31.
 */
public class FragmentList4 extends Fragment {
    private ImageView map,ex1,ex2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.toolbar_list3, container,false);
        return view;
    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        map = (ImageView) getView().findViewById(R.id.map);
        ex1= (ImageView) getView().findViewById(R.id.explanation1);
        ex2 = (ImageView) getView().findViewById(R.id.explanation2);
        Bitmap bm1 = readBitMap(getActivity(),R.drawable.list3_map);
        Bitmap bm2 = readBitMap(getActivity(),R.drawable.list3_1);
        Bitmap bm3 = readBitMap(getActivity(),R.drawable.list3_2);
        map.setImageBitmap(bm1);
        ex1.setImageBitmap(bm2);
        ex2.setImageBitmap(bm3);
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
}

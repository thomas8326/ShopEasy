package cn.picksomething.drawlayouttest.Mission_cook;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import cn.picksomething.drawlayouttest.LastPage.PointCookLast;
import cn.picksomething.drawlayouttest.R;
import cn.picksomething.drawlayouttest.pictureavoidOOM;

/**
 * Created by tt791_000 on 2016/9/1.
 */
public class Mission_cook_cooking_frg1 extends Fragment {
    private ImageView cc1;
    private ImageView cc2;
    private ImageView cc3;
    private ImageView cc4;
    private ImageView cc5;
    private ImageView cc6;
    private ImageView cc7;
    private ImageView cc8;
    private ImageView cc9;
    private ImageView cc10;
    private ImageView cc11;
    private ImageView cc12;
    private ImageView cc13;
    private ImageView cc14;
    private FragmentTransaction ft;
    private FragmentManager manager;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mission_cooking_cook_frg1, container,false);
        return view;
    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initButton();
        manager = getFragmentManager();
        cc1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),PointCookLast.class);
                Bundle bundle = new Bundle();
                bundle.putString("Ingredient", "P1");
                bundle.putInt("page",1);
                intent.putExtras(bundle);
                startActivity(intent);
                recybit();
            }
        });
        cc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),PointCookLast.class);
                Bundle bundle = new Bundle();
                bundle.putString("Ingredient", "P2");
                bundle.putInt("page",1);
                intent.putExtras(bundle);
                startActivity(intent);
                recybit();
            }
        });
        cc3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),PointCookLast.class);
                Bundle bundle = new Bundle();
                bundle.putString("Ingredient", "P3");
                bundle.putInt("page",1);
                intent.putExtras(bundle);
                startActivity(intent);
                recybit();
            }
        });
        cc4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),PointCookLast.class);
                Bundle bundle = new Bundle();
                bundle.putString("Ingredient", "P4");
                bundle.putInt("page",1);
                intent.putExtras(bundle);
                startActivity(intent);
                recybit();
            }
        });
        cc5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),PointCookLast.class);
                Bundle bundle = new Bundle();
                bundle.putString("Ingredient", "P5");
                bundle.putInt("page",1);
                intent.putExtras(bundle);
                startActivity(intent);
                recybit();
            }
        });
        cc6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),PointCookLast.class);
                Bundle bundle = new Bundle();
                bundle.putString("Ingredient", "P6");
                bundle.putInt("page",1);
                intent.putExtras(bundle);
                startActivity(intent);
                recybit();
            }
        });
        cc7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),PointCookLast.class);
                Bundle bundle = new Bundle();
                bundle.putString("Ingredient", "P7");
                bundle.putInt("page",1);
                intent.putExtras(bundle);
                startActivity(intent);
                recybit();
            }
        });
        cc8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),PointCookLast.class);
                Bundle bundle = new Bundle();
                bundle.putString("Ingredient2", "P8");
                intent.putExtras(bundle);
                startActivity(intent);
                recybit();
            }
        });
        cc9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),PointCookLast.class);
                Bundle bundle = new Bundle();
                bundle.putString("Ingredient2", "P9");
                intent.putExtras(bundle);
                startActivity(intent);
                recybit();
            }
        });
        cc10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),PointCookLast.class);
                Bundle bundle = new Bundle();
                bundle.putString("Ingredient2", "P10");
                intent.putExtras(bundle);
                startActivity(intent);
                recybit();
            }
        });
        cc11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),PointCookLast.class);
                Bundle bundle = new Bundle();
                bundle.putString("Ingredient2", "P11");
                intent.putExtras(bundle);
                startActivity(intent);
                recybit();
            }
        });
        cc12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),PointCookLast.class);
                Bundle bundle = new Bundle();
                bundle.putString("Ingredient2", "P12");
                intent.putExtras(bundle);
                startActivity(intent);
                recybit();
            }
        });
        cc13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),PointCookLast.class);
                Bundle bundle = new Bundle();
                bundle.putString("Ingredient2", "P13");
                intent.putExtras(bundle);
                startActivity(intent);
                recybit();
            }
        });
        cc14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),PointCookLast.class);
                Bundle bundle = new Bundle();
                bundle.putString("Ingredient2", "P14");
                intent.putExtras(bundle);
                startActivity(intent);
                recybit();
            }
        });
    }
    protected void initButton()
    {
        cc1 =(ImageView)getView().findViewById(R.id.CC1);
        cc2 =(ImageView)getView().findViewById(R.id.CC2);
        cc3 =(ImageView)getView().findViewById(R.id.CC3);
        cc4 =(ImageView)getView().findViewById(R.id.CC4);
        cc5 =(ImageView)getView().findViewById(R.id.CC5);
        cc6 =(ImageView)getView().findViewById(R.id.CC6);
        cc7 =(ImageView)getView().findViewById(R.id.CC7);
        cc8 =(ImageView)getView().findViewById(R.id.CC8);
        cc9 =(ImageView)getView().findViewById(R.id.CC9);
        cc10 =(ImageView)getView().findViewById(R.id.CC10);
        cc11 =(ImageView)getView().findViewById(R.id.CC11);
        cc12 =(ImageView)getView().findViewById(R.id.CC12);
        cc13 =(ImageView)getView().findViewById(R.id.CC13);
        cc14 =(ImageView)getView().findViewById(R.id.CC14);
        pictureavoidOOM pictureavoidOOM = new pictureavoidOOM();
        Bitmap bm1 = pictureavoidOOM.readBitMap(getActivity(),R.drawable.i1);
        Bitmap bm2 =  pictureavoidOOM.readBitMap(getActivity(),R.drawable.i2);
        Bitmap bm3 = pictureavoidOOM.readBitMap(getActivity(),R.drawable.i3);
        Bitmap bm4 = pictureavoidOOM.readBitMap(getActivity(),R.drawable.i4);
        Bitmap bm5 = pictureavoidOOM.readBitMap(getActivity(),R.drawable.i5);
        Bitmap bm6 = pictureavoidOOM.readBitMap(getActivity(),R.drawable.i6);
        Bitmap bm7 =pictureavoidOOM.readBitMap(getActivity(),R.drawable.i7);
        Bitmap bm8 = pictureavoidOOM.readBitMap(getActivity(),R.drawable.i8);
        Bitmap bm9 =  pictureavoidOOM.readBitMap(getActivity(),R.drawable.i9);
        Bitmap bm10 = pictureavoidOOM. readBitMap(getActivity(),R.drawable.beans);
        Bitmap bm11 = pictureavoidOOM.readBitMap(getActivity(),R.drawable.i11);
        Bitmap bm12 = pictureavoidOOM. readBitMap(getActivity(),R.drawable.i12);
        Bitmap bm13= pictureavoidOOM.readBitMap(getActivity(),R.drawable.i13);
        Bitmap bm14= pictureavoidOOM.readBitMap(getActivity(),R.drawable.i14);
        cc1.setImageBitmap(bm1);
        cc2.setImageBitmap(bm2);
        cc3.setImageBitmap(bm3);
        cc4.setImageBitmap(bm4);
        cc5.setImageBitmap(bm5);
        cc6.setImageBitmap(bm6);
        cc7.setImageBitmap(bm7);
        cc8.setImageBitmap(bm8);
        cc9.setImageBitmap(bm9);
        cc10.setImageBitmap(bm10);
        cc11.setImageBitmap(bm11);
        cc12.setImageBitmap(bm12);
        cc13.setImageBitmap(bm13);
        cc14.setImageBitmap(bm14);
    }
    protected void recybit()
    {   Bitmap bm1 = pictureavoidOOM.readBitMap(getActivity(),R.drawable.i1);
        Bitmap bm2 =  pictureavoidOOM.readBitMap(getActivity(),R.drawable.i2);
        Bitmap bm3 = pictureavoidOOM.readBitMap(getActivity(),R.drawable.i3);
        Bitmap bm4 = pictureavoidOOM.readBitMap(getActivity(),R.drawable.i4);
        Bitmap bm5 = pictureavoidOOM.readBitMap(getActivity(),R.drawable.i5);
        Bitmap bm6 = pictureavoidOOM.readBitMap(getActivity(),R.drawable.i6);
        Bitmap bm7 =pictureavoidOOM.readBitMap(getActivity(),R.drawable.i7);
        Bitmap bm8 = pictureavoidOOM.readBitMap(getActivity(),R.drawable.i8);
        Bitmap bm9 =  pictureavoidOOM.readBitMap(getActivity(),R.drawable.i9);
        Bitmap bm10 = pictureavoidOOM. readBitMap(getActivity(),R.drawable.beans);
        Bitmap bm11 = pictureavoidOOM.readBitMap(getActivity(),R.drawable.i11);
        Bitmap bm12 = pictureavoidOOM. readBitMap(getActivity(),R.drawable.i12);
        Bitmap bm13= pictureavoidOOM.readBitMap(getActivity(),R.drawable.i13);
        Bitmap bm14= pictureavoidOOM.readBitMap(getActivity(),R.drawable.i14);

        bm1.recycle();
        bm2.recycle();
        bm3.recycle();
        bm4.recycle();
        bm5.recycle();
        bm6.recycle();
        bm7.recycle();
        bm8.recycle();
        bm9.recycle();
        bm10.recycle();
        bm11.recycle();
        bm12.recycle();
        bm13.recycle();
        bm14.recycle();
        System.gc();
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
                    getActivity().finish();
                    return true;
                }
                return false;
            }
        });
    }



}

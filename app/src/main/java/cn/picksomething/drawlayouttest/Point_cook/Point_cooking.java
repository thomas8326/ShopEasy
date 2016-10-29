package cn.picksomething.drawlayouttest.Point_cook;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.picksomething.drawlayouttest.LastPage.PointCooklast2;
import cn.picksomething.drawlayouttest.R;

/**
 * Created by tt791_000 on 2016/7/6.
 */
public class Point_cooking extends android.support.v4.app.Fragment {

    private boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    private ImageView left1,right1,left2,right2;
    private RecyclerView mRecyclerView;
    private RecyclerView mRecyclerView2;
    private Point_cooking_Recycle mAdapter;
    private Point_cooking_Recycle2 mAdapter2;
    private List<Bitmap> mDatas;
    private LinearLayoutManager linearLayoutManager;
    private LinearLayoutManager linearLayoutManager2;
    private ArrayList<String> mDatasTxt;
    private List<Bitmap> mDatas2;
    private ArrayList<String> mDatasTxt2;
    private String[] arr ={"T1","T2","T3", "T4", "T5","T6","T7"};
    private String[] arr2 = {"T8","T9","T10","T11","T12","T13","T14"};
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.point_cooking, container, false);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initDatas();
        initDatas2();
        //得到控件
        mRecyclerView = (RecyclerView)getView().findViewById(R.id.id_recyclerview_horizontal);
        mRecyclerView2 = (RecyclerView) getView().findViewById(R.id.id_recycleview_horizontal2);
        left1 = (ImageView) getView().findViewById(R.id.left);
        right1 = (ImageView) getView().findViewById(R.id.right);
        left2 = (ImageView) getView().findViewById(R.id.left2);
        right2 = (ImageView) getView().findViewById(R.id.right2);
        //设置布局管理器
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);//橫放
        mRecyclerView.setLayoutManager(linearLayoutManager);

        linearLayoutManager2 = new LinearLayoutManager(getActivity());
        linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);//橫放
        mRecyclerView2.setLayoutManager(linearLayoutManager2);

        //设置适配器
        mAdapter = new Point_cooking_Recycle(getActivity(), mDatas,mDatasTxt);
        mAdapter.setOnItemClickLitener(new Point_cooking_Recycle.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(),PointCooklast2.class);
                Bundle bundle = new Bundle();
                bundle.putString("Ingredient", arr[position]);
                bundle.putInt("page",1);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        mAdapter2 = new Point_cooking_Recycle2(getActivity(),mDatas2,mDatasTxt2);
        mAdapter2.setOnItemClickLitener(new Point_cooking_Recycle2.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent2 = new Intent(getActivity(),PointCooklast2.class);
                Bundle bundle2 = new Bundle();
                bundle2.putString("Ingredient2", arr2[position]);
                intent2.putExtras(bundle2);
                startActivity(intent2);
            }
        });

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                visibleItemCount = linearLayoutManager.getChildCount();
                totalItemCount = linearLayoutManager.getItemCount();
                pastVisiblesItems = linearLayoutManager.findFirstVisibleItemPosition();
                if(loading) {
                    if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                        left1.setVisibility(View.VISIBLE);
                        right1.setVisibility(View.GONE);
                    }
                    if (pastVisiblesItems == 0) {
                        left1.setVisibility(View.GONE);
                        right1.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
        mRecyclerView2.setAdapter(mAdapter2);
        mRecyclerView2.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                visibleItemCount = linearLayoutManager2.getChildCount();
                totalItemCount = linearLayoutManager2.getItemCount();
                pastVisiblesItems = linearLayoutManager2.findFirstVisibleItemPosition();
                if(loading) {
                    if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                        left2.setVisibility(View.VISIBLE);
                        right2.setVisibility(View.GONE);
                    }
                    if (pastVisiblesItems == 0) {
                        left2.setVisibility(View.GONE);
                        right2.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }

    public static Bitmap readBitMap(Context context, int resId){
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        opt.inSampleSize = 4;
        //獲取資源圖片
        InputStream is = context.getResources().openRawResource(resId);
        return BitmapFactory.decodeStream(is,null,opt);
    }

    private void initDatas()
    {
        Bitmap bm1  =readBitMap(getActivity(),R.drawable.i1);
        Bitmap bm2 =  readBitMap(getActivity(),R.drawable.i2);
        Bitmap bm3 =readBitMap(getActivity(),R.drawable.i3);
        Bitmap bm4 =readBitMap(getActivity(),R.drawable.i4);
        Bitmap bm5 =readBitMap(getActivity(),R.drawable.i5);
        Bitmap bm6 =readBitMap(getActivity(),R.drawable.i6);
        Bitmap bm7 = readBitMap(getActivity(),R.drawable.i7);
        mDatas = new ArrayList<Bitmap>(Arrays.asList(bm1,
                bm2,bm3,bm4,bm5,bm6,bm7));
        mDatasTxt = new ArrayList<String>(Arrays.asList("牛",
                "豬","羊" , "雞", "鴨","鵝","魚"));

    }
    private void initDatas2()
    {
        Bitmap bm8 =readBitMap(getActivity(),R.drawable.i8);
        Bitmap bm9 = readBitMap(getActivity(),R.drawable.i9);
        Bitmap bm10 = readBitMap(getActivity(),R.drawable.beans);
        Bitmap bm11 =readBitMap(getActivity(),R.drawable.i11);
        Bitmap bm12 = readBitMap(getActivity(),R.drawable.i12);
        Bitmap bm13=readBitMap(getActivity(),R.drawable.i13);
        Bitmap bm14=readBitMap(getActivity(),R.drawable.i14);
        mDatas2 = new ArrayList<Bitmap>(Arrays.asList(bm8,bm9,bm10,bm11,bm12,bm13,bm14));
        mDatasTxt2 = new ArrayList<String>(Arrays.asList("海鮮","菇",
                "豆","澱粉", "葉菜根莖", "果實","蛋"));

    }
}

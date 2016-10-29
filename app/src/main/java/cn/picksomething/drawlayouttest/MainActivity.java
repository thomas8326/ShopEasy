package cn.picksomething.drawlayouttest;


import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import cn.picksomething.drawlayouttest.Databases.DatabaseHelper;
import cn.picksomething.drawlayouttest.Toolbar.FragmentAboutus;
import cn.picksomething.drawlayouttest.Toolbar.FragmentCookbook;
import cn.picksomething.drawlayouttest.Toolbar.FragmentFavorite;
import cn.picksomething.drawlayouttest.Toolbar.FragmentHistory;
import cn.picksomething.drawlayouttest.Toolbar.FragmentList;
import cn.picksomething.drawlayouttest.Toolbar.FragmentMain;
import cn.picksomething.drawlayouttest.Toolbar.FragmentMember;


public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;//側邊欄
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView mNavigationView;



    private ViewPager adViewPager;
    private LinearLayout pagerLayout;
    private List<View> pageViews;
    private ImageView[] imageViews;
    private ImageView imageView;
    private AdPageAdapter adapter;
    private AtomicInteger atomicInteger = new AtomicInteger(0);
    private boolean isContinue = true;
//
    private ViewPager viewPager;
    private cn.picksomething.drawlayouttest.Databases.TestDatabaseHelper dbhelp;
    private cn.picksomething.drawlayouttest.Databases.DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDB = new DatabaseHelper(this);
        initViewPager();
        findViews();
        setupToolbar();
        setupDrawerContent(mNavigationView);


        mToggle = setupDrawerToggle();
        // Tie DrawerLayout events to the ActionBarToggle
        mDrawerLayout.addDrawerListener(mToggle);

        View headerView = mNavigationView.getHeaderView(0);
        ImageView memberImg = (ImageView) headerView.findViewById(R.id.Member_img);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.bighead);
        memberImg.setImageBitmap(getRoundedCornerBitmap(bitmap, 10000.0f));
        memberImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v4.app.Fragment fragment = new FragmentMember();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.popBackStack();
                fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
                mDrawerLayout.closeDrawers();
            }
        });

        showDefaultFragment();

    }

    private void findViews() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
    }//mDrawerLayout , mNavigationView 初始化

    private void setupToolbar() {
        // Set a Toolbar to replace the ActionBar.
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
    }//mToolbar初始化

    private void setupDrawerContent(NavigationView navigationView) {

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        selectDrawerItem(menuItem);//側邊欄item動作
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar,
                R.string.drawer_open, R.string.drawer_close);
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        android.support.v4.app.Fragment fragment;

        switch (menuItem.getItemId()) {

            case R.id.nav_second_fragment:
                fragment = new FragmentFavorite();
                break;
            case R.id.nav_third_fragment:
                fragment = new FragmentHistory();
                break;
            case R.id.nav_fourth_fragment:
                fragment = new FragmentList();
                break;
            case R.id.nav_sub_third_fragment:
                fragment = new FragmentAboutus();
                break;
            case R.id.nav_sub_fourth_fragment:
                fragment = new FragmentCookbook();
                break;
            default:
                fragment = new FragmentMain();
                break;
        }
        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title

        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        mDrawerLayout.closeDrawers();
    }

    private void showDefaultFragment() {
        Fragment fragment = new FragmentMain();
        ;
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

    }


    @Override
    public void onBackPressed() {
        if (isNavDrawerOpen()) {
            closeNavDrawer();
        } else {
            super.onBackPressed();
        }
    }

    protected boolean isNavDrawerOpen() {
        return mDrawerLayout != null && mDrawerLayout.isDrawerOpen(GravityCompat.START);
    }

    protected void closeNavDrawer() {
        if (mDrawerLayout != null) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    // `onPostCreate` called when activity start-up is complete after `onStart()`
    // NOTE! Make sure to override the method with only a single `Bundle` argument
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        mToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.

        // Handle action buttons
        switch (item.getItemId()) {
            case R.id.action_map:
                // create intent to perform web search for this planet
                Intent intent = new Intent();
                intent.setClass(this, MapButton.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }




        private void initViewPager() {

        //从布局文件中获取ViewPager父容器
        pagerLayout = (LinearLayout) findViewById(R.id.view_pager_content);
        //创建ViewPager
        adViewPager = new ViewPager(this);

        //获取屏幕像素相关信息
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        //根据屏幕信息设置ViewPager广告容器的宽高
        adViewPager.setLayoutParams(new ViewGroup.LayoutParams(dm.widthPixels* 5/5, dm.heightPixels * 5/ 10));

        //将ViewPager容器设置到布局文件父容器中
        pagerLayout.addView(adViewPager);

        initPageAdapter();
        initCirclePoint();

        adViewPager.setAdapter(adapter);
        adViewPager.setOnPageChangeListener(new AdPageChangeListener());

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (isContinue) {
                        viewHandler.sendEmptyMessage(atomicInteger.get());
                        atomicOption();
                    }
                }
            }
        }).start();
    }


    private void atomicOption() {
        atomicInteger.incrementAndGet();
        if (atomicInteger.get() > imageViews.length - 1) {
            atomicInteger.getAndAdd(-5);
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {

        }
    }

    /*
     * 每隔固定时间切换广告栏图片
     */
    private final Handler viewHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            adViewPager.setCurrentItem(msg.what);
            super.handleMessage(msg);
        }

    };

    public static Bitmap readBitMap(Context context, int resId){
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        opt.inSampleSize = 1;
        //獲取資源圖片
        InputStream is = context.getResources().openRawResource(resId);
        return BitmapFactory.decodeStream(is,null,opt);
    }

    private void initPageAdapter() {
        pageViews = new ArrayList<View>();
//        Bitmap bm1 = readBitMap(this,R.drawable.ad1);
        ImageView img1 = new ImageView(this);
        img1.setImageResource(R.drawable.ad1);
        pageViews.add(img1);

        Bitmap bm2 = readBitMap(this,R.drawable.ad2);
        ImageView img2 = new ImageView(this);
        img2.setImageBitmap(bm2);
        pageViews.add(img2);

        Bitmap bm3 = readBitMap(this,R.drawable.ad3);
        ImageView img3 = new ImageView(this);
        img3.setImageBitmap(bm3);
        pageViews.add(img3);

        Bitmap bm4 = readBitMap(this,R.drawable.ad);
        ImageView img4 = new ImageView(this);
        img4.setImageBitmap(bm4);
        pageViews.add(img4);
        //要新的廣告 用相同格式下去加即可

        adapter = new AdPageAdapter(pageViews);
    }

    private void initCirclePoint(){
        ViewGroup group = (ViewGroup) findViewById(R.id.viewGroup);
        imageViews = new ImageView[pageViews.size()];
        //小圓點
        for (int i = 0; i < pageViews.size(); i++) {
            //创建一个ImageView
            imageView = new ImageView(this);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(10,10));
            imageViews[i] = imageView;

            //初始值, 默认第0个选中
            if (i == 0) {
                imageViews[i]
                        .setBackgroundResource(R.drawable.point_focused);
            } else {
                imageViews[i]
                        .setBackgroundResource(R.drawable.point_unfocused);
            }
            //将小圆点放入到布局中
            group.addView(imageViews[i]);
        }
    }

    /**
     *	ViewPager 页面改变监听器
     */
    private final class AdPageChangeListener implements ViewPager.OnPageChangeListener {

        /**
         * 页面滚动状态发生改变的时候触发
         */
        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        /**
         * 页面滚动的时候触发
         */
        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        /**
         * 页面选中的时候触发
         */
        @Override
        public void onPageSelected(int arg0) {
            //获取当前显示的页面是哪个页面
            atomicInteger.getAndSet(arg0);
            //重新设置原点布局集合
            for (int i = 0; i < imageViews.length; i++) {
                imageViews[arg0]
                        .setBackgroundResource(R.drawable.point_focused);
                if (arg0 != i) {
                    imageViews[i]
                            .setBackgroundResource(R.drawable.point_unfocused);
                }
            }
        }
    }


    private final class AdPageAdapter extends PagerAdapter {
        private List<View> views = null;

        /**
         * 初始化数据源, 即View数组
         */
        public AdPageAdapter(List<View> views) {
            this.views = views;
        }

        /**
         * 从ViewPager中删除集合中对应索引的View对象
         */
        @Override
        public void destroyItem(View container, int position, Object object) {
            ((ViewPager) container).removeView(views.get(position));
        }

        /**
         * 获取ViewPager的个数
         */
        @Override
        public int getCount() {
            return views.size();
        }

        /**
         * 从View集合中获取对应索引的元素, 并添加到ViewPager中
         */
        @Override
        public Object instantiateItem(View container, int position) {
            ((ViewPager) container).addView(views.get(position), 0);
            return views.get(position);
        }

        /**
         * 是否将显示的ViewPager页面与instantiateItem返回的对象进行关联
         * 这个方法是必须实现的
         */
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
    //會員圖片圓形化
    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap,float roundPx)
    {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }
protected  void onDestroy()
{
    super.onDestroy();
    System.exit(0);
    android.os.Process.killProcess(android.os.Process.myPid());

}


}


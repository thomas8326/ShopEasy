package cn.picksomething.drawlayouttest.LastPage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cn.picksomething.drawlayouttest.MapButton;
import cn.picksomething.drawlayouttest.R;

/**
 * Created by tt791_000 on 2016/8/21.
 */
public class FestivalLast extends AppCompatActivity{


    private String festivalPart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mission_cooking_party_lastpage);
        Bundle bundle = getIntent().getExtras();
        festivalPart = bundle.getString("_festivalpart");
        defaultFragment();
        title();
        init();
    }
    private void defaultFragment()
    {
        FestivalLastF1 festivalLastF1 = new FestivalLastF1();
        Bundle bundle=new Bundle();
        bundle.putString("_festivalpart", festivalPart);
        //向detailFragment传入参数
        festivalLastF1.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack();
        fragmentManager.beginTransaction().replace(R.id.lastpageFragment, festivalLastF1).commit();
    }
    protected void init()
    {
        ImageView back;
        ImageView map;
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        map = (ImageView) findViewById(R.id.map);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FestivalLast.this,MapButton.class));
            }
        });

    }
    private void title() {
        TextView title;
        title = (TextView) findViewById(R.id.TOPtitle);
        if (festivalPart.equals("K1"))
            title.setText("春節");
        else if (festivalPart.equals("K2"))
            title.setText("元宵節");
        else if (festivalPart.equals("K3"))
            title.setText("端午節");
        else if (festivalPart.equals("K4"))
            title.setText("中秋節");
        else if (festivalPart.equals("K5"))
            title.setText("感恩節");
        else if (festivalPart.equals("K6"))
            title.setText("萬聖節");
        else if (festivalPart.equals("K7"))
            title.setText("聖誕節");
        else if (festivalPart.equals("K8"))
            title.setText("情人節");

    }
}
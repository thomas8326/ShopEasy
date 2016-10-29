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
 * Created by tt791_000 on 2016/7/16.
 */
public class PartyLast extends AppCompatActivity{
    
    private String partyid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mission_cooking_party_lastpage);
        Bundle bundle = getIntent().getExtras();
        partyid = bundle.getString("_partyid");
        defaultFragment();
        title();
        init();

    }
    private void defaultFragment()
    {
        PartyLastF1 partyLastF1 = new PartyLastF1();
        Bundle bundle=new Bundle();
        bundle.putString("_partyid", partyid);
        //向detailFragment传入参数
        partyLastF1.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack();
        fragmentManager.beginTransaction().replace(R.id.lastpageFragment, partyLastF1).commit();
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
                startActivity(new Intent(PartyLast.this,MapButton.class));
            }
        });

    }
    private void title()
    {

        TextView title;
        title = (TextView)findViewById(R.id.TOPtitle);
        if(partyid.equals("P1"))
            title.setText("辦桌請客");
        else if(partyid.equals("P2"))
            title.setText("嬰孩滿月");
        else if(partyid.equals("P3"))
            title.setText("生日派對");
        else if(partyid.equals("P4"))
            title.setText("朋友聚會");
    }
}

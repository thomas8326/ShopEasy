package cn.picksomething.drawlayouttest;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by tt791_000 on 2016/9/11.
 */
public class SearchableActivity extends Activity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onNewIntent(Intent intent) { //activity重新置頂

        super.onNewIntent(intent);
        doSearchQuery(intent);

    }

// 對searchable activity的調用仍是標準的intent，我們可以從intent中獲取信息，即要搜索的內容

    private void doSearchQuery(Intent intent){

        if(intent == null)

            return;

        String queryAction = intent.getAction();

        if( Intent.ACTION_SEARCH.equals( intent.getAction())){ //如果是通過ACTION_SEARCH來調用，即如果通過搜索調用

            String queryString = intent.getStringExtra(SearchManager.QUERY); //獲取搜索內容
            Log.i("search   ",queryAction+"  "+queryString);


        }

    }


}



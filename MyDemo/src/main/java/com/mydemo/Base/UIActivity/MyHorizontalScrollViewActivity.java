package com.mydemo.Base.UIActivity;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mydemo.Base.Base2Activity;
import com.mydemo.R;

/**
 * Created by HaoPz on 2018/7/10.
 * <p>
 * 横向滑动的View
 */

public class MyHorizontalScrollViewActivity extends Base2Activity {
    String[] str1 = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14",
            "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26"};
    String[] str2 = new String[]{"a", "b", "c", "d", "e", "f", "j", "h", "i", "j", "k", "l", "m", "n",
            "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

    @Override
    public void setDate() {

    }

    @Override
    public void setView(View contentView) {
        ListView listView1 = (ListView) findViewById(R.id.listView1);
        ListView listView2 = (ListView) findViewById(R.id.listView2);

        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,str1);
        listView1.setAdapter(arrayAdapter1);


        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,str2);
        listView2.setAdapter(arrayAdapter2);
    }

    @Override
    protected View getContentView() {
        return inflateView(R.layout.activity_ui_my_horizontal_scroll_view);
    }


}

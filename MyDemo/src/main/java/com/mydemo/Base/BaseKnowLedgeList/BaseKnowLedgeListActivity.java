package com.mydemo.Base.BaseKnowLedgeList;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.mydemo.Base.Base2Activity;
import com.mydemo.Base.BaseKnowLedgeList.Activity.BaseKnowLedgeActivity;
import com.mydemo.Base.BaseKnowLedgeList.BroadCastReceiver.BaseKnowLedgeBroadCastReceiver;
import com.mydemo.Base.BaseKnowLedgeList.Provider.BaseKnowLedgeContentProvider;
import com.mydemo.Base.BaseKnowLedgeList.Service.BaseKnowLedgeService;
import com.mydemo.Base.BaseKnowLedgeList.StorageTechnology.StorageTechnologyList;
import com.mydemo.Base.BaseKnowLedgeList.VersionDiff.AndroidVersionDiffActivity;
import com.mydemo.R;

/**
 * Created by HaoPz on 2018/4/2.
 */

public class BaseKnowLedgeListActivity extends Base2Activity {

    private TextView baseKnowLedgeListActivity;

    private TextView baseKnowledgeListBrocastReceiver;

    private TextView baseKnowledgeListService;

    private TextView baseKnowledgeListContentProvider;

    private TextView baseKnowledgeListFragment;

    private TextView baseKnowledgeStorageTechnology;

    private TextView baseKnowledgeVersionDiff;

    private TextView toolBarTextView;

    @Override
    public void setDate() {
        baseKnowLedgeListActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoActivity(BaseKnowLedgeActivity.class);
            }
        });


        baseKnowledgeListBrocastReceiver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoActivity(BaseKnowLedgeBroadCastReceiver.class);
            }
        });

        baseKnowledgeListService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoActivity(BaseKnowLedgeService.class);
            }
        });

        baseKnowledgeListContentProvider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoActivity(BaseKnowLedgeContentProvider.class);
            }
        });

        baseKnowledgeListFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // gotoActivity(BaseKnowLedgeFragment.class);
            }
        });

        baseKnowledgeStorageTechnology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoActivity(StorageTechnologyList.class);
            }
        });

        baseKnowledgeVersionDiff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoActivity(AndroidVersionDiffActivity.class);
            }
        });
    }

    @Override
    public void setView(View contentView) {
        toolBarTextView = (TextView) contentView.findViewById(R.id.toolBar);
        toolBarTextView.setText("Android 基础知识列表");

        baseKnowLedgeListActivity = (TextView) findViewById(R.id.base_knowledge_list_activity);
        baseKnowLedgeListActivity.setText("Activity");

        baseKnowledgeListBrocastReceiver = (TextView) findViewById(R.id.base_knowledge_list_brocastReceiver);
        baseKnowledgeListBrocastReceiver.setText("BroadCastReceiver");

        baseKnowledgeListService = (TextView) findViewById(R.id.base_knowledge_list_service);
        baseKnowledgeListService.setText("Receiver");

        baseKnowledgeListContentProvider = (TextView) findViewById(R.id.base_knowledge_list_content_provider);
        baseKnowledgeListContentProvider.setText("ContentProvider");

        baseKnowledgeListFragment = (TextView) findViewById(R.id.base_knowledge_list_fragment);
        baseKnowledgeListFragment.setText("Fragment");

        baseKnowledgeStorageTechnology = (TextView) findViewById(R.id.base_knowledge_storage_technology);
        baseKnowledgeStorageTechnology.setText("AndroidStorage[存储]Technology");

        baseKnowledgeVersionDiff = (TextView) findViewById(R.id.base_knowledge_version_diff);
        baseKnowledgeVersionDiff.setText(R.string.AndroidVersionDiff);
    }

    @Override
    protected View getContentView() {
        return LayoutInflater.from(BaseKnowLedgeListActivity.this).inflate(R.layout.activity_base_knowledge_list, null);
    }
}

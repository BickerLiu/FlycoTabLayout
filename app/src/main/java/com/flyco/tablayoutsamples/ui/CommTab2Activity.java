package com.flyco.tablayoutsamples.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.flyco.tablayoutsamples.R;
import com.flyco.tablayoutsamples.adapter.CommonTab2Adapter;
import com.flyco.tablayoutsamples.entity.ListEntity;
import com.flyco.tablayoutsamples.entity.TabEntity;
import com.flyco.tablayoutsamples.utils.ViewFindUtils;

import java.util.ArrayList;

public class CommTab2Activity extends AppCompatActivity {

    private View mDecorView;

    private HorizontalScrollView mHorizontalScrollView;

    private CommonTabLayout mCommonTabLayout;

    private RecyclerView mRecyclerView;

    private final String[] mTitles = {
            "热门", "iOS", "Android"
            , "前端", "后端", "设计", "工具资源"
    };
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    private ArrayList<ListEntity> mListEntities = new ArrayList<>();

    private CommonTab2Adapter mAdapter;

    private LinearLayoutManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comm_tab2);
        initView();
        setUpData();
    }


    private void initView() {
        mDecorView = getWindow().getDecorView();
        mHorizontalScrollView = ViewFindUtils.find(mDecorView, R.id.horizontal);
        mCommonTabLayout = ViewFindUtils.find(mDecorView, R.id.common_tab);
        mRecyclerView = ViewFindUtils.find(mDecorView, R.id.recycler);

        mManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mManager);

        mAdapter = new CommonTab2Adapter(mListEntities);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                scrollToCurrentTab();
            }
        });
        mCommonTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                for (int i = 0; i < mListEntities.size(); i++) {
                    if (TextUtils.equals(mListEntities.get(i).getTitle(),
                            mTabEntities.get(position).getTabTitle())) {
                        mManager.scrollToPositionWithOffset(i,0);
                        break;
                    }
                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }


    private void setUpData() {

        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], 0, 0));
            mListEntities.add(new ListEntity(mTitles[i], mTitles[i] + " " + 1));
            mListEntities.add(new ListEntity(mTitles[i], mTitles[i] + " " + 2));
            mListEntities.add(new ListEntity(mTitles[i], mTitles[i] + " " + 3));
            mListEntities.add(new ListEntity(mTitles[i], mTitles[i] + " " + 4));
            mListEntities.add(new ListEntity(mTitles[i], mTitles[i] + " " + 5));
        }
        mCommonTabLayout.setTabData(mTabEntities);
        mCommonTabLayout.notifyDataSetChanged();
        mCommonTabLayout.setCurrentTab(0);

        mAdapter.notifyDataSetChanged();
    }

    private void scrollToCurrentTab() {
        int first = mManager.findFirstCompletelyVisibleItemPosition();
        int last = mManager.findLastCompletelyVisibleItemPosition();
        if (first == 0) {
            calcToSetTab(0);
        } else {
            if (first > 0 && last > 0) {
                String currentTitle = mTabEntities.
                        get(mCommonTabLayout.getCurrentTab()).
                        getTabTitle();
                if (!TextUtils.equals(mListEntities.get(first).getTitle(), currentTitle)) {
                    for (int tab = 0; tab < mTabEntities.size(); tab++) {
                        if (TextUtils.equals(mTabEntities.get(tab).getTabTitle(),
                                mListEntities.get(first).getTitle())) {
                            calcToSetTab(tab);
                            break;
                        }
                    }
                }
            }
        }
    }

    private void calcToSetTab(int position) {
        View tabView = mCommonTabLayout.getTabView(position);
        if (tabView != null) {
            mHorizontalScrollView.smoothScrollTo(tabView.getLeft() -
                            (mHorizontalScrollView.getWidth() / 2 - (tabView.getWidth()) / 2),
                    0);
        }
        mCommonTabLayout.setCurrentTab(position);
    }
}



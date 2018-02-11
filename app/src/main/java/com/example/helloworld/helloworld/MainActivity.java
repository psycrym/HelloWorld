package com.example.helloworld.helloworld;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {

    public FragmentTabHost tabHost;
    private LayoutInflater inflater;
    private Class[] fragmentArray = {OneFragment.class, TwoFragment.class, ThreeFragment.class};
    private String[] tabArray = {"One", "Two", "Three"};
    private TextView lastSelectTV;
    public int lastSelectedIndex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initView() {
        // 实例化布局对象
        inflater = LayoutInflater.from(this);

        // 实例化TabHost对象，得到TabHost
        tabHost = findViewById(android.R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        // 得到fragment的个数
        int count = fragmentArray.length;

        for (int i = 0; i < count; i++) {
            // 为每一个Tab按钮设置view
            TabHost.TabSpec tabSpec = tabHost.newTabSpec("tab" + i).setIndicator(getTabItemView(i));
            // 将Tab按钮添加进Tab选项卡中
            tabHost.addTab(tabSpec, fragmentArray[i], null);
        }

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {

            @Override
            public void onTabChanged(String arg0) {
                // TODO Auto-generated method stub
                int tabIndex = 0;

                if (arg0.endsWith("tab0")) {
                    tabIndex = 0;
                } else if (arg0.endsWith("tab1")) {
                    tabIndex = 1;
                } else if (arg0.endsWith("tab2")) {
                    tabIndex = 2;
                } else if (arg0.endsWith("tab3")) {
                    tabIndex = 3;
                }

                TextView tabText = tabHost.getTabWidget().getChildAt(tabIndex).findViewById(R.id.tab_tv);
                if (lastSelectTV != null && lastSelectTV != tabText) {
                    lastSelectTV.setTextColor(getResources().getColor(R.color.white));
                }

                lastSelectedIndex = tabIndex;
                lastSelectTV = tabText;
                tabText.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        });

        tabHost.setCurrentTab(lastSelectedIndex);
    }

    private View getTabItemView(int index) {
        LinearLayout tabView = (LinearLayout) inflater.inflate(R.layout.tab_layout, null);
        TextView tabTV = tabView.findViewById(R.id.tab_tv);
        tabTV.setText(tabArray[index]);

//        当前选中
        if (index == 0) {
            tabTV.setTextColor(getResources().getColor(R.color.colorPrimary));
            lastSelectTV = tabTV;
        }

        return tabView;
    }

}

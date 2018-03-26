package org.monster.android.tab;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.monster.android.tab.fragment.NearbyTabFragment;
import org.monster.android.tab.fragment.LiveTabFragment;
import org.monster.android.tab.fragment.GoodsTabFragment;
import org.monster.android.tab.fragment.MessageFragment;
import org.monster.android.tab.fragment.MineFragment;
import org.monster.android.tab.utils.FragmentTabHost;
import org.monster.android.tab.utils.TabLayout;
import org.monster.android.tab.utils.ViewUtils;

public class MainActivity extends BaseActivity {

    public String[] tabTags = new String[]{"nearby", "live", "rainbow", "message", "mine"};
    public Class[] tabCls = new Class[]{NearbyTabFragment.class, LiveTabFragment.class,
            GoodsTabFragment.class, MessageFragment.class, MineFragment.class};
    int[] tabCustomView = new int[]{R.layout.main_tab_near, R.layout.main_tab_live,
            R.layout.main_tab_rainbow, R.layout.main_tab_msg, R.layout.main_tab_mime};

    private FragmentTabHost mTabHost;
    TabLayout mTabLayout;
    private TextView msgCount;
    private View msgCountLayout;
    private int mCurrentItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabLayout = (TabLayout) findViewById(R.id.main_tabs);
        msgCount = (TextView) findViewById(R.id.msgCount);
        msgCountLayout = findViewById(R.id.msgCountLayout);

        mTabHost.setup(this, getSupportFragmentManager(), R.id.container, false);

        for (int i = 0; i < tabTags.length; i++) {
            mTabHost.addTab(mTabHost.newTabSpec(tabTags[i]).setIndicator(tabTags[i]),
                    tabCls[i], null);
            mTabLayout.addTab(mTabLayout.newTab().setTag(tabTags[i]));
            mTabLayout.getTabAt(i).setCustomView(tabCustomView[i]);
        }

        initEvent();
    }
    protected void initEvent() {

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mCurrentItem = tab.getPosition();
                mTabHost.setCurrentTabByTag((String) tab.getTag());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        locateMsgCount();
        updateUnreadNumView(12);
    }
    /**
     * 未读数标签的位置
     */
    private void locateMsgCount() {
        final int tabCount = 5;     // 总tab的数量
        final int prevTabCount = 3; // 消息tab之前的tab的数量
        int leftPadding = (int) (ViewUtils.getScreenWidth(this)
                * (1 + prevTabCount * 2) / (tabCount * 2)
                + getResources().getDimension(R.dimen.indicate_text_offset));
        msgCountLayout.setPadding(leftPadding, 0, 0, 0);
    }
    /**
     * 更新消息数
     *
     * @param unreadMsgNum
     */
    public void updateUnreadNumView(final int unreadMsgNum) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (unreadMsgNum <= 0) {
                    msgCount.setVisibility(View.GONE);
                    msgCount.setText("");
                } else if (unreadMsgNum <= 999) {
                    msgCount.setVisibility(View.VISIBLE);
                    msgCount.setText(String.valueOf(unreadMsgNum));
                } else {
                    msgCount.setVisibility(View.VISIBLE);
                    msgCount.setText("999+");
                }
            }
        });

    }
}

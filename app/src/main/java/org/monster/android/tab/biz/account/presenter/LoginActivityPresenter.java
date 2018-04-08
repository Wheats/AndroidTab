package org.monster.android.tab.biz.account.presenter;

import android.content.Context;

import org.monster.android.tab.biz.account.ui.view.LoginActivityView;
import org.monster.android.tab.biz.base.Presenter;
import org.monster.android.tab.net.XUtils;

/**
 * Created by zhanghuatao on 2016/9/26.
 */
public interface LoginActivityPresenter extends Presenter<LoginActivityView> {

    void login(Context mContext, String name, String code, String device, String channel_id);

    void getVerifyCode(Context mContext, String phone);

}

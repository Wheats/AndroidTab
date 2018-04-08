package org.monster.android.tab.biz.account.ui.view;

import org.monster.android.tab.bean.account.LoginJsonBean;
import org.monster.android.tab.biz.base.BaseView;

/**
 * Created by monster on 8/4/18.
 */

public interface LoginActivityView extends BaseView {

    void loginSuccess(LoginJsonBean jsonBean);

    void loginError(int code,String msg);

    void getVerifyCodeSuccess();

    void getVerifyCodeError(int code,String msg);
}

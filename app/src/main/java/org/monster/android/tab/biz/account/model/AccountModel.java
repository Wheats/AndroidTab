package org.monster.android.tab.biz.account.model;

import android.content.Context;

import org.monster.android.tab.biz.base.PlusBaseService;
import org.monster.android.tab.net.XUtils;

/**
 * Created by monster on 8/4/18.
 */

public interface AccountModel extends PlusBaseService {

    void login(Context mContext, String name, String code, String device, String channel_id, XUtils.ResultListener resultListener);

    void getVerifyCode(Context mContext, String phone, XUtils.ResultListener resultListener);

}

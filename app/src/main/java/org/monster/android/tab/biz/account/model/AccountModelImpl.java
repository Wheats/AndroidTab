package org.monster.android.tab.biz.account.model;

import android.content.Context;
import android.util.Log;

import org.monster.android.tab.net.ServiceHelper;
import org.monster.android.tab.net.XUtils;

import java.util.Map;

/**
 * Created by monster on 8/4/18.
 */

public class AccountModelImpl implements AccountModel {

    private Context mContext;

    public AccountModelImpl(Context context) {
        this.mContext = context;
    }

    @Override
    public void getVerifyCode(Context mContext, String phone, XUtils.ResultListener resultListener) {
        String url = ServiceHelper.buildUrl("/app/sendCode");
        Log.d("getVerifyCode",url);
        ServiceHelper.ParamBuilder paramBuilder = new ServiceHelper.ParamBuilder(mContext);
        paramBuilder.add("phone", phone);
        Map<String, String> params = paramBuilder.build();
        XUtils.getInstance().post(url, params, resultListener);
    }

    @Override
    public void login(Context mContext, String phone, String code, String device, String channel_id, XUtils.ResultListener resultListener) {
        String url = ServiceHelper.buildUrl("/user/login");
        ServiceHelper.ParamBuilder paramBuilder = new ServiceHelper.ParamBuilder(mContext);
        paramBuilder.add("phone", phone)
                .add("code", code)
                .add("device", device)
                .add("channel_id", channel_id);
        Map<String, String> params = paramBuilder.build();
        XUtils.getInstance().post(url, params, resultListener);
    }

}

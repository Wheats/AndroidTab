package org.monster.android.tab.biz.account.presenter;

import android.content.Context;

import com.google.gson.Gson;

import org.json.JSONObject;
import org.monster.android.tab.R;
import org.monster.android.tab.bean.account.LoginJsonBean;
import org.monster.android.tab.biz.account.model.AccountModel;
import org.monster.android.tab.biz.account.model.AccountModelImpl;
import org.monster.android.tab.biz.account.ui.view.LoginActivityView;
import org.monster.android.tab.biz.base.BasePresenter;
import org.monster.android.tab.net.XUtils;
import org.monster.android.tab.utils.Contetns;

/**
 * Created by monster on 8/4/18.
 */

public class LoginActivityPresenterImpl extends BasePresenter<LoginActivityView> implements LoginActivityPresenter {

    private AccountModel mModel;

    Context mContext;

    public LoginActivityPresenterImpl(Context context) {
        mContext = context;
        mModel = new AccountModelImpl(context);
    }

    @Override
    public void getVerifyCode(Context context, String phone) {
        mModel.getVerifyCode(context, phone, new XUtils.ResultListener() {
            @Override
            public void onResponse(String result) {
                try {
                    if (getActivityView() != null) {
                        getActivityView().hideLoading();
                    }
                    JSONObject jsonObject = new JSONObject(result);
                    int code = jsonObject.getInt("code");
                    if (code== Contetns.STATE_OK) {
                        if (getActivityView() != null) {
                            getActivityView().getVerifyCodeSuccess();
                        }
                    } else {
                        if (getActivityView() != null) {
                            getActivityView().getVerifyCodeError(code, "验证码错误");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (getActivityView() != null) {
                        getActivityView().hideLoading();
                    }
                    if (getActivityView() != null) {
                        getActivityView().loginError(Contetns.JSON_PART, mContext.getString(R.string.part_json_error));
                    }
                }
            }

            @Override
            public void onError(Throwable error) {
                if (getActivityView() != null) {
                    getActivityView().loginError(Contetns.NET_ERROR, error.getMessage());
                }
            }
        });
    }

    @Override
    public void login(final Context context, String name, String code, String device, String channel_id) {
        if (getActivityView() != null) {
            getActivityView().showLoading("登录中...");
        }
        mModel.login(context, name, code, device, channel_id, new XUtils.ResultListener() {
            @Override
            public void onResponse(String result) {
                try {
                    if (getActivityView() != null) {
                        getActivityView().hideLoading();
                    }
                    Gson gson = new Gson();
                    final LoginJsonBean jsonData = gson.fromJson(result, LoginJsonBean.class);
                    if (jsonData.getCode() == Contetns.STATE_OK) {
                        if (getActivityView() != null) {
                            getActivityView().loginSuccess(jsonData);
                        }
                    } else {
                        if (getActivityView() != null) {
                            getActivityView().loginError(jsonData.getCode(), jsonData.getMsg());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (getActivityView() != null) {
                        getActivityView().loginError(Contetns.JSON_PART, mContext.getString(R.string.part_json_error));
                    }
                }

            }

            @Override
            public void onError(Throwable error) {

            }
        });
    }
}

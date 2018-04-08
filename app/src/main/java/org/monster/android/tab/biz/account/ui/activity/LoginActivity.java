package org.monster.android.tab.biz.account.ui.activity;


import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.monster.android.tab.R;
import org.monster.android.tab.bean.account.LoginJsonBean;
import org.monster.android.tab.biz.account.presenter.LoginActivityPresenter;
import org.monster.android.tab.biz.account.presenter.LoginActivityPresenterImpl;
import org.monster.android.tab.biz.account.ui.view.LoginActivityView;
import org.monster.android.tab.biz.base.BaseActivity;
import org.monster.android.tab.biz.base.ProgressView;
import org.monster.android.tab.utils.Contetns;
import org.monster.android.tab.utils.SharedPreferencesUtil;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
/**
 * Created by monster on 8/4/18.
 */
@ContentView(R.layout.activity_login)
public class LoginActivity extends BaseActivity implements LoginActivityView {

    @ViewInject(R.id.login_phone)
    EditText mLoginPhone;
    @ViewInject(R.id.login_code)
    EditText mLoginCode;
    @ViewInject(R.id.login)
    TextView mLoginButton;
    @ViewInject(R.id.iv_login_qq)
    ImageView mIvLoginQq;
    @ViewInject(R.id.iv_login_wechat)
    ImageView mIvLoginWechat;
    @ViewInject(R.id.iv_login_sina)
    ImageView mIvLoginSina;
    @ViewInject(R.id.btn_get_code_ac_login)
    TextView btnGetCode;

    LoginActivityPresenter mPresenter;

    private InputMethodManager imm = null;
    public static final int LOGIN_SUCCESS = 1;
    public static final int LOGIN_FAIL = 2;
    private static final int SDK_PERMISSION_REQUEST = 127;
    private static final int AGAIN_PERMISSION_REQUEST = 126;
    private int thirdPlatformType = -1;
    private int thirdPlatformGender = 0;
    private CountDownTimer timerDown = new CountDownTimer(60000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            btnGetCode.setText(millisUntilFinished / 1000 + "秒");
        }

        @Override
        public void onFinish() {
            btnGetCode.setEnabled(true);
            btnGetCode.setText("验证码");
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        setListeners();
    }

    private void setListeners() {
        TextWatcher textChangeL = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (mLoginPhone.getText().toString().trim().isEmpty() || mLoginCode.getText().toString().trim().isEmpty()) {
                    mLoginButton.setSelected(false);
                } else {
                    mLoginButton.setSelected(true);
                }
            }
        };
        mLoginPhone.addTextChangedListener(textChangeL);
        mLoginCode.addTextChangedListener(textChangeL);
    }

    @Event(value = {R.id.btn_get_code_ac_login, R.id.iv_login_wechat, R.id.iv_login_sina, R.id.iv_login_qq})
    private void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_get_code_ac_login:
                String phoneNum = mLoginPhone.getText().toString().trim();
                if (phoneNum.length() != 11) {
                    Toast.makeText(LoginActivity.this, "手机号位数不正确", Toast.LENGTH_SHORT).show();
                    return;
                } else if (phoneNum.charAt(0) != '1') {
                    Toast.makeText(LoginActivity.this, "手机格式不正确", Toast.LENGTH_SHORT).show();
                    return;
                }
                //验证通过后，执行倒计时
                btnGetCode.setEnabled(false);
                timerDown.start();
                mPresenter.getVerifyCode(LoginActivity.this, phoneNum);
                break;
            case R.id.login:
                if (mLoginPhone.getText().toString().trim().isEmpty() || mLoginCode.getText().toString().trim().isEmpty()) {
                    Toast.makeText(LoginActivity.this, "用户名、密码填写不完整", Toast.LENGTH_SHORT).show();
                } else {
                    login();
                }
                break;
        }
    }

    private void login() {
        String login_name = mLoginPhone.getText().toString().trim();
        String login_code = mLoginCode.getText().toString().trim();
        final String mDeviceId = (String) SharedPreferencesUtil.getParam(this, Contetns.DEVICE_ID, "");
        String mChannelId = SharedPreferencesUtil.getChannelId();
        mPresenter.login(this, login_name, login_code, mDeviceId, mChannelId);
    }

    @Override
    protected int getLayoutResID() {
        return 0;
    }

    @Override
    protected void attachPresenter() {
        mPresenter = new LoginActivityPresenterImpl(this);
        mPresenter.attachView(this);
    }

    @Override
    protected void detachPresenter() {
        mPresenter.detachView();
        mPresenter = null;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            setResult(LOGIN_FAIL);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timerDown != null) {
            timerDown.cancel();
        }
    }

    @Override
    public void showLoading(String msg) {
        ProgressView.show(this, msg);
    }

    @Override
    public void hideLoading() {
        ProgressView.dismiss();
    }

    @Override
    public void loginSuccess(LoginJsonBean jsonBean) {
        Log.d("loginSuccess", jsonBean.toString());
        Toast.makeText(LoginActivity.this, "loginSuccess : " + jsonBean.getData().getUser_name(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginError(int code, String msg) {
        Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void getVerifyCodeSuccess() {

    }

    @Override
    public void getVerifyCodeError(int code, String msg) {

    }
}

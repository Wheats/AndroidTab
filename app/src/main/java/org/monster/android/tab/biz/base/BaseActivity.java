package org.monster.android.tab.biz.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import org.monster.android.tab.MiGoApplication;

import butterknife.ButterKnife;

/**
 * @author zhanghuatao
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    protected ProgressView mProgressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && isMIUI6Later()) {
//            int systemUiVisibility = getWindow().getDecorView().getSystemUiVisibility();
//            if ((systemUiVisibility & View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
//                    == View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR) {
//                setStatusBarDarkModeForMIUI6(getWindow(), true);
//            }
//        }
        if (getLayoutResID() > 0) {

            setContentView(getLayoutResID());

            injectViews();

            attachPresenter();
        }
    }

    protected abstract int getLayoutResID();

    protected abstract void attachPresenter();

    protected abstract void detachPresenter();

    private void injectViews() {
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dismissLoading();

        ButterKnife.unbind(this);

        detachPresenter();
    }

    @Override
    public void showLoading(String msg) {
        if (isFinishing()) return;
        mProgressView.show(MiGoApplication.getApp(), msg);
    }

    @Override
    public void hideLoading() {
        if (mProgressView != null && !isFinishing()) {
            mProgressView.dismiss();
        }
    }

    /**
     * 销毁对话框
     */
    private void dismissLoading() {
        if (mProgressView != null) {
            mProgressView.dismiss();
            mProgressView = null;
        }
    }

    @Override
    public void showError(String msg) {
    }

    @Override
    public void showEmpty(String msg) {

    }
}

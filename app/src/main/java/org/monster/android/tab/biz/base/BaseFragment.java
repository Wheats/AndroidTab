package org.monster.android.tab.biz.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.monster.android.tab.MiGoApplication;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment implements BaseView {

    protected ProgressView mProgressView;

    protected LayoutInflater mLayoutInflater;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mLayoutInflater = inflater;
        View view= inflater.inflate(getLayoutResID(),container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        injectViews(view);

        attachPresenter();
    }

    protected abstract int getLayoutResID();

    protected abstract void attachPresenter();

    protected abstract void detachPresenter();

    private void injectViews(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        ButterKnife.unbind(this);

        detachPresenter();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void showLoading(String msg) {
        Activity activity = getActivity();
        if(activity == null){
            return;
        }
        if(!activity.isFinishing()){
            mProgressView.show(MiGoApplication.getApp(),msg);
        }
    }

    @Override
    public void hideLoading() {
        Activity activity = getActivity();
        if(activity == null){
            return;
        }
        if(!activity.isFinishing()){
            mProgressView.dismiss();
        }
        mProgressView = null;
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void showEmpty(String msg) {

    }

    public void networkChanged(String net){

    }

    public void networkOffline() {

    }
}

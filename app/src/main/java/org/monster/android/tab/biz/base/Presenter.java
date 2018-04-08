package org.monster.android.tab.biz.base;

public interface Presenter<V extends BaseView> {

    void attachView(V baseView);

    void detachView();
}

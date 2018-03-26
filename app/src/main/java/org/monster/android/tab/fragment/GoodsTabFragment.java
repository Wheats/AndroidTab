package org.monster.android.tab.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.monster.android.tab.R;

/**
 * Created by monster on 23/3/18.
 */

public class GoodsTabFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_goods, container, false);
        return rootView;
    }

}

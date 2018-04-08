package org.monster.android.tab.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.monster.android.tab.R;
import org.xutils.view.annotation.ContentView;
import org.xutils.x;

/**
 * Created by monster on 23/3/18.
 */
@ContentView(R.layout.fragment_nearby)
public class NearbyTabFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = x.view().inject(this,inflater, container);
        return rootView;
    }
}

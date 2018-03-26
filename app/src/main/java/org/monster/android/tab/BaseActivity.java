package org.monster.android.tab;

import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by monster on 23/3/18.
 */

public abstract class BaseActivity extends AppCompatActivity {
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
    }
}

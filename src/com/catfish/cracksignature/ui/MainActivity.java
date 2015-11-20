package com.catfish.cracksignature.ui;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.Log;

import com.catfish.cracksignature.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            PackageInfo pi = getPackageManager().getPackageInfo(this.getPackageName(), 0);
            Log.i("catfish", pi.signatures[0].toCharsString());
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}

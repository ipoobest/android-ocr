package com.poobest.com.mockpeoject;

import android.app.Application;

import com.poobest.com.mockpeoject.model.Contextor;

/**
 * Created by j.poobest on 9/20/2017 AD.
 */

public class MockApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize thing(s) here.
        Contextor.getInstance().init(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}

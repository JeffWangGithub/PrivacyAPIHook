package com.glanwang.privacyapihook;

import android.app.Application;
import android.content.Context;

/**
 * @title:
 * @description:
 * @company: Netease
 * @author: GlanWang
 * @blame: 王广丛
 * @version: Created on 2021/6/23.
 */
public class CustomApp extends Application {

    private static Context mInstance;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        mInstance = this;
    }

    public static Context getContext() {
        return mInstance;
    }

}

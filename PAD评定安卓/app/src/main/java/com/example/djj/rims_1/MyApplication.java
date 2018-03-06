package com.example.djj.rims_1;

import android.app.Application;
import android.content.Context;

import com.example.djj.rims_1.utiles.SharedPreferencesHelper;


/**
 * 描述: 自定义Application
 * 作者|时间: djj on 2018/2/24 11:01
 * 博客地址: http://www.jianshu.com/u/dfbde65a03fc
 *
 */

public class MyApplication extends Application {

    private static MyApplication mInstance;
    public static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
        SharedPreferencesHelper.init(getApplicationContext());
        mInstance = this;

    }

    public static MyApplication getmInstance() {
        return mInstance;
    }

}

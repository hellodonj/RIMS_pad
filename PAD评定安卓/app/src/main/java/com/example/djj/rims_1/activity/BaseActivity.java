package com.example.djj.rims_1.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;

import com.orhanobut.logger.Logger;

/**
 * 描述: 基类activity
 * 作者|时间: djj on 2018/2/24 13:52
 * 博客地址: http://www.jianshu.com/u/dfbde65a03fc
 */

@SuppressLint("Registered")
public class BaseActivity extends Activity {

    protected Context mContext;
    private ProgressDialog pd;
    private BroadcastReceiver mBrReceiver;
    final private static String GET_ACTION = "com.android.server.scannerservice.broadcast";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        /**
         * 动态注册广播
         */
        mBrReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(GET_ACTION)) {
                    String barcode = intent.getStringExtra("scannerdata");
                    if (!TextUtils.isEmpty(barcode)) {
                        barcode = barcode.replaceAll("\n", "");
                        Logger.d(barcode);
                    }
                }
            }
        };
        IntentFilter filter = new IntentFilter(GET_ACTION);
        registerReceiver(mBrReceiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /*解除注册*/
        unregisterReceiver(mBrReceiver);
        finish();
    }

    protected void showDialog(String title, String text) {
        if (pd == null) {
            pd = new ProgressDialog(mContext);
        }
        if (!TextUtils.isEmpty(title)) {
            pd.setTitle(title);
        }
        pd.setMessage(text);
        pd.setCanceledOnTouchOutside(false);
        pd.show();
    }

    protected void dismissDialog() {
        if (pd != null) {
            pd.dismiss();
        }
    }
}

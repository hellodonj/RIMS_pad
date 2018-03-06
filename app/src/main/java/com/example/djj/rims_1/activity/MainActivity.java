package com.example.djj.rims_1.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.example.djj.rims_1.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 描述: 主界面
 * 作者|时间: djj on 2017/12/17 14:28
 * 博客地址: http://www.jianshu.com/u/dfbde65a03fc
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.web_view)
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // 启用javascript
        webView.getSettings().setJavaScriptEnabled(true);
        // 从assets目录下面的加载html
        webView.loadUrl("http://172.19.104.17:6688/test.aspx");//"file:///android_asset/web.html"

    }
}

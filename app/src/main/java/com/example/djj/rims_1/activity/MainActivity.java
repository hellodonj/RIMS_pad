package com.example.djj.rims_1.activity;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.djj.rims_1.R;
import com.example.djj.rims_1.utiles.SharedPreferencesHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 描述: 主界面
 * 作者|时间: djj on 2017/12/17 14:28
 * 博客地址: http://www.jianshu.com/u/dfbde65a03fc
 */
public class MainActivity extends BaseActivity {


    @BindView(R.id.ibtn_left)
    ImageButton ibtnLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ibtn_right)
    ImageButton ibtnRight;
    @BindView(R.id.web_view)
    WebView webView;
    String url = SharedPreferencesHelper.getInstance().getData("url", "").toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        tvTitle.setText("康复评定");
        ibtnLeft.setImageResource(R.mipmap.ic_back);
        ibtnRight.setVisibility(View.GONE);


        // 启用javascript
        webView.getSettings().setJavaScriptEnabled(true);
        // 从assets目录下面的加载html
        webView.loadUrl(url);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        initView();
    }

    private void initView() {
        webView.loadUrl(url);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String s) {
                webView.loadUrl(s);
                return true;
            }
        });
    }

    @OnClick({R.id.ibtn_left, R.id.ibtn_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ibtn_left:
                finish();
                break;
            case R.id.ibtn_right:
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}

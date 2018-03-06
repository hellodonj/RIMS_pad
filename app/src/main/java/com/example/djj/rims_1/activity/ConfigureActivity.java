package com.example.djj.rims_1.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.djj.rims_1.R;
import com.example.djj.rims_1.utiles.SharedPreferencesHelper;
import com.example.djj.rims_1.utiles.WebServiceUtils;
import com.orhanobut.logger.Logger;

import org.ksoap2.serialization.SoapPrimitive;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConfigureActivity extends BaseActivity {

    @BindView(R.id.et_ip)
    EditText etIp;
    @BindView(R.id.et_port)
    EditText etPort;
    @BindView(R.id.ibtn_left)
    ImageButton ibtnLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ibtn_right)
    ImageButton ibtnRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        ButterKnife.bind(this);

        tvTitle.setText("环境配置");
        ibtnLeft.setImageResource(R.mipmap.ic_back);
        ibtnRight.setVisibility(View.GONE);

        String ip = SharedPreferencesHelper.getInstance().getData("ip", "").toString();
        String port = SharedPreferencesHelper.getInstance().getData("port", "").toString();
        if (ip != null && port != null) {
            etIp.setText(ip);
            etPort.setText(port);
        }
    }

    @OnClick({R.id.ibtn_left, R.id.btn_login1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ibtn_left:
                finish();
                break;
            case R.id.btn_login1:
                String IP = etIp.getText().toString().trim();
                String port = etPort.getText().toString().trim();

                if (!TextUtils.isEmpty(IP) && !TextUtils.isEmpty(port)) {
                    SharedPreferencesHelper.getInstance().saveData("ip", IP);
                    SharedPreferencesHelper.getInstance().saveData("port", port);
                    //缓存登录数据到本地
                    StringBuffer sb = new StringBuffer();
                    sb.append("http://").append(IP).append(":").append(port).append("/WebInterface.asmx?wsdl");
                    String url = sb.toString();
                    SharedPreferencesHelper.getInstance().saveData("url", url);
                    String METHOD_NAME = "INTestConnect";
                    showDialog("", "验证中...");
                    WebServiceUtils.callWebService(url, METHOD_NAME, "", new WebServiceUtils.WebServiceCallBack() {
                        @Override
                        public void callBack(SoapPrimitive result) {
                            if (result != null) {
                                dismissDialog();
                                String results = result.toString();
                                Logger.json(results);
                                if (results.equals("T")) {
                                    finish();
                                }
                            } else {
                                Toast.makeText(ConfigureActivity.this, "IP或端口号不正确！", Toast.LENGTH_SHORT).show();
                                dismissDialog();
                            }

                        }
                    });
                } else {
                    Toast.makeText(ConfigureActivity.this, "请先输入IP和端口号！", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}

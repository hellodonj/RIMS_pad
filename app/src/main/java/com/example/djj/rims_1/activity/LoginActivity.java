package com.example.djj.rims_1.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.djj.rims_1.R;
import com.example.djj.rims_1.bean.RequestUser;
import com.example.djj.rims_1.bean.ResponseUser;
import com.example.djj.rims_1.utiles.JsonUtils;
import com.example.djj.rims_1.utiles.SharedPreferencesHelper;
import com.example.djj.rims_1.utiles.WebServiceUtils;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;

import org.ksoap2.serialization.SoapPrimitive;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 描述: 登录界面
 * 作者|时间: djj on 2017/12/17 13:59
 * 博客地址: http://www.jianshu.com/u/dfbde65a03fc
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.field_account)
    EditText fieldAccount;
    @BindView(R.id.field_password)
    EditText fieldPassword;
    @BindView(R.id.btn_login)
    TextView btnLogin;
    @BindView(R.id.btn_clear)
    ImageButton btnClear;
    @BindView(R.id.btn_lock)
    ImageButton btnLock;
    @BindView(R.id.tv_config)
    TextView tvConfig;
    @BindView(R.id.tv_version)
    TextView tvVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        tvVersion.setText("当前版本：" + getVersionCode() + "." + getVersionName());
        //记住用户名
        String id = SharedPreferencesHelper.getInstance().getData("id", "").toString();
        String password = SharedPreferencesHelper.getInstance().getData("password", "").toString();
        if (id != null && password != null) {
            fieldAccount.setText(id);
            fieldPassword.setText(password);
        }

        btnLogin.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        btnLock.setOnClickListener(this);
    }

    @OnClick({R.id.btn_login, R.id.btn_clear, R.id.btn_lock, R.id.tv_config})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.btn_clear:
                fieldAccount.setText("");
                fieldPassword.setText("");
                break;
            case R.id.btn_lock:
                fieldPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                break;
            case R.id.tv_config:
                Intent intent1 = new Intent(LoginActivity.this, ConfigureActivity.class);
                startActivity(intent1);
                break;
        }
    }

    //region 登录方法
    private void login() {
        String url = SharedPreferencesHelper.getInstance().getData("url", "").toString();
        String account = fieldAccount.getText().toString().trim();
        String password = fieldPassword.getText().toString().trim();

        List<RequestUser> lists = new ArrayList<>();
        RequestUser user = new RequestUser();
        user.setId(account);
        lists.add(user);
        String param = JsonUtils.toJson(lists);
        if (!url.equals("")) {
            if (!account.equals("")) { //&& !password.equals("")
                showDialog("", "登录中...");
                String METHOD_NAME = "IN1";
                WebServiceUtils.callWebService(url, METHOD_NAME, param, new WebServiceUtils.WebServiceCallBack() {
                    @Override
                    public void callBack(SoapPrimitive result) {
                        if (result != null) {
                            dismissDialog();
                            Logger.i(result.toString());
                            String results = result.toString();
                            if (!TextUtils.isEmpty(results)) {
                                Logger.json(result.toString());
                                List<ResponseUser> users = JsonUtils.fromJson(results, new TypeToken<List<ResponseUser>>() {
                                }.getType());
                                String account = fieldAccount.getText().toString().trim();
                                String password = fieldPassword.getText().toString().trim();
                                boolean isLogin = false;
                                for (ResponseUser user : users) {
                                    //缓存登录数据到本地
                                    SharedPreferencesHelper.getInstance().saveData("id", user.getId());
                                    SharedPreferencesHelper.getInstance().saveData("password", user.getPassword());
                                    SharedPreferencesHelper.getInstance().saveData("name", user.getName());
                                    SharedPreferencesHelper.getInstance().saveData("area", user.getKsmc());
                                    if (account.equals(user.getId()) && password.equals(user.getPassword())) {
                                        isLogin = true;
                                        break;
                                    }
                                }
                                if (isLogin) {
                                    Intent intent = new Intent(LoginActivity.this, PatientListActivity.class);
                                    startActivity(intent);
                                } else {
                                    //登录失败的提示
                                    Toast.makeText(mContext, "登录错误！ 错误的密码或用户名。", Toast.LENGTH_SHORT).show();
                                }
                            }
                        } else {
                            Toast.makeText(mContext, "网络连接有误！", Toast.LENGTH_SHORT).show();
                            dismissDialog();
                        }
                    }
                });
            } else {
                Toast.makeText(mContext, "工号和密码不能为空！", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(mContext, "请先填写IP和端口号！", Toast.LENGTH_SHORT).show();
        }
    }
    //endregion

    /**
     * 获取版本号
     *
     * @return
     */
    public int getVersionCode() {
        PackageManager manager = getPackageManager();//获取包管理器
        try {
            //通过当前的包名获取包的信息
            PackageInfo info = manager.getPackageInfo(getPackageName(), 0);//获取包对象信息
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取版本名
     *
     * @return
     */
    public String getVersionName() {
        PackageManager manager = getPackageManager();
        try {
            //第二个参数代表额外的信息，例如获取当前应用中的所有的Activity
            PackageInfo packageInfo = manager.getPackageInfo(getPackageName(), PackageManager.GET_ACTIVITIES);
            ActivityInfo[] activities = packageInfo.activities;
            showActivities(activities);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    public void showActivities(ActivityInfo[] activities) {
        for (ActivityInfo activity : activities) {
            Log.i("activity=========", activity.name);
        }
    }


}

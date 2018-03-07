package com.example.djj.rims_1.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.chaychan.viewlib.PowerfulEditText;
import com.example.djj.rims_1.R;
import com.example.djj.rims_1.adapter.PatientAdapter;
import com.example.djj.rims_1.bean.PatientRequest;
import com.example.djj.rims_1.bean.PatientResponse;
import com.example.djj.rims_1.utiles.DateUtil;
import com.example.djj.rims_1.utiles.JsonUtils;
import com.example.djj.rims_1.utiles.SharedPreferencesHelper;
import com.example.djj.rims_1.utiles.WebServiceUtils;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;

import org.ksoap2.serialization.SoapPrimitive;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 描述: 康复病人列表界面
 * 作者|时间: djj on 2018/3/6 15:20
 * 博客地址: http://www.jianshu.com/u/dfbde65a03fc
 */

public class PatientListActivity extends BaseActivity {

    @BindView(R.id.patient_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.ibtn_left)
    ImageButton ibtnLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ibtn_right)
    ImageButton ibtnRight;
    @BindView(R.id.et_scan)
    PowerfulEditText etScan;
    @BindView(R.id.hospital_spinner)
    Spinner hospitalSpinner;
    @BindView(R.id.patient_area_spinner)
    Spinner patientAreaSpinner;
    @BindView(R.id.btn_search)
    Button btnSearch;
    private ArrayAdapter<String> mHospitalAdapter;
    private ArrayAdapter<String> mPatientAreaAdapter;
    private Context mContext = PatientListActivity.this;
    private PatientAdapter mPatientAdapter;
    private List<PatientResponse> mPatientList;
    private List<PatientResponse> temporaryResponses = new ArrayList<PatientResponse>();
    private RecyclerView.LayoutManager mLayoutManger;
    String id = SharedPreferencesHelper.getInstance().getData("id", "").toString();
    String url = SharedPreferencesHelper.getInstance().getData("url", "").toString();
    final int pageSize = 20; // 固定大小
    int startIndex = 1;  // 起始页（从1开始）

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_list);
        ButterKnife.bind(this);

        initData();

        projectWebServer(1, 20);

        mRecyclerView.addItemDecoration(new DividerItemDecoration(PatientListActivity.this, DividerItemDecoration.VERTICAL));
        mLayoutManger = new LinearLayoutManager(PatientListActivity.this);
        mRecyclerView.setLayoutManager(mLayoutManger);
        mPatientAdapter = new PatientAdapter(PatientListActivity.this);
        mRecyclerView.setAdapter(mPatientAdapter);
        mPatientAdapter.setOnItemClickListener(new PatientAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(PatientListActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //region 扫码登录监听
        etScan.setOnRightClickListener(new PowerfulEditText.OnRightClickListener() {
            @Override
            public void onClick(EditText editText) {
                String scanResult = etScan.getText().toString();
                Intent intent = new Intent(PatientListActivity.this, ScanProjectActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("scanResult", scanResult);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        //endregion
    }

    private void initData() {
        tvTitle.setText("患者评定列表");
        ibtnLeft.setImageResource(R.mipmap.ic_back);
        ibtnRight.setVisibility(View.GONE);
        List<String> list = new ArrayList<String>();
        list.add("医院名称一");
        list.add("医院名称二");
        list.add("医院名称三");
        list.add("医院名称四");
        list.add("厦门弘爱医院");
        mHospitalAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, list);
        hospitalSpinner.setAdapter(mHospitalAdapter);

        List<String> list1 = new ArrayList<String>();
        list1.add("病区一");
        list1.add("病区二");
        list1.add("病区三");
        list1.add("病区四");
        list1.add("病区五");
        list1.add("病区六");
        mPatientAreaAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, list1);
        patientAreaSpinner.setAdapter(mPatientAreaAdapter);

    }

    private void projectWebServer(int startIndex, int pageSize) {
        //获取当前年月日
        Date date = new Date();
        String dateNowStr = DateUtil.getCurrentDateFormat(date);
        List<PatientRequest> patientList = new ArrayList<PatientRequest>();
        final PatientRequest request = new PatientRequest();
        request.setId(id);
        request.setYyrq(dateNowStr);//dateNowStr "20170502"
        request.setRowcount(pageSize);
        request.setPagenum(startIndex);
        patientList.add(request);
        String param = JsonUtils.toJson(patientList);
        showDialog("", "加载中...");
        String METHOD_NAME = "IN14";
        WebServiceUtils.callWebService(url, METHOD_NAME, param, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(SoapPrimitive result) {
                dismissDialog();
                String results = null;
                try {
                    results = result.toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (!TextUtils.isEmpty(results)) {
                    Logger.json(results);
                    mPatientList = JsonUtils.fromJson(results, new TypeToken<List<PatientResponse>>() {
                    }.getType());
                    if (mPatientList != null && mPatientList.size() > 1) {
                        temporaryResponses.addAll(mPatientList);
                        mPatientAdapter.setPatientData(temporaryResponses);
                    }
                } else {
                    Toast.makeText(PatientListActivity.this, "无数据......", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @OnClick({R.id.ibtn_left, R.id.ibtn_right, R.id.btn_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ibtn_left:
                finish();
                break;
            case R.id.ibtn_right:
                break;
            case R.id.btn_search:
                projectWebServer(2, 5);
                break;
        }
    }


}

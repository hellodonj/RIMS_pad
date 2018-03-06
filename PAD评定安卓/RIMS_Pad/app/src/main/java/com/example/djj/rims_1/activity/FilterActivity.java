package com.example.djj.rims_1.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.djj.rims_1.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 描述: 筛选界面
 * 作者|时间: djj on 2018/3/5 10:04
 * 博客地址: http://www.jianshu.com/u/dfbde65a03fc
 */
public class FilterActivity extends AppCompatActivity {


    @BindView(R.id.tv_filter)
    TextView tvFilter;
    @BindView(R.id.hospital_spinner)
    Spinner hospitalSpinner;
    @BindView(R.id.patient_area_spinner)
    Spinner patientAreaSpinner;
    @BindView(R.id.tv_filter_config)
    TextView tvFilterConfig;
    private ArrayAdapter<String> mHospitalAdapter;
    private ArrayAdapter<String> mPatientAdapter;
    private Context mContext = FilterActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        ButterKnife.bind(this);

        tvFilter.setText("筛选配置");
        initData();
    }

    private void initData() {
        List<String> list = new ArrayList<String>();
        list.add("医院名称一");
        list.add("医院名称二");
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
        mPatientAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, list1);
        patientAreaSpinner.setAdapter(mPatientAdapter);

    }

    @OnClick(R.id.tv_filter_config)
    public void onViewClicked() {
        Intent intent = new Intent(FilterActivity.this, PatientListActivity.class);
        startActivity(intent);
    }
}

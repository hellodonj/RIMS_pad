package com.example.djj.rims_1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chaychan.viewlib.PowerfulEditText;
import com.example.djj.rims_1.R;
import com.example.djj.rims_1.adapter.PatientAdapter;
import com.example.djj.rims_1.bean.PatientResponse;
import com.example.djj.rims_1.bean.TaskRequest;
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
 * 描述: 搜索结果界面
 * 作者|时间: djj on 2018/2/26 13:15
 * 博客地址: http://www.jianshu.com/u/dfbde65a03fc
 */
public class ScanProjectActivity extends BaseActivity {

    @BindView(R.id.ibtn_left)
    ImageButton mIbtnLeft;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.ibtn_right)
    ImageButton mIbtnRight;
    @BindView(R.id.et_scan)
    PowerfulEditText mEtScan;
    @BindView(R.id.scan_layout)
    LinearLayout mScanLayout;
    @BindView(R.id.patient_list)
    RecyclerView mRecyclerView;
    private PatientAdapter mPatientAdapter;
    private List<PatientResponse> mPatientResponse;
    private List<PatientResponse> temporaryResponses = new ArrayList<PatientResponse>();
    private RecyclerView.LayoutManager mLayoutManager;
    String id = SharedPreferencesHelper.getInstance().getData("id", "").toString();
    String url = SharedPreferencesHelper.getInstance().getData("url", "").toString();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_project);
        ButterKnife.bind(this);

        mIbtnLeft.setImageResource(R.mipmap.ic_back);
        mIbtnRight.setVisibility(View.GONE);
        mScanLayout.setVisibility(View.GONE);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String str = bundle.getString("scanResult");

        mRecyclerView.addItemDecoration(new DividerItemDecoration(ScanProjectActivity.this, DividerItemDecoration.VERTICAL));
        mLayoutManager = new LinearLayoutManager(ScanProjectActivity.this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mPatientAdapter = new PatientAdapter(ScanProjectActivity.this);
        mRecyclerView.setAdapter(mPatientAdapter);
        Date date = new Date();
        final String dateNowStr = DateUtil.getCurrentDateFormat(date);

        List<TaskRequest> taskList = new ArrayList<>();
        final TaskRequest request = new TaskRequest();
        request.setId(id);
        request.setYyrq(dateNowStr);//不需要预约日期  dateNowStr  "20170502"
        request.setXtbz("1");
        request.setHzxm(str);//str 根据姓名去查询
        request.setKh("");//卡号
        request.setBqmc("");//病区名称
        request.setXmmc("");//项目名称
        request.setTxm("");//str扫码结果
        taskList.add(request);
        String param = JsonUtils.toJson(taskList);
        String methodName = "INField03";
        WebServiceUtils.callWebService(url, methodName, param, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(SoapPrimitive result) {
                dismissDialog();
                if (result == null) {
                    mTvTitle.setText("无项目");
                    Toast.makeText(ScanProjectActivity.this, "无数据......", Toast.LENGTH_SHORT).show();
                } else {
                    String results = result.toString();
                    if (!TextUtils.isEmpty(result.toString())) {
                        Logger.json(results);
                        mPatientResponse = JsonUtils.fromJson(results, new TypeToken<List<PatientResponse>>() {
                        }.getType());
                        if (mPatientResponse != null && mPatientResponse.size() > 0) {
                            for (int i = 0; i < mPatientResponse.size(); i++) {
                                PatientResponse tt = mPatientResponse.get(i);
                                mTvTitle.setText(tt.getHzxm());
                            }
                            mPatientAdapter.setPatientData(mPatientResponse);
                            mPatientAdapter.notifyDataSetChanged();
                        }
                    }
                }
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
}

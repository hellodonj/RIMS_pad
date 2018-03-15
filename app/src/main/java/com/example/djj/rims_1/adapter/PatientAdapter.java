package com.example.djj.rims_1.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.djj.rims_1.R;
import com.example.djj.rims_1.bean.PatientResponse;

import java.util.List;

/**
 * 描述: 病人列表adapter
 * 作者|时间: djj on 2018/2/23 14:11
 * 博客地址: http://www.jianshu.com/u/dfbde65a03fc
 */

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.MyViewHolder> {

    private List<PatientResponse> mPatientList;
    private Context mContext;

    public PatientAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setPatientData(List<PatientResponse> patientList) {
        this.mPatientList = patientList;
        notifyDataSetChanged();
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_patient_list, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        //绑定数据
        PatientResponse patientResponse = mPatientList.get(position);
        holder.patientName.setText(patientResponse.getHzxm());
        holder.patientStatus.setVisibility(View.VISIBLE);
        holder.patientAge.setText(patientResponse.getHznl() + "岁/");
        holder.patientSex.setText(patientResponse.getXb());
        holder.patientBed.setText(patientResponse.getCwdm() + "床");
        holder.patientArea.setText(patientResponse.getBqmc());
        holder.patientDiagnosis.setText("诊断："+patientResponse.getZdmc());
        holder.patientCost.setText("新农合");
        holder.patientTime.setText("2018-3-15");
        holder.attendDoctor.setText("张伟");

        // 如果设置了回调，则设置点击事件
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int layoutPosition = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView, layoutPosition);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (mPatientList != null) {
            return mPatientList.size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        public TextView patientName;
        public TextView patientStatus;
        public TextView patientAge;
        public TextView patientSex;
        public TextView patientBed;
        public TextView patientArea;
        public TextView patientCost;       //费用类别
        public TextView patientTime;
        public TextView patientDiagnosis;  //诊断信息
        public TextView attendDoctor;      //主治医师

        public MyViewHolder(View itemView) {
            super(itemView);
            patientName = itemView.findViewById(R.id.patient_name);
            patientStatus = itemView.findViewById(R.id.patient_status);
            patientAge = itemView.findViewById(R.id.patient_age);
            patientSex = itemView.findViewById(R.id.patient_sex);
            patientBed = itemView.findViewById(R.id.patient_bed);
            patientArea = itemView.findViewById(R.id.patient_area);
            patientCost = itemView.findViewById(R.id.patient_cost_category);
            patientTime = itemView.findViewById(R.id.patient_time);
            patientDiagnosis = itemView.findViewById(R.id.patient_diagnosis);
            attendDoctor = itemView.findViewById(R.id.attend_doctor);
        }
    }

    /**
     * 定义接口回调
     */
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
}

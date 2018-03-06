package com.example.djj.rims_1.bean;

/**
 * desc: 任务信息请求参数
 * author: dj
 * date: 2017/2/27 15:48
 */

public class TaskRequest {
    //工号
    private String id;
    //预约日期
    private String yyrq;
    //系统标志
    private String xtbz;
    //患者姓名
    private String hzxm;
    //卡号
    private String kh;
    //病区名称
    private String bqmc;
    //项目名称
    private String xmmc;
    //条形码
    private String txm;


    public String toString() {
        return "{\"id\":\"" + id + "\",\"yyrq\":" + yyrq + "}";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getYyrq() {
        return yyrq;
    }

    public void setYyrq(String yyrq) {
        this.yyrq = yyrq;
    }

    public String getXtbz() {
        return xtbz;
    }

    public void setXtbz(String xtbz) {
        this.xtbz = xtbz;
    }

    public String getHzxm() {
        return hzxm;
    }

    public void setHzxm(String hzxm) {
        this.hzxm = hzxm;
    }

    public String getKh() {
        return kh;
    }

    public void setKh(String kh) {
        this.kh = kh;
    }

    public String getBqmc() {
        return bqmc;
    }

    public void setBqmc(String bqmc) {
        this.bqmc = bqmc;
    }

    public String getXmmc() {
        return xmmc;
    }

    public void setXmmc(String xmmc) {
        this.xmmc = xmmc;
    }

    public String getTxm() {
        return txm;
    }

    public void setTxm(String txm) {
        this.txm = txm;
    }
}

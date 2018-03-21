package com.example.djj.rims_1.bean;

/**
 * 描述: 患者列表实体
 * 作者|时间: djj on 2018/2/23 15:15
 * 博客地址: http://www.jianshu.com/u/dfbde65a03fc
 */

public class PatientResponse {

    //治疗明细行号
    private int xh;
    //项目序号
    private String xmxh;
    //项目代码
    private String xmdm;
    //项目名称
    private String xmmc;
    //拼音
    private String py;
    //五笔
    private String wb;
    //患者姓名
    private String hzxm;
    //患者年龄
    private String hznl;
    //患者主键
    private String patid;
    //病历号
    private String blh;
    //首页序号
    private String syxh;
    //性别
    private String xb;
    //系统标志
    private String xtbz;
    //床位代码
    private String cwdm;
    //病区代码
    private String bqdm;
    //科室代码
    private String ksdm;
    //项目状态
    private String xmzt;
    //项目单价
    private String xmdj;
    //项目时长
    private String xmsc;
    private String xmscSrc;
    //执行百分比
    private String zxbl;
    //操作时长
    private String czsc;
    //执行次数
    private String zxcs;
    //治疗部位
    private String zlbw;
    //治疗剂量
    private String zljl;
    //剂量单位
    private String jldw;
    //治疗频次
    private String zlpc;
    //治疗方法
    private String zlff;
    //注意事项
    private String zysx;
    //治疗反应
    private String zlfy;
    //取消原因
    private String qxyy;
    //收费标准
    private String sfbz;
    //治疗时间
    private String zlsj;
    //取消时间
    private String qxsj;
    //医嘱序号
    private String yzxh;
    //录入日期
    private String lrrq;
    //治疗医生代码
    private String zlys;
    //治疗医生名称
    private String zlysmc;
    //阶段小结
    private String jdxj;
    //住院病人为首页序号，门诊病人为卡号
    private String kh;
    //病区名称
    private String bqmc;
    //设备标志
    private String sbbz;
    //诊断
    private String zdmc;
    //会诊标志
    private String hzbz;
    //费用类别
    private String fylb;
    //入院日期
    private String ryrq;
    //主治医师
    private String zzysmc;

    public int getXh() {
        return xh;
    }

    public void setXh(int xh) {
        this.xh = xh;
    }

    public String getXmxh() {
        return xmxh;
    }

    public void setXmxh(String xmxh) {
        this.xmxh = xmxh;
    }

    public String getXmdm() {
        return xmdm;
    }

    public void setXmdm(String xmdm) {
        this.xmdm = xmdm;
    }

    public String getXmmc() {
        return xmmc;
    }

    public void setXmmc(String xmmc) {
        this.xmmc = xmmc;
    }

    public String getPy() {
        return py;
    }

    public void setPy(String py) {
        this.py = py;
    }

    public String getWb() {
        return wb;
    }

    public void setWb(String wb) {
        this.wb = wb;
    }

    public String getHzxm() {
        return hzxm;
    }

    public void setHzxm(String hzxm) {
        this.hzxm = hzxm;
    }

    public String getHznl() {
        return hznl;
    }

    public void setHznl(String hznl) {
        this.hznl = hznl;
    }

    public String getPatid() {
        return patid;
    }

    public void setPatid(String patid) {
        this.patid = patid;
    }

    public String getBlh() {
        return blh;
    }

    public void setBlh(String blh) {
        this.blh = blh;
    }

    public String getSyxh() {
        return syxh;
    }

    public void setSyxh(String syxh) {
        this.syxh = syxh;
    }

    public String getXb() {
        return xb;
    }

    public void setXb(String xb) {
        this.xb = xb;
    }

    public String getXtbz() {
        return xtbz;
    }

    public void setXtbz(String xtbz) {
        this.xtbz = xtbz;
    }

    public String getCwdm() {
        return cwdm;
    }

    public void setCwdm(String cwdm) {
        this.cwdm = cwdm;
    }

    public String getBqdm() {
        return bqdm;
    }

    public void setBqdm(String bqdm) {
        this.bqdm = bqdm;
    }

    public String getKsdm() {
        return ksdm;
    }

    public void setKsdm(String ksdm) {
        this.ksdm = ksdm;
    }

    public String getXmzt() {
        return xmzt;
    }

    public void setXmzt(String xmzt) {
        this.xmzt = xmzt;
    }

    public String getXmdj() {
        return xmdj;
    }

    public void setXmdj(String xmdj) {
        this.xmdj = xmdj;
    }

    public String getXmsc() {
        return xmsc;
    }

    public void setXmsc(String xmsc) {
        this.xmsc = xmsc;
    }

    public String getXmscSrc() {
        return xmscSrc;
    }

    public void setXmscSrc(String xmscSrc) {
        this.xmscSrc = xmscSrc;
    }

    public String getZxbl() {
        return zxbl;
    }

    public void setZxbl(String zxbl) {
        this.zxbl = zxbl;
    }

    public String getCzsc() {
        return czsc;
    }

    public void setCzsc(String czsc) {
        this.czsc = czsc;
    }

    public String getZxcs() {
        return zxcs;
    }

    public void setZxcs(String zxcs) {
        this.zxcs = zxcs;
    }

    public String getZlbw() {
        return zlbw;
    }

    public void setZlbw(String zlbw) {
        this.zlbw = zlbw;
    }

    public String getZljl() {
        return zljl;
    }

    public void setZljl(String zljl) {
        this.zljl = zljl;
    }

    public String getJldw() {
        return jldw;
    }

    public void setJldw(String jldw) {
        this.jldw = jldw;
    }

    public String getZlpc() {
        return zlpc;
    }

    public void setZlpc(String zlpc) {
        this.zlpc = zlpc;
    }

    public String getZlff() {
        return zlff;
    }

    public void setZlff(String zlff) {
        this.zlff = zlff;
    }

    public String getZysx() {
        return zysx;
    }

    public void setZysx(String zysx) {
        this.zysx = zysx;
    }

    public String getZlfy() {
        return zlfy;
    }

    public void setZlfy(String zlfy) {
        this.zlfy = zlfy;
    }

    public String getQxyy() {
        return qxyy;
    }

    public void setQxyy(String qxyy) {
        this.qxyy = qxyy;
    }

    public String getSfbz() {
        return sfbz;
    }

    public void setSfbz(String sfbz) {
        this.sfbz = sfbz;
    }

    public String getZlsj() {
        return zlsj;
    }

    public void setZlsj(String zlsj) {
        this.zlsj = zlsj;
    }

    public String getQxsj() {
        return qxsj;
    }

    public void setQxsj(String qxsj) {
        this.qxsj = qxsj;
    }

    public String getYzxh() {
        return yzxh;
    }

    public void setYzxh(String yzxh) {
        this.yzxh = yzxh;
    }

    public String getLrrq() {
        return lrrq;
    }

    public void setLrrq(String lrrq) {
        this.lrrq = lrrq;
    }

    public String getZlys() {
        return zlys;
    }

    public void setZlys(String zlys) {
        this.zlys = zlys;
    }

    public String getZlysmc() {
        return zlysmc;
    }

    public void setZlysmc(String zlysmc) {
        this.zlysmc = zlysmc;
    }

    public String getJdxj() {
        return jdxj;
    }

    public void setJdxj(String jdxj) {
        this.jdxj = jdxj;
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

    public String getSbbz() {
        return sbbz;
    }

    public void setSbbz(String sbbz) {
        this.sbbz = sbbz;
    }

    public String getZdmc() {
        return zdmc;
    }

    public void setZdmc(String zdmc) {
        this.zdmc = zdmc;
    }

    public String getFylb() {
        return fylb;
    }

    public void setFylb(String fylb) {
        this.fylb = fylb;
    }

    public String getRyrq() {
        return ryrq;
    }

    public void setRyrq(String ryrq) {
        this.ryrq = ryrq;
    }

    public String getHzbz() {
        return hzbz;
    }

    public void setHzbz(String hzbz) {
        this.hzbz = hzbz;
    }

    public String getZzysmc() {
        return zzysmc;
    }

    public void setZzysmc(String zzysmc) {
        this.zzysmc = zzysmc;
    }
}

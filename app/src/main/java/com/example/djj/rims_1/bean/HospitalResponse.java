package com.example.djj.rims_1.bean;

/**
 * 描述: 医院列表返回实体
 * 作者|时间: djj on 2018/3/12 13:59
 * 博客地址: http://www.jianshu.com/u/dfbde65a03fc
 */

public class HospitalResponse {

    //医院代码
    private String yydm;
    //医院名称
    private String yymc;

    public String getYydm() {
        return yydm;
    }

    public void setYydm(String yydm) {
        this.yydm = yydm;
    }

    public String getYymc() {
        return yymc;
    }

    public void setYymc(String yymc) {
        this.yymc = yymc;
    }
}

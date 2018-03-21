package com.example.djj.rims_1.bean;

/**
 * 登录请求返回
 * Created by CharlesLui on 20/02/2017.
 */

public class ResponseUser {

    /**
     * id : 00//工号
     * name : supervisor//名称
     * password ://密码
     * regist_time : 2017012515:15:41//注册时间
     * ksdm : 3201//科室代码
     * ksmc : 内科//科室名称
     */
    private String id;//工号
    private String name;
    private String password;
    private String regist_time;
    private String ksdm;
    private String ksmc;
    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegist_time() {
        return regist_time;
    }

    public void setRegist_time(String regist_time) {
        this.regist_time = regist_time;
    }

    public String getKsdm() {
        return ksdm;
    }

    public void setKsdm(String ksdm) {
        this.ksdm = ksdm;
    }

    public String getKsmc() {
        return ksmc;
    }

    public void setKsmc(String ksmc) {
        this.ksmc = ksmc;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ResponseUser{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", regist_time='" + regist_time + '\'' +
                ", ksdm='" + ksdm + '\'' +
                ", ksmc='" + ksmc + '\'' +
                '}';
    }
}

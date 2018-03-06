package com.example.djj.rims_1.bean;

/**
 * 描述: 病人列表请求参数
 * 作者|时间: djj on 2018/2/23 16:11
 * 博客地址: http://www.jianshu.com/u/dfbde65a03fc
 */

public class PatientRequest {

    //工号
    private String id;
    //预约日期
    private String yyrq;
    //一页数据总数
    private int rowcount;
    //页码
    private int pagenum;

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

    public int getRowcount() {
        return rowcount;
    }

    public void setRowcount(int rowcount) {
        this.rowcount = rowcount;
    }

    public int getPagenum() {
        return pagenum;
    }

    public void setPagenum(int pagenum) {
        this.pagenum = pagenum;
    }
}

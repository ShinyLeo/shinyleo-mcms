package com.shinyleo.basic.entity;

import com.shinyleo.base.entity.BaseEntity;
import com.shinyleo.basic.constant.e.LogEnum;

import java.util.Date;

/**
 * Created by shinyleo on 17/7/20.
 * -日志基础
 */
public class BasicLogEntity  extends BaseEntity {

    private int basicLogId;

    private int basicLogAppId;

    private Date basicLogDatetime;

    private String basicLogIp;

    private int basicLogIsMobile;

    private int basicLogBasicId;

    public int getBasicLogId() {
        return basicLogId;
    }

    public void setBasicLogId(int basicLogId) {
        this.basicLogId = basicLogId;
    }

    public int getBasicLogAppId() {
        return basicLogAppId;
    }

    public void setBasicLogAppId(int basicLogAppId) {
        this.basicLogAppId = basicLogAppId;
    }

    public Date getBasicLogDatetime() {
        return basicLogDatetime;
    }

    public void setBasicLogDatetime(Date basicLogDatetime) {
        this.basicLogDatetime = basicLogDatetime;
    }

    public String getBasicLogIp() {
        return basicLogIp;
    }

    public void setBasicLogIp(String basicLogIp) {
        this.basicLogIp = basicLogIp;
    }

    public int getBasicLogIsMobile() {
        return basicLogIsMobile;
    }

    public void setBasicLogIsMobile(int basicLogIsMobile) {
        this.basicLogIsMobile = basicLogIsMobile;
    }

    public void setBasicLogIsMobile(LogEnum basicLogIsMobile) {
        this.basicLogIsMobile = basicLogIsMobile.toInt();
    }

    public int getBasicLogBasicId() {
        return basicLogBasicId;
    }

    public void setBasicLogBasicId(int basicLogBasicId) {
        this.basicLogBasicId = basicLogBasicId;
    }
}

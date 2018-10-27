package com.qf.logmonitor1706.domain;

/**
 * 规则记录信息表
 */
public class Record {
    // 告警信息编号
    private int id;
    // 所属应用
    private int appId;
    // 所属规则
    private int ruleId;
    // 是否发送邮件
    private int isEmail;
    // 是否发送信息
    private int isPhone;
    // 是否处理完毕
    private int isClose;
    // 告警信息
    private String line;

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", appId=" + appId +
                ", ruleId=" + ruleId +
                ", isEmail=" + isEmail +
                ", isPhone=" + isPhone +
                ", isClose=" + isClose +
                ", line='" + line + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public int getRuleId() {
        return ruleId;
    }

    public void setRuleId(int ruleId) {
        this.ruleId = ruleId;
    }

    public int getIsEmail() {
        return isEmail;
    }

    public void setIsEmail(int isEmail) {
        this.isEmail = isEmail;
    }

    public int getIsPhone() {
        return isPhone;
    }

    public void setIsPhone(int isPhone) {
        this.isPhone = isPhone;
    }

    public int getIsClose() {
        return isClose;
    }

    public void setIsClose(int isClose) {
        this.isClose = isClose;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }
}

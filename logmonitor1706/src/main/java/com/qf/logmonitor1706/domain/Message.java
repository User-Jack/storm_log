package com.qf.logmonitor1706.domain;

import java.io.Serializable;

/**
 * 信息内容
 */
public class Message implements Serializable {
    // 日志信息所属应用编号
    private int appId;
    // 信息内容
    private String line;
    // 规则编号
    private int ruleId;
    // 规则关键字
    private String keyword;
    // 是否发送邮件
    private int isEmail;
    // 应用程序名称
    private String appName;

    @Override
    public String toString() {
        return "Message{" +
                "appId=" + appId +
                ", line='" + line + '\'' +
                ", ruleId=" + ruleId +
                ", keyword='" + keyword + '\'' +
                ", isEmail=" + isEmail +
                ", appName='" + appName + '\'' +
                '}';
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public int getRuleId() {
        return ruleId;
    }

    public void setRuleId(int ruleId) {
        this.ruleId = ruleId;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getIsEmail() {
        return isEmail;
    }

    public void setIsEmail(int isEmail) {
        this.isEmail = isEmail;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
}

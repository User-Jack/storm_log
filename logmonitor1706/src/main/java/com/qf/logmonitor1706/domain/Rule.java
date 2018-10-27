package com.qf.logmonitor1706.domain;

/**
 * 规则表
 */
public class Rule {
    // 规则编号
    private int id;
    // 规则名称
    private String name;
    // 过滤关键字
    private String keyword;
    // 是否可用
    private int isValid;
    // 所属应用
    private int appId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getIsValid() {
        return isValid;
    }

    public void setIsValid(int isValid) {
        this.isValid = isValid;
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    @Override
    public String toString() {
        return "Rule{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", keyword='" + keyword + '\'' +
                ", isValid=" + isValid +
                ", appId=" + appId +
                '}';
    }
}

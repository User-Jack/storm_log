package com.qf.logmonitor1706.domain;

/**
 * 应用程序表
 */
public class App {
    // 应用程序编号
    private int id;
    // 应用程序名称
    private String name;
    // 是否在线
    private int isOnline;
    // 所属类别
    private int typeId;
    // 应用程序负责人id
    private String userId;

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

    public int getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(int isOnline) {
        this.isOnline = isOnline;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "App{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isOnline=" + isOnline +
                ", typeId=" + typeId +
                ", userId='" + userId + '\'' +
                '}';
    }
}

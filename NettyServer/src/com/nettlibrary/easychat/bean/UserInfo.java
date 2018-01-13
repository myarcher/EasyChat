package com.nettlibrary.easychat.bean;

import java.util.Date;

/**
 * Created by Administrator on 2018/1/5.
 */
public class UserInfo extends BaseInfo {
    private long uid;
    private String uname;
    private String uphone;
    private int level;
    private int sex;


    public UserInfo(long uid) {
        this.uname = "新用户";
        this.uid=uid;
        this.uphone="";
        this.level=1;
        this.sex=3;
    }
    public UserInfo() {
        this.uname = "新用户";
        this.uid=new Date().getTime();
        this.uphone="";
        this.level=1;
        this.sex=3;
    }

    public UserInfo(String uname, String upawd) {
        this.uname = uname;
        this.uid=new Date().getTime();
        this.uphone="";
        this.level=1;
        this.sex=3;
    }

    public UserInfo(long uid, String uname, String uphone, int level, int sex) {
        this.uid = uid;
        this.uname = uname;
        this.uphone = uphone;
        this.level = level;
        this.sex = sex;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }


    public String getUphone() {
        return uphone;
    }

    public void setUphone(String uphone) {
        this.uphone = uphone;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}

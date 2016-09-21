package com.iblogstreet.smalltalk.table;

import cn.bmob.v3.BmobObject;

/*
 *  @项目名：  SmallTalk 
 *  @包名：    com.iblogstreet.smalltalk.table
 *  @文件名:   Account
 *  @创建者:   Army
 *  @创建时间:  2016/9/16 20:27
 *  @描述：    用户账号
 */
public class Account extends BmobObject{
    private static final String TAG = "Account";
    private String name;
    private String pwd;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPwd() {
        return pwd;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}

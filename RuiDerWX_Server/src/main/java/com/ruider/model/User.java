package com.ruider.model;

import java.sql.Blob;
import java.util.Date;

/**
 * Created by mahede on 2018/11/27.
 */
public class User {

    private int id;

    private String nickName;

    private int age;

    private String password;

    private String mobilePhone;

    private String image;

    private String autograph;    //用户签名

    private int status;

    private String realName;

    private String sex;

    private int level;

    private String openId;

    private String headurl;

    private String userIp;

    private String createTime;

    private String extendField1;

    private String extendField2;

    private String extendField3;

    private String extendField4;

    private String extendField5;

    private String extendField6;

    private String extendField7;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getOpenid() {
        return openId;
    }

    public void setOpenid(String openId) {
        this.openId = openId;
    }

    public String getHeadurl() {
        return headurl;
    }

    public void setHeadurl(String headUrl) {
        this.headurl = headUrl;
    }

    public String getUserip() {
        return userIp;
    }

    public void setUserip(String userIp) {
        this.userIp = userIp;
    }

    public User() {}

    public User(int id, String nickName,int age, String password, String mobilePhone, String image, String autograph, int status, String realName, String sex, int level, String openid, String headurl, String userip, String createTime, String extendField1, String extendField2, String extendField3, String extendField4, String extendField5, String extendField6, String extendField7) {
        this.id = id;
        this.nickName = nickName;
        this.age = age;
        this.password = password;
        this.mobilePhone = mobilePhone;
        this.image = image;
        this.autograph = autograph;
        this.status = status;
        this.realName = realName;
        this.sex = sex;
        this.level = level;
        this.openId = openid;
        this.headurl = headurl;
        this.userIp = userip;
        this.createTime = createTime;
        this.extendField1 = extendField1;
        this.extendField2 = extendField2;
        this.extendField3 = extendField3;
        this.extendField4 = extendField4;
        this.extendField5 = extendField5;
        this.extendField6 = extendField6;
        this.extendField7 = extendField7;
    }

    public User(String nickName, int age, String mobilePhone, String sex, String openId, String userIp, String createtime) {
        this.nickName = nickName;
        this.age = age;
        this.mobilePhone = mobilePhone;
        this.sex = sex;
        this.openId = openId;
        this.userIp = userIp;
        this.createTime = createtime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String  getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAutograph() {
        return autograph;
    }

    public void setAutograph(String autograph) {
        this.autograph = autograph;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getExtendField1() {
        return extendField1;
    }

    public void setExtendField1(String extendField1) {
        this.extendField1 = extendField1;
    }

    public String getExtendField2() {
        return extendField2;
    }

    public void setExtendField2(String extendField2) {
        this.extendField2 = extendField2;
    }

    public String getExtendField3() {
        return extendField3;
    }

    public void setExtendField3(String extendField3) {
        this.extendField3 = extendField3;
    }

    public String getExtendField4() {
        return extendField4;
    }

    public void setExtendField4(String extendField4) {
        this.extendField4 = extendField4;
    }

    public String getExtendField5() {
        return extendField5;
    }

    public void setExtendField5(String extendField5) {
        this.extendField5 = extendField5;
    }

    public String getExtendField6() {
        return extendField6;
    }

    public void setExtendField6(String extendField6) {
        this.extendField6 = extendField6;
    }

    public String getExtendField7() {
        return extendField7;
    }

    public void setExtendField7(String extendField7) {
        this.extendField7 = extendField7;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + nickName + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", image='" + image + '\'' +
                ", autograph='" + autograph + '\'' +
                ", status=" + status +
                ", realName='" + realName + '\'' +
                ", sex='" + sex + '\'' +
                ", level=" + level +
                ", openId='" + openId + '\'' +
                ", headUrl='" + headurl + '\'' +
                ", userIp='" + userIp + '\'' +
                ", createTime='" + createTime + '\'' +
                ", extendField1='" + extendField1 + '\'' +
                ", extendField2='" + extendField2 + '\'' +
                ", extendField3='" + extendField3 + '\'' +
                ", extendField4='" + extendField4 + '\'' +
                ", extendField5='" + extendField5 + '\'' +
                ", extendField6='" + extendField6 + '\'' +
                ", extendField7='" + extendField7 + '\'' +
                '}';
    }
}

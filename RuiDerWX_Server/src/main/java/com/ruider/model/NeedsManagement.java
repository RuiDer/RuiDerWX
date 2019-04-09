package com.ruider.model;

import java.util.Date;

/**
 * Created by mahede on 2018/11/27.
 */
public class NeedsManagement {

    int id;

    int categoryId;

    int userId;

    String title;

    String content;

    Date startTime;

    Date deadline;

    String qq;

    String weChat;

    String phoneNo;

    int limitNo;

    Date createTime;

    Date updateTime;

    private int joinNo;    //当前加入人数

    private String viewNo;   //点击量

    private int isOverTime;

    private int isOverNo;

    private String extendField5;

    private String extendField6;

    private String extendField7;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWeChat() {
        return weChat;
    }

    public void setWeChat(String weChat) {
        this.weChat = weChat;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public int getLimitNo() {
        return limitNo;
    }

    public void setLimitNo(int limitNo) {
        this.limitNo = limitNo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getJoinNo() {
        return joinNo;
    }

    public void setJoinNo(int joinNo) {
        this.joinNo = joinNo;
    }

    public String getViewNo() {
        return viewNo;
    }

    public void setViewNo(String extendField2) {
        this.viewNo = viewNo;
    }

    public int getIsOverTime() {
        return isOverTime;
    }

    public void setIsOverTime(int isOverTime) {
        this.isOverTime = isOverTime;
    }

    public int getIsOverNo() {
        return isOverNo;
    }

    public void setIsOverNo(int extendField4) {
        this.isOverNo = isOverNo;
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
        return "Carpooling{" +
                "id=" + id +
                ", categoryId=" + categoryId +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", startTime=" + startTime +
                ", deadline=" + deadline +
                ", qq='" + qq + '\'' +
                ", weChat='" + weChat + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", limitNo=" + limitNo +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", extendField1='" + joinNo + '\'' +
                ", extendField2='" + viewNo + '\'' +
                ", extendField3='" + isOverTime + '\'' +
                ", extendField4='" + isOverNo + '\'' +
                ", extendField5='" + extendField5 + '\'' +
                ", extendField6='" + extendField6 + '\'' +
                ", extendField7='" + extendField7 + '\'' +
                '}';
    }
}

package com.ruider.model;

import java.util.Date;

public class MessageManage {

    private int id;

    private int categoryId;

    private int userId;

    private int needsId;

    private String content;

    private Date createTime;

    private int isApproved;    //是否被同意，同意为1，未同意为0

    private int userIsWatched;      //用户是否查看的标志，查看为1，未查看为0

    private int messageId;

    private String replyIds;

    private int masterId;

    private int masterIsWatched;

    private int isReply;

    private int approvedOrRefuseIsViewed;

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

    public int getNeedsId() {
        return needsId;
    }

    public void setNeedsId(int needsId) {
        this.needsId = needsId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(int isApproved) {
        this.isApproved = isApproved;
    }

    public int getUserIsWatched() {
        return userIsWatched;
    }

    public void setUserIsWatched(int userIsWatched) {
        this.userIsWatched = userIsWatched;
    }

    public int getMessageId() { return messageId; }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getReplyIds() {
        return replyIds;
    }

    public void setReplyIds(String replyIds) {
        this.replyIds = replyIds;
    }

    public int getMasterId() {
        return masterId;
    }

    public void setMasterId(int masterId) {
        this.masterId = masterId;
    }

    public int getMasterIsWatched() {
        return masterIsWatched;
    }

    public void setMasterIsWatched(int masterIsWatched) {
        this.masterIsWatched = masterIsWatched;
    }

    public int getIsReply() {
        return isReply;
    }

    public void setIsReply(int isReply) {
        this.isReply = isReply;
    }

    public int getApprovedOrRefuseIsViewed() {
        return approvedOrRefuseIsViewed;
    }

    public void setApprovedOrRefuseIsViewed(int approvedOrRefuseIsViewed) {
        this.approvedOrRefuseIsViewed = approvedOrRefuseIsViewed;
    }

    @Override
    public String toString() {
        return "MessageManage{" +
                "id=" + id +
                ", categoryId=" + categoryId +
                ", userId=" + userId +
                ", needsId=" + needsId +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", isApproved=" + isApproved +
                ", userIsWatched=" + userIsWatched +
                ", messageId=" + messageId +
                ", replyIds='" + replyIds + '\'' +
                ", masterId=" + masterId +
                ", masterIsWatched=" + masterIsWatched +
                ", isReply=" + isReply +
                ", approvedOrRefuseIsViewed=" + approvedOrRefuseIsViewed +
                '}';
    }
}

package com.ruider.service.impl;

import com.ruider.mapper.MessageManageMapper;
import com.ruider.mapper.NeedsManagementMapper;
import com.ruider.model.MessageManage;
import com.ruider.model.NeedsManagement;
import com.ruider.model.User;
import com.ruider.service.MessageManageService;
import com.ruider.service.NeedsManagementService;
import com.ruider.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class MessageManageServiceImpl implements MessageManageService {

    @Autowired
    private MessageManageMapper messageManageMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private NeedsManagementService needsManagementService;

    @Autowired
    private NeedsManagementMapper needsManagementMapper;



    @Override
    public int addEvaluate(HashMap<String,Object> paramMap) throws Exception{
        Date createTime = new Date();
        MessageManage messageManage = new MessageManage();
        messageManage.setCategoryId(Integer.valueOf(paramMap.get("categoryId").toString()));
        messageManage.setUserId(Integer.valueOf(paramMap.get("userId").toString()));
        messageManage.setContent(paramMap.get("content").toString());
        messageManage.setCreateTime(createTime);
        messageManage.setIsApproved(0);           //是否同意默认为0
        //评论
        if(paramMap.get("messageId") == null) {
            //获取主人id masterId
            int needsId = Integer.valueOf(paramMap.get("needsId").toString());
            messageManage.setNeedsId(needsId);
            NeedsManagement needsManagement = needsManagementService.getNeedsDetailsById(needsId);
            int masterId = needsManagement.getUserId();
            messageManage.setMasterId(masterId);
            messageManage.setUserIsWatched(1);        //用户显示已经查看
            messageManage.setMasterIsWatched(0);      //需求发布者是否查看，未看为0，查看为1
            messageManage.setMessageId(0);            //回复归属的评论或者申请的id，默认为0
            messageManage.setIsReply(1);              //设置是否为评论，评论为1，未评论为0
            return messageManageMapper.addEvaluate(messageManage);
        }
        //回复
        else {
            int messageId = Integer.valueOf(paramMap.get("messageId").toString());
            messageManage.setNeedsId(Integer.valueOf(paramMap.get("needsId").toString()));
            messageManage.setMessageId(messageId);
            messageManage.setUserId(Integer.valueOf(paramMap.get("masterId").toString()));
            messageManage.setMasterId(Integer.valueOf(paramMap.get("userId").toString()));
            messageManage.setUserIsWatched(1);
            messageManage.setMasterIsWatched(0);
            messageManageMapper.addEvaluate(messageManage);
            String replyIds = messageManageMapper.getMessageById(messageId).getReplyIds();
            messageManage.setIsReply(0);                //设置是否为评论，评论为1，未评论为0
            if(replyIds == null || "".equals(replyIds)){
                replyIds = String.valueOf(messageManage.getId());
            }else{
                replyIds = replyIds + "," + messageManage.getId();
            }
            return messageManageMapper.addReply(replyIds, messageId);
        }
    }

    @Override
    public int addReply(HashMap<String,Object> paramMap) throws Exception {
        int replyId = addEvaluate(paramMap);
        int messageId = Integer.valueOf(paramMap.get("messageId").toString());
        MessageManage messageManage = messageManageMapper.getMessageById(messageId);
        String replyIds = messageManage.getReplyIds();
        if(replyIds == null || "".equals(replyIds)){
            replyIds = String.valueOf(replyId);
        }else{
            replyIds += ","+replyId;
        }
        messageManageMapper.addReply(replyIds, messageId);
        return replyId;
    }

    @Override
    public List<HashMap<String, Object>> getMessageByNeedsIdAndCategoryId(@Param("needsId") int needsId, @Param("categoryId") int categoryId) throws Exception {
        List<HashMap<String,Object>> result = new ArrayList();
        List<MessageManage> list = messageManageMapper.getAndSetMessageByNeedIdAndCategoryId(needsId, categoryId,1);
        for (MessageManage messageManage : list) {
            HashMap<String,Object> map = new HashMap<>();
            map.put("id", messageManage.getId());
            map.put("categoryId", messageManage.getCategoryId());
            map.put("content", messageManage.getContent());
            map.put("createTime", messageManage.getCreateTime());
            map.put("isApproved", messageManage.getIsApproved());
            map.put("userIsWatched", messageManage.getUserIsWatched());
            map.put("masterIsWatched", messageManage.getMasterIsWatched());
            map.put("needsId", needsId);
            map.put("replyIds", messageManage.getReplyIds());
            map.put("userId", messageManage.getUserId());
            int masterId = messageManage.getMasterId();
            map.put("masterId", masterId);
            int userId = messageManage.getUserId();
            User user = userService.selectUserById(userId);
            map.put("userNickName", user.getNickName());
            User master = userService.selectUserById(masterId);
            map.put("masterNickName", master.getNickName());
            //对每一条评论获取所有回复
            String replyIds = messageManage.getReplyIds();
            List<HashMap<String, Object>> replyMessage = new ArrayList<>();
            if (!(replyIds == null || replyIds.equals(""))) {
                if (replyIds.contains(",")) {
                    String[] replyMessageIds = replyIds.split(",");
                    for (int i = 0; i<replyMessageIds.length; i++) {
                        HashMap<String,Object> replyMap = new HashMap<>();
                        MessageManage reply = messageManageMapper.getMessageById(Integer.valueOf(replyMessageIds[i]));
                        replyMap.put("id", reply.getId());
                        replyMap.put("categoryId", reply.getCategoryId());
                        replyMap.put("createTime", reply.getCreateTime());
                        replyMap.put("content", reply.getContent());
                        replyMap.put("userId", reply.getUserId());
                        replyMap.put("isApproved", reply.getIsApproved());
                        replyMap.put("userIsWatched", reply.getUserIsWatched());
                        replyMap.put("masterIsWatched", reply.getMasterIsWatched());
                        replyMap.put("needsId", needsId);
                        replyMap.put("replyIds", reply.getReplyIds());
                        replyMap.put("masterId", reply.getMasterId());
                        replyMap.put("isReply", reply.getIsReply());
                        User replyMaster = userService.selectUserById(reply.getMasterId());
                        replyMap.put("masterNickName", replyMaster.getNickName());
                        User replyUser = userService.selectUserById(reply.getUserId());
                        replyMap.put("userNickName", replyUser.getNickName());
                        replyMessage.add(replyMap);
                    }
                }
                else {
                    HashMap<String,Object> replyMap = new HashMap<>();
                    MessageManage reply = messageManageMapper.getMessageById(Integer.valueOf(replyIds));
                    replyMap.put("id", reply.getId());
                    replyMap.put("categoryId", reply.getCategoryId());
                    replyMap.put("createTime", reply.getCreateTime());
                    replyMap.put("content", reply.getContent());
                    replyMap.put("userId", reply.getUserId());
                    replyMap.put("isApproved", reply.getIsApproved());
                    replyMap.put("userIsWatched", reply.getUserIsWatched());
                    replyMap.put("masterIsWatched", reply.getMasterIsWatched());
                    replyMap.put("needsId", needsId);
                    replyMap.put("replyIds", reply.getReplyIds());
                    replyMap.put("masterId", reply.getMasterId());
                    replyMap.put("isReply", reply.getIsReply());
                    User replyMaster = userService.selectUserById(reply.getMasterId());
                    replyMap.put("masterNickName", replyMaster.getNickName());
                    User replyUser = userService.selectUserById(reply.getUserId());
                    replyMap.put("userNickName", replyUser.getNickName());
                    replyMessage.add(replyMap);
                }
            }
            map.put("replyMessage", replyMessage);
            result.add(map);
        }
        return result;
    }

    //获取评论，回复，申请，同意，拒绝，被评论，被申请，被回复，过期，人员上限所有消息
    @Override
    public List<HashMap<String, Object>> getAndSetMessageByUserIdAndCategoryId(@Param("userId") int masterId, @Param("categoryId") int categoryId, int isReply) throws Exception{
        List<HashMap<String,Object>> result = new ArrayList();
        /*List<NeedsManagement> needsManagements = needsManagementService.getNeedsByUserId(masterId);
        for (NeedsManagement needsManagement : needsManagements) {
            int needsId = needsManagement.getId();
            //根据needsId和isReply(评论为1，回复为0)获取评论
            List<MessageManage> list = messageManageMapper.getAndSetMessageByNeedIdAndCategoryId(needsId, categoryId,isReply);
            for (MessageManage messageManage : list) {
                HashMap<String,Object> map = new HashMap<>();
                List<MessageManage> replyMessages = new ArrayList<>();
                List<HashMap<String,Object>> replyMessagess = new ArrayList<>();
                map.put("messageContent", messageManage.getContent());
                int messageId = messageManage.getId();
                messageManageMapper.updateMasterIsWatchedById(messageId);
                replyMessages = messageManageMapper.getReplyByMessageId(messageId);
                for (MessageManage reply : replyMessages) {
                    HashMap<String,Object> replyDetails = new HashMap<>();
                    replyDetails.put("id", reply.getId());
                    replyDetails.put("categoryId", reply.getCategoryId());
                    replyDetails.put("createTime", reply.getCreateTime());
                    replyDetails.put("content", reply.getContent());
                    replyDetails.put("userId", reply.getUserId());
                    replyDetails.put("isApproved", reply.getIsApproved());
                    replyDetails.put("userIsWatched", reply.getUserIsWatched());
                    replyDetails.put("masterIsWatched", reply.getMasterIsWatched());
                    replyDetails.put("needsId", needsId);
                    replyDetails.put("replyIds", reply.getReplyIds());
                    replyDetails.put("masterId", reply.getMasterId());
                    replyDetails.put("isReply", reply.getIsReply());
                    User master = userService.selectUserById(reply.getMasterId());
                    replyDetails.put("masterNickName", master.getNickName());
                    User user = userService.selectUserById(reply.getUserId());
                    replyDetails.put("userNickName", user.getNickName());
                    messageManageMapper.updateMasterIsWatchedById(reply.getId());
                    replyMessagess.add(replyDetails);
                    messageManageMapper.updateMasterIsWatchedById(reply.getCategoryId());
                }
                map.put("id", messageId);
                map.put("categoryId", messageManage.getCategoryId());
                map.put("createTime", messageManage.getCreateTime());
                map.put("userId", messageManage.getUserId());
                map.put("isApproved", messageManage.getIsApproved());
                map.put("userIsWatched", messageManage.getUserIsWatched());
                map.put("masterIsWatched", messageManage.getMasterIsWatched());
                map.put("needsId", needsId);
                map.put("replyMessages", replyMessagess);
                map.put("replyIds", messageManage.getReplyIds());
                map.put("masterId", masterId);
                map.put("isReply", messageManage.getIsReply());
                User master = userService.selectUserById(masterId);
                map.put("masterNickName", master.getNickName());
                User user = userService.selectUserById(messageManage.getUserId());
                map.put("userNickName", user.getNickName());
                map.put("title", needsManagement.getTitle());
                map.put("needsContent",needsManagement.getContent());
                map.put("startTime", needsManagement.getStartTime());
                map.put("deadline", needsManagement.getDeadline());
                map.put("limitNo", needsManagement.getLimitNo());
                map.put("joinNo", needsManagement.getJoinNo());
                result.add(map);
            }
        }*/

        //申请
        if(categoryId == 1) {
            List<MessageManage> messageManages = messageManageMapper.getMessageByMasterIdAndCategoryId(masterId,categoryId, isReply);
            for (MessageManage messageManage : messageManages) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("messageContent", messageManage.getContent());
                messageManageMapper.updateMasterIsWatchedById(messageManage.getId());
                map.put("id", messageManage.getId());
                map.put("categoryId", messageManage.getCategoryId());
                map.put("createTime", messageManage.getCreateTime());
                map.put("userId", messageManage.getUserId());
                map.put("isApproved", messageManage.getIsApproved());
                map.put("userIsWatched", messageManage.getUserIsWatched());
                map.put("masterIsWatched", messageManage.getMasterIsWatched());
                int needsId = messageManage.getNeedsId();
                map.put("needsId", needsId);
                map.put("masterId", masterId);
                map.put("isReply", messageManage.getIsReply());
                User master = userService.selectUserById(masterId);
                map.put("masterNickName", master.getNickName());
                User user = userService.selectUserById(messageManage.getUserId());
                map.put("userNickName", user.getNickName());
                NeedsManagement needsManagement = needsManagementMapper.getNeedsDetailsById(needsId);
                map.put("title", needsManagement.getTitle());
                map.put("needsContent", needsManagement.getContent());
                map.put("startTime", needsManagement.getStartTime());
                map.put("deadline", needsManagement.getDeadline());
                map.put("limitNo", needsManagement.getLimitNo());
                map.put("joinNo", needsManagement.getJoinNo());
                result.add(map);
            }
            //申请被同意或者拒绝
            messageManages = messageManageMapper.getMessageByUserIdAndCategoryIdAndIsApprovedAndapprovedOrRefuseIsViewed(masterId, categoryId);
            for (MessageManage messageManage : messageManages) {
                HashMap<String,Object> map = new HashMap<>();
                //申请消息被同意或者拒绝，申请者第一次得到同意或者拒绝消息的通知，以approvedOrRefuseIsViewed为标志，以approvedOrRefuseIsViewed查看为1，未查看为0
                if (messageManage.getIsApproved() != 0) {
                    map.put("messageContent", messageManage.getContent());
                    messageManageMapper.updateApprovedOrRefuseIsViewed(messageManage.getId(), 1);
                    map.put("id", messageManage.getId());
                    map.put("categoryId", messageManage.getCategoryId());
                    map.put("createTime", messageManage.getCreateTime());
                    map.put("userId", messageManage.getUserId());
                    map.put("isApproved", messageManage.getIsApproved());
                    map.put("userIsWatched", messageManage.getUserIsWatched());
                    map.put("masterIsWatched", messageManage.getMasterIsWatched());
                    int needsId = messageManage.getNeedsId();
                    map.put("needsId", needsId);
                    map.put("masterId", masterId);
                    map.put("isReply", messageManage.getIsReply());
                    User master = userService.selectUserById(masterId);
                    map.put("masterNickName", master.getNickName());
                    User user = userService.selectUserById(messageManage.getUserId());
                    map.put("userNickName", user.getNickName());
                    NeedsManagement needsManagement = needsManagementMapper.getNeedsDetailsById(needsId);
                    map.put("title", needsManagement.getTitle());
                    map.put("needsContent", needsManagement.getContent());
                    map.put("startTime", needsManagement.getStartTime());
                    map.put("deadline", needsManagement.getDeadline());
                    map.put("limitNo", needsManagement.getLimitNo());
                    map.put("joinNo", needsManagement.getJoinNo());
                    result.add(map);
                }
            }
        }
        //评论与被评论
        else if(categoryId == 2) {
            //被评论
            List<MessageManage> messageManages1 = messageManageMapper.getMessageByMasterIdAndCategoryId(masterId, categoryId, isReply);
            for (MessageManage messageManage : messageManages1) {
                HashMap<String, Object> map = new HashMap<>();
                List<MessageManage> replyMessages = new ArrayList<>();
                List<HashMap<String, Object>> replyMessagess = new ArrayList<>();
                map.put("messageContent", messageManage.getContent());
                int messageId = messageManage.getId();
                messageManageMapper.updateMasterIsWatchedById(messageId);
                replyMessages = messageManageMapper.getReplyByMessageId(messageId);
                for (MessageManage reply : replyMessages) {
                    HashMap<String, Object> replyDetails = new HashMap<>();
                    replyDetails.put("id", reply.getId());
                    replyDetails.put("categoryId", reply.getCategoryId());
                    replyDetails.put("createTime", reply.getCreateTime());
                    replyDetails.put("content", reply.getContent());
                    replyDetails.put("userId", reply.getUserId());
                    replyDetails.put("isApproved", reply.getIsApproved());
                    replyDetails.put("userIsWatched", reply.getUserIsWatched());
                    replyDetails.put("masterIsWatched", reply.getMasterIsWatched());
                    replyDetails.put("needsId", reply.getNeedsId());
                    replyDetails.put("replyIds", reply.getReplyIds());
                    replyDetails.put("masterId", reply.getMasterId());
                    replyDetails.put("isReply", reply.getIsReply());
                    User master = userService.selectUserById(reply.getMasterId());
                    replyDetails.put("masterNickName", master.getNickName());
                    User user = userService.selectUserById(reply.getUserId());
                    replyDetails.put("userNickName", user.getNickName());
                    messageManageMapper.updateMasterIsWatchedById(reply.getId());
                    replyMessagess.add(replyDetails);
                    messageManageMapper.updateMasterIsWatchedById(reply.getCategoryId());
                }
                map.put("id", messageId);
                map.put("categoryId", messageManage.getCategoryId());
                map.put("createTime", messageManage.getCreateTime());
                map.put("userId", messageManage.getUserId());
                map.put("isApproved", messageManage.getIsApproved());
                map.put("userIsWatched", messageManage.getUserIsWatched());
                map.put("masterIsWatched", messageManage.getMasterIsWatched());
                int needsId = messageManage.getNeedsId();
                NeedsManagement needsManagement = needsManagementMapper.getNeedsDetailsById(needsId);
                map.put("needsId", needsId);
                map.put("replyMessages", replyMessagess);
                map.put("replyIds", messageManage.getReplyIds());
                map.put("masterId", masterId);
                map.put("isReply", messageManage.getIsReply());
                User master = userService.selectUserById(masterId);
                map.put("masterNickName", master.getNickName());
                User user = userService.selectUserById(messageManage.getUserId());
                map.put("userNickName", user.getNickName());
                map.put("title", needsManagement.getTitle());
                map.put("needsContent", needsManagement.getContent());
                map.put("startTime", needsManagement.getStartTime());
                map.put("deadline", needsManagement.getDeadline());
                map.put("limitNo", needsManagement.getLimitNo());
                map.put("joinNo", needsManagement.getJoinNo());
                result.add(map);
            }
            //评论
            List<MessageManage> messageManages2 = messageManageMapper.getMessageByUserIdAndCategoryId(masterId, categoryId, isReply);
            for (MessageManage messageManage : messageManages2) {
                HashMap<String, Object> map = new HashMap<>();
                List<MessageManage> replyMessages = new ArrayList<>();
                List<HashMap<String, Object>> replyMessagess = new ArrayList<>();
                map.put("messageContent", messageManage.getContent());
                int messageId = messageManage.getId();
                messageManageMapper.updateMasterIsWatchedById(messageId);
                replyMessages = messageManageMapper.getReplyByMessageId(messageId);
                for (MessageManage reply : replyMessages) {
                    HashMap<String, Object> replyDetails = new HashMap<>();
                    replyDetails.put("id", reply.getId());
                    replyDetails.put("categoryId", reply.getCategoryId());
                    replyDetails.put("createTime", reply.getCreateTime());
                    replyDetails.put("content", reply.getContent());
                    replyDetails.put("userId", reply.getUserId());
                    replyDetails.put("isApproved", reply.getIsApproved());
                    replyDetails.put("userIsWatched", reply.getUserIsWatched());
                    replyDetails.put("masterIsWatched", reply.getMasterIsWatched());
                    replyDetails.put("needsId", reply.getNeedsId());
                    replyDetails.put("replyIds", reply.getReplyIds());
                    replyDetails.put("masterId", reply.getMasterId());
                    replyDetails.put("isReply", reply.getIsReply());
                    User master = userService.selectUserById(reply.getMasterId());
                    replyDetails.put("masterNickName", master.getNickName());
                    User user = userService.selectUserById(reply.getUserId());
                    replyDetails.put("userNickName", user.getNickName());
                    messageManageMapper.updateMasterIsWatchedById(reply.getId());
                    replyMessagess.add(replyDetails);
                    messageManageMapper.updateMasterIsWatchedById(reply.getCategoryId());
                }
                map.put("id", messageId);
                map.put("categoryId", messageManage.getCategoryId());
                map.put("createTime", messageManage.getCreateTime());
                map.put("userId", messageManage.getUserId());
                map.put("isApproved", messageManage.getIsApproved());
                map.put("userIsWatched", messageManage.getUserIsWatched());
                map.put("masterIsWatched", messageManage.getMasterIsWatched());
                int needsId = messageManage.getNeedsId();
                NeedsManagement needsManagement = needsManagementMapper.getNeedsDetailsById(needsId);
                map.put("needsId", needsId);
                map.put("replyMessages", replyMessagess);
                map.put("replyIds", messageManage.getReplyIds());
                map.put("masterId", masterId);
                map.put("isReply", messageManage.getIsReply());
                User master = userService.selectUserById(masterId);
                map.put("masterNickName", master.getNickName());
                User user = userService.selectUserById(messageManage.getUserId());
                map.put("userNickName", user.getNickName());
                map.put("title", needsManagement.getTitle());
                map.put("needsContent", needsManagement.getContent());
                map.put("startTime", needsManagement.getStartTime());
                map.put("deadline", needsManagement.getDeadline());
                map.put("limitNo", needsManagement.getLimitNo());
                map.put("joinNo", needsManagement.getJoinNo());
                result.add(map);
            }
        }

        return result;
    }

    @Override
    public List<HashMap<String,Object>> checkOverTimeNeeds(@Param("userId") int userId) throws Exception {
        List<HashMap<String,Object>> result = new ArrayList();
        Date checkTime = new Date();
        List<NeedsManagement> needsManagements = needsManagementMapper.checkOverTimeNeeds(userId, checkTime);
        for (NeedsManagement needsManagement : needsManagements) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", needsManagement.getId());
            map.put("categoryId", 3);
            map.put("createTime", needsManagement.getCreateTime());
            map.put("userId", needsManagement.getUserId());
            map.put("title", needsManagement.getTitle());
            map.put("needsContent",needsManagement.getContent());
            map.put("startTime", needsManagement.getStartTime());
            map.put("deadline", needsManagement.getDeadline());
            map.put("limitNo", needsManagement.getLimitNo());
            map.put("joinNo", needsManagement.getJoinNo());
            needsManagementMapper.updateIsOverTime(needsManagement.getId());
            result.add(map);
        }
        return result;
    }

    @Override
    public List<HashMap<String,Object>> checkOverNumber(@Param("userId") int userId) throws Exception {
        List<HashMap<String,Object>> result = new ArrayList();
        Date checkTime = new Date();
        List<NeedsManagement> needsManagements = needsManagementMapper.checkOverNumber(userId);
        for (NeedsManagement needsManagement : needsManagements) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", needsManagement.getId());
            map.put("categoryId", 4);
            map.put("createTime", needsManagement.getCreateTime());
            map.put("userId", needsManagement.getUserId());
            map.put("title", needsManagement.getTitle());
            map.put("needsContent",needsManagement.getContent());
            map.put("startTime", needsManagement.getStartTime());
            map.put("deadline", needsManagement.getDeadline());
            map.put("limitNo", needsManagement.getLimitNo());
            map.put("joinNo", needsManagement.getJoinNo());
            needsManagementMapper.riseJoinNO(needsManagement.getId());
            result.add(map);
        }
        return result;
    }


    @Override
    public HashMap<String,Integer> getNoWatchedMessageNo(int masterId) throws Exception{
        int applyNo = 0, evaluateNo = 0, overTimeNo = 0, overNumberNo = 0, allNoWatchedNo = 0;
        //获取评论，回复，申请加入的未读消息数
        List<MessageManage> messageManages = messageManageMapper.getMessageByMasterIdAndMasterNoWatched(masterId);
        HashMap<String,Integer> map = new HashMap<>();
        for(MessageManage messageManage : messageManages) {
            int categoryId = messageManage.getCategoryId();
            if(categoryId == 1) { applyNo++; allNoWatchedNo++;}
            else if(categoryId == 2) {evaluateNo++; allNoWatchedNo++;}
            else if(categoryId == 3) {overTimeNo++; allNoWatchedNo++;}
            else if(categoryId == 4) {overNumberNo++; allNoWatchedNo++;}
        }
        //获取申请者申请加入被同意或者被拒绝的未读消息数
        messageManages = messageManageMapper.getMessageByUserIdAndCategoryIdAndIsApprovedAndapprovedOrRefuseIsViewed(masterId, 1);
        for (MessageManage messageManage : messageManages) {
            if (messageManage.getIsApproved() != 0 && messageManage.getApprovedOrRefuseIsViewed() == 0) {
                applyNo++; allNoWatchedNo++;
            }
        }
        //过期数量
        List<NeedsManagement> needsManagements = needsManagementMapper.checkOverTimeNeeds(masterId, new Date());
        for (NeedsManagement needsManagement : needsManagements) {
            if (needsManagement.getIsOverTime() == 0) {
                overTimeNo++; allNoWatchedNo++;
            }
        }
        //人员已满
        needsManagements = needsManagementMapper.checkOverNumber(masterId);
        for (NeedsManagement needsManagement : needsManagements) {
            if (needsManagement.getIsOverNo() == 0) {
                overNumberNo++; allNoWatchedNo++;
            }
        }
        map.put("applyNo", applyNo);
        map.put("evaluateNo", evaluateNo);
        map.put("overTimeNo", overTimeNo);
        map.put("overNumberNo", overNumberNo);
        map.put("allNoWatchedNo", allNoWatchedNo++);
        return map;
    }
    @Override
    public int updateIsApproved(@Param("isApproved") int isApproved, @Param("id") int id) throws Exception{
        MessageManage messageManage = messageManageMapper.getMessageById(id);
        NeedsManagement needsManagement = needsManagementMapper.getNeedsDetailsById(messageManage.getNeedsId());
        if (needsManagement.getLimitNo() < needsManagement.getJoinNo()) {
            needsManagementMapper.riseJoinNO(needsManagement.getId());
            return messageManageMapper.updateIsApproved(isApproved, id);
        }
        else {
            return 0;
        }
    }

}

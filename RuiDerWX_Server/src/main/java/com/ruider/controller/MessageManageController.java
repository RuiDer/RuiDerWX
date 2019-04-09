package com.ruider.controller;

import com.ruider.common.Result;
import com.ruider.service.MessageManageService;
import com.ruider.service.NeedsManagementService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("MessageManage")
public class MessageManageController {

    private final Logger logger = LoggerFactory.getLogger(MessageManageController.class);

    @Autowired
    private MessageManageService messageManageService;

    @Autowired
    private NeedsManagementService needsManagementService;

    /*
     * 添加评论
     */
    @PostMapping("addEvaluate")
    public Result addEvaluate(@RequestBody HashMap<String,Object> paramMap) {
        Result result = new Result();
        try{
            logger.info("【新增评论开始】 addEvaluate start");
            int count = messageManageService.addEvaluate(paramMap);
            if(count < 0) {
                logger.info("【新增评论失败】 addEvaluate fail");
                result.setMessage("添加评论失败");
                result.setCode(Result.FAIL_CODE);
                result.setIsSuccess(false);
            }else {
                logger.info("【新增评论成功】 addEvaluate success");
                result.setMessage("添加评论成功");
                result.setIsSuccess(true);
            }
            return result;
        }
        catch (Exception e) {
            logger.error("【新增评论失败】 addEvaluate fail", e);
            result.setMessage("添加评论失败");
            result.setCode(Result.EXCEPTION_CODE);
            result.setIsSuccess(false);
            return result;
        }
    }

    /*
     * 添加回复
     */
    @PostMapping("addReply")
    public Result addReply(@RequestBody HashMap<String,Object> paramMap) {
        Result result = new Result();
        try{
            logger.info("【添加回复开始】 addApply start");
            int count = messageManageService.addEvaluate    (paramMap);
            if(count < 0) {
                logger.info("【添加回复失败】 addApply fail");
                result.setMessage("添加回复失败");
                result.setCode(Result.FAIL_CODE);
                result.setIsSuccess(false);
            }else {
                logger.info("【添加回复成功】 addApply success");
                result.setMessage("添加回复成功");
                result.setIsSuccess(true);
            }
            return result;
        }
        catch (Exception e) {
            logger.error("【添加回复失败】 addApply fail", e);
            result.setMessage("添加回复失败");
            result.setCode(Result.EXCEPTION_CODE);
            result.setIsSuccess(false);
            return result;
        }
    }

    /*
     * function:根据需求id获取评论回复列表
     */
    @GetMapping("getMessageByNeedsIdAndCategoryId")
    public Result getMessageByNeedsIdAndCategoryId(@Param("needsId") int needsId, @Param("categoryId") int categoryId ) {
        Result result = new Result();
        try{
            logger.info("【根据需求id获取评论回复列表】 getMessageByNeedsIdAndCategoryId start");
            List list = messageManageService.getMessageByNeedsIdAndCategoryId(needsId, categoryId);
            result.setIsSuccess(true);
            result.setMessage("根据需求id获取评论回复列表成功");
            result.setData(list);
            logger.info("【根据需求id获取评论回复列表成功】 getMessageByNeedsIdAndCategoryId success");
            return result;
        }
        catch(Exception e) {
            result.setIsSuccess(false);
            result.setMessage("根据需求id获取评论回复列表失败");
            result.setCode(Result.EXCEPTION_CODE);
            logger.error("【根据需求id获取评论回复列表失败】 getMessageByNeedsIdAndCategoryId fail",e);
            return result;
        }
    }

    /*
     * 根据需求id和消息类型id获取未查看的信息，并且设置查看状态为查看状态
     */
    @GetMapping("getMessageList")
    public Result getMessageList(@Param("userId") String userId, @Param("categoryId") String categoryId, @Param("isReply") String isReply) {
        Result result = new Result();
        try{
            logger.info("【根据需求id和消息类型id获取未查看的信息，并且设置查看状态为查看状态】 getMessageList start");
            int categoryIds = Integer.valueOf(categoryId);
            List list = new ArrayList();
            //根据categoryId获取列表，categoryId 1，2为获取申请，评论列表；3，4获取过期，人员上限列表
            if (categoryIds == 1 || categoryIds == 2) {
                list = messageManageService.getAndSetMessageByUserIdAndCategoryId(Integer.valueOf(userId), categoryIds, Integer.valueOf(isReply));
            }
            else if(categoryIds == 3) {
                list = messageManageService.checkOverTimeNeeds(Integer.valueOf(userId));
            }
            else {
                list = messageManageService.checkOverNumber(Integer.valueOf(userId));
            }
            result.setIsSuccess(true);
            result.setMessage("根根据需求id和消息类型id获取未查看的信息，并且设置查看状态为查看状态成功");
            result.setData(list);
            logger.info("【根据需求id和消息类型id获取未查看的信息，并且设置查看状态为查看状态成功】 getMessageList success");
            return result;
        }
        catch(Exception e) {
            result.setIsSuccess(false);
            result.setMessage("根据需求id和消息类型id获取未查看的信息，并且设置查看状态为查看状态失败");
            result.setCode(Result.EXCEPTION_CODE);
            logger.error("【根据需求id和消息类型id获取未查看的信息，并且设置查看状态为查看状态失败】 getMessageList fail",e);
            return result;
        }
    }

    /**
     * 获取未被查看的消息数量
     */
    @GetMapping("getNoWatchedMessageNo")
    public Result getNoWatchedMessageNo(@Param("userId") int userId) {
        Result result = new Result();
        try{
            logger.info("【获取未被查看的消息数量】 getNoWatchedMessageNo start");
            HashMap map = messageManageService.getNoWatchedMessageNo(userId);
            result.setIsSuccess(true);
            result.setMessage("获取未被查看的消息数量成功");
            result.setData(map);
            logger.info("【获取未被查看的消息数量成功】 getNoWatchedMessageNo success");
            return result;
        }
        catch(Exception e) {
            result.setIsSuccess(false);
            result.setMessage("获取未被查看的消息数量失败");
            result.setCode(Result.EXCEPTION_CODE);
            logger.error("【获取未被查看的消息数量失败】 getNoWatchedMessageNo fail",e);
            return result;
        }
    }

    /**
     * 申请消息是否被同意或者拒绝
     * @param isApproved
     * @param id
     * @return
     */
    @GetMapping("setIsApproved")
    public Result setIsApproved(@Param("isApproved") int isApproved, @Param("id") int id) {
        Result result = new Result();
        try {
            logger.info("【申请消息是否被同意或者拒绝开始】 setIsApproved start");
            int count = messageManageService.updateIsApproved(isApproved, id);
            if(count < 0) {
                logger.info("【申请消息是否被同意或者拒绝失败】 setIsApproved fail");
                result.setCode(Result.FAIL_CODE);
                result.setMessage("申请消息是否被同意或者拒绝失败");
                result.setIsSuccess(false);
            }
            else if (count == 0){
                logger.info("【申请消息是否被同意或者拒绝失败】 setIsApproved fail");
                result.setMessage("申请消息是否被同意或者拒绝失败");
                result.setCode(Result.OVER_NUMBER);
                result.setIsSuccess(true);
            }
            else {
                logger.info("【申请消息是否被同意或者拒绝成功】 setIsApproved success");
                result.setMessage("申请消息是否被同意或者拒绝成功");
                result.setIsSuccess(true);
            }
        }
        catch (Exception e) {
            logger.info("【申请消息是否被同意或者拒绝失败】 setIsApproved fail");
            result.setCode(Result.EXCEPTION_CODE);
            result.setMessage("申请消息是否被同意或者拒绝失败");
            result.setIsSuccess(false);
        }
        return result;
    }
}

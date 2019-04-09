package com.ruider.controller;

import com.ruider.common.Result;
import com.ruider.model.NeedsManagement;
import com.ruider.service.NeedsManagementService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import java.util.HashMap;

/**
 * Created by mahede on 2018/12/2.
 */
@RestController
@RequestMapping("needsManagement")
public class NeedsManagementController {

    private final Logger logger = LoggerFactory.getLogger(NeedsManagementController.class);

    @Autowired
    private NeedsManagementService  needsManagementService;

    @RequestMapping("test")
    public String test() {
        return "test";
    }

    @GetMapping("getNeedsByUserId")
    public Result getNeedsByUserId(@RequestParam("userId") int userId) {
        Result result = new Result();
        try{
            logger.info("【获取用户所有列表开始】 getNeedsByUserId start");
            result.setData(needsManagementService.getNeedsByUserId(userId));
            result.setMessage("获取用户所有列表成功");
            result.setIsSuccess(true);
            logger.info("【获取用户所有列表成功】 getNeedsByUserId success");
            return result;
        }
        catch (Exception e) {
            result.setMessage("获取用户所有列表失败");
            result.setIsSuccess(false);
            logger.error("【获取用户所有列表失败】 getNeedsByUserId fail", e);
            return result;
        }
    }

    @GetMapping("getNeedsByUserIdAndCategotyId")
    public Result getNeedsByUserIdAndCategotyId(@RequestParam("userId") int userId, @RequestParam("categoryId") int categoryId) {
        Result result = new Result();
        try{
            logger.info("【获取类型列表开始】 getNeedsByUserIdAndCategotyId start");
            result.setData(needsManagementService.getNeedsByUserIdAndCategoryId(userId, categoryId));
            result.setMessage("获取类型列表成功");
            result.setIsSuccess(true);
            logger.info("【获取类型列表成功】 getNeedsByUserIdAndCategotyId success");
            return result;
        }
        catch (Exception e) {
            result.setMessage("获取类型列表失败");
            result.setIsSuccess(false);
            logger.error("【获取类型列表失败】 getNeedsByUserIdAndCategotyId fail", e);
            return result;
        }
    }

    @PostMapping("addNeeds")
    public Result addNeeds(@RequestBody HashMap<String,Object> paramMap) {
        Result result = new Result();
        try{
            logger.info("【新增需求开始】 addNeeds start");
            needsManagementService.addNeeds(paramMap);
            result.setMessage("新增需求开始成功");
            result.setIsSuccess(true);
            logger.info("【新增需求成功】 addNeeds success");
            return result;
        }
        catch (Exception e) {
            result.setMessage("新增需求失败");
            result.setIsSuccess(false);
            logger.error("【新增需求失败】 addNeeds fail", e);
            return result;
        }
    }

    @GetMapping("deleteNeeds/{id}")
    public Result deleteNeeds(@PathVariable("id") String id) {
        Result result = new Result();
        try{
            logger.info("【删除需求开始】 deleteNeeds start");
            needsManagementService.deleteNeeds(Integer.valueOf(id));
            result.setMessage("新增需求开始成功");
            result.setIsSuccess(true);
            logger.info("【删除需求成功】 deleteNeeds success");
            return result;
        }
        catch (Exception e) {
            result.setMessage("删除需求失败");
            result.setIsSuccess(false);
            logger.error("【删除需求失败】 deleteNeeds fail", e);
            return result;
        }
    }

    @GetMapping("getNeedsDetailsById")
    public Result getNeedsDetailsById(@RequestParam String id) {
        Result result = new Result();
        try{
            logger.info("【获取需求信息开始】 getNeedsDetailsById start");
            NeedsManagement needsManagement = needsManagementService.getNeedsDetailsById(Integer.valueOf(id));
            result.setMessage("获取需求信息开始成功");
            result.setData(needsManagement);
            result.setIsSuccess(true);
            logger.info("【获取需求信息成功】 getNeedsDetailsById success");
            return result;
        }
        catch (Exception e) {
            result.setMessage("获取需求信息失败");
            result.setIsSuccess(false);
            logger.error("【获取需求信息失败】 getNeedsDetailsById fail", e);
            return result;
        }
    }

    @PostMapping("editNeeds")
    public Result editNeeds(@RequestBody HashMap<String,Object> paramMap) {
        Result result = new Result();
        try{
            logger.info("【修改需求开始】 editNeeds start");
            needsManagementService.editNeeds(paramMap);
            result.setMessage("修改需求开始成功");
            result.setIsSuccess(true);
            logger.info("【修改需求成功】 editNeeds success");
            return result;
        }
        catch (Exception e) {
            result.setMessage("修改需求失败");
            result.setIsSuccess(false);
            logger.error("【修改需求失败】 editNeeds fail", e);
            return result;
        }
    }

    @GetMapping("getNeedsByCategoryId")
    public Result getNeedsByCategoryId(@RequestParam("categoryId") int categoryId) {
        Result result = new Result();
        try{
            logger.info("【获取类型列表开始】 getNeedsByCategoryId start");
            result.setData(needsManagementService.getNeedsByCategoryId(categoryId));
            result.setMessage("获取类型列表成功");
            result.setIsSuccess(true);
            logger.info("【获取类型列表成功】 getNeedsByCategoryId success");
            return result;
        }
        catch (Exception e) {
            result.setMessage("获取类型列表失败");
            result.setIsSuccess(false);
            logger.error("【获取类型列表失败】 getNeedsByCategoryId fail", e);
            return result;
        }
    }

}

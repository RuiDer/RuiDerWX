package com.ruider.controller;

/**
 * function 管理用户数据
 * Created by mahede on 2018/11/27.
 */
import com.ruider.common.Result;
import com.ruider.model.User;
import com.ruider.utils.CommonUtils;
import com.ruider.utils.HttpRequest;
import net.sf.json.JSONObject;
import com.ruider.service.UserService;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/userInfo")
public class UserController {

    @Autowired
    private UserService userService;

    @Value("${APPID}")
    private String APPID;

    @Value("${APPSECRECT}")
    private String APPSECRECT;

    @Value("${GRANTTYPE}")
    private String GRANTTYPE;

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    /*
     * function: 用户访问入口，使用code换取openId
     * @Param code
     * @return openid
     */
    @GetMapping("IfAuthorizationEd")
    public Result IfAuthorizationEd(String encryptedData, String iv, String code) {
        Result result = new Result();

        if (code == null || code.length() == 0) {
            result.setIsSuccess(false);
            result.setMessage("code不能为空");
            return result;
        }

        /*try{
            logger.info("monitor if user grant authorization start");

            String params = "appid=" + APPID + "&secret=" + APPSECRECT + "&js_code=" + code + "&grant_type=" + GRANTTYPE;
            //发送请求dc
            String sr = HttpRequest.sendGet("https://api.weixin.qq.com/sns/jscode2session", params);
            //解析相应内容（转换成json对象）
            JSONObject json = JSONObject.fromObject(sr);
            //获取会话密钥（session_key）
            String session_key = json.get("session_key").toString();
            //用户的唯一标识（openid）
            String openid = (String) json.get("openid");
            Map<String,String> userInfo = new HashMap<>();
            userInfo.put("session_key", session_key);
            userInfo.put("openid", openid);
            result.setIsSuccess(true);
            result.setData(userInfo);
            result.setMessage("获取微信用户信息成功");
            logger.info("【获取微信用户信息成功】test success");
            return result;
        }catch (Exception e) {
            logger.error("【获取微信用户信息成功】test fail", e);
            result.setCode(Result.FAIL_CODE);
            result.setIsSuccess(false);
            result.setMessage("获取微信用户信息成功");
            return result;
        }*/
        String session_key = "RMB5IApZWVhJypWD26cEtQ==";
        //用户的唯一标识（openid）
        String openid = "ovYLr4vw4CTUKIG_eraz8PMr_oc4";
        Map<String,String> userInfo = new HashMap<>();
        userInfo.put("session_key", session_key);
        userInfo.put("openid", openid);
        result.setIsSuccess(true);
        result.setData(userInfo);
        result.setMessage("获取微信用户信息成功");
        logger.info("【获取微信用户信息成功】IfAuthorizationEd success");
        return result;
    }



    /*
     * function: 用户访问权限允许，新用户添加到用户表
     * @Param paramMap
     * @return userId,openId
     */
    /*
    @RequestMapping("/AuthorizationEd")
    public  Result AuthorizationEd (@RequestBody HashMap<String,Object> paramMap){
        Result result = new Result();
        try{
            logger.info("AuthorizationEd and add userInfo start");
            int ret = userService.addUser(paramMap);
            if(ret == -1) {
                result.setCode(Result.ALREADY_SAVED);
                result.setIsSuccess(false);
                result.setMessage("该用户已经被关注");
                logger.info("【用户信息已存在】addUserInfoIfNoSaved fail");
            }else{
                result.setIsSuccess(true);
                result.setMessage("用户信息保存成功");
                logger.info("【用户信息保存成功】addUserInfoIfNoSaved success");
            }

            return result;
        }catch (Exception e) {
            logger.error("【用户信息保存失败】addUserInfoIfNoSaved fail", e);
            result.setCode(Result.FAIL_CODE);
            result.setIsSuccess(false);
            result.setMessage("用户信息保存失败");
            return result;
        }
    }*/


    /*
     * function: 用户访问权限允许，新用户添加到用户表
     * @Param paramMap
     * @return userId,openId
     */
    @PostMapping(value = "addUserInfoIfNoSaved")
    public Result addUserInfoIfNoSaved (@RequestBody HashMap<String,Object> paramMap) {
        Result result = new Result();
        try{
            logger.info("addUserInfoIfNoSaved start");
            int ret = userService.addUser(paramMap);
            result.setIsSuccess(true);
            result.setData(ret);
            result.setMessage("用户信息保存或者查询成功");
            logger.info("【用户信息保存或者查询成功】addUserInfoIfNoSaved success");
            return result;
        }catch (Exception e) {
            logger.error("【用户信息保存或者查询失败】addUserInfoIfNoSaved fail", e);
            result.setCode(Result.FAIL_CODE);
            result.setIsSuccess(false);
            result.setMessage("用户信息保存或者查询失败");
            return result;
        }
    }

    /*
     * function: 获取用户数据
     * @Param paramMap
     * @return Result
     */
    @GetMapping(value = "getUserDetails")
    public Result getUserDetails(@RequestParam("userId") String userId) {
        Result result = new Result();
        try {
            logger.info("getUserDetails start ");
            User user = userService.getUserDetails(Integer.valueOf(userId));
            result.setIsSuccess(true);
            result.setData(user);
            result.setMessage("获取用户信息成功");
            logger.info("【获取用户信息成功】getUserDetails success");
            return result;
        }catch (Exception e){
            logger.error("【获取用户信息成功】getUserDetails fail", e);
            result.setCode(Result.FAIL_CODE);
            result.setIsSuccess(false);
            result.setMessage("获取用户信息失败");
            return result;
        }
    }

    /*
     * function: 编辑用户数据
     * @Param paramMap
     * @return Result
     */
    @PostMapping(value = "editUserDetails")
    public Result editUserDetails (@RequestBody HashMap<String,Object> paramMap) {
        Result result = new Result();
        try{
            logger.info("editUserDetails start");
            userService.updateUser(paramMap);
            result.setIsSuccess(true);
            result.setMessage("用户信息更新成功");
            logger.info("【用户信息更新成功】editUserDetails success");
            return result;
        }catch (Exception e) {
            logger.error("【用户信息更新失败】editUserDetails fail", e);
            result.setCode(Result.FAIL_CODE);
            result.setIsSuccess(false);
            result.setMessage("用户信息更新失败");
            return result;
        }
    }



}

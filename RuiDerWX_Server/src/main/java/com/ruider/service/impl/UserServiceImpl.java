package com.ruider.service.impl;

/**
 * Created by mahede on 2018/11/27.
 */
import com.ruider.mapper.UserMapper;
import com.ruider.model.User;
import com.ruider.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean login(String username,String password){
        User userEntity = new User ();
        userEntity.setNickName( username );
        userEntity.setPassword ( password );

        User user = userMapper.selectUser ( userEntity );
        if (user != null){
            return true;
        }
        return false;
    }

    @Override
    public int addUser(HashMap<String, Object> paramMap) throws Exception {
        User user;
        String openId = paramMap.get("openId").toString();
        user = userMapper.selectUserByOpenId(openId);
        if(user == null) {
            user = new User();
            user.setOpenid(openId);
            user.setHeadurl(paramMap.get("headUrl").toString());
            user.setImage(paramMap.get("image").toString());
            user.setSex(paramMap.get("sex").toString());
            user.setNickName(paramMap.get("nickName").toString());
            Date createTime = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            user.setCreateTime(simpleDateFormat.format(createTime));
            userMapper.addUser(user);
            return user.getId();
        }
        else {
            return user.getId();
        }

    }

    @Override
    public User getUserDetails (int userId) throws Exception {
        User user;
        user = userMapper.selectUserById(userId);
        if(user.getSex().equals("1")) user.setSex("男");
        else user.setSex("女");
        return user;

    }

    @Override
    public int updateUser(HashMap<String,Object> paramMap) throws Exception{
        User user = new User();
        user.setAge(Integer.valueOf(paramMap.get("age").toString()));
        user.setSex(paramMap.get("sex").toString());
        user.setMobilePhone(paramMap.get("phone").toString());
        user.setAutograph(paramMap.get("autograph").toString());
        user.setHeadurl(paramMap.get("headerUrl").toString());
        user.setRealName(paramMap.get("realName").toString());
        user.setId(Integer.valueOf(paramMap.get("id").toString()));
        return userMapper.updateUser(user);
    }

    @Override
    public User selectUserById(int userId) {
        return userMapper.selectUserById(userId);
    }

}
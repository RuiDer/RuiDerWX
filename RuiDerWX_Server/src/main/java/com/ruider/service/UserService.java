package com.ruider.service;

import com.ruider.model.User;

import java.util.HashMap;

/**
 * Created by mahede on 2018/11/27.
 */
public interface UserService {
    boolean login(String username,String password);

    int addUser(HashMap<String,Object> paramMap) throws Exception;

    User getUserDetails(int userId) throws Exception;

    int updateUser(HashMap<String,Object> paramMap) throws Exception;

    User selectUserById(int userId);
}

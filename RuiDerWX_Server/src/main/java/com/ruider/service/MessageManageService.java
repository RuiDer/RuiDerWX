package com.ruider.service;

import com.ruider.model.MessageManage;
import com.ruider.model.NeedsManagement;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface MessageManageService {

    int addEvaluate(HashMap<String,Object> paramMap) throws Exception;

    int addReply(HashMap<String,Object> paramMap) throws Exception;

    List<HashMap<String, Object>> getMessageByNeedsIdAndCategoryId(@Param("needsId") int needsId, @Param("categoryId") int categoryId) throws Exception;

    List<HashMap<String, Object>> getAndSetMessageByUserIdAndCategoryId(@Param("userId") int userId, @Param("categoryId") int categoryId, @Param("isReply") int isReply) throws Exception;

    HashMap<String,Integer> getNoWatchedMessageNo(int userId) throws Exception;

    int updateIsApproved(@Param("isApproved") int isApproved, @Param("id") int id) throws Exception;

    List<HashMap<String,Object>> checkOverTimeNeeds(@Param("userId") int userId) throws Exception;

    List<HashMap<String,Object>> checkOverNumber(@Param("userId") int userId) throws Exception;

}

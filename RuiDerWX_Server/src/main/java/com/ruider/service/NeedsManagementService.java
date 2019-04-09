package com.ruider.service;

import com.ruider.model.NeedsManagement;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by mahede on 2018/12/2.
 */
public interface NeedsManagementService {

    List<NeedsManagement> getNeedsByUserId(int userId) throws Exception;

    List<NeedsManagement> getNeedsByUserIdAndCategoryId(int userId, int categotyId) throws Exception;

    void addNeeds(HashMap<String,Object> paramMap) throws Exception;

    void deleteNeeds(int id) throws Exception;

    NeedsManagement getNeedsDetailsById(int id) throws Exception;

    void editNeeds(HashMap<String,Object> paramMap) throws Exception;

    List<NeedsManagement> getNeedsByCategoryId(int categotyId) throws Exception;

}

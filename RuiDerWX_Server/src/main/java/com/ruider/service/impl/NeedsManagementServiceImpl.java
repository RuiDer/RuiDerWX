package com.ruider.service.impl;

import com.ruider.mapper.NeedsManagementMapper;
import com.ruider.model.NeedsManagement;
import com.ruider.service.NeedsManagementService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by mahede on 2018/12/2.
 */
@Service
public class NeedsManagementServiceImpl implements NeedsManagementService{

    @Autowired
    NeedsManagementMapper needsManagementMapper;

    @Override
    public List<NeedsManagement> getNeedsByUserId(int userId) throws Exception{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<NeedsManagement> list = needsManagementMapper.getNeedsByUserId(userId);
        for(NeedsManagement needsManagement : list) {
            needsManagement.setStartTime(simpleDateFormat.parse(simpleDateFormat.format(needsManagement.getStartTime())));
            needsManagement.setDeadline(simpleDateFormat.parse(simpleDateFormat.format(needsManagement.getDeadline())));
        }

        return list;
    }

    @Override
    public List<NeedsManagement> getNeedsByUserIdAndCategoryId(int userId, int categotyId) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<NeedsManagement> list = needsManagementMapper.getNeedsByUserIdAndCategoryId(userId, categotyId);
        for(NeedsManagement needsManagement : list) {
            needsManagement.setStartTime(simpleDateFormat.parse(simpleDateFormat.format(needsManagement.getStartTime())));
            needsManagement.setDeadline(simpleDateFormat.parse(simpleDateFormat.format(needsManagement.getDeadline())));
        }
        return list;
    }

    @Override
     public void addNeeds(HashMap<String,Object> paramMap) throws Exception {
         NeedsManagement needsManagement = new NeedsManagement();
         needsManagement.setCategoryId(Integer.valueOf(paramMap.get("categoryId").toString())+1);
         needsManagement.setUserId(Integer.valueOf(paramMap.get("userId").toString()));
         needsManagement.setTitle(paramMap.get("title").toString());
         needsManagement.setContent(paramMap.get("content").toString());
         SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
         needsManagement.setStartTime(simpleDateFormat.parse(paramMap.get("startTime").toString()));
         needsManagement.setDeadline(simpleDateFormat.parse(paramMap.get("deadline").toString()));
         needsManagement.setQq(paramMap.get("qq").toString());
         needsManagement.setWeChat(paramMap.get("weChat").toString());
         needsManagement.setPhoneNo(paramMap.get("phoneNo").toString());
         needsManagement.setLimitNo(Integer.valueOf(paramMap.get("limitNo").toString()));
         needsManagement.setCreateTime(new Date());
         needsManagement.setUpdateTime(new Date());
         needsManagementMapper.addNeeds(needsManagement);
    }

    @Override
    public void deleteNeeds(int id) throws Exception {
        needsManagementMapper.deleteNeeds(id);
    }

    @Override
    public NeedsManagement getNeedsDetailsById(int id) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        NeedsManagement needsManagement = needsManagementMapper.getNeedsDetailsById(id);
        needsManagement.setStartTime(simpleDateFormat.parse(simpleDateFormat.format(needsManagement.getStartTime())));
        needsManagement.setDeadline(simpleDateFormat.parse(simpleDateFormat.format(needsManagement.getDeadline())));
        return needsManagement;
    }

    @Override
    public void editNeeds(HashMap<String,Object> paramMap) throws Exception{
        NeedsManagement needsManagement = new NeedsManagement();
        needsManagement.setCategoryId(Integer.valueOf(paramMap.get("categoryId").toString()));
        needsManagement.setUserId(Integer.valueOf(paramMap.get("userId").toString()));
        needsManagement.setTitle(paramMap.get("title").toString());
        needsManagement.setContent(paramMap.get("content").toString());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        needsManagement.setStartTime(simpleDateFormat.parse(paramMap.get("startTime").toString()));
        needsManagement.setDeadline(simpleDateFormat.parse(paramMap.get("deadline").toString()));
        needsManagement.setQq(paramMap.get("qq").toString());
        needsManagement.setWeChat(paramMap.get("weChat").toString());
        needsManagement.setId(Integer.valueOf(paramMap.get("id").toString()));
        needsManagement.setLimitNo(Integer.valueOf(paramMap.get("limitNo").toString()));
        needsManagement.setPhoneNo(paramMap.get("phoneNo").toString());
        needsManagement.setUpdateTime(new Date());
        needsManagementMapper.editNeeds(needsManagement);
    }

    @Override
    public List<NeedsManagement> getNeedsByCategoryId(int categotyId) throws Exception{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<NeedsManagement> list = needsManagementMapper.getNeedsByCategoryId(categotyId);
        for(int i = 0; i<list.size(); i++) {
            NeedsManagement needsManagement = list.get(i);
            if(needsManagement.getIsOverTime() == 0) {
                if (needsManagement.getDeadline().compareTo(new Date()) < 0 ) {
                    needsManagement.setIsOverTime(1);
                    needsManagementMapper.updateIsOverTime(needsManagement.getId());
                    list.set(i, needsManagement);
                }
            }
            needsManagement.setStartTime(simpleDateFormat.parse(simpleDateFormat.format(needsManagement.getStartTime())));
            needsManagement.setDeadline(simpleDateFormat.parse(simpleDateFormat.format(needsManagement.getDeadline())));
        }
        return list;
    }
}

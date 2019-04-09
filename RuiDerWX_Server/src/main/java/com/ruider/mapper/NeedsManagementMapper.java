package com.ruider.mapper;

import com.ruider.model.NeedsManagement;
import org.apache.ibatis.annotations.*;
import org.mybatis.spring.annotation.MapperScan;

import java.util.Date;
import java.util.List;

/**
 * Created by mahede on 2018/12/2.
 */
@Mapper
public interface NeedsManagementMapper {

    @Select("select * from `needsManage` where `userId` = #{userId}")
    List<NeedsManagement> getNeedsByUserId(int userId);

    @Select("select * from `needsManage` where `userId` = #{userId} AND `categoryId` = #{categoryId}")
    List<NeedsManagement> getNeedsByUserIdAndCategoryId(@Param("userId") int userId,@Param("categoryId") int categoryId);

    @Insert("Insert into `needsManage`(`categoryId`, `userId`, `title`, `content`, `startTime`, `deadline`,`qq`, `weChat`, `phoneNo`, `limitNo`, `createTime`, `updateTime`) values(#{categoryId}, #{userId}, #{title}, #{content}, #{startTime}, #{deadline}, #{qq}, #{weChat}, #{phoneNo}, #{limitNo}, #{createTime}, #{updateTime})")
    int addNeeds(NeedsManagement needsManagement);

    @Delete("delete from `needsManage` where `id` = #{id}")
    int deleteNeeds(@Param("id") int id);

    @Select("select * from `needsManage` where `id` = #{id}")
    NeedsManagement getNeedsDetailsById(int id);

    @Update("update `needsManage` set `categoryId` = #{categoryId}, `userId`= #{userId}, `title`= #{title}, `content`= #{content}, `startTime`= #{startTime}, `deadline`= #{deadline},`qq`= #{qq}, `weChat`= #{weChat}, `phoneNo`= #{phoneNo}, `limitNo`= #{limitNo}, `updateTime`= #{updateTime}  where `id` = #{id}")
    int editNeeds(NeedsManagement needsManagement);

    @Select("select * from `needsManage` where `categoryId` = #{categoryId}")
    List<NeedsManagement> getNeedsByCategoryId(int categotyId);

    @Select("select * from `needsManage` where `deadline` < #{checkTime} and `userId` = #{userId}")
    List<NeedsManagement> checkOverTimeNeeds(@Param("userId") int userId, Date checkTime);

    @Select("select * from `needsManage` where `joinNo` = `limitNo` and `userId` = #{userId}")
    List<NeedsManagement> checkOverNumber(@Param("userId") int userId);

    @Update("update `needsManage` set `joinNo` = `joinNo`+1 where `id` = #{needId}")
    int riseJoinNO(int needId);

    @Update("update `needsManage` set `isOverTime` = 1 where `id`=#{id}")
    int updateIsOverTime(int id);

    @Update("update `needsManage` set `isOverNo` = 1 where `id`=#{id}")
    int updateIsOverNO(int id);
}

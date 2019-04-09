package com.ruider.mapper;

import com.ruider.model.MessageManage;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MessageManageMapper {

    @Select("select * from `messageManage` where `id`=#{id}")
    MessageManage getMessageById(int id);

    @Insert("insert into `messageManage`(`categoryId`, `userId`, `needsId`, `content`, `createTime`, `isApproved`, `userIsWatched`, `masterIsWatched`, `isReply`, `masterId`, `messageId`) values(#{categoryId}, #{userId}, #{needsId}, #{content}, #{createTime}, #{isApproved}, #{userIsWatched}, #{masterIsWatched}, #{isReply}, #{masterId}, #{messageId})")
    @Options(useGeneratedKeys=true,keyProperty="id",keyColumn="id")
    int addEvaluate(MessageManage messageManage);

    @Update("update `messageManage` set `replyIds`= #{replyIds} where `id`=#{messageId}")
    int addReply(String replyIds, int messageId);

    @Select("select * from `messageManage` where `needsId`=#{needsId} and `categoryId`=#{categoryId}")
    List<MessageManage> getMessageByNeedsIdAndCategoryId(@Param("needsId") int needsId, @Param("categoryId") int categoryId);

    @Select("select * from `messageManage` where `needsId`=#{needsId} and `categoryId`=#{categoryId} and `isReply`=#{isReply}")
    List<MessageManage> getAndSetMessageByNeedIdAndCategoryId(@Param("needsId") int needsId, @Param("categoryId") int categoryId, int isReply);

    @Update("update `messageManage` set `masterIsWatched` = 1 where `id` = #{id}")
    int updateMasterIsWatchedById( int id);

    @Select("select * from `messageManage` where `needsId`=#{needsId} and `masterIsWatched` = 0")
    List<MessageManage> getNoWatchedMessage(int needsId);

    @Select("select * from `messageManage` where `userId`=#{userId} and `categoryId`=#{categoryId} and `isReply` = #{isReply}")
    List<MessageManage> getMessageByUserIdAndCategoryId(@Param("userId") int userId, @Param("categoryId") int categoryId, int isReply);

    @Update("update `messageManage` set `isApproved`=#{isApproved} where `id`=#{id}")
    int updateIsApproved(int isApproved, @Param("id") int id);

    @Select("select * from `messageManage` where `masterId`=#{masterId}")
    List<MessageManage> getMessageByMasterId(int masterId);

    @Select("select * from `messageManage` where `messageId`=#{messageId}")
    List<MessageManage> getReplyByMessageId(int messageId);

    @Select("select * from `messageManage` where `masterId`=#{masterId} and `categoryId` = #{categoryId} and `isReply`=#{isReply}")
    List<MessageManage> getMessageByMasterIdAndCategoryId(int masterId, int categoryId, int isReply);

    @Select("select * from `messageManage` where `masterId`=#{masterId} and `categoryId` = #{categoryId}")
    List<MessageManage> getMessageByUserIdAndCategoryIdAndIsApprovedAndapprovedOrRefuseIsViewed(int masterId, int categoryId);

    @Update("update `messageManage` set `approvedOrRefuseIsViewed`=#{approvedOrRefuseIsViewed} where `id`=#{id}")
    int updateApprovedOrRefuseIsViewed(int id, int approvedOrRefuseIsViewed);

    @Select("select * from `messageManage` where `masterId`=#{masterId} and `masterIsWatched` = 0")
    List<MessageManage> getMessageByMasterIdAndMasterNoWatched(int masterId);

}

package com.ruider.mapper;

import com.ruider.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

/**
 * Created by mahede on 2018/11/27.
 */
@Mapper
@CacheConfig(cacheNames = "users")
public interface UserMapper {

    @Insert("insert into `user`" +
            "(`nickName`, `age`, `image`, `openid`,`headUrl`, `password`, `mobilePhone`,`autograph`, `status`, `realName`, `sex`,`level`,`createTime`,`extendField1`,`extendField2`, `extendField3`, `extendField4`, `extendField5`, `extendField6`, `extendField7`) " +
            "values(#{nickName}, #{age}, #{image}, #{openid},#{headurl}, #{password}, #{mobilePhone}, #{autograph}, #{status}, #{realName},#{sex},#{level},#{createTime},#{extendField1},#{extendField2},#{extendField3},#{extendField4},#{extendField5},#{extendField6},#{extendField7})")

    @Options(useGeneratedKeys=true,keyProperty="id",keyColumn="id")
    int addUser(User user);

    @Delete("delete from `user` where `id` = #{id}")
    //@CacheEvict(key ="#p0",allEntries=true)
    int deleteUserById(int id);

    @Update("update `user` set `age` = #{age}, `mobilePhone` = #{mobilePhone}, `autograph` = #{autograph}, `realName` = #{realName}, `sex` = #{sex}, `headUrl` = #{headurl} where `id` = #{id} ")
    //@CachePut(key = "#p0")
    int updateUser(User user);

    @Select("select * from `RuiDer`.`user`" +
            " where `nickName`= #{nickName} and `sex` = #{sex} and `image` = #{image} ")
    //@Cacheable(key ="#p0")
    User selectUser(User user);


    @Select("select * from `RuiDer`.`user`" +
            " where `openid` = #{openid}")
    //@Cacheable(key ="#p0")
    User selectUserByOpenId(String openid);

    @Select("select * from `RuiDer`.`user` where `id` = #{id}")
    //@Cacheable(key ="#p0")
    User selectUserById(int id);


    //更新头像
    @Update("update `user` set `image` = #{image} where `id` = #{id}")
    //@CachePut(key = "#p0")
    int updateImage(User user);

    //更新登录状态
    @Update("update `user` set `status` = #{status} where `id` = #{id}")
    //@CachePut(key = "#p0")
    int updateStatus(User user);

    //更新用户基本信息
    @Update("update `user` set `nickName` = #{nickName}, `headurl` = #{headurl}, `sex` = #{sex}, `userip` = #{userip} where `openid` = #{openid}")
    //@CachePut(key = "#p0")
    int updateUserMseeage(String openid, String nickname, String headurl, String sex, String userip);

    @Insert("insert into `user`(`nickName`, `sex`, `createTime`, `image`) values(#{nickName}, #{sex},  #{createTime}, #{image})")
    int addUserByWX(User user);
}

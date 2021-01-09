package com.td.mapper;

import com.td.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
@Mapper
@Repository
public interface UserMapper {
    /**
     * 根据useremail 查询记录数量
     * @return Integer
     */
    Integer countUserByUseremail(String useremail);


    /**
     *往数据表中添加一个user
     * @param user
     * @return Integer
     */
    Integer insertUser(User user);

    /**
     *
     * @param user
     * @return user:查询一个用户
     */
    User queryUser(User user);

    /**
     *改变数据表中一个user的密码
     * @param user
     * @return Integer
     */
    Integer updateUser(User user);

    /**
     *
     * @param user
     * @return 返回 数据库返回的值
     */
    Integer  updateKeywords(User user);

    /**
     *
     * @param useremail
     * @return
     */
    User findByName(String useremail);
}

package com.td.service;

import com.td.pojo.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    /**
     * 根据useremail 查询记录数量
     * @return Integer
     */
    Integer countUserByUseremail(String useremail);

    /**
     *往数据表中添加一个user
     * @param user
     * @return String
     */
    String  addUser(User user);

    /**
     *
     * @param user
     * @return user:查询一个用户
     */
    User queryUser(User user);

    /**
     *改变数据表中一个user的密码
     * @param user
     * @return String
     */
    String updatePassword(User user);

    /**
     *
     * @param user
     * @return String 状态码
     */
    String  updateKeywords(User user);

    /**
     *
     * @param useremail
     * @return
     */
    User findByName(String useremail);
}

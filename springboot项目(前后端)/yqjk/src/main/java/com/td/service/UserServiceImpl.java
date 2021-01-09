package com.td.service;

import com.td.mapper.UserMapper;
import com.td.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    public static String flag = "";
    public static Integer state=-1;

    @Autowired
    UserMapper userMapper;


    @Override
    public Integer countUserByUseremail(String useremail) {
        return userMapper.countUserByUseremail(useremail);
    }

    @Override
    public String addUser(User user) {
        flag = "fail";
        Integer count = userMapper.countUserByUseremail(user.getUseremail());
        if (count > 0) {//mail已经存在
            flag = "same mail";
        } else if (userMapper.insertUser(user)>0){
           flag="success"; //操作成功
        }
        return flag;
    }

    @Override
    public User queryUser(User user) {
        return userMapper.queryUser(user);
    }

    @Override
    public String updatePassword(User user) {
        flag = "fail";
        Integer count = userMapper.countUserByUseremail(user.getUseremail());
        if (count <1) {//mail不存在
            flag = "no mail";
        } else if (userMapper.updateUser(user)>0){
            flag="success"; //操作成功
        }
        return flag;
    }

    @Override
    public String updateKeywords(User user) {
        String flag="fail";
        Integer count=  userMapper.updateKeywords(user);
        if (count >0) {//数据库操作成功
            flag = "success";
        }
        return flag;
    }

    @Override
    public User findByName(String useremail) {
        return userMapper.findByName(useremail);
    }
}

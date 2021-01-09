package com.td.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


/**
 * @Param:
 * id:对应字段id
 * useremail:对应字段注册邮箱
 * password:对应字段用户密码
 * root:对应权限字段 默认会normal
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class User {
Integer id;
String useremail;
String password;
String root;
String keywords;
}

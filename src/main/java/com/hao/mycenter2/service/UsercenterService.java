package com.hao.mycenter2.service;

import com.hao.mycenter2.model.Usercenter;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletRequest;

import java.net.http.HttpRequest;

/**
 * 用户服务
* @author hao'qi'wei
* @description 针对表【usercenter(用户中心信息表)】的数据库操作Service
* @createDate 2024-03-01 16:55:10
 *
*/
public interface UsercenterService extends IService<Usercenter> {
    /**
     * 用户登录态键
     */

    /**
     *  用户注册
     *
     * @param userAccount 用户账户
     * @param userPassword 用户密码
     * @param checkPassword  校验密码
     * @return 新用户id
     */
    long userRegister(String userAccount,String userPassword,String checkPassword);

    /**
     * 用户登录
     *
     * @param userAccount 账号
     * @param userPassword 密码
     * @return 返回脱敏后的用户信息
     */
    Usercenter dologin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 用户脱敏
     * @param originUser 脱敏前user
     * @return 脱敏后user
     */
    Usercenter getSafety(Usercenter originUser);
}

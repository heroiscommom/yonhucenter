package com.hao.mycenter2.service;

import com.hao.mycenter2.model.Usercenter;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 用户服务
* @author hao'qi'wei
* @description 针对表【usercenter(用户中心信息表)】的数据库操作Service
* @createDate 2024-03-01 16:55:10
 *
*/
public interface UsercenterService extends IService<Usercenter> {

    /**
     *  用户注册
     *
     * @param userAccount 用户账户
     * @param userPassword 用户密码
     * @param checkPassword  校验密码
     * @return 新用户id
     */
    long userRegister(String userAccount,String userPassword,String checkPassword);
}

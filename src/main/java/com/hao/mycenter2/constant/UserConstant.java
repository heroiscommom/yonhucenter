package com.hao.mycenter2.constant;

/**
 * 用户常量接口
 *
 * @author awei
 */
public interface UserConstant {
    String USER_LOGIN_STATE = "userLoginState";

    // ---------权限----------

    /**
     * 权限管理 0 - 普通用户 1 - 管理员
     */
    int DEFAULT_ROLE = 0;
    int ADMIN_ROLE = 1;
}

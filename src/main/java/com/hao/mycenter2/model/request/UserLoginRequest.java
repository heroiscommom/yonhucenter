package com.hao.mycenter2.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户登录请求体
 *
 * @author awei
 */
@Data
public class UserLoginRequest implements Serializable {
    public static final long serialVersionUID = 31312313455661423L;
    private String userAccount;
    private String userPassword;
}


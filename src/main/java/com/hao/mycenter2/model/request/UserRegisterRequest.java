package com.hao.mycenter2.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户注册请求体
 *
 * @author awei
 */
@Data
public class UserRegisterRequest implements Serializable {
    public static final long serialVersionUID = 31312313455661423L;
    private String userAccount;
    private String userPassword;
    private String checkPassword;
}


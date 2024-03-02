package com.hao.mycenter2.model;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户中心信息表
 * @TableName usercenter
 */
@TableName(value ="usercenter")
@Data
public class Usercenter implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 头像
     */
    private String avatarurl;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 密码
     */
    private String userpassword;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 是否有效（是否被封号1表示被封，0表示没有）
     */
    private String isvalid;

    /**
     * 0 - 普通用户 1 - 管理员
     */
    private Integer role;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 更新时间
     */
    private Date updatetime;

    /**
     * 是否删除(0-未删, 1-已删)
     */
    @TableLogic
    private Integer isdeleted;

    /**
     * 账号
     */
    private String useraccount;

    /**
     * 用户状态（1-正常）
     */
    private Integer userstatus;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}

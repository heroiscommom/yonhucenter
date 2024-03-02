-- 用户中心信息表
create table if not exists mycenter.`usercenter`
(
    `id` bigint not null auto_increment comment '主键' primary key,
    `username` varchar(256)  comment '用户名',
    `avatarUrl` varchar(256)  comment '头像',
    `gender` varchar(256)  comment '性别',
    `password` varchar(256) not null comment '密码',
    `phone` varchar(256)  comment '电话',
    `email` varchar(256) comment '邮箱',
    `isValid` varchar(256)  comment '是否有效（是否被封号1表示被封，0表示没有）',
    `role` varchar(256) not null comment '0 - 普通用户，1 - 管理员',
    `userAccount` varchar(256) not null comment '用户账号',
    `userState` varchar(256) not null comment '用户状态',
    `createTime` datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    `updateTime` datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    `iSdeleted` tinyint default 0 not null comment '是否删除(0-未删, 1-已删)'
    ) comment '用户中心信息表';

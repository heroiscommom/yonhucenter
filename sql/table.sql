-- 用户中心信息表
create table if not exists mycenter.`usercenter`
(
    `id` bigint not null auto_increment comment '主键' primary key,
    `username` varchar(256) not null comment '用户名',
    `avatarUrl` varchar(256) not null comment '头像',
    `gender` varchar(256) not null comment '性别',
    `password` varchar(256) not null comment '密码',
    `phone` varchar(256) not null comment '电话',
    `email` varchar(256) not null comment '邮箱',
    `isValid` varchar(256) not null comment '是否有效（是否被封号1表示被封，0表示没有）',
    `createTime` datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    `updateTime` datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    `iSdeleted` tinyint default 0 not null comment '是否删除(0-未删, 1-已删)'
    ) comment '用户中心信息表';

insert into mycenter.`usercenter` (`username`, `avatarUrl`, `gender`, `password`, `phone`, `email`, `isValid`) values ('孟熠彤', 'www.cristin-douglas.biz', '0', 'uo', '17778922159', 'lynn.friesen@yahoo.com', '0');
insert into mycenter.`usercenter` (`username`, `avatarUrl`, `gender`, `password`, `phone`, `email`, `isValid`) values ('贾雪松', 'www.annis-lang.org', '0', '3E', '15329560199', 'ezequiel.hilpert@yahoo.com', '0');
insert into mycenter.`usercenter` (`username`, `avatarUrl`, `gender`, `password`, `phone`, `email`, `isValid`) values ('吕靖琪', 'www.ross-douglas.name', '0', 'LudA', '17212890803', 'miquel.robel@yahoo.com', '0');
insert into mycenter.`usercenter` (`username`, `avatarUrl`, `gender`, `password`, `phone`, `email`, `isValid`) values ('孔天磊', 'www.foster-larkin.name', '0', 'jHmii', '15116617125', 'rick.stanton@yahoo.com', '0');
insert into mycenter.`usercenter` (`username`, `avatarUrl`, `gender`, `password`, `phone`, `email`, `isValid`) values ('沈皓轩', 'www.ezra-flatley.co', '0', '8h', '17713229733', 'orville.bergnaum@gmail.com', '0');
insert into mycenter.`usercenter` (`username`, `avatarUrl`, `gender`, `password`, `phone`, `email`, `isValid`) values ('邹鹤轩', 'www.ebony-davis.name', '0', 'zvN', '15048875567', 'rene.breitenberg@hotmail.com', '0');
insert into mycenter.`usercenter` (`username`, `avatarUrl`, `gender`, `password`, `phone`, `email`, `isValid`) values ('魏伟泽', 'www.trey-hegmann.name', '0', 'Te', '15214956209', 'charis.kuhn@yahoo.com', '0');
insert into mycenter.`usercenter` (`username`, `avatarUrl`, `gender`, `password`, `phone`, `email`, `isValid`) values ('邓熠彤', 'www.jenae-jenkins.net', '0', 'Lh3L', '17223904349', 'shonta.conroy@yahoo.com', '0');
insert into mycenter.`usercenter` (`username`, `avatarUrl`, `gender`, `password`, `phone`, `email`, `isValid`) values ('张浩宇', 'www.earl-rodriguez.co', '0', '9HO', '15737276686', 'burt.prosacco@hotmail.com', '0');
insert into mycenter.`usercenter` (`username`, `avatarUrl`, `gender`, `password`, `phone`, `email`, `isValid`) values ('熊睿渊', 'www.brent-jacobs.name', '0', '0Eki', '17804175526', 'gertha.mclaughlin@yahoo.com', '0');
insert into mycenter.`usercenter` (`username`, `avatarUrl`, `gender`, `password`, `phone`, `email`, `isValid`) values ('石明', 'www.dovie-gleason.org', '0', 'IGcsI', '15735086451', 'blake.bashirian@gmail.com', '0');
insert into mycenter.`usercenter` (`username`, `avatarUrl`, `gender`, `password`, `phone`, `email`, `isValid`) values ('贺楷瑞', 'www.lessie-kunze.name', '0', 'lb', '15270463001', 'billy.fritsch@yahoo.com', '0');
insert into mycenter.`usercenter` (`username`, `avatarUrl`, `gender`, `password`, `phone`, `email`, `isValid`) values ('武伟宸', 'www.colton-waelchi.io', '0', 'EpF', '17171013837', 'mckinley.ullrich@hotmail.com', '0');
insert into mycenter.`usercenter` (`username`, `avatarUrl`, `gender`, `password`, `phone`, `email`, `isValid`) values ('顾乐驹', 'www.laura-kuhic.co', '0', 'sG', '15835479078', 'cortez.zemlak@yahoo.com', '0');
insert into mycenter.`usercenter` (`username`, `avatarUrl`, `gender`, `password`, `phone`, `email`, `isValid`) values ('赖伟泽', 'www.lissa-ruecker.io', '0', 'YN1UR', '15674198190', 'marcel.windler@yahoo.com', '0');
insert into mycenter.`usercenter` (`username`, `avatarUrl`, `gender`, `password`, `phone`, `email`, `isValid`) values ('彭锦程', 'www.rosina-dickens.org', '0', 'gJDt', '18102497578', 'teressa.kemmer@yahoo.com', '0');
insert into mycenter.`usercenter` (`username`, `avatarUrl`, `gender`, `password`, `phone`, `email`, `isValid`) values ('杨伟诚', 'www.alane-schmeler.name', '0', 'PS', '17882357645', 'valrie.walker@yahoo.com', '0');
insert into mycenter.`usercenter` (`username`, `avatarUrl`, `gender`, `password`, `phone`, `email`, `isValid`) values ('吴楷瑞', 'www.tonisha-muller.io', '0', 'CZF5J', '17375246881', 'bobbie.mosciski@hotmail.com', '0');
insert into mycenter.`usercenter` (`username`, `avatarUrl`, `gender`, `password`, `phone`, `email`, `isValid`) values ('郭鹏', 'www.delmar-reynolds.net', '0', 'D8fC', '18275948839', 'voncile.okuneva@yahoo.com', '0');
insert into mycenter.`usercenter` (`username`, `avatarUrl`, `gender`, `password`, `phone`, `email`, `isValid`) values ('史笑愚', 'www.ira-effertz.info', '0', 'IOj', '15016257937', 'jay.hartmann@gmail.com', '0');

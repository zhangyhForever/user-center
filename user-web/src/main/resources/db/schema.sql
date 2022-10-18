drop table if exists t_user_center;
create table t_user_center (
    id          int auto_increment comment '用户id（自增）'
        primary key,
    username    varchar(100) default ''                not null comment '用户名',
    pwd         varchar(100) default ''                not null comment '用户密码',
    email       varchar(50)  default ''                not null comment '邮箱',
    phone       bigint       default 0                 not null comment '电话号码',
    create_time timestamp    default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time timestamp    default CURRENT_TIMESTAMP not null comment '修改时间',
    is_delete   tinyint      default 0                 not null comment '删除标识'
) engine = innodb;



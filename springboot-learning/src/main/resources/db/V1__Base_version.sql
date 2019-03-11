DROP TABLE IF EXISTS users;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL auto_increment COMMENT '主键id',
  `userName` varchar(32) default NULL COMMENT '用户名',
  `passWord` varchar(32) default NULL COMMENT '密码',
  `user_sex` varchar(32) default NULL,
  `nick_name` varchar(32) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
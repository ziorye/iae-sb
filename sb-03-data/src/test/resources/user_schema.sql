DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`       int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `name`     varchar(30) NOT NULL DEFAULT '' COMMENT '姓名',
    `age`      int(11) DEFAULT NULL COMMENT '年龄',
    `email`    varchar(100)         DEFAULT NULL COMMENT '邮箱',
    `password` varchar(100)         DEFAULT NULL COMMENT '密码',
    PRIMARY KEY (`id`)
);
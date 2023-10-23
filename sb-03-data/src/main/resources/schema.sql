DROP TABLE IF EXISTS `city`;
CREATE TABLE `city`
(
    `id`      int(11) unsigned NOT NULL AUTO_INCREMENT,
    `name`    varchar(255) DEFAULT NULL,
    `state`   varchar(255) DEFAULT NULL,
    `country` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
);
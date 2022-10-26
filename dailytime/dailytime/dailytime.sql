CREATE DATABASE dailytime;
USE dailytime;
CREATE TABLE `user`(
`id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
`username` VARCHAR(50) NOT NULL,
`password` VARCHAR(50) NOT NULL,
`email` VARCHAR(20),
`telephone` VARCHAR(20),
`loginTime` DATE,
`registerTime` DATE
)

ALTER TABLE `user` MODIFY COLUMN loginTime VARCHAR(36);

SELECT * FROM `user`;

ALTER TABLE `user` MODIFY COLUMN id  VARCHAR(36) COMMENT 'ID';

ALTER TABLE `user` CHANGE loginTime lastLoginTime VARCHAR(36);

ALTER TABLE `user` ADD COLUMN address VARCHAR(200);

ALTER TABLE `user` ADD COLUMN birthday VARCHAR(36);

ALTER TABLE `user` ADD COLUMN userrole VARCHAR(25);



CREATE TABLE `article`(
`id` VARCHAR(36) PRIMARY KEY NOT NULL,
`article_user` VARCHAR(32),
`edit_time` VARCHAR(36),
`article_content` LONGTEXT,
`read_count` VARCHAR(36)
)

ALTER TABLE `article` CHANGE article_user article_author VARCHAR(32);

SELECT * FROM `article`;


ALTER TABLE `article` ADD COLUMN article_title VARCHAR(64);





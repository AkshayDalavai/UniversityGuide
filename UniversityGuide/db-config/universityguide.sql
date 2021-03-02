DROP DATABASE if exists `university_guide`;

CREATE DATABASE  IF NOT EXISTS `university_guide`;
USE `university_guide`;

--
-- Table structure for table `ug_users`
--


DROP TABLE IF EXISTS `users`;


CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(255) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `created_by` varchar(50) NOT NULL,
  `updated_by`varchar(50) DEFAULT NULL,
  `updated_date` timestamp NULL DEFAULT NULL,
  `university` varchar(50) NOT NULL,
  `roles` varchar(20) DEFAULT 'USER' NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_userid` (`id`),
  UNIQUE KEY `unique_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `posts`;
CREATE TABLE `posts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NOT NULL,
  `id_category` int(11) NOT NULL,
  `title` varchar(100) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_date` timestamp NULL DEFAULT NULL,
  `created_by` varchar(50) NOT NULL,
  `updated_by`varchar(50) DEFAULT NULL,
  `has_comments` bool NOT NULL DEFAULT FALSE,
  `has_like` bool NOT NULL DEFAULT FALSE,
  `posts_content` text NOT NULL,
  `likes_count`int(11) DEFAULT 0,
  `is_anonymous`bool NOT NULL DEFAULT FALSE,
  `comments_count`int(11) DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_postsid` (`id`),
  FOREIGN KEY (`id_user`) REFERENCES `users`(`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  FOREIGN KEY (`id_category`) REFERENCES `category`(`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments`(
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NOT NULL,
  `id_posts` int(11) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_date` timestamp NULL DEFAULT NULL,
  `created_by` varchar(50) NOT NULL,
  `updated_by`varchar(50) DEFAULT NULL,
  `comments_content` text,
  `likes`int(11) NOT NULL,
  `is_anonymous`bool NOT NULL DEFAULT FALSE,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_commentsid` (`id`),
  FOREIGN KEY (`id_user`) REFERENCES `users`(`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  FOREIGN KEY (`id_posts`) REFERENCES `posts`(`id`) ON DELETE CASCADE ON UPDATE NO ACTION
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


USE `university_guide`;
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(50) DEFAULT 'USER' NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `unique_roleid` (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

insert into university_guide.role (`name`) VALUES ('USER');
insert into university_guide.role (`name`) VALUES ('ADMIN'); 

DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`(
	`id_user` int(11) NOT NULL,
    `id_role` int(11) NOT NULL,
     FOREIGN KEY (`id_user`) REFERENCES `users`(`id`),
	 FOREIGN KEY (`id_role`) REFERENCES `role`(`id`) 
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `auth_token`;
CREATE TABLE `auth_token`(
	`token` varchar(50) NOT NULL,
    PRIMARY KEY (`token`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `postslikes`;
CREATE TABLE `postslikes`(
	`id` int(11) NOT NULL AUTO_INCREMENT,
    `id_posts` int(11) NOT NULL,
    `id_user` int(11) NOT NULL,
    `posts_likes` bool NOT NULL DEFAULT FALSE, 	
    PRIMARY KEY (`id`),
    FOREIGN KEY (`id_user`) REFERENCES `users`(`id`),
    FOREIGN KEY (`id_posts`) REFERENCES `posts`(`id`) ON DELETE CASCADE ON UPDATE NO ACTION
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `commentslikes`;
CREATE TABLE `commentslikes`(
	`id` int(11) NOT NULL AUTO_INCREMENT,
    `id_posts` int(11) NOT NULL,
    `id_user` int(11) NOT NULL,
    `id_comments` int(11) NOT NULL,
    `comments_likes` bool NOT NULL DEFAULT FALSE,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`id_user`) REFERENCES `users`(`id`),
    FOREIGN KEY (`id_comments`) REFERENCES `comments`(`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
    FOREIGN KEY (`id_posts`) REFERENCES `posts`(`id`) ON DELETE CASCADE ON UPDATE NO ACTION
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;



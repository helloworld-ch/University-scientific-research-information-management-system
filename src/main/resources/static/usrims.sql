CREATE DATABASE USRIMS;

/**
创建高校
**/
IF EXISTS `usrims` DROP usrims; 
CREATE TABLE `usrims`.`college`( 
	`college_id` CHAR(6) NOT NULL COMMENT '高校id', 
	`college_name` VARCHAR(30) NOT NULL COMMENT '高校名字', 
	`is_deleted` TINYINT NOT NULL DEFAULT 0 COMMENT '逻辑删除', 
	`gmt_create` DATETIME NOT NULL COMMENT '创建时间', 
	`gmt_modified` DATETIME NOT NULL COMMENT '修改时间', 
	PRIMARY KEY (`college_id`) 
) ENGINE=INNODB CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci; 

/**
创建教师
**/
DROP TABLE teacher;
CREATE TABLE `usrims`.`teacher`( 
	`teacher_id` CHAR(11) NOT NULL COMMENT '教师id', 
	`teacher_name` VARCHAR(30) NOT NULL COMMENT '教师名字',
	`password` VARCHAR(30) NOT NULL COMMENT '密码',
	`college_id` CHAR(6) NOT NULL COMMENT '高校id',
	`teacher_iden` CHAR(1) NOT NULL COMMENT '教师身份',
	`is_deleted` TINYINT NOT NULL DEFAULT 0 COMMENT '逻辑删除', 
	`gmt_create` DATETIME NOT NULL COMMENT '创建时间', 
	`gmt_modified` DATETIME NOT NULL COMMENT '修改时间', 
	PRIMARY KEY (`teacher_id`) 
) ENGINE=INNODB CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci; 

/**
老师身份
**/
DROP TABLE iden;
CREATE TABLE `usrims`.`iden`( 
	`iden_id` CHAR(1) NOT NULL COMMENT '身份id', 
	`iden_name` VARCHAR(30) NOT NULL COMMENT '身份名字',
	`is_deleted` TINYINT NOT NULL DEFAULT 0 COMMENT '逻辑删除', 
	`gmt_create` DATETIME NOT NULL COMMENT '创建时间', 
	`gmt_modified` DATETIME NOT NULL COMMENT '修改时间', 
	PRIMARY KEY (`iden_id`) 
) ENGINE=INNODB CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci; 


/**
创建学生
**/
DROP TABLE student;
CREATE TABLE `usrims`.`student`( 
	`student_id` CHAR(11) NOT NULL COMMENT '学生id', 
	`student_name` VARCHAR(30) NOT NULL COMMENT '学生名字',
	`password` VARCHAR(30) NOT NULL COMMENT '密码',
	`college_id` CHAR(6) NOT NULL COMMENT '高校id',
	`grade` INT(1) NOT NULL COMMENT '年级',
	`is_deleted` TINYINT NOT NULL DEFAULT 0 COMMENT '逻辑删除', 
	`gmt_create` DATETIME NOT NULL COMMENT '创建时间', 
	`gmt_modified` DATETIME NOT NULL COMMENT '修改时间', 
	PRIMARY KEY (`student_id`) 
) ENGINE=INNODB CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci; 

/**
创建科研
**/
DROP TABLE research;
CREATE TABLE `usrims`.`research`( 
	`research_id` CHAR(14) NOT NULL COMMENT '研究id', 
	`research_title` VARCHAR(30) NOT NULL COMMENT '研究名字',
	`type_id` CHAR(5) NOT NULL COMMENT '研究类型id',
	`teacher_id` CHAR(11) NOT NULL COMMENT '老师ID',
	`is_deleted` TINYINT NOT NULL DEFAULT 0 COMMENT '逻辑删除', 
	`gmt_create` DATETIME NOT NULL COMMENT '创建时间', 
	`gmt_modified` DATETIME NOT NULL COMMENT '修改时间', 
	PRIMARY KEY (`research_id`) 
) ENGINE=INNODB CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci; 

/**
分类
**/
DROP TABLE `type`;
CREATE TABLE `usrims`.`type`( 
	`type_id` CHAR(5) NOT NULL COMMENT '分类id', 
	`type_name` VARCHAR(30) NOT NULL COMMENT '分类名字',
	`is_deleted` TINYINT NOT NULL DEFAULT 0 COMMENT '逻辑删除', 
	`gmt_create` DATETIME NOT NULL COMMENT '创建时间', 
	`gmt_modified` DATETIME NOT NULL COMMENT '修改时间', 
	PRIMARY KEY (`type_id`) 
) ENGINE=INNODB CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci; 

/**
人员介绍
**/
DROP TABLE person_info;
CREATE TABLE `usrims`.`person_info`( 
	`person_id` CHAR(11) NOT NULL COMMENT '人员id', 
	`info` VARCHAR(30) NOT NULL COMMENT '人员介绍',
	`is_deleted` TINYINT NOT NULL DEFAULT 0 COMMENT '逻辑删除', 
	`gmt_create` DATETIME NOT NULL COMMENT '创建时间', 
	`gmt_modified` DATETIME NOT NULL COMMENT '修改时间', 
	PRIMARY KEY (`person_id`) 
) ENGINE=INNODB CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci; 

/**
研究简介
**/
DROP TABLE introduce;
CREATE TABLE `usrims`.`introduce`( 
	`introduce_id` CHAR(14) NOT NULL COMMENT '研究id', 
	`info` VARCHAR(1024) NOT NULL COMMENT '研究介绍',
	`is_deleted` TINYINT NOT NULL DEFAULT 0 COMMENT '逻辑删除', 
	`gmt_create` DATETIME NOT NULL COMMENT '创建时间', 
	`gmt_modified` DATETIME NOT NULL COMMENT '修改时间', 
	PRIMARY KEY (`introduce_id`) 
) ENGINE=INNODB CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci; 

/**
科研成果版本
**/
DROP TABLE result;
CREATE TABLE `usrims`.`result`( 
	`research_id` CHAR(14) NOT NULL COMMENT '研究id', 
	`version` INT(3) NOT NULL COMMENT '版本',
	`content` VARCHAR(255) NOT NULL COMMENT '内容',
	`is_deleted` TINYINT NOT NULL DEFAULT 0 COMMENT '逻辑删除', 
	`gmt_create` DATETIME NOT NULL COMMENT '创建时间', 
	`gmt_modified` DATETIME NOT NULL COMMENT '修改时间', 
	PRIMARY KEY (`research_id`) 
) ENGINE=INNODB CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci; 

/**
学生科研关系
**/
DROP TABLE student_research;
CREATE TABLE `usrims`.`student_research`( 
	`research_id` CHAR(14) NOT NULL COMMENT '研究id', 
	`student_id` INT(3) NOT NULL COMMENT '版本',
	`is_deleted` TINYINT NOT NULL DEFAULT 0 COMMENT '逻辑删除', 
	`gmt_create` DATETIME NOT NULL COMMENT '创建时间', 
	`gmt_modified` DATETIME NOT NULL COMMENT '修改时间', 
	PRIMARY KEY (`research_id`,`student_id`) 
) ENGINE=INNODB CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci; 




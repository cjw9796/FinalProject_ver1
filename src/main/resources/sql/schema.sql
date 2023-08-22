CREATE DATABASE if not exists finalproject;

use finalproject;

delete from finalproject.user;

CREATE TABLE if not exists `user` (
                        `user_id`	varchar(30)	NOT NULL,
                        `user_number`	int	NOT NULL,
                        `user_name`	varchar(10)	NOT NULL,
                        `user_password`	varchar(12)	NOT NULL,
                        `user_phone`	varchar(15)	NULL,
                        `user_gender`	varchar(1)	NOT NULL,
                        `user_date`	date	NOT NULL,
                        `user_mbti`	varchar(4)	NULL
);


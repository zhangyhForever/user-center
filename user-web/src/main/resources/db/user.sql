CREATE DATABASE IF NOT EXISTS user_center;

ALTER DATABASE user_center
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;

GRANT ALL PRIVILEGES ON user_center.* TO 'usercenter'@'%' IDENTIFIED BY 'usercenter';

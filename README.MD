### 在数据库中执行db/下的user.sql脚本。需确保所创建用户是否有增删改查的权限。
* 可通过 select * from mysql.user where user='usercenter';查看权限。
* 没有权限执行 update mysql.user set Select_priv = 'Y', Insert_priv = 'Y', Update_priv = 'Y', Delete_priv = 'Y' where user='usercenter';；


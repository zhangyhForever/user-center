# 克隆应用并打包
~~~
git clone git@github.com:zhangyhForever/user-center.git
cd user-center
mvn clean package
~~~

# 部署方式
## 数据库和应用单独部署
### 1、 安装mysql
~~~
docker run -it --rm \
-v mysql_data:/var/lib/mysql \
-v mysql_config:/etc/mysql/conf.d \
--name sqlserver \
-e MYSQL_ROOT_PASSWORD= \
-e MYSQL_ALLOW_EMPTY_PASSWORD=true \
-p 3306:3306 \
mysql:8.0
~~~
### 2、执行db/user.sql脚本创建用户和数据库
### 3、制作镜像（在项目根目录执行）
~~~
docker build --tag user-center .
~~~
### 4、docker启动项目
~~~
docker run --name user-server --link sqlserver user-center
~~~
## 使用docker-compose命令启动应用（在项目根目录执行）
~~~
docker-compose -f docker-compose.yml up --build
~~~
> tips: docker-compose启动过程springboot项目会报错，由mysql容器未完全启动成功所导致，最终web项目启动成功即可。

### 如果需要使用邮件功能，需在打包前完善application.properties中mail相关配置

### 接口文档
~~~
http://localhost:8080/swagger-ui.html
~~~
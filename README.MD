### 克隆应用并打包
~~~
git clone git@github.com:zhangyhForever/user-center.git
cd user-center
mvn clean package
~~~

### 使用docker-compose命令启动应用
~~~
docker-compose -f docker-compose.yml up --build
~~~
> tips: 启动过程springboot项目会报错，由mysql容器未完全启动成功所导致，最终web项目启动成功即可。

### 如果需要使用邮件功能，需在打包前完善application.properties中mail相关配置

### 接口文档
~~~
http://localhost:8080/swagger-ui.html
~~~
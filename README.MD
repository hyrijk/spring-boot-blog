Spring Boot step by step

1. [使用 idea 新建 Spring Boot 项目](http://www.jianshu.com/p/45ba074dbc81)
2. [使用 Spring Maven 仓库加速 Maven jar 包下载](http://www.jianshu.com/p/87100dd1ec52)
3. [实现用户注册接口](http://www.jianshu.com/p/07184349738a)
4. [实现用户登录接口](http://www.jianshu.com/p/d99e4de60e5d)
5. [配置 logback 和全局异常处理](http://www.jianshu.com/p/e0b6f29f9676)
6. [使用 spring 拦截器和自定义注解进行登录拦截](http://www.jianshu.com/p/97362fdf039e)
7. [通过自定义 @CurrentUser 获取当前登录用户](http://www.jianshu.com/p/01a6a61d9e02)
8. [实现文章添加和查询接口](http://www.jianshu.com/p/e54077b0bf37)
9. [使用 fastjson 解析数据](http://www.jianshu.com/p/45682cd30ca2)

# 部署说明

---

## docker 
默认 18080 端口

1. [安装 docker](https://get.daocloud.io/#install-docker)
2. [安装 docker-compose](https://get.daocloud.io/#install-compose)
3. 修改 mysql 用户名和密码  
    ```
    $ cp application.yml.sample application.yml
    $ vi application.yml
    ```

    ```
    $ cp mysql.env mysql.env.local
    $ vi mysql.env.local
    ```
4. package  
    ```
    $ docker-compose up maven
    ```
5. run  
    - 直接运行  
        ```
        $ docker-compose up app
        ```
    - 后台运行  
        ```
        $ docker-compose start app
        ```

---

## 非docker
默认 8080 端口

1. package  
    ```
    $ mvn clean package 
    ```
2. run  
    ```
    $ nohub java -jar target/spring-boot-blog-0.0.1-SNAPSHOT.jar &
    ```

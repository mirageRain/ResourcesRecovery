# O2O资源回收系统

## 一、简介
-  本项目是中国大学生服务外包大赛比赛项目，利用互联网O2O思想，将回收从业者与预约用户的信息进行双向配对，通过微信公众号平台作为载体推广运行，后端使用RESTful风格编写API接口，使用前后端分离设计开发，项目在阿里云服务器上部署运行。
- Demo演示：[http://wx.mabotao.com(微信客户端打开)](http://wx.mabotao.com)
- 项目地址：[https://github.com/mirageRain/ResourcesRecovery](https://github.com/mirageRain/ResourcesRecovery)
-  效果截图：
![效果截图](http://www.mabotao.com/imgCDN/ResourcesRecovery1.png "用户.jpg")
![效果截图](http://www.mabotao.com/imgCDN/ResourcesRecovery4.png "用户.jpg")

## 二、使用技术
-  开发工具使用 Eclipse、HBuilder、IntelliJ IDEA、微信开发者工具。
-  数据库：MYSQL
-  后台开发框架：Spring MVC+Spring+Mybatis
-  安全管理框架：Spring Security
-  微信单点登录：Spring Social
-  前端开发框架：layUi+mui+SUI Mobile+JQuery+Bootstrap

## 三、环境要求
-  JDK8或更高版本
-  MySQL5.7或更高版本

## 四、部署说明
1.  创建MySQL数据库，字符集选择为utf8-mb4(用来支持特殊字符)。
2.  在eclipse中导入maven项目。点击eclipse菜单File - Import，选择Maven - Existing Maven Projects。
3.  设置项目编码为utf-8，选择jdk1.8版本或以上。
4.  修改数据库连接。打开/src/main/resources/application.properties文件，根据实际情况修改spring.datasource.url、spring.datasource.user、spring.datasource.password的值。
5.  修改/src/main/resources/application.properties文件中的server.port来改变运行的端口号。
5.  编译项目。在eclipse中，右键点击项目名，选择Run as - Maven build...，Goals填入clean package，然后点击Run，第一次运行需要下载jar包，请耐心等待。
6.  部署项目。运行DemoApplication项目入口。
7.  访问系统。后台地址：http://localhost:9090/admin/admin_login；用户名：admin，密码：admin。

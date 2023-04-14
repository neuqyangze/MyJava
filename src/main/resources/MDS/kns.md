##Mysql服务器时区问题
报错信息：
```
java.sql.SQLException: The server time zone value ‘ÖÐ¹ú±ê×¼Ê±¼ä’ is unrecognized or represents more than one time zone. You must configure either the server or JDBC driver (via the serverTimezone configuration property) to use a more specifc time zone value if you want to utilize time zone support.
```
错误原因：

本地时区和MySQL服务器设置的时区不一致造成的；

解决办法

```
步骤一：修改java中的时区为东八区
// serverTimezone可以设置为北京时间GMT%2B8、上海时间Asia/Shanghai或者香港时间Hongkong
url: jdbc:mysql://localhost:3306/test?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8&useSSL=true
```
```
步骤二：修改MySQL数据库的时区为东八区
// 方法一：使用命令（优点：不需要重启MySQL服务，缺点：一旦MySQL服务被重启，设置就会消失）
mysql> set time_zone = '+8:00';
mysql> set global time_zone = '+8:00';
// 方法二：修改my.ini配置文件（优点：永久保存设置，缺点：需重启MySQL服务）
[mysqld]
// 设置默认时区
default-time_zone='+8:00'
```
###Mysql config 参数

---

```
# 启动命令
--default-time-zone=timezone
# 配置文件
default-time-zone=timezone
# 运行期间
set global time_zone = timezone

set global time_zone='+8:00'
set global time_zone='Asia/Shanghai'
```
###查看操作系统时区

```
## 使用date命令
date +"%Z %z"
date -R

## 使用timedatectl
timedatectl|grep "Timezone"

## 查看文件 /etc/timezone
cat /etc/timezone
```
#
#
#
#
# 看起来我的ec2上跑不了  写入凭证也不行  大概得在key来源的账户上才能用(王八账户上)

# 权限配置
export AWS_ACCESS_KEY_ID = AKIAZOE75PO2ZOQ2JRXQ
export AWS_SECRET_ACCESS_KEY = 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


# 简单几行的tomcat安装法
git clone https://github.com/mulinShi/IOT_Project2
sudo su
sudo apt update
sudo apt -y install tomcat8

# 把war传过来
# pscp project.war ubuntu@ip:/home/ubuntu
mv /home/ubuntu/IOT_Project2/iot.war /var/lib/tomcat8/webapps

# 绑定80到8080
/sbin/iptables -t nat -I PREROUTING -p tcp --dport 80 -j REDIRECT --to-port 8080

service tomcat8 start

# 这个是绑定主页ip的  暂时可以不加  访问就用ip/iot
<Context path="" docBase="/var/lib/tomcat8/webapps/iot" debug="0" privileged="true" reloadable="true"/>  

# 日志在/var/lib/tomcat8/webapps/logs/catalina.out
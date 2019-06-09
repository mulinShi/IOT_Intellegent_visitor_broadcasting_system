# Configure the EC2 instance


# install tomcat8
git clone https://github.com/mulinShi/IOT_Project2
sudo su
sudo apt update
sudo apt -y install tomcat8

# move the web project file
mv /home/ubuntu/IOT_Project2/iot.war /var/lib/tomcat8/webapps

# bind port 80 to 8080
/sbin/iptables -t nat -I PREROUTING -p tcp --dport 80 -j REDIRECT --to-port 8080

# install awscli for use AWS Rekognition API
sudo apt -y install python3-pip
pip3 install awscli
aws configure
AWS_ACCESS_KEY_ID = YOUR_ACCESS_KEY
AWS_SECRET_ACCESS_KEY = YOUR_SECRET_KEY

# config the tomcat8 home page
sed -i '/<!-- Access/i\        <Context path="" docBase="/var/lib/tomcat8/webapps/iot" debug="0" privileged="true" reloadable="true"/>' /var/lib/tomcat8/conf/server.xml

# log file are locate at: /var/lib/tomcat8/webapps/logs/catalina.out

# start tomcat8
service tomcat8 start

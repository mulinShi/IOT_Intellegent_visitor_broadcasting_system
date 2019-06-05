create a RDS

configuration on ubuntu instance(use same security group)
1. log in
ssh -i "C:\key\test.pem" ubuntu@ec2-18-217-166-223.us-east-2.compute.amazonaws.com
2. install mysql
sudo apt-get update
sudo apt-get install mysql-server
3. login remote mysql
mysql -h database-2.caf3kfsfob9t.us-east-2.rds.amazonaws.com -P 3306 -u shi -p
4. Setting Up MySQL/JDBC Driver on Ubuntu
sudo apt-get install libmysql-java

configuration for Java enviroment
TODO-------------------

Parameter for Database 
String url = "jdbc:mysql://database-2.caf3kfsfob9t.us-east-2.rds.amazonaws.com:3306/";
String userName = "shi";
String password = "dafeifei";
String dbName = "database-2";
String driver = "com.mysql.jdbc.Driver";
Connection connection = DriverManager.getConnection(url + dbName, userName, password);





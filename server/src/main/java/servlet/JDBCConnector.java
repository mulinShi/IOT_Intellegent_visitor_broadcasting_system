/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.sql.*;

/**
 *
 * @author 64661
 */
public class JDBCConnector {
    static String url = "jdbc:mysql://db.caf3kfsfob9t.us-east-2.rds.amazonaws.com:3306/";
    static String user = "shi";
    static String password = "dafeifei";
    static String dbName = "iot";

    public Statement newConnector() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = DriverManager.getConnection(url+dbName, user, password);
            System.out.println("Successully fetch");
            Statement  statement = connect.createStatement();
            return statement;
            // Result set get the result of the SQL query
//            ResultSet resultSet = statement
//                    .executeQuery("select * from user");
//            while(resultSet.next()){
//            System.out.println(resultSet.getString("id")+" "+resultSet.getString("name"));
//            }

        } catch (Exception e) { 
            e.printStackTrace();
            return null;
        }
    }
}

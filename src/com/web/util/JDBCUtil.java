package com.web.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {

    public static Connection getCon() throws Exception{
        //加载数据库驱动
        String driver = "com.mysql.cj.jdbc.Driver";
        Class.forName(driver);
        String url = "jdbc:mysql://localhost:3306/student_information_statistics?characterEncoding=utf8&useSSL=false&serverTimezone=GMT";
        String user = "root";
        String password = "root";
        return DriverManager.getConnection(url, user, password);
    }

    //关闭数据库连接
    public static void getClose(Connection con) throws SQLException {
        if (con != null){
            con.close();
        }
    }
}

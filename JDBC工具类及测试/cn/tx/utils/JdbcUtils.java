package cn.tx.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * JDBC工具类1.0版本
 * JDBC工具类2.0版本(智能一些)，编写properties属性文件，程序可以读取属性文件
 */
public class JdbcUtils {
    /**
     * 1.驱动类
     * 2.数据库地址
     * 3.用户名
     * 4.密码
     */
    private static final String driverclass;
    private static final String url;
    private static final String username;
    private static final String password;
    /*
    静态代码块初始化常量
     */
    static {
        //加载属性文件，给常量赋值
        Properties pro = new Properties() ;
        InputStream stream = JdbcUtils.class.getResourceAsStream("db.properties");
        try {
            //加载属性文件
            pro.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //常量的赋值
        driverclass = pro.getProperty("driverclass");
        url = pro.getProperty("url");
        username = pro.getProperty("username");
        password = pro.getProperty("password");
    }

    /**
     * 加载驱动
     */
    public static void loadDriver(){
        try {
            //加载驱动类
            Class.forName(driverclass) ;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 加载完驱动，获取到链接，返回连接对象
     * @return
     */
    public static Connection getConnection(){
        //加载驱动
        loadDriver();
        //获取到链接对象。返回
        Connection conn = null;
        try {
            // 获取到链接
            conn = DriverManager.getConnection(url,username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn ;
    }

    /**
     * 关闭资源
     * @param conn
     * @param stmt
     * @param rs
     */
    public static void close(Connection conn, Statement stmt, ResultSet rs){
        try {
            if(rs != null) {
                rs.close();
            }
            if(stmt != null) {
                stmt.close();
            }
            if(conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * 关闭资源
     * @param conn
     * @param stmt
     */
    public static void close(Connection conn, Statement stmt){
        try {
            if(stmt != null) {
                stmt.close();
            }
            if(conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

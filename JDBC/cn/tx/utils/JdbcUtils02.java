package cn.tx.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * JDBC工具类1.0版本
 * JDBC工具类2.0版本(智能一些)，编写properties属性文件，程序可以读取属性文件
 * JDBC工具类3.0版本
 */
public class JdbcUtils02 {
    //连接池对象
    private static DataSource DATA_SOURCE;
    /*
    静态代码块初始化常量
     */
    static {
        //加载属性文件
        Properties pro = new Properties() ;
        InputStream stream = JdbcUtils02.class.getResourceAsStream("/driud.properties");
        try {
            //加载属性文件
            pro.load(stream);
            //创建连接池对象
            DATA_SOURCE = DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 从连接池中获取连接，返回
     * @return
     */
    public static Connection getConnection(){
        Connection conn = null ;
        try {
            // 获取到链接
            conn = DATA_SOURCE.getConnection();
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

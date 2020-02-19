package cn.tx.demo;

import com.mysql.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCDemo02 {

    public static void main(String[] args) {
        /*//测试增加数据
        new JDBCDemo02().testInsert();*/
        /*//测试修改
        new JDBCDemo02().testUpdate();*/
        //测试删除
        new JDBCDemo02().testdelete();
    }

    public void testInsert(){
        //数据库连接
        Connection conn = null;
        //执行sql语句对象
        Statement stmt = null;
        try {
            //注册驱动
            DriverManager.registerDriver(new Driver());
            //获取链接
            conn = DriverManager.getConnection("jdbc:mysql:///jdbcdemo","root","root");
            //获取statement对象
            stmt = conn.createStatement();
            //sql语句
            String sql = "insert into t_user values(null, 'ddd','111','12344@123.com')" ;
            //执行sql语句
            stmt.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //释放资源
            try {
                if (stmt != null){
                    stmt.close();
                }
                if(conn != null){
                    conn.close() ;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void testUpdate(){
        //数据库连接
        Connection conn = null;
        //执行sql语句对象
        Statement stmt = null;
        try {
            //注册驱动
            DriverManager.registerDriver(new Driver());
            //获取链接
            conn = DriverManager.getConnection("jdbc:mysql:///jdbcdemo","root","root");
            //获取statement对象
            stmt = conn.createStatement();
            //sql语句
            String sql = "update t_user set username = 'eee' where id = 4" ;
            //执行sql语句
            stmt.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //释放资源
            try {
                if (stmt != null){
                    stmt.close();
                }
                if(conn != null){
                    conn.close() ;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void testdelete(){
        //数据库连接
        Connection conn = null;
        //执行sql语句对象
        Statement stmt = null;
        try {
            //注册驱动
            DriverManager.registerDriver(new Driver());
            //获取链接
            conn = DriverManager.getConnection("jdbc:mysql:///jdbcdemo","root","root");
            //获取statement对象
            stmt = conn.createStatement();
            //sql语句
            String sql = "delete from t_user where id = 4" ;
            //执行sql语句
            stmt.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //释放资源
            try {
                if (stmt != null){
                    stmt.close();
                }
                if(conn != null){
                    conn.close() ;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}

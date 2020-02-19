package cn.tx.demo;

import com.alibaba.druid.pool.DruidDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 测试连接池对象
 */
public class JdbcDemo05 {
    public static void main(String[] args) {
        //创建连接池对象，从连接池中获取到连接对象
        DruidDataSource dataSource = new DruidDataSource();
        //设置四个参数， 驱动类 地址 用户名 密码
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql:///jdbcdemo");
        dataSource.setUsername("root");
        dataSource.setPassword("root");

        //设置初始化连接个数，默认是0
        dataSource.setInitialSize(5);
        //设置最大连接数
        dataSource.setMaxActive(10);
        //最大等待时间，单位是毫秒
        dataSource.setMaxWait(2000);

        //定义连接对象
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            //获取到连接对象
            conn = dataSource.getConnection();
            //编写sql语句
            String sql = "insert into t_user values(null,?,?,? )";
            //预编译sql语句
            stmt = conn.prepareStatement(sql);
            //设置值
            stmt.setString(1,"eeee");
            stmt.setString(2,"eeee");
            stmt.setString(3,"eeee");
            //执行SQL语句
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            // 释放资源
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(conn != null){
                try {
                    // 把conn关闭了，其实连接池的底层已经对close方法进行增强。原来是销毁连接，现在是归还连接。
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}

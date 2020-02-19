package cn.tx.demo;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 使用配置文件的方式测试连接池对象
 */
public class JdbcDemo06{
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            // 加载属性文件，使用工厂对象创建连接池对象
            Properties pro = new Properties();
            // 加载属性文件
            InputStream inputStream = JdbcDemo06.class.getResourceAsStream("druid.properties");
            pro.load(inputStream);
            // 创建连接池对象
            DataSource dataSource = DruidDataSourceFactory.createDataSource(pro);

            // 获取连接，预编译SQL语句
            conn = dataSource.getConnection();
            // 编写SQL语句
            String sql = "insert into t_user values (null,?,?,?)";
            // 预编译SQL语句
            stmt = conn.prepareStatement(sql);
            // 设置值
            stmt.setString(1,"fff");
            stmt.setString(2,"fff");
            stmt.setString(3,"fff");
            // 执行sql
            stmt.executeUpdate();
        } catch (Exception e) {
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

package cn.tx.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 测试工具类的使用
 */
public class UtilsTest {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            //加载驱动，获取链接
            conn = JdbcUtils.getConnection();
            //编写sql语句
            String sql = "insert into t_user values(null, 'qqqq','21414','2141141@2131.com')" ;
            //获取到执行sql语句的对象
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            JdbcUtils.close(conn, stmt);
        }

    }
}

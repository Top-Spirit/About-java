package cn.tx.demo;

import cn.tx.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * 演示事务的操作
 */

public class JdbcDemo04 {

    public static void main(String[] args) {
        Connection conn = null;
        // 预编译执行SQL语句对象
        PreparedStatement stmt = null;
        try {
            // 获取到连接，数据库连接比较稀有的，创建和销毁需要消耗时间
            conn = JdbcUtils.getConnection();

            // 开启事务，设置事务不自动提交，需要手动提交
            conn.setAutoCommit(false);

            // 使用?占位符 update t_account set money = money - 1000 where username = '冠希';
            String sql = "update t_account set money = money + ? where username = ?";
            // 预编译SQL语句，把SQL语句固定
            stmt = conn.prepareStatement(sql);

            // 需要给 ? 设置值
            stmt.setDouble(1,-1000);
            stmt.setString(2,"冠希");
            // 执行SQL语句，给冠希扣除1000元
            stmt.executeUpdate();
            // =============================================

            // 模拟异常
            int a = 10/0;

            // =============================================
            stmt.setDouble(1,1000);
            stmt.setString(2,"美美");
            // 执行SQL语句
            stmt.executeUpdate();

            // 提交事务
            conn.commit();

        } catch (SQLException e) {
            try {
                // 回滚事务
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            JdbcUtils.close(conn,stmt);
        }
    }
}

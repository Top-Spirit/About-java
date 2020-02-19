package cn.tx.demo;

import cn.tx.utils.JdbcUtils;

import java.sql.*;

/**
 * 演示SQL注入问题，漏洞
 * 在已知用户名的情况下，通过语言关键字，登陆系统。密码随意输入。
 * SQL注入产生的原因是SQL语句的拼接，利用SQL关键字产生效果。
 *
 * 解决SQL注入问题，采用SQL语句预编译的方式，把SQL语句中的参数使用？占位符来表示，先把
 * SQL语句预编译，格式固定的。再给？传入值，传入的内容都表示值。数据库会判断SQL执行的结果。
 *
 */

public class JdbcDemo03 {
    public static void main(String[] args) {
        /*
        // 模拟登录的功能，有SQL注入的问题
        String result = new JdbcTest4().login("aaa'or'1=1", "1234sdfsce");
        System.out.println(result);
        */
        String result = new JdbcDemo03().login2("aaa", "123");
        System.out.println(result);
    }


    /**
     * 采用预编译的方式，解决SQL注入的问题
     * @param username
     * @param password
     * @return
     */
    public String login2(String username,String password){
        Connection conn = null;
        // 预编译执行SQL语句对象
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            // 获取到连接
            conn = JdbcUtils.getConnection();
            // 使用?占位符
            String sql = "select * from t_user where username = ? and password = ?";
            // 预编译SQL语句，把SQL语句固定
            stmt = conn.prepareStatement(sql);
            // 需要给 ? 设置值
            stmt.setString(1,username);
            stmt.setString(2,password);
            // 执行SQL语句
            rs = stmt.executeQuery();
            // 遍历数据
            if(rs.next()){
                // 表示存在数据，如果存在，说明用户名和密码编写正确
                return "登录成功...";
            }else{
                return "登录失败了...";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close(conn,stmt,rs);
        }
        return null;
    }

    /**
     * 模拟登录的功能，通过用户名和密码从数据库中查询
     * @param username
     * @param password
     * @return
     */
    public String login(String username,String password){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            // 获取到连接
            conn = JdbcUtils.getConnection();
            // 编写SQL语句的拼接  '1=1' true  password = '1234sdfsce' false  true and false  整体上false
            // String sql = "select * from t_user where username = 'aaa' or '1=1' and password = '1234sdfsce'";
            // String sql = "select * from t_user where username = 'aaa' or false";
            String sql = "select * from t_user where username = '"+username+"' and password = '"+password+"'";
            // 执行sql
            stmt = conn.createStatement();
            // 执行
            rs = stmt.executeQuery(sql);
            // 遍历数据
            if(rs.next()){
                // 表示存在数据，如果存在，说明用户名和密码编写正确
                return "登录成功...";
            }else{
                return "登录失败了...";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close(conn,stmt,rs);
        }
        return null;
    }
}

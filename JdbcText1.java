package cn.tx;

import com.mysql.jdbc.Driver;
import java.sql.*;

/**
 * jdbc入门程序
 */

public class JdbcText1 {
    public static void main(String[] args) {
        // 数据库链接
        Connection conn = null ;
        // 能执行sql语句的对象
        Statement stmt = null ;
        //查询数据封装到结果集当中，表格的形式
        ResultSet rs = null;
        try {
            //加载驱动类
            DriverManager.registerDriver(new Driver());
            //获取链接：1数据库链接地址 2用户名 3密码
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdemo", "root", "root");
            //简写方式
//            Connection conn = DriverManager.getConnection("jdbc:mysql:///jdbcdemo", "root", "root")；
            //执行sql语句，编写sql语句
            String sql = "select * from t_user" ;
            //有能够执行sql语句的对像，statement对象
            stmt = conn.createStatement();
            //执行sql语句，得到结果
            rs = stmt.executeQuery(sql);
            //遍历结果集
            while(rs.next()){
                //得到对应的结果
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                //输出结果
                System.out.println(id + "-" + username+"-"+password+"-"+email);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //释放资源反着释放
            try {
                if(rs != null){
                    rs.close();
                }
                if (stmt != null){
                    stmt.close();
                }
                if (conn != null){
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

package behind.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbmanger {
    private static String jdbc="com.mysql.cj.jdbc.Driver";
    private static final String db_url="jdbc:mysql://localhost:3306/ishop?serverTimezone=GMT%2B8&amp&useSSL=false";
    private static final String users="root";
    private static final String pass="123456";
    static{
        try{
            Class.forName(jdbc);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConn() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(db_url, users, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}

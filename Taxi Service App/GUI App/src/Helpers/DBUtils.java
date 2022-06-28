package Helpers;

import java.sql.*;

public class DBUtils {
    public static Connection getDbConnection() throws SQLException {
        // jdbc:mysql://localhost:3306/college

        String connectionString = "jdbc:mysql://" + Config.dbHost + ":" + Config.dbPort + "/" + Config.dbName;

        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

        return DriverManager.getConnection("jdbc:mysql://localhost:3306/college?useTimezone=true&serverTimezone=UTC", "root", "");
    }
}

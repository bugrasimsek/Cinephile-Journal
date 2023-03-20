package MYSQLMethods;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public abstract class DatabaseManager {
    //used git ignore    

    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }

    public static Connection getConnection() throws SQLException {
        connection = DriverManager.getConnection(url, username, password);
        return connection;
    }

    
}

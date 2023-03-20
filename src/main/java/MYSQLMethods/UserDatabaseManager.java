package MYSQLMethods;

import LoginSignUp.User;
import System.SystemClass;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDatabaseManager extends DatabaseManager {

    public static int id;
    public static String name;
    public static String username;
    public static String password;

    public UserDatabaseManager() {
        url = "jdbc:mysql://localhost:3306/userdb";
    }

    public void addUserRow(User user) throws SQLException {
        String query = "INSERT INTO userdb.user_info"
                + "(username, name, surname, password) VALUES(?,?,?,?)";

        PreparedStatement addUserStatement = getConnection().prepareStatement(query);
        addUserStatement.setString(1, user.getUsername());
        addUserStatement.setString(2, user.getName());
        addUserStatement.setString(3, user.getSurname());
        addUserStatement.setString(4, user.getPassword());

        addUserStatement.execute();
        addUserStatement.close();
    }

    public boolean checkUsername(String username) throws SQLException {
        String query = "SELECT *\n"
                + "FROM user_info\n"
                + "WHERE username = (?)";

        PreparedStatement searchUserStatement = getConnection().prepareStatement(query);
        searchUserStatement.setString(1, username);
        ResultSet resultset = searchUserStatement.executeQuery();

        if (resultset.next()) {
            this.id = resultset.getInt(1);
            this.username = username;
            this.name = resultset.getString(3);
            this.password = resultset.getString(5);
            searchUserStatement.close();
            return true;
        } else {
            searchUserStatement.close();
            return false;
        }

    }

    public boolean checkPassword(String username, String password) throws SQLException {
        String query = "SELECT password\n"
                        + "FROM user_info\n"
                        + "WHERE username = (?)";

        PreparedStatement addUserStatement = getConnection().prepareStatement(query);
        addUserStatement.setString(1, username);
        ResultSet resultset = addUserStatement.executeQuery();
        if (resultset.next()) {

            if (resultset.getString(1).equals(password)) {
                return true;
            } else {
                return false;
            }

        }

        addUserStatement.close();

        return false;
    }
    

}

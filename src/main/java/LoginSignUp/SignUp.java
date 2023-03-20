package LoginSignUp;

import MYSQLMethods.UserDatabaseManager;
import System.SystemClass;
import java.sql.SQLException;

public class SignUp {

    public static boolean addUser(String name, String surname, String username, String password)
            throws SQLException {

        UserDatabaseManager userDatabase = new UserDatabaseManager();

        userDatabase.getConnection();

        Boolean userExists = userDatabase.checkUsername(username);

        if (userExists) {
            return false;
        } else if (!userExists) {
            User user = new User(name, surname, username, password);
            SystemClass.userList.add(user);
            

            userDatabase.addUserRow(user);

            return true;
        }

        userDatabase.disconnect();

        return false;
    }

}

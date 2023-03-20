
package LoginSignUp;



import GUI.Page3;
import GUI.Page4;
import GUI.Page7;
import GUI.Page8;
import MYSQLMethods.MovieDatabaseManager;
import MYSQLMethods.UserDatabaseManager;
import System.SystemClass;
import java.sql.SQLException;
import javax.swing.JLabel;


public class Login {

    public static boolean login(String username, String password) throws SQLException{
        UserDatabaseManager userDatabase = new UserDatabaseManager();
        
        userDatabase.getConnection();
        
        Boolean userExist = userDatabase.checkUsername(username);
        Boolean passwordCorrect = userDatabase.checkPassword(username, password);
        
        userDatabase.disconnect();
        
        if(userExist && passwordCorrect)
        {
            System.out.println("User with ID: " + UserDatabaseManager.id + " has logged in");
            SystemClass.userList.add(0, new User(UserDatabaseManager.name, "TempSurname", username, password));
          
            return true;
        }
           
        
        else    
            return false;
    }
    
    
}

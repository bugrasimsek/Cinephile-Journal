package LoginSignUp;

import MYSQLMethods.UserDatabaseManager;

public class User extends Person {
    String username;
    String password;

    public User(String name, String surname,
            String username, String password) {
        super(name, surname);
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return this.name + this.surname + this.username + this.password;
    }
    
    

}

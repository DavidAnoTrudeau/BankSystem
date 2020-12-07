package atm;

import java.util.ArrayList;
/**
 *
 * @author David A-T
 * 
 */
public class Bank {
    private static ArrayList<User> users = new ArrayList<>();
    
    public static void addUser(String userName, String password){
        users.add(new User(userName, password));
    }
    
    public static ArrayList<User> getUsers(){
        return users;
    }
}

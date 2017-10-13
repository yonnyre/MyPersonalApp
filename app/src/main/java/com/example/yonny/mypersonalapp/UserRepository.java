package com.example.yonny.mypersonalapp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yonny on 09/10/2017.
 */

public class UserRepository {

    public static List<User> users = new ArrayList<>();

    static{
        users.add(new User(100, "yonny1", "tecsup", "Yonny Rivera 1"));
        users.add(new User(200, "yonny2", "tecsup", "Yonny Rivera 2"));
        users.add(new User(300, "yonny3", "tecsup", "Yonny Rivera 3"));
    }

    public static User login(String username, String password){
        for (User user : users){
            if(user.getUsername().equalsIgnoreCase(username) && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }


    public static User getUser(String username){
        for (User user : users){
            if(user.getUsername().equalsIgnoreCase(username)){
                return user;
            }
        }
        return null;
    }

    public static User addUsuario(String username, String password){
        int id=100*(users.size()+1);
        User user = new User(id, username, password, username);
        users.add(user);
        return user;
    }


}

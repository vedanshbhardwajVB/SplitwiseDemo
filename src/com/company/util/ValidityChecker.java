package com.company.util;

import com.company.model.User;
import java.util.List;

public class ValidityChecker {

    public static boolean checkRegisterValidity(String email, List<User> existingUsers){
        boolean userValidToRegister = true;
            for(User u : existingUsers){
                if(u.getUserEmail().equals(email)){
                    userValidToRegister = false;
                    break;
                }
            }
        return userValidToRegister;
    }

    public static User checkLoginValidity (String email, String password,  List<User> existingUsers){
        //if(existingUsers.isEmpty()) return null;
            for(User u : existingUsers){
                if(u.getUserEmail().equals(email) && u.getPassword().equals(password)){
                    return  u;
                }
            }
        return null;
    }

}

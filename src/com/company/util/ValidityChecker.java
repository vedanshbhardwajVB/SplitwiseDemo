package com.company.util;

import com.company.model.User;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidityChecker {

    public static boolean checkRegisterValidity(String email, List<User> existingUsers){
        if(!checkEmailValidity(email)){
            System.out.println("Your email id looks wrong ! Make sure it is of the format -> \"username@domainname\"");
            System.exit(1212);
        }
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
        if(!checkEmailValidity(email)){
            System.out.println("Your email id looks wrong ! Make sure it is of the format -> \"username@domainname\"");
            System.exit(1212);
        }
            for(User u : existingUsers){
                if(u.getUserEmail().equals(email) && u.getPassword().equals(password)){
                    return  u;
                }
            }
        return null;
    }

    private static boolean checkEmailValidity (String email){
        String reg = "[\\w_\\.\\-]+[@][a-z]+[\\.][a-z]{2,3}";
        Pattern pt = Pattern.compile(reg);
        Matcher m = pt.matcher(email);
        return m.matches();
    }

}

package com.company.util;

import com.company.io.WriteData;
import com.company.model.User;

import java.util.List;
import java.util.Scanner;

public class RegisterUtil {
    public static User createUser (List<User> userList){
        User u=null;
        Scanner sc = new Scanner(System.in);
        System.out.println("\t Let's set you up ...");
        System.out.println("\t Enter your name");
        String name = sc.nextLine();
        System.out.println("\t Enter your email id ");    //TODO -> use REGEX to validate email id format (do via validity checker class)
        String email = sc.nextLine();

            boolean isValid = ValidityChecker.checkRegisterValidity(email, userList);
            if(isValid){
                System.out.println("\t Set up a password for yourself ");
                String password = sc.nextLine();
                System.out.println("\t Retype the password to confirm ");
                if(sc.nextLine().equals(password)){
                    System.out.println("Creating your account.....");
                    //everything is fine then add a new User to List of users and that's it.
                    u=new User(name, email, password);
                    userList.add(u);
                    System.out.println("\t Wohooo ! Excited to have you here !! ");
                }
                else{
                    System.out.println("The passwords don't match ! Try again.");
                }
            }
            else
                System.out.println("That email id is already registered. Try logging in.");
            return u;
    }
}

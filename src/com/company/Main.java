package com.company;

import com.company.io.LoadData;
import com.company.io.WriteData;
import com.company.model.User;
import com.company.util.ValidityChecker;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Scanner sc = new Scanner(System.in);
        List<User> userList = LoadData.loader();
        User currentUser=null;
        System.out.println("Welcome to My Splitwise ! \n Enter 0 -> Existing User \n Enter 1-> New User ");
        int choice = sc.nextInt();
        sc.nextLine();
        if (choice == 0) {
            System.out.println("\t Enter your email id ");
            String email = sc.nextLine();
            System.out.println("\t Enter your password ");
            String password = sc.nextLine();
            currentUser = ValidityChecker.checkLoginValidity(email, password, userList);
            if(currentUser == null){
                //problem
                System.out.println("Some problem occurred while logging you in. Check your credentials and try again !");
            }
            else {
                //good to go
                System.out.println(currentUser);
            }
        }
        else if (choice == 1){
            System.out.println("\t Let's set you up ...");
            System.out.println("\t Enter your name");
            String name = sc.nextLine();
            System.out.println("\t Enter your email id ");
            String email = sc.nextLine();
            boolean isValid = ValidityChecker.checkRegisterValidity(email, userList);
            if(isValid){
                System.out.println("\t Set up a password for yourself ");
                String password = sc.nextLine();
                System.out.println("\t Retype the password to confirm ");
                if(sc.nextLine().equals(password)){
                    System.out.println("Creating your account.....");
                    currentUser = WriteData.writer(userList, name, email, password);
                    System.out.println("\t Wohooo ! Excited to have you here , now try logging in. \n Thankyou ");
                }
                else{
                    System.out.println("The passwords don't match ! Try again.");
                    System.exit(0);
                }

            }
            else{
                System.out.println("That email id is already registered. Try logging in.");
            }
        }
        else {
            System.out.println("Oops ! You made a wrong choice, Try Again Later");
        }

        if(currentUser == null) System.exit(0);

        System.out.println("Welcome "+currentUser.getUserName());
        System.out.println("What would you like to do ?");
        System.out.println("Enter 1 for ");
    }
}

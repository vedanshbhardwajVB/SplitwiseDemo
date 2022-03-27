package com.company;

import com.company.io.LoadData;
import com.company.io.WriteData;
import com.company.model.User;
import com.company.util.ExpenseUtil;
import com.company.util.FriendsUtil;
import com.company.util.ValidityChecker;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Scanner sc = new Scanner(System.in);
        List<User> userList = LoadData.userLoader();
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
                System.out.println("Some problem occurred while logging you in. \n" +
                        "Either you entered a wrong password, or you're not yet registered. \n" +
                        "Check your credentials and try again !");
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
            System.out.println("\t Enter your email id ");    //TODO -> use REGEX to validate email id format (do via validitychecker class)
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
        System.out.println("1 -> See your friends \n" +
                            "2 -> Add friends \n" +
                            "3 -> Add Expense \n" +
                            "4 -> See your expenses ");
        int menuChoice = sc.nextInt();
        sc.nextLine();
        // need to load the current expenseList from JSON first
        List<User.Expense> expenseList = LoadData.expenseLoader();

        switch (menuChoice) {
            case 1 :
                List<User> friends = FriendsUtil.getFriendsOf(currentUser, userList);
                FriendsUtil.printFriends(friends);
                break;
            case 2 :
                if(userList.size() == 1)
                    System.out.println("There are no users except you here. Sorry !");
                else{
                    // no need to load as friends are already loaded within userList at the start of main function
                    FriendsUtil.createFriendsFor(currentUser, userList);
                    WriteData.updateExistingUser(userList);
                }
                break;

            case 3 :


                //if(expenseList.get(0).) System.out.println("Load hokr empty aa rhi");
                //for(User.Expense e : expenseList)
                  //  System.out.println(e);
                if(expenseList == null)
                    expenseList=new ArrayList<>();
                ExpenseUtil.createExpense(currentUser, expenseList, userList);
                System.out.println("Debiugger main ()-> size of this expenseList is"+expenseList.size());
                WriteData.expenseWriter(expenseList);
                System.out.println("Expense added from main too!!");
                break;

            case 4 :
                for(User.Expense ue : expenseList){
                    if(currentUser.getUserId().equals(ue.getMadeBy())){
                        System.out.println(ue);
                    }
                }
                break;
            default:
                System.out.println("\"Oops ! You made a wrong choice, Try Again Later");
        }
    }
}

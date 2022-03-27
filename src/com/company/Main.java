package com.company;

import com.company.io.LoadData;
import com.company.io.WriteData;
import com.company.model.User;
import com.company.util.ExpenseUtil;
import com.company.util.FriendsUtil;
import com.company.util.RegisterUtil;
import com.company.util.ValidityChecker;
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
                System.exit(1212);
            }
            else {
                //good to go
            }
        }
        else if (choice == 1){
            currentUser=RegisterUtil.createUser(userList);
            if(currentUser!=null)
            WriteData.userWriter(userList);
        }
        else {
            System.out.println("Oops ! You made a wrong choice, Try Again Later");
        }

        if(currentUser == null) System.exit(0);

        System.out.println("Welcome "+currentUser.getUserName());
        System.out.println("What would you like to do ?");
        System.out.println("1 -> See your friends \n" + "2 -> Add friends \n" + "3 -> Add Expense \n" + "4 -> See your expenses ");
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
                    WriteData.userWriter(userList);
                }
                break;

            case 3 :
                ExpenseUtil.createExpense(currentUser, expenseList, userList);
                WriteData.expenseWriter(expenseList);
                break;

            case 4 :
                List<User.Expense> currentUserExpenses = ExpenseUtil.getExpenseOf(currentUser, expenseList);
                ExpenseUtil.printExpense(currentUserExpenses);
                break;
            default:
                System.out.println("\"Oops ! You made a wrong choice, Try Again Later");
        }
    }
}
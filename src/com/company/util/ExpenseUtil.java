package com.company.util;

import com.company.model.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class ExpenseUtil {
    public static void createExpense(User currentUser, List<User.Expense> expenseList, List<User> userList) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hey "+currentUser.getUserName()+" what is this expense for ?");
        String expenseTitle = sc.nextLine();
        System.out.println("What was the cost of the expense you paid for ?");
        double cost = sc.nextDouble();
        sc.nextLine();
        System.out.println("Fetching your friends list ...........");
        List<User> friendsList = FriendsUtil.getFriendsOf(currentUser, userList);
        FriendsUtil.printFriends(friendsList);

        if(friendsList.size() == 0)
            System.out.println(" Try adding some friends first !");
        else{
            System.out.println("Choose friends from your above friends list to share the expense --->");
            System.out.println("How many friends would you like to share it with ?");
            int frndCountForExpense=sc.nextInt();
            sc.nextLine();
            //double perHeadShare=cost/(frndCountForExpense + 1);
            List<UUID> paidFor = new ArrayList<>(frndCountForExpense);
            while(frndCountForExpense > 0){
                System.out.println("Enter the index of user from above list..");
                int indexToAdd = sc.nextInt();
                sc.nextLine();
                if(paidFor.contains(friendsList.get(indexToAdd).getUserId())){
                    System.out.println("You've aleready added "+friendsList.get(indexToAdd).getUserName()+" to this expense.");
                }
                else{
                    System.out.println("Adding user with index number "+indexToAdd+" as indebted to this expense "+indexToAdd);
                    paidFor.add(friendsList.get(indexToAdd).getUserId()); // adding id of the selected friend user from friendList
                }

                frndCountForExpense--;
            }
            User.Expense newExpense = currentUser.new Expense(expenseTitle, cost, paidFor);
            expenseList.add(newExpense);
        }
    }

    public static List<User.Expense> getExpenseOf (User user, List<User.Expense> expenseList){

        List<User.Expense> currentUserExpenseList = new ArrayList<>();
        for(User.Expense ue : expenseList){
            if(ue.getMadeBy().equals(user.getUserId()))
                currentUserExpenseList.add(ue);
        }
        return currentUserExpenseList;
    }

    public static void printExpense (List<User.Expense> expenseList){
        int expenseSize = expenseList.size();
        if (expenseSize == 0)
            System.out.println("You have not spent any money on anyone !");
        else {
            for (int i = 0; i < expenseSize; i++)
                System.out.println("-->"+ i + "\t" + expenseList.get(i));
        }
    }
}
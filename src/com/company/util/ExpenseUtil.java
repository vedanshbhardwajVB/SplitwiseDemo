package com.company.util;

import com.company.model.User;

import java.util.*;

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
            //double perHeadShare=cost/(frndCountForExpense + 1);
            List<UUID> paidFor = new ArrayList<>();
            while(true){
                System.out.println("Enter the index of your friends from above list.... \n OR Enter a negative value to stop adding friends to this expense.");
                int indexToAdd = sc.nextInt();
                sc.nextLine();
                if(indexToAdd < 0) break;
                if(paidFor.contains(friendsList.get(indexToAdd).getUserId())){
                    System.out.println("You've already added "+friendsList.get(indexToAdd).getUserName()+" to this expense.");
                }
                else{
                    System.out.println("Adding user with index number "+indexToAdd+" as indebted to this expense ");
                    paidFor.add(friendsList.get(indexToAdd).getUserId()); // adding id of the selected friend user from friendList
                }
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

    public static HashMap<String , Double> getExpenseFriendwise(List<User.Expense> currentUserExpenseList, List<User> currentUserFriendsList){

        HashMap<UUID, Double> idMap = new HashMap<>();
        for(User friend : currentUserFriendsList){
            idMap.put(friend.getUserId(), 0.0);
        }

        for(User.Expense e : currentUserExpenseList){
            double perHeadCost = e.getCost()/(e.getPaidFor().size()+1) ;
            for(UUID id : e.getPaidFor()){
                double debtSoFar = idMap.get(id);
                idMap.put(id, debtSoFar+perHeadCost);
            }
        }

        HashMap<String , Double> nameMap = new HashMap<>();
        for(Map.Entry<UUID, Double> e : idMap.entrySet()){
            UUID currID = e.getKey();
            for(User friend : currentUserFriendsList){
                if(friend.getUserId().equals(currID)){
                    nameMap.put(friend.getUserName(), e.getValue());
                }
            }
        }
        return nameMap;
    }

    public static void printExpenseFriendwise(HashMap<String, Double> map){
        System.out.println("This is the total sum of money you paid for your friends ---------->");
        for(Map.Entry<String, Double> e : map.entrySet()){
            System.out.println(e.getKey()+" -> "+e.getValue());
        }

    }
}
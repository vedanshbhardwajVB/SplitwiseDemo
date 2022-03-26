package com.company;

import com.company.io.LoadData;
import com.company.io.WriteData;
import com.company.model.User;
import com.company.util.ValidityChecker;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

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

        switch (menuChoice) {
            case 1 :
                printFriendsList(currentUser, userList);
                break;
            case 2 :
                if(userList.size() == 1)
                    System.out.println("There are no users except you here. Sorry !");
                else{
                    int userToBeFriendsCount=0;
                    for(int i=0; i<userList.size(); i++){
                        if(currentUser.equals(userList.get(i)) || currentUser.getFriends().contains(userList.get(i).getUserId()) )
                            continue;
                        System.out.println(i+" "+userList.get(i));
                        userToBeFriendsCount++;
                    }
                    if(userToBeFriendsCount == 0){
                        System.out.println("All users are already your friends.");
                    }
                    else{
                        System.out.println("Choose users from the above list to add to your friend list --->");
                        System.out.println("How many friends would you like to add ?");
                        int frndCount=sc.nextInt();
                        sc.nextLine();
                        while(frndCount > 0){
                            System.out.println("Enter the index of user from above list..");
                            int indexToAdd = sc.nextInt();
                            sc.nextLine();
                            System.out.println("Adding user with index number "+indexToAdd+" as your friend "+indexToAdd);
                            if(  ! currentUser.getFriends().contains(userList.get(indexToAdd).getUserId())){   // add user only if not already added to friends of currentUSer
                                currentUser.getFriends().add(userList.get(indexToAdd).getUserId());
                                userList.get(indexToAdd).getFriends().add(currentUser.getUserId());
                            }
                            else{
                                System.out.println(userList.get(indexToAdd).getUserName()+" is already your friend.");
                            }

                            frndCount--;
                        }
                        System.out.println("reached here ");
                        WriteData.updateFile(userList);
                    }
                }
                break;

            case 3 :
                break;
            case 4 :
                break;
            default:
                System.out.println("\"Oops ! You made a wrong choice, Try Again Later");
        }
    }

    public static void printFriendsList(User currentUser, List<User> userList) {

        List<UUID> currentUserFriends = currentUser.getFriends();
        int friendsSize = currentUserFriends.size();
        if (friendsSize == 0)
            System.out.println("You don't have any friends right now !");
        else {
            for (int i = 0; i < friendsSize; i++) {
                UUID currentFriendID = currentUserFriends.get(i);
                for (User thisUser : userList) {
                    if (thisUser.getUserId().equals(currentFriendID))
                        System.out.println(i + "->" + thisUser);
                }

            }
        }
    }
}

package com.company.util;

import com.company.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class FriendsUtil {
    public static void createFriendsFor(User currentUser, List<User> userList){

        List<User> friendsList = getFriendsOf(currentUser, userList);
        if(friendsList.size() == userList.size()-1){
            System.out.println("All users are already your friends.");
        }
        else{
            for(int i=0; i<userList.size(); i++){
                User u=userList.get(i);
                if(friendsList.contains(u) || u.equals(currentUser)) continue;
                System.out.println(i+" "+userList.get(i));
            }
            System.out.println("-------------------These are the available users yo can add to your friend list-------------------");
            System.out.println();
            Scanner sc = new Scanner(System.in);
            while(true){
                System.out.println("Enter the index of user from above list...... \n OR Enter a negative value to stop adding friends ");
                int indexToAdd = sc.nextInt();
                sc.nextLine();
                if(indexToAdd < 0) break;
                if(currentUser.equals(userList.get(indexToAdd)))
                    System.out.println("You can't add yourself as your friend. Check the index you typed !");
                else{
                    if( ! currentUser.getFriends().contains(userList.get(indexToAdd).getUserId())){   // add user only if not already added to friends of currentUSer
                        System.out.println("Adding "+userList.get(indexToAdd).getUserName()+" with index number "+indexToAdd+" as your friend "+indexToAdd);
                        currentUser.getFriends().add(userList.get(indexToAdd).getUserId());
                        userList.get(indexToAdd).getFriends().add(currentUser.getUserId());
                    }
                    else{
                        System.out.println(userList.get(indexToAdd).getUserName()+" is already your friend.");
                    }
                }
            }
            System.out.println("Great job adding friends !!");
        }
    }

    public static List<User> getFriendsOf (User currentUser, List<User> userList){
        //returns list of users which are friends of the current user, or new empty list otherwise
        List<User> friendsList = new ArrayList<>();
        List<UUID> currentUserFriends = currentUser.getFriends();
        int friendsSize = currentUserFriends.size();
        if (friendsSize == 0)
            return friendsList;

            for (int i = 0; i < friendsSize; i++) {
                UUID currentFriendID = currentUserFriends.get(i);
                for (User thisUser : userList) {
                    if (thisUser.getUserId().equals(currentFriendID)){
                        friendsList.add(thisUser);
                    }
                }
            }
        return friendsList;
    }

    public static void printFriends(List<User> friendsList){
        int friendsSize = friendsList.size();
        if (friendsSize == 0)
            System.out.println("You don't have any friends right now !");
        else {
            for (int i = 0; i < friendsSize; i++)
                        System.out.println("-->  "+i+ "\t" + friendsList.get(i));
        }
    }
}

package com.company.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    int userId;
    String userName;
    String userEmail;
    String password;
    List<User> friends;
    static int noOfUser=0;

    public User(String userName, String userEmail, String password) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.password = password;
        this.friends= new ArrayList<>();
        this.userId=noOfUser++;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getPassword() {
        return password;
    }

    public List<User> getFriends() {
        return friends;
    }

    @Override
    public String toString() {
        return (this.userName+"->"+this
                .userEmail);
    }
}

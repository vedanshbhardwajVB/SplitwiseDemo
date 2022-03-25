package com.company;

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
}

package com.company.io;

import com.company.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WriteData {
    public static User writer(List<User> usersList, String userName, String userEmail, String password){
        if(usersList == null)
            usersList = new ArrayList<>();
        User u = new User(userName, userEmail, password);
        usersList.add(u);
        try{
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            FileWriter writer = new FileWriter("entriesB.json");
            gson.toJson(usersList, writer);
            writer.close();
        }catch(IOException e) {
            e.printStackTrace();
            System.out.println("Some technical error occurred, could not create your account.");
            System.exit(0);
            u = null;
            return u;
        }
    return u;
    }
}

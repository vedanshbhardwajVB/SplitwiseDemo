package com.company.io;

import com.company.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WriteData {
    public static void userWriter(List<User> usersList){
        try{
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            FileWriter writer = new FileWriter("User Entries.json");
            gson.toJson(usersList, writer);
            writer.close();
        }catch(IOException e) {
            e.printStackTrace();
            System.out.println("Some technical error occurred, could not create your account.");
            System.exit(0);
        }
    }

    public static void expenseWriter(List<User.Expense> expenseList){
        try{
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            FileWriter writer = new FileWriter("User Expenses.json");
            gson.toJson(expenseList, writer);
            writer.close();
        }catch(IOException e) {
            e.printStackTrace();
            System.out.println("Some technical error occurred, could not create your account.");
            System.exit(0);
        }
    }
}

package com.company.io;

import com.company.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class LoadData {
    public static List<User> userLoader() {
        List<User> userList = new ArrayList<>();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (Reader reader = new FileReader("entriesB.json")) {

            // Convert JSON File to Java Object
            Type userListType = new TypeToken<ArrayList<User>>(){}.getType();
            userList =  gson.fromJson(reader, userListType);

            // print staff
            System.out.println(userList);

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("inside exception of userloader ");
            return userList;
        }

        return userList;
    }

    public static List<User.Expense> expenseLoader () {
        List<User.Expense> expenseList = new ArrayList<>();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (Reader reader = new FileReader("expensesB.json")) {

            // Convert JSON File to Java Object
            Type userListType = new TypeToken<ArrayList<User.Expense>>(){}.getType();
            expenseList =  gson.fromJson(reader, userListType);

            // print staff
            //System.out.println(userList);

        } catch (IOException e) {
            //e.printStackTrace();
            return expenseList;
        }

        return expenseList;
    }
}

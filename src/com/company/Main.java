package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to My Splitwise ! \\n Enter 0 -> Existing User \\n Enter 1-> New User ");
        int choice = sc.nextInt();
        if (choice == 0) {

        }
        else if (choice == 1){

        }
        else {
            System.out.println("Oops ! You made a wrong choice, Try Again Later");
        }


    }
}

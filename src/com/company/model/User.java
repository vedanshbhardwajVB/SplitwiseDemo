package com.company.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {
    private UUID userId;
    private String userName;
    private String userEmail;
    private String password;
    private List<UUID> friends;

    public User(String userName, String userEmail, String password) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.password = password;
        this.friends= new ArrayList<>();
        this.userId=UUID.randomUUID();
    }

    public UUID getUserId() {
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

    public List<UUID> getFriends() {
        return friends;
    }

    @Override
    public String toString() {
        return (this.userName+"\t"+this.userEmail);
    }
    
    public class Expense {
        UUID madeBy ;
        String title;
        Double cost;
        List<UUID> paidFor;
        LocalDateTime timeCreated;


        public Expense(String title, Double cost, List<UUID> paidFor) {
            this.title = title;
            this.cost = cost;
            this.paidFor = paidFor;
            timeCreated=LocalDateTime.now();
            this.madeBy = getUserId();
        }

        public String getTitle() {
            return title;
        }

        public UUID getMadeBy() {
            return madeBy;
        }

        public Double getCost() {
            return cost;
        }

        public List<UUID> getPaidFor() {
            return paidFor;
        }

        public LocalDateTime getTimeCreated() {
            return timeCreated;
        }

        @Override
        public String toString() {
            return (getTitle()+"\t"+getCost()+"    paid on "+timeCreated.toString()+" for "+paidFor.size()+" people.");
        }
    }
}

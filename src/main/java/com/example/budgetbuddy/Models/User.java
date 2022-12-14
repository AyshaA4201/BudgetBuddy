package com.example.budgetbuddy.Models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.*;

@Getter
@Setter
@ToString
public class User {

    public String username;
    public int accountId;
    public List<Account> accounts = new ArrayList<>();
    int userId;
    static int totalBalance;

    public User(String username){
        Random r = new Random();
        accountId = r.nextInt(99999-10000)+10000;
        this.username = username;
    }

    public Account getAccount(Integer accountId){
        for(Account a : accounts){
            if(a.getAccountId() == accountId){
                return a;
            }
        }
        return null;
    }

    public void deleteBankAccount(Integer accountId){
        for(Account a: accounts){
            if(a.getAccountId()==accountId){
                accounts.remove(a);
            }
        }
    }

    public int getTotalBalance(){
        totalBalance=0;
        for (Account a : accounts){
            totalBalance+=a.getBalance();
        }
        return totalBalance;
    }

    public void addAccount(Account account){
        accounts.add(account);
    }

}

package com.example.budgetbuddy.Models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
@Setter
@ToString
public class Account {

    int initialBalance;
    int balance;
    int accountId;
    List<Transaction> transactions = new ArrayList<>();

    public Account (int balance){
        this.balance = balance;
        this.initialBalance=balance;
        Random r = new Random();
        this.accountId = r.nextInt(9999-1000)+1000;
    }

    public void makeTransaction(Transaction transaction){
        balance -= transaction.getAmount();
        transactions.add(transaction);
    }

//    public String listAllTransactions(int accountId){
//        String text="You account number has the following transactions: \n";
//        for(Transaction transaction : transactions){
//            text += "The transaction with the transaction number "+
//                    transaction.getTransactionId()+ " was: " +
//                    transaction.getAmount() +"\nAnd the relevance tag is " +
//                    transaction.getRelevance() + "\n\n";
//        }
//        return text;
//    }
}

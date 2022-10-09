package com.example.budgetbuddy.Models;

import com.example.budgetbuddy.DTO.TransferDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Random;

@Getter
@Setter
@ToString
public class Transaction {
    int accountId;
    int transactionId;
    int amount;
    int relevance;

    public Transaction(int accountId, int amount, int relevance) {
        this.accountId = accountId;
        this.amount = amount;
        this.relevance = relevance;
    }

    public Transaction(TransferDTO transferDTO){
        accountId = transferDTO.getAccountNumber();
        amount = transferDTO.getMoneyTransferred();
        relevance = transferDTO.getRelevanceNumber();
        Random r = new Random();
        transactionId = r.nextInt(9999-1000)+1000;
    }
}

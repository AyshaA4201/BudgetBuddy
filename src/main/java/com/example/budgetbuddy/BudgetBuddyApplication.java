package com.example.budgetbuddy;

import com.example.budgetbuddy.DTO.TransferDTO;
import com.example.budgetbuddy.Data.Users;
import com.example.budgetbuddy.Models.Account;
import com.example.budgetbuddy.Models.Transaction;
import com.example.budgetbuddy.Models.User;
import com.example.budgetbuddy.Services.TransactionServices;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BudgetBuddyApplication {
    public static void main(String[] args) {
        CommandLineRunner();
        SpringApplication.run(BudgetBuddyApplication.class, args);
    }


    public static void CommandLineRunner(){
        Users users = new Users();
        User aysha = new User("aysha");
        TransactionServices transactionServices = new TransactionServices();
        Account account = new Account(500);
        Account account2 = new Account(500);
        aysha.addAccount(account);
        aysha.addAccount(account2);
        users.saveUser(aysha);
        TransferDTO transferDTO = new TransferDTO("aysha",20, account.getAccountId(),1);
        TransferDTO transferDTO1 = new TransferDTO("aysha",20, account.getAccountId(),2);
        TransferDTO transferDTO2 = new TransferDTO("aysha",20, account.getAccountId(),3);
        TransferDTO transferDTO3 = new TransferDTO("aysha",20, account.getAccountId(),4);
        transactionServices.makeTransfer(transferDTO);
        transactionServices.makeTransfer(transferDTO1);
        transactionServices.makeTransfer(transferDTO2);
        transactionServices.makeTransfer(transferDTO3);
    }
}

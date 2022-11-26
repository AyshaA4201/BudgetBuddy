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
        SpringApplication.run(BudgetBuddyApplication.class, args);
    }

    @Bean
    public void CommandLineRunner(){
        Users users = new Users();
        User aysha = new User("aysha");
        TransactionServices transactionServices = new TransactionServices();
        Account account = new Account(500);
        Account account2 = new Account(500);
        aysha.addAccount(account);
        aysha.addAccount(account2);
        users.saveUser(aysha);
        TransferDTO transferDTO = new TransferDTO("aysha",20, account.getAccountId(),1);
        transactionServices.makeTransfer(transferDTO);
    }
}

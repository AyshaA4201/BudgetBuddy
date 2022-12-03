package com.example.budgetbuddy.Services;

import com.example.budgetbuddy.DTO.TransferDTO;
import com.example.budgetbuddy.DTO.UserDTO;
import com.example.budgetbuddy.Data.Users;
import com.example.budgetbuddy.Models.Account;
import com.example.budgetbuddy.Models.Transaction;
import com.example.budgetbuddy.Models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServices {
    Users users = new Users();

    public String createUser(UserDTO userDTO){
        User user = new User(userDTO.getUsername());
        Account account = new Account(userDTO.getBalance());
        user.addAccount(account);
        users.saveUser(user);
        return "hi " + user.getUsername()+".\nYour new account number is: "+
                account.getAccountId() +".\nAnd your balance is: "
                + account.getBalance();
    }

    public String createAccount(UserDTO userDTO){
        User user = users.getUserByUsername(userDTO.getUsername());
        Account account = new Account(userDTO.getBalance());
        user.addAccount(account);
        return "New account created.\nThe account ID is: "+ account.getAccountId() +
                "\nAnd the account balance is: " + account.getBalance();
    }

    public String listAllMyAccounts(String username){
        User user = users.getUserByUsername(username);
        List<Account> accounts = user.getAccounts();
        String text="The following are your accounts:\n";
        for(Account a : accounts){
            text += "\tYou account with the number " + a.getAccountId() + " has a balance of " +
                    a.getBalance() + "\n";
        }
        return text;
    }

    public User getUser(String username){
        return users.getUserByUsername(username);
    }
}

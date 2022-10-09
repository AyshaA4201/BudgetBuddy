package com.example.budgetbuddy.Services;

import com.example.budgetbuddy.DTO.TransferDTO;
import com.example.budgetbuddy.DTO.UserDTO;
import com.example.budgetbuddy.Data.Users;
import com.example.budgetbuddy.Models.Account;
import com.example.budgetbuddy.Models.Transaction;
import com.example.budgetbuddy.Models.User;
import org.springframework.stereotype.Service;

@Service
public class TransactionServices {
    Users users = new Users();

    public String makeTransfer(TransferDTO transferDTO){
        User user = users.getUserByUsername(transferDTO.getUsername());
        Account account = user.getAccount(transferDTO.getAccountNumber());
        Transaction transaction = new Transaction(transferDTO);
        account.makeTransaction(transaction);
        return "You have made a transaction of " + transaction.getAmount() + " from your account "
                + transaction.getAccountId() +".\nYour new balance in that account is: "+
                account.getBalance();
    }

    public String getTransactionHistory(UserDTO userDTO){
        Account account = users.getUserByUsername(userDTO.getUsername()).getAccount(userDTO.getAccountId());
        String text="You account number has the following transactions: \n\n";
        for(Transaction transaction : account.getTransactions()){
            text += "The transaction with the transaction number "+
                    transaction.getTransactionId()+ " was: " +
                    transaction.getAmount() +"\n\tThe relevance tag is " +
                    transaction.getRelevance() + "\n";
        }
        return text;
    }
}

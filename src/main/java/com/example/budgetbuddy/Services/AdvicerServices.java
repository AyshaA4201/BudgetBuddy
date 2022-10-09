package com.example.budgetbuddy.Services;

import com.example.budgetbuddy.DTO.UserDTO;
import com.example.budgetbuddy.Data.Users;
import com.example.budgetbuddy.Models.Account;
import com.example.budgetbuddy.Models.Transaction;
import com.example.budgetbuddy.Models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class AdvicerServices {
    Users users = new Users();

    public String getAdvice(UserDTO userDTO){
        User user = users.getUserByUsername(userDTO.getUsername());
        String message="";

        for (Account a : user.getAccounts()){
            List<Integer> list = getUserAccountBalance(a.getTransactions());
            message += "This is your review for account "+ a.getAccountId()+ " with the initial balance of " +
                    a.getInitialBalance() + ":\n";
            for(int i=0;i<4;i++){
                message += "\tYou've spent " + list.get(i) +" on";
                switch(i){
                    case(0):
                        message += " vital expenses. You should be spending " + (a.getInitialBalance()*.6) +
                                ", so you've spent  " + getAdviceMessage((a.getInitialBalance())*.6 - list.get(i), a);
                        break;
                    case(1):
                        message += " important expenses. You should be spending " + (a.getInitialBalance()*.2) +
                                ", so you've spent  " + getAdviceMessage((a.getInitialBalance())*.2 - list.get(i), a);
                        break;
                    case(2):
                        message += " wanted expenses. You should be spending " + (a.getInitialBalance()*.15) +
                                ", so you've spent  " + getAdviceMessage((a.getInitialBalance())*.15 - list.get(i), a);
                        break;
                    case(3):
                        message += " unneeded expenses. You should be spending " + (a.getInitialBalance()*.05) +
                                ", so you've spent  " + getAdviceMessage((a.getInitialBalance())*.05 - list.get(i), a);
                        break;
                }
            }
            message+="\n";
        }
        return message;
    }

    public String getAdviceMessage(Double balance,Account account){
        String message = "";
        if(balance>0){
            message+=balance + " less dollars than what you should be spending. Keep it up!\n";
        }else{
            balance*=-1;
            message+=balance +" more dollars than what you should be spending. " +getRecommendationMessage(account);
        }
        return message;
    }

    public List<Integer> getUserAccountBalance(List<Transaction> transactions){
        List<Integer> list = new ArrayList<>(Collections.nCopies(4,0));
        for(Transaction t : transactions){
            switch(t.getRelevance()) {
                case (1):
                    list.set(0,list.get(0)+t.getAmount());
                    break;
                case (2):
                    list.set(1,list.get(1)+t.getAmount());
                    break;
                case (3):
                    list.set(2,list.get(2)+t.getAmount());
                    break;
                case (4):
                    list.set(3,list.get(3)+t.getAmount());
                    break;
            }
        }
        return list;
    }

    public String getRecommendationMessage(Account account){
        String message = "We recommend cutting your largest transaction in this account, which was of ";
        int index=0;
        Transaction largestTransaction =account.getTransactions().get(index);
        for(Transaction t :account.getTransactions()){
            if(t.getAmount()>largestTransaction.getAmount()){
                largestTransaction = t;
                index++;
            }
        }
        message += largestTransaction.getAmount()+ " dollars and has the id of " +
                largestTransaction.getTransactionId();
        return message;
    }
}

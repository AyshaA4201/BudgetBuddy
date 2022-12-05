package com.example.budgetbuddy.DTO;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TransferDTO {
    String username;
    int moneyTransferred;
    int accountNumber;
    int relevanceNumber;

    public TransferDTO() {
    }

    public TransferDTO(String username, int moneyTransferred, int accountNumber, int relevanceNumber) {
        this.username = username;
        this.moneyTransferred = moneyTransferred;
        this.accountNumber = accountNumber;
        this.relevanceNumber = relevanceNumber;
    }
}

package com.example.budgetbuddy.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDTO {
    String username;
    int balance;
    int accountId;
}

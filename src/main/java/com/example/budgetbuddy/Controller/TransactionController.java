package com.example.budgetbuddy.Controller;

import com.example.budgetbuddy.DTO.TransferDTO;
import com.example.budgetbuddy.DTO.UserDTO;
import com.example.budgetbuddy.Services.TransactionServices;
import com.example.budgetbuddy.Services.UserServices;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="transactions")
public class TransactionController {
    TransactionServices transactionServices = new TransactionServices();

    @GetMapping(path="viewAll", consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> viewAllTransactions(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(transactionServices.getTransactionHistory(userDTO));
    }

    @PostMapping(path="makeTransaction",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> makeTransaction(@RequestBody TransferDTO transferDTO){
        return ResponseEntity.ok(transactionServices.makeTransfer(transferDTO));
    }
}

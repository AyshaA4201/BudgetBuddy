package com.example.budgetbuddy.Controller;

import com.example.budgetbuddy.DTO.TransferDTO;
import com.example.budgetbuddy.DTO.UserDTO;
import com.example.budgetbuddy.Models.User;
import com.example.budgetbuddy.Services.TransactionServices;
import com.example.budgetbuddy.Services.UserServices;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping()
public class TransactionController {
    TransactionServices transactionServices = new TransactionServices();
    UserServices userServices = new UserServices();

    @GetMapping(path="viewAll", consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> viewAllTransactions(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(transactionServices.getTransactionHistory(userDTO));
    }

    @PostMapping(path="makeTransaction",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> makeTransaction(@RequestBody TransferDTO transferDTO){
        return ResponseEntity.ok(transactionServices.makeTransfer(transferDTO));
    }

    @GetMapping("ConcurrentPayments")
    public String userTransactions(Model model){
        TransferDTO userDTO = new TransferDTO();
        model.addAttribute("user", userDTO);
        return "ConcurrentPayments";
    }

    @PostMapping("ConcurrentPayments")
    public String userTransaction(@ModelAttribute TransferDTO transferDTO, Model model){
        User aysha = userServices.getUser("aysha");
        transferDTO.setUsername(aysha.getUsername());
        transactionServices.makeTransfer(transferDTO);
        model.addAttribute("user", transferDTO);
        return "AmountTransferred";
    }
}

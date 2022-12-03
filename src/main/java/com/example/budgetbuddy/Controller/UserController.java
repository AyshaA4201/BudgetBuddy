package com.example.budgetbuddy.Controller;

import com.example.budgetbuddy.DTO.UserDTO;
import com.example.budgetbuddy.Models.User;
import com.example.budgetbuddy.Services.UserServices;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RequestMapping()
@Controller
public class UserController {
    UserServices userServices = new UserServices();

    @GetMapping("Home")
    public String home(Model model){
        User aysha = userServices.getUser("aysha");
        model.addAttribute("user", aysha);
        return "home";
    }

    @PostMapping(path="createUser",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@RequestBody UserDTO user){
        return ResponseEntity.ok(userServices.createUser(user));
    }

    @PostMapping(path="addBankAccount", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createAccount(@RequestBody UserDTO user) {
        return ResponseEntity.ok(userServices.createAccount(user));
    }

    @GetMapping(path="{username}/viewBankAccounts", consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> listAllAccounts(@RequestBody UserDTO username){
        return ResponseEntity.ok(userServices.listAllMyAccounts(username.getUsername()));
    }


    @GetMapping("/hilo")
    public String dlkd(Model model){
        return "index";
    }

    @GetMapping("Contact")
    public String Contact(Model model){
        return "Contact";
    }

    @GetMapping("LinkedAccounts")
    public String LinkedAccounts(Model model){
        return "LinkedAccounts";
    }

    @GetMapping("Goals")
    public String Goals(Model model){
        return "Goals";
    }

    @GetMapping("Notes")
    public String Notes(Model model){
        return "Notes";
    }

    @GetMapping("IncomeTracker")
    public String IncomeTracker(Model model){
        return "IncomeTracker";
    }

    @GetMapping("ConcurrentPayments")
    public String ConcurrentPayments(Model model){
        return "ConcurrentPayments";
    }
}

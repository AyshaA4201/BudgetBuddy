package com.example.budgetbuddy.Controller;

import com.example.budgetbuddy.DTO.TransferDTO;
import com.example.budgetbuddy.DTO.UserDTO;
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

    @PostMapping(path="createUser",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@RequestBody UserDTO user){
        return ResponseEntity.ok(userServices.createUser(user));
    }

    @PostMapping(path="addBankAccount", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createAccount(@RequestBody UserDTO user) {
        return ResponseEntity.ok(userServices.createAccount(user));
    }

    @GetMapping(path="viewBankAccounts", consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> listAllAccounts(@RequestBody UserDTO username){
        return ResponseEntity.ok(userServices.listAllMyAccounts(username.getUsername()));
    }

    @GetMapping()
    public String dkd(Model model){
        //model.addAttribute("message", "puton");
        return "home";
    }

    @GetMapping("/hilo")
    public String dlkd(Model model){
        return "index";
    }
}

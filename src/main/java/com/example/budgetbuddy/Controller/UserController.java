package com.example.budgetbuddy.Controller;

import com.example.budgetbuddy.DTO.UserDTO;
import com.example.budgetbuddy.Models.Account;
import com.example.budgetbuddy.Models.User;
import com.example.budgetbuddy.Services.AdvisorServices;
import com.example.budgetbuddy.Services.UserServices;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RequestMapping()
@Controller
public class UserController {
    UserServices userServices = new UserServices();
    AdvisorServices advisorServices = new AdvisorServices();

    @GetMapping("Home")
    public String home(Model model){
        User aysha = userServices.getUser("aysha");
        List<Account> accounts = aysha.getAccounts();
        model.addAttribute("user", aysha);
        model.addAttribute("accounts", accounts);
        return "home";
    }

    @GetMapping("Contact")
    public String Contact(Model model){ return "Contact"; }

    @GetMapping("AddAccount")
    public String AddAccount(Model model){
        UserDTO userDTO = new UserDTO();
        model.addAttribute("user", userDTO);
        return "AddAccount";
    }

    @PostMapping("AddAccount")
    public String AddAccount(Model model, @ModelAttribute UserDTO userDTO){
        model.addAttribute("user", userDTO);
        userServices.createAccount(userDTO);
        return "AccountAdded";
    }

    @GetMapping("LinkedAccounts")
    public String LinkedAccounts(Model model){
        User aysha = userServices.getUser("aysha");
        model.addAttribute("user", aysha);
        return "LinkedAccounts";
    }

    @GetMapping("Notes")
    public String Notes(Model model){ return "Notes"; }

    @GetMapping("IncomeTracker")
    public String IncomeTracker(Model model){ return "IncomeTracker"; }

    @GetMapping("ConcurrentPayments")
    public String ConcurrentPayments(Model model){ return "ConcurrentPayments"; }

    @GetMapping("Goals")
    public String Goals(Model model){
        User aysha = userServices.getUser("aysha");
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(aysha.getUsername());
        userDTO.setBalance(aysha.getTotalBalance());
        userDTO.setAccountId(aysha.getAccountId());
        String answer = advisorServices.getAdvice(userDTO);
        model.addAttribute("advice", answer);
        return "Goals";
    }

    @PostMapping(path="createUser",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@RequestBody UserDTO user){
        return ResponseEntity.ok(userServices.createUser(user));
    }

    @PostMapping(path="addBankAccount", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String createAccount(@RequestBody UserDTO user) {
        userServices.createAccount(user);
        return "AccountAdded";
    }

    @GetMapping(path="viewBankAccounts", consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> listAllAccounts(@RequestBody UserDTO username){
        return ResponseEntity.ok(userServices.listAllMyAccounts(username.getUsername()));
    }

}

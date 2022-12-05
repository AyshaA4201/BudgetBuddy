package com.example.budgetbuddy.Controller;

import com.example.budgetbuddy.DTO.AccountDTO;
import com.example.budgetbuddy.DTO.UserDTO;
import com.example.budgetbuddy.Models.Account;
import com.example.budgetbuddy.Models.User;
import com.example.budgetbuddy.Services.AdvisorServices;
import com.example.budgetbuddy.Services.UserServices;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RequestMapping()
@Controller
public class UserController {
    UserServices userServices = new UserServices();
    AdvisorServices advisorServices = new AdvisorServices();
    User aysha = userServices.getUser("aysha");
    List<Account> accounts = aysha.getAccounts();

    @GetMapping()
    public ModelAndView Home(){
        //modelMap.addAttribute("attribute", "Home");
        return new ModelAndView("redirect:" + "Home");
    }

    @GetMapping("Home")
    public String home(Model model){
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
        User user = userServices.getUser("aysha");
        userDTO.setUsername(user.getUsername());
        model.addAttribute("user", userDTO);
        userServices.createAccount(userDTO);
        return "AccountAdded";
    }

    @PostMapping("DeleteAccount")
    public String DeleteAccount(Model model, @ModelAttribute AccountDTO accountDTO){
        User user = userServices.getUser("aysha");
        accountDTO.setUsername(user.getUsername());
        userServices.deleteAccount(accountDTO);
        return "AccountDeleted";
    }

    @GetMapping("DeleteAccount")
    public String DeleteAccount(Model model){
        AccountDTO accountDTO = new AccountDTO();
        model.addAttribute("user", accountDTO);
        return "DeleteAccount";
    }

    @GetMapping("LinkedAccounts")
    public String LinkedAccounts(Model model){
        User aysha = userServices.getUser("aysha");
        model.addAttribute("user", aysha);
        return "LinkedAccounts";
    }

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

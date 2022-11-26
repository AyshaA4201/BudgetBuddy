package com.example.budgetbuddy.Controller;

import com.example.budgetbuddy.DTO.UserDTO;
import com.example.budgetbuddy.Services.UserServices;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class LoginController {
    UserServices userServices = new UserServices();
    
    @PostMapping(consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userServices.login(userDTO));
    }
}

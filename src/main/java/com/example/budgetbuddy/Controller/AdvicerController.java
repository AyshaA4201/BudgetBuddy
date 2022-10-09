package com.example.budgetbuddy.Controller;

import com.example.budgetbuddy.DTO.UserDTO;
import com.example.budgetbuddy.Services.AdvicerServices;
import com.example.budgetbuddy.Services.UserServices;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="advice")
public class AdvicerController {
    AdvicerServices advicerServices = new AdvicerServices();

    @GetMapping(path="getAdvice",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAdvice(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(advicerServices.getAdvice(userDTO));
    }
}

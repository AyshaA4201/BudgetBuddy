package com.example.budgetbuddy.Controller;

import com.example.budgetbuddy.DTO.UserDTO;
import com.example.budgetbuddy.Services.AdvisorServices;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping(path="advice")
public class AdvisorController {
    AdvisorServices advicerServices = new AdvisorServices();

    @GetMapping(path="getAdvice",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAdvice(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(advicerServices.getAdvice(userDTO));
    }
}

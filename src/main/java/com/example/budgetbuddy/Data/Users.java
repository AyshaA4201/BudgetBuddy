package com.example.budgetbuddy.Data;

import com.example.budgetbuddy.Models.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
@Getter
@Setter
public class Users {
    static List<User> users = new ArrayList<>();



    public void saveUser(User user){
        users.add(user);
    }

    public User getUserByUsername(String username){
        for(User e : users){
            if(e.getUsername().equals(username)){
                return e;
            }
        }
        return null;
    }


}

package com.example.database.Controller;

import com.example.database.Model.User;
import com.example.database.Service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity getAllUser(){
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }

    @PostMapping("/create")
    public ResponseEntity addUser(@Valid @RequestBody User user , Errors err){
        if(err.hasErrors()){
            String msg = err.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(msg);
        }
        userService.addCoffee(user);
        return ResponseEntity.status(200).body("User is added!");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@Valid @RequestBody User user , Errors err , @PathVariable int id){
        if(err.hasErrors()){
            String msg = err.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(msg);
        }
        if(userService.updateUser(id, user))
            return ResponseEntity.status(200).body("User is updated!");

        return ResponseEntity.status(400).body("User with this ID dosent exist!");

    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable int id){
        if(userService.deleteUser(id)){
            return ResponseEntity.status(200).body("User is deleted!");
        }
        return ResponseEntity.status(200).body("User with this ID dosent exist!");
    }
}

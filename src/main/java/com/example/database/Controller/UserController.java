package com.example.database.Controller;

import com.example.database.Model.User;
import com.example.database.Service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/user")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {
    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity getAllUser(){
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }
    //,consumes = MediaType.APPLICATION_JSON_VALUE
    @PostMapping(path = "/create")
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
    @GetMapping("/getById/{id}")
    public ResponseEntity findUserById(@PathVariable int id) {
        return ResponseEntity.status(200).body(userService.findUserById(id));
    }
    @GetMapping("/getByName/{name}")
    public ResponseEntity findUserByName(@PathVariable String name) {
        return ResponseEntity.status(200).body(userService.findByName(name));
    }
    //1
    @GetMapping("/check/{email}/{password}")
    public ResponseEntity findUserEmail(@PathVariable("email") String email ,@PathVariable("password") Integer password) {

        return  ResponseEntity.status(200).body(userService.findByEmailAndPassword(email,password));
    }

    //2
    @GetMapping("/getEmail/{email}")
    public ResponseEntity findUserEmail(@PathVariable String email) {
        return ResponseEntity.status(200).body(userService.findUserByEmail(email));
    }

    //3
    @GetMapping("/getRole/{role}")
    public ResponseEntity findUserRole(@PathVariable String role) {
        return ResponseEntity.status(200).body(userService.findByRole(role));
    }

    //4
    @GetMapping("/getAge/{age}")
    public ResponseEntity findUserRole(@PathVariable Integer age) {
        return ResponseEntity.status(200).body(userService.findByAge(age));
    }





}

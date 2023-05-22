package com.example.database.Service;

import com.example.database.Model.User;

import com.example.database.Repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;

    public List<User> getAllUsers (){
        return userRepo.findAll();
    }

    public void addCoffee(User u){
        userRepo.save(u);
    }

    public boolean updateUser (int id , User user1){
        Optional<User> u=userRepo.findById(id);
        if(u.isPresent()) {
            User user2 = u.get();
            user2.setName(user1.getName());
            user2.setUsername(user1.getUsername());
            user2.setEmail(user1.getEmail());
            user2.setRole(user1.getRole());
            user2.setPassword(user1.getPassword());
            user2.setAge(user1.getAge());
            userRepo.save(user2);
            return true;
        }
        return false;
    }

    public boolean deleteUser(int id){
        Optional<User> u=userRepo.findById(id);
        if(u.isPresent()) {
            userRepo.deleteById(id);
            return true;
        }
        return false;
    }
}

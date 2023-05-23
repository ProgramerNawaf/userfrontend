package com.example.database.Service;

import com.example.database.Model.User;

import com.example.database.Repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

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
        User user2=userRepo.findUserById(id);
        if(user2 != null) {
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

    public User findUserById(int id) {
        if (userRepo.getUserById(id) == null){
            throw new ArithmeticException("Not found!");
    }
        return userRepo.getUserById(id);

    }
    public User findUserByEmail(String email) {
        if (userRepo.getUserByEmail(email) == null){
            throw new ArithmeticException("Not found!");
        }
        return userRepo.getUserByEmail(email);
    }

    public List<User> findByName(String name){
        if(userRepo.findUserByName(name) == null){
            throw new ArithmeticException("User Not found!");
        }
        return userRepo.findUserByName(name);
    }

    public List<User> findByRole(String role){
        if(userRepo.getUsersByRole(role).isEmpty()){
            throw new ArithmeticException("User Not found!");
        }
        return userRepo.getUsersByRole(role);
    }
    public List<User> findByAge(Integer age){
        if(userRepo.getUsersByAge(age).isEmpty()){
            throw new ArithmeticException("User Not found!");
        }
        return userRepo.getUsersByAge(age);
    }

    public User findByEmailAndPassword(String email , Integer password ){
        if(userRepo.getUsersByEmailPassword(email , password) == null) {
            throw new ArithmeticException("User Not found e!");
        }
        return userRepo.getUsersByEmailPassword(email , password);
    }
}

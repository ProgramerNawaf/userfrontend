package com.example.database.Repo;

import com.example.database.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User , Integer> {

    User findUserById(Integer id);
    @Query("select s from User s where s.id=?1")
    User getUserById(Integer id);
    @Query("select s from User s where s.email=?1")
    User getUserByEmail(String email);
    List <User> findUserByName(String name);

    @Query(value = "select s from User s where s.role = ?1 ")
    List<User> getUsersByRole(String role);

    @Query(value = "select s from User s where s.age >= ?1 ")
    List<User> getUsersByAge(Integer age);

    @Query(value = "select s from User s where s.email = ?1 and s.password=?2")
    User getUsersByEmailPassword(String email , Integer password);

//    User findUserByEmailAndPassword(String email , Integer passowrd);
}

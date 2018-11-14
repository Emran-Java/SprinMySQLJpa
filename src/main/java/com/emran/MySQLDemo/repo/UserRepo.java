package com.emran.MySQLDemo.repo;

import com.emran.MySQLDemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User,Integer> {

    User findByEmail(String email);

    List<User> findAll();

}

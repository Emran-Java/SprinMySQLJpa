package com.emran.MySQLDemo.service;

import com.emran.MySQLDemo.model.User;
import com.emran.MySQLDemo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepo userRepo;

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }
}

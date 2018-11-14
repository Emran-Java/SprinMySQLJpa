package com.emran.MySQLDemo.service;

import com.emran.MySQLDemo.model.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findUserByEmail(String email);
}

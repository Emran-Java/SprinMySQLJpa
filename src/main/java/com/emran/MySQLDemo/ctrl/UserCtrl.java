package com.emran.MySQLDemo.ctrl;


import com.emran.MySQLDemo.model.User;
import com.emran.MySQLDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserCtrl {

    @Autowired
    UserService userService;

    @GetMapping(value = "/user")
    public String userAll(){

        List<User> users = userService.findAll();

        return ""+users;
    }

    @GetMapping(value = "/user/{email}")
    public String userByEmail(@PathVariable String email){

        User user = userService.findUserByEmail(email);

        return ""+user;
    }
}

package com.emran.MySQLDemo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;


@Entity
@Table(name = "USER")
public class User extends Human{

    @Column(name = "EMAIL", nullable = false, length = 100, unique = true)
    private String email;

    @Column(name = "PHONE", nullable = true, length = 30)
    private String phone;

    public User() { }


    public User(String email, String phone) {
        this.email = email;
        this.phone = phone;
    }

    public User(String firstName, String lastName, Date dob, GenderEnm gender, String email, String phone) {
        super(firstName, lastName, dob, gender);
        this.email = email;
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}'+""+super.toString();
    }
}

# SprinMySQLJpa

1. <u>Create a Spring boot gradle project:</u>
<a href="https://start.spring.io/" target="_blank">start.spring.io</a>
<img src="https://github.com/Emran-Java/SprinMySQLJpa/blob/master/extra_files/Screenshot%20at%202018-11-14%2016-15-26.png" alt="Demo Screenshot 1" style="max-width:100%;"/>
<p>
- Add dependencies: Web, JPA, MySQL
<br>
- After import project in IntelliJ IDEA, create project structure by packaging to manage projects files. It's optional
  <br> <b>package names</b>:
  <br> <u>config</u> - Configuration: In this diractory contain java main class files, as like main function class here.
  <br> <u>ctrl</u> - Control: In this directory contain controlar java files. Control concept comes from MVC architecture in Spring.
  <br> <u>model</u> - Model: In this directory contain all model java files. Model concept from MVC architecture in Spring.
  <br> <u>repo</u> - Repository: In this directory contain all repository java interface files. Repository act like DAO (Data Access Object) concept
  <br> <u>service</u> - Service: In this directory contain all repo accessable java class and java interface files. Service act as middle position between controller and repository
  <br> ... <br>
  </p>
  <img src="https://github.com/Emran-Java/SprinMySQLJpa/blob/master/extra_files/Screenshot%20at%202018-11-14%2016-59-30.png" alt="Demo Screenshot 1" style="max-width:100%;"/>
<br>
<p>
- set packages as component and entityScan by annotation in main class
<br>
package com.emran.MySQLDemo.config;
<br>
<br>import org.springframework.boot.SpringApplication;
<br>import org.springframework.boot.autoconfigure.SpringBootApplication;
<br>import org.springframework.boot.autoconfigure.domain.EntityScan;
<br>import org.springframework.context.annotation.ComponentScan;
<br>import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
<br><br>
<br>@SpringBootApplication
<br>@ComponentScan("com.emran.MySQLDemo")
<br>@EntityScan("com.emran.MySQLDemo.model")
<br>@EnableJpaRepositories(basePackages = {"com.emran.MySQLDemo.repo"})
<br>
//add extends SpringBootServletInitializer this for war
<br>public class SpringMySQLJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMySQLJpaApplication.class, args);

	}
}

- Now create model with table colomun and other annotation in model package.
<br>
<br>--- BaseModel.java ---<br>
package com.emran.MySQLDemo.model;
<br>
<br>import javax.persistence.Column;
<br>import javax.persistence.Id;
<br>import javax.persistence.MappedSuperclass;
<br>import java.io.Serializable;
<br>
<br>@MappedSuperclass
<br>public class BaseModel implements Serializable {
<br>
<br>@Id
<br>@Column(name="ID", unique = true)
    <br>private Integer id;


    //Default constructor
    public BaseModel(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id.toString();
    }
}

<br>-------------------------------<br>
<br> Add a enum file for gender
<br>--- GenderEnm.java ---<br>
<br>package com.emran.MySQLDemo.model;
<br>
<br>public enum GenderEnm {
<br>    MALE, FEMALE, OTHER
<br>}

<br>-------------------------------<br>


<br>--- Human.java ---<br>

<br>package com.emran.MySQLDemo.model;
<br>
<br>import javax.persistence.Column;
<br>import javax.persistence.MappedSuperclass;
<br>import javax.persistence.Temporal;
<br>import javax.persistence.TemporalType;
<br>import java.util.Date;
<br>
<br>@MappedSuperclass
<br>public class Human extends BaseModel {

    @Column(name = "FIRST_NAME", nullable = false, length = 30)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = true, length = 15)
    private String lastName;

    @Column(name = "DOB")
    @Temporal(TemporalType.DATE)
    private Date dob;

    @Column(name = "GENDER", nullable = false, length = 15)
    private GenderEnm gender;


    public Human() {

    }

    public Human(String firstName, String lastName, Date dob, GenderEnm gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.gender = gender;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void setGender(GenderEnm gender) {
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDob() {
        return dob;
    }

    public GenderEnm getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "Human{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                ", gender=" + gender +
                '}'+super.toString();
    }
}
<br>-------------------------------<br>

<br>--- User.java ---<br>

<br>package com.emran.MySQLDemo.model;
<br>
<br>import javax.persistence.Column;
<br>import javax.persistence.Entity;
<br>import javax.persistence.Table;
<br>import java.util.Date;
<br>
<br>@Entity
<br>@Table(name = "USER")
<br>public class User extends Human{

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

<br>-------------------------------<br>

<br>Create controllare for user model, user controllare use for user table and user service. 

<br>--- UserCtrl ---<br>
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
<br>-------------------------------<br>

<br>Create repository for user, 
<br>--- UserRepo ---<br>
<br>package com.emran.MySQLDemo.repo;
<br>
<br>import com.emran.MySQLDemo.model.User;
<br>import org.springframework.data.jpa.repository.JpaRepository;
<br>
<br>import java.util.List;
<br>
<br>public interface UserRepo extends JpaRepository<User,Integer> {

    User findByEmail(String email);

    List<User> findAll();

}
<br>-------------------------------<br>

<br>Create user service interface and user service implementation
<br>--- UserService ---<br>
<br>package com.emran.MySQLDemo.service;
<br>
<br>import com.emran.MySQLDemo.model.User;
<br>
<br>import java.util.List;
<br>
<br>public interface UserService {

    List<User> findAll();

    User findUserByEmail(String email);
}
 
<br>-------------------------------<br>

<br>--- UserServiceImpl ---<br>
<br>package com.emran.MySQLDemo.service;
<br>
<br>import com.emran.MySQLDemo.model.User;
<br>import com.emran.MySQLDemo.repo.UserRepo;
<br>import org.springframework.beans.factory.annotation.Autowired;
<br>import org.springframework.stereotype.Service;
<br>
<br>import java.util.List;
<br>
<br>@Service
<br>public class UserServiceImpl implements UserService{

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

<br>-------------------------------<br>
</p>
<p>
- now configure <i>application.yml</i> file
<img src="https://github.com/Emran-Java/SprinMySQLJpa/blob/master/extra_files/Screenshot%20at%202018-11-14%2019-09-00.png" alt="Demo Screenshot 1" style="max-width:100%;"/>
<br>
<br>server:
  <br>port: 9090
  <br>servlet:
    <br>context-path: /demo
<br>
<br>spring:
  <br>jpa:
    <br>generate-ddl: true
    <br>show-sql: true
    <br>hibernate:
      <br>show-sql: true
      <br>ddl-auto: update
      <br>naming.physical-strategy: org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
    <br>properties:
      <br>hibernate:
        <br>default_schema: app
        <br>id.new_generator_mappings: true
        <br>dialect: org.hibernate.dialect.MySQL5Dialect
  <br>datasource:
    <br>url: jdbc:mysql://localhost:3306/bd_spring_demo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC
    <br>username: root
    <br>password:
</p>
<p>
- In UserCtrl.java file

<br>Use userAll() function by url: localhost:9090/demo/user
<br>to get all user data
<br>and
<br>Use userByEmail() function by url: localhost:9090/demo/user/name@mail.com
<br>to get single user information by email
<br>database name: bd_spring_demo
<p>
- Noe run this project and browse 
<br>localhost:9090/demo/user
<br>and
<br>localhost:9090/demo/user/name@mail.com
</p>
</p>
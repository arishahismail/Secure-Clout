package client.springbootvuejs.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import client.springbootvuejs.domain.RegisterUser;
import client.springbootvuejs.repository.UserRepository;

@RestController()
@RequestMapping("/api")
public class BackendController {
    @Autowired
    // private UserRepository userRepository;
    UserRepository repository;

    @GetMapping("/RegisterUser")
    public List<RegisterUser> getAllUsers() {
        System.out.println("Getting all users...");
        List<RegisterUser> user = new ArrayList<>();
        repository.findAll().forEach(user::add);

        return user;

    }

    @PostMapping("/RegisterUser")
    public RegisterUser postRegisterUser(@RequestBody RegisterUser registeruser) {
        RegisterUser reguser = repository
                .save(new RegisterUser(registeruser.getName(), registeruser.getEmail(), registeruser.getPassword()));
        return reguser;
    }

    // @DeleteMapping

    private static final Logger LOG = LoggerFactory.getLogger(BackendController.class);
    public static final String HELLO_TEXT = "Hello from Spring Boot Backend!";

    @RequestMapping(path = "/hello")
    public @ResponseBody String sayHello() {
        LOG.info("GET called on /hello resource");
        return HELLO_TEXT;
    }

}

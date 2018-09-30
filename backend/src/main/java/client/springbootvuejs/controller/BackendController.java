package client.springbootvuejs.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import client.springbootvuejs.domain.RegisterUser;
import client.springbootvuejs.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class BackendController {
    @Autowired
    UserRepository repository;

    @GetMapping("/RegisterUser/")
    public List<RegisterUser> getAllUsers() {
        System.out.println("Getting all users...");
        List<RegisterUser> user = new ArrayList<>();
        repository.findAll().forEach(user::add);

        return user;

    }

    @PostMapping("/RegisterUser/")
    public RegisterUser postRegisterUser(@RequestBody RegisterUser registeruser) {
        RegisterUser _users = repository
                .save(new RegisterUser(registeruser.getName(), registeruser.getEmail(), registeruser.getPassword()));

        return _users;
    }

    // @DeleteMapping
}

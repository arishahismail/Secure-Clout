package client.springbootvuejs.controller;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import client.springbootvuejs.domain.OTP;
import client.springbootvuejs.domain.PasswordHashing;
import client.springbootvuejs.domain.RegisterUser;
import client.springbootvuejs.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class BackendUserController {
    @Autowired
    UserRepository repository;

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public RegisterUser postRegisterUser(@RequestBody RegisterUser registeruser) {
        PasswordHashing encrypt = new PasswordHashing();
        String hashpass;
        try {
            hashpass = encrypt.hash(registeruser.getPassword());
            System.out.println(hashpass);
            RegisterUser _users = repository
                    .save(new RegisterUser(registeruser.getName(), registeruser.getEmail(), hashpass));
            return _users;

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return registeruser;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public List<RegisterUser> findByEmail(@PathVariable String email) {
        List<RegisterUser> users = repository.findByEmail(email);
        return users;
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.GET)
    public char[] validateUser(@PathVariable String code) {
        OTP otp = new OTP();
        char[] temppass = otp.temp_pass();
        System.out.println(temppass);
        return temppass;
    }

}

package client.springbootvuejs.controller;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.solr.SolrProperties;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.util.SocketUtils;
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
public class BackendUserController implements ErrorController {
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

    @RequestMapping("/all")
    public List<RegisterUser> getAll() {

        try {
            return repository.findAll();
        } catch (Exception e) {
            System.out.println("The error is " + e + " KAPPPPPPPPPPPPPPPPPAAAAAAAAAAAAAAA");
        }
        return repository.findAll();
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String justTry() {

        try {
            return "We made it";
        } catch (Exception e) {
            return "Fucckkk";
        }
    }

    // @RequestMapping(value = "/login")
    // public List<RegisterUser> getId() {
    // return repository.findOne(RegisterUser.getID(id));
    // }

    @RequestMapping(value = "/authenticate", method = RequestMethod.GET)
    public char[] validateUser(@PathVariable String code) {
        OTP otp = new OTP();
        char[] temppass = otp.temp_pass();
        System.out.println(temppass);
        return temppass;
    }

    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public String error() {
        return "Error handling";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }

}

package client.springbootvuejs.controller;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import client.springbootvuejs.domain.CloutUser;
import client.springbootvuejs.domain.OTP;
import client.springbootvuejs.domain.PasswordHashing;
import client.springbootvuejs.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class BackendUserController {
    @Autowired
    UserRepository repository;

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public CloutUser postRegisterUser(@RequestBody CloutUser registeruser) {
        PasswordHashing encrypt = new PasswordHashing();
        String hashpass;
        try {
            hashpass = encrypt.hash(registeruser.getPassword());
            System.out.println(hashpass);
            CloutUser _users = repository
                    .save(new CloutUser(registeruser.getName(), registeruser.getEmail(), hashpass));
            return _users;

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return registeruser;
    }
   
    @RequestMapping(value="/login/{email}/{pass}", method = RequestMethod.GET)    
    public CloutUser getUser(@PathVariable ("email")String email, @PathVariable ("pass") String pass) {
        System.out.println("Getting information "+ email);
        CloutUser user = repository.findByEmail(email);
        if (user == null) {
			return null;
        }
        else {
            try {
                PasswordHashing decrypt = new PasswordHashing();
                String hashpass = user.getPassword();
                System.out.println(hashpass);
                boolean comparepass = decrypt.checkhash(pass, hashpass);
                if (comparepass = true) {
                    System.out.println("hash similar");
                    return user;
                } else {
                    return null;
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @RequestMapping(value = "/otp", method = RequestMethod.GET)
    public char[] validateUser(String code) {
        OTP otp = new OTP();
        char[] temppass = otp.temp_pass();
        System.out.println(temppass);
        return temppass;
    }

    @RequestMapping("/tell")
    public List<CloutUser> getAll() {
        try {
            System.out.println(repository.findAll());
            return repository.findAll();
        } catch (Exception e) {
            System.out.println("The error is " + e + " KAPPPPPPPPPPPPPPPPPAAAAAAAAAAAAAAA");
        }
        return repository.findAll();
    }
}

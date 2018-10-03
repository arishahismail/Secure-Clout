package client.springbootvuejs.domain;

import java.security.NoSuchAlgorithmException;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordHashing {
    public String hash(String txtpass) throws NoSuchAlgorithmException {
        String encryptpass = BCrypt.hashpw(txtpass, BCrypt.gensalt(12));
        System.out.println(encryptpass);
        return encryptpass;
    }

    public boolean checkhash(String pass, String hashpass) throws NoSuchAlgorithmException {
        boolean matched = BCrypt.checkpw(pass, hashpass);
        System.out.println(matched);
        return matched;

    }
}
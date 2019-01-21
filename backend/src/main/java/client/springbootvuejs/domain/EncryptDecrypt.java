package client.springbootvuejs.domain;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class EncryptDecrypt {

    private static Cipher ecipher;
    private static Cipher dcipher;
    // 8-byte initialization vector
    private static byte[] iv = { (byte) 0xB2, (byte) 0x12, (byte) 0xD5, (byte) 0xB2, (byte) 0x44, (byte) 0x21,
            (byte) 0xC3, (byte) 0xC3 };

    public void FileEncrypt(String file_location, String encryptFile) {
        try {
            SecretKey key = KeyGenerator.getInstance("DES").generateKey();
            AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);
            ecipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);

            encrypt(new FileInputStream(file_location), new FileOutputStream(encryptFile));
            
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found:" + e.getMessage());
            return;
        } catch (InvalidAlgorithmParameterException e) {
            System.out.println("Invalid Alogorithm Parameter:" + e.getMessage());
            return;
        } catch (NoSuchAlgorithmException e) {
            System.out.println("No Such Algorithm:" + e.getMessage());
            return;
        } catch (NoSuchPaddingException e) {
            System.out.println("No Such Padding:" + e.getMessage());
            return;
        } catch (InvalidKeyException e) {
            System.out.println("Invalid Key:" + e.getMessage());
            return ;
        }

    }

    public void FileDecrypt(String encryptFile, String decryptFile) {
        try {
            SecretKey key = KeyGenerator.getInstance("DES").generateKey();
            AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);

            dcipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

            dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);

            decrypt(new FileInputStream(encryptFile), new FileOutputStream(decryptFile));
            
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found:" + e.getMessage());
            return;
        } catch (InvalidAlgorithmParameterException e) {
            System.out.println("Invalid Alogorithm Parameter:" + e.getMessage());
            return;
        } catch (NoSuchAlgorithmException e) {
            System.out.println("No Such Algorithm:" + e.getMessage());
            return;
        } catch (NoSuchPaddingException e) {
            System.out.println("No Such Padding:" + e.getMessage());
            return;
        } catch (InvalidKeyException e) {
            System.out.println("Invalid Key:" + e.getMessage());
            return ;
        }

    }

    private static void encrypt(InputStream is, OutputStream os) {
        try {
            byte[] buf = new byte[1024];
            // bytes at this stream are first encoded
            os = new CipherOutputStream(os, ecipher);
            // read in the clear text and write to out to encrypt
            int numRead = 0;
            while ((numRead = is.read(buf)) >= 0) {
                os.write(buf, 0, numRead);
            }
            // close all streams
            os.close();
        } catch (IOException e) {
            System.out.println("I/O Error:" + e.getMessage());
        }
        System.out.println("this is what? " + os);
    }

    private static void decrypt(InputStream is, OutputStream os) {
        try {
            byte[] buf = new byte[1024];
            // bytes read from stream will be decrypted
            CipherInputStream cis = new CipherInputStream(is, dcipher);
            // read in the decrypted bytes and write the clear text to out
            int numRead = 0;
            while ((numRead = cis.read(buf)) >= 0) {
                os.write(buf, 0, numRead);
            }
            // close all streams
            cis.close();
            is.close();
            os.close();
        } catch (IOException e) {
            System.out.println("I/O Error:" + e.getMessage());
        }
    }

    // public static void main(String[] args) {
    //     EncryptDecrypt encrypt = new EncryptDecrypt();
    //      System.out.println(encrypt.FileEncrypt("/Users/Arishah/Documents/GitHub/Secure-Clout/backend/src/main/java/client/cleartext.txt/","encrypted.dat"));
    
    // } 
}

	
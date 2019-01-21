package client.springbootvuejs.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import client.springbootvuejs.domain.CloutFiles;
import client.springbootvuejs.domain.EncryptDecrypt;
import client.springbootvuejs.repository.FileRepository;

@RestController
@RequestMapping("/api")
public class BackendFileController implements FileRepository {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public BackendFileController(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }

    @RequestMapping(value="/upload/{email}/{file}/{file_name}", method = RequestMethod.POST)
    public String singleFileUpload(@RequestParam("file") String multipart, @RequestParam("email") String email, @RequestParam("file_name")String file_name, String date) {
        try {
            CloutFiles cloutfiles = new CloutFiles();
            cloutfiles.setuser_id("ismailarishah@gmail.com");
            multipart = "encrypted.dat";
            EncryptDecrypt encrypt = new EncryptDecrypt();
            encrypt.FileEncrypt("/Users/Arishah/Documents/GitHub/Secure-Clout/backend/src/main/java/client/cleartext.txt/",multipart);
            cloutfiles.set_file(new Binary(BsonBinarySubType.BINARY, multipart.getBytes()));
            cloutfiles.setfile_name("namename");
            cloutfiles.set_date(new Date());
            mongoTemplate.save(cloutfiles);
            System.out.println(cloutfiles);
        } catch (Exception e) {
            e.printStackTrace();
            return "failure";
        }
        return "success";
    }

    @RequestMapping(value ="/download", method = RequestMethod.POST)
    public String retrieveFile(String email){

        CloutFiles demoDocument = mongoTemplate.findOne(new BasicQuery("{emailId : \""+email+"\", docType : \"pictures\"}"), CloutFiles.class);
        System.out.println(demoDocument);
        // Binary document = demoDocument.getDocument();
        // if(document != null) {
        //     FileOutputStream fileOuputStream = null;
        //     try {
        //         fileOuputStream = new FileOutputStream(RETRIEVE_FOLDER + "prof_pic.jpg");
        //         fileOuputStream.write(document.getData());
        //     } catch (Exception e) {
        //         e.printStackTrace();
        //         return "failure";
        //     } finally {
        //         if (fileOuputStream != null) {
        //             try {
        //                 fileOuputStream.close();
        //             } catch (IOException e) {
        //                 e.printStackTrace();
        //                 return "failure";
        //             }
        //         }
        //     }
        // }
        return "success";
    }

    @Override
    public CloutFiles saveFiles(CloutFiles cloutfiles) {
        mongoTemplate.save(cloutfiles);
        return cloutfiles;
    }

    @Override
    public List<CloutFiles> findByemail() {
        return null;
    }

    @Override
    public CloutFiles updateFiles(CloutFiles cloutfiles) {
        return null;
    }

    @Override
    public void deleteFile(CloutFiles cloutfiles) {

    }
}
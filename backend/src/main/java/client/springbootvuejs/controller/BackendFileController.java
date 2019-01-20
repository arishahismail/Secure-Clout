package client.springbootvuejs.controller;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import client.springbootvuejs.domain.CloutFiles;

@RestController
@RequestMapping("/api")
public class BackendFileController {

    @Autowired
    MongoTemplate mongoTemplate;

    // FilesRepository filerepository;
    @PostMapping("/upload")
    public String singleFileUpload(String multipart, String email, String file_name, String date) {
    // public String singleFileUpload(@RequestParam("file") MultipartFile multipart, @RequestParam("email") String email, String file_name, String date) {
        try {
            CloutFiles cloutfiles = new CloutFiles();
            cloutfiles.setuser_id("ismailarishah@gmail.com");
            multipart = "/Users/Arishah/Downloads";
            cloutfiles.set_file(new Binary(BsonBinarySubType.BINARY, multipart.getBytes()));
            cloutfiles.setfile_name("namename");
            cloutfiles.set_date("11/11/2019");
            mongoTemplate.save(cloutfiles);
            System.out.println(cloutfiles);
        } catch (Exception e) {
            e.printStackTrace();
            return "failure";
        }
        return "success";
    }

}
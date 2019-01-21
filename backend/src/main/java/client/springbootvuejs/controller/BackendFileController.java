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
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
 
    @RequestMapping(value="/upload/{email}/{file}/{filename}", method = RequestMethod.POST)
    public String FileUpload(@PathVariable("email") String email, @PathVariable ("file") String filelocation, @PathVariable ("filename") String filename) {
        try {
            CloutFiles cloutfiles = new CloutFiles();
            cloutfiles.setuser_id(email); 

            String toEncrypt = filename + ".dat";
            String file_location = "/Users/Arishah/Desktop/" + filelocation;
            EncryptDecrypt encrypt = new EncryptDecrypt();
            encrypt.FileEncrypt(file_location,toEncrypt);
            cloutfiles.set_file(new Binary(BsonBinarySubType.BINARY, toEncrypt.getBytes()));
            cloutfiles.setfile_name(filename);
            cloutfiles.set_date(new Date());
            mongoTemplate.save(cloutfiles);
            System.out.println(cloutfiles);
        } catch (Exception e) {
            e.printStackTrace();
            return "failure";
        }
        return "success";
    }

    // @RequestMapping(value ="/download/{email}/{filename}", method = RequestMethod.GET)
    // public String retrieveFile(@PathVariable("email") String email, @PathVariable ("filename") String filename){
    @RequestMapping(value ="/download", method = RequestMethod.POST)
    public String retrieveFile(String email, String filename){
        email = "ismailarishah@gmail.com";
        filename = "test";
        Query query = new Query();
        query.addCriteria(Criteria.where("file_name").is(filename));
        CloutFiles download = mongoTemplate.findOne(query, CloutFiles.class);
        // CloutFiles download = mongoTemplate.findOne(new BasicQuery("{user_id : \"" + email+"\", file_name : \"" + filename+"}"), CloutFiles.class);
        System.out.println(download);
        Binary document = download.getDocument();
        if(document != null) {
            FileOutputStream fileOuputStream = null;
            try {
                fileOuputStream = new FileOutputStream(RETRIEVE_FOLDER + "prof_pic.jpg");
                fileOuputStream.write(document.getData());
            } catch (Exception e) {
                e.printStackTrace();
                return "failure";
            } finally {
                if (fileOuputStream != null) {
                    try {
                        fileOuputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        return "failure";
                    }
                }
            }
        }
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
    public CloutFiles findByfileName(String file_name){
        mongoTemplate.
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
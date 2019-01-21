package client.springbootvuejs.domain;

import java.util.Date;

import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document (collection = "_files")

public class CloutFiles {
    @Id
    @Field
    private ObjectId _id;
    @Field
    private String user_id;
    private String filelocation;
    @Field
    private Binary file;
    @Field
    private String file_name;
    @Field
    private Date date;

    public CloutFiles(){

    }

    public ObjectId getid() {
        return _id;
    }
    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getuser_id() {
        return user_id;
    }
    public void setuser_id(String user_id) {
        this.user_id = user_id;
    }

    public String get_filelocation(){
        return filelocation;
    }
    public void set_filelocation(String filelocation){
        this.filelocation = filelocation;
    }

    public Binary getfile() {
        return file;
    }
    public void set_file(Binary file) {
        this.file = file;
    }

    public String getfile_name() {
        return file_name;
    }
    public void setfile_name(String file_name) {
        this.file_name = file_name;
    }

    public Date getdate() {
        return date;
    }
    public void set_date(Date date) {
        this.date = date;
    }
    @Override
    public String toString() {
        return String.format("File details\n" + user_id + " "+ file + " " + file_name + " " + date);
    }

}
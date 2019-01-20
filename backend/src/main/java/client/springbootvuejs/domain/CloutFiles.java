package client.springbootvuejs.domain;

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
    @Field
    private Binary file;
    @Field
    private String file_name;
    @Field
    private String date;

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

    public String getdate() {
        return date;
    }
    public void set_date(String date) {
        this.date = date;
    }
    @Override
    public String toString() {
        return String.format(user_id + file + file_name + date);
    }

}
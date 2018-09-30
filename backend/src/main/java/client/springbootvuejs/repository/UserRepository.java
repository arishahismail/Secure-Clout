package client.springbootvuejs.repository;

import client.springbootvuejs.domain.RegisterUser;

import org.springframework.data.mongodb.repository.MongoRepository;
// import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends MongoRepository<RegisterUser, String> {

    // List<RegisterUser> findByname(@Param("name") String name);

    // List<RegisterUser> findByemail(@Param("email") String email);

    List<RegisterUser> findBypassword(@Param("password") String password);

    RegisterUser findByemail(String email);

}

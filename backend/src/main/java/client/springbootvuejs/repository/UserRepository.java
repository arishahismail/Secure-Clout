package client.springbootvuejs.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import client.springbootvuejs.domain.RegisterUser;

public interface UserRepository extends MongoRepository<RegisterUser, String> {

    List<RegisterUser> findByEmail(String email);

    // Iterable<RegisterUser> findAll();
}

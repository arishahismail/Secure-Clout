package client.springbootvuejs.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import client.springbootvuejs.domain.CloutUser;

public interface UserRepository extends MongoRepository<CloutUser, String> {

    CloutUser findByEmail(String email);

}

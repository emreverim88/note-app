package com.emreverim.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.emreverim.entity.User;

@Repository
public interface UserRepository extends MongoRepository<User, Long> {

}

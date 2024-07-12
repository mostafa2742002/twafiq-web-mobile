package com.web.marriage.user.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.web.marriage.user.entity.UserAlgorithm;

@Repository
public interface UserAlgorithmRepository extends MongoRepository<UserAlgorithm, String> {

    UserAlgorithm findByUserId(String userId);
}

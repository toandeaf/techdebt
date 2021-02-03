package com.signifyd.techdebt.dao;

import com.signifyd.techdebt.model.Interview;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InterviewRepository extends MongoRepository<Interview, String> {

    @Query("{ 'engineer.username' : ?0 }")
    Optional<Interview> findByEngineerId(String engineerId);
}

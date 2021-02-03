package com.signifyd.techdebt.dao;

import com.signifyd.techdebt.model.Engineer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EngineerRepository extends MongoRepository<Engineer, String> {
}

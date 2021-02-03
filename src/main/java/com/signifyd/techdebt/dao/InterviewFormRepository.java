package com.signifyd.techdebt.dao;

import com.signifyd.techdebt.model.InterviewForm;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewFormRepository extends MongoRepository<InterviewForm, String> {
}

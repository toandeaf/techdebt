package com.signifyd.techdebt.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "interview_form")
@Data
public class InterviewForm {

    @Id
    private Integer version;
    private List<String> questions;
}

package com.signifyd.techdebt.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collection = "interview")
@Data
public class Interview {

    @Id
    private String id;
    private Map<String, String> questionsAndAnswers;
    private Engineer engineer;
}

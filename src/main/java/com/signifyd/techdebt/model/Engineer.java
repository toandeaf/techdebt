package com.signifyd.techdebt.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "engineer")
@Data
public class Engineer {
    @Id
    private String username;
    private String fullName;
    private String team;
    private String location;
    private String department;
    private String jobTitle;
    private Boolean isManager;
    private Boolean hasBeenInterviewed;
}

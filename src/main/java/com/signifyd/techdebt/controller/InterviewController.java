package com.signifyd.techdebt.controller;

import com.signifyd.techdebt.model.Interview;
import com.signifyd.techdebt.model.InterviewForm;
import com.signifyd.techdebt.service.InterviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/interview")
public class InterviewController {

    final
    InterviewService interviewService;

    public InterviewController(InterviewService interviewService) {
        this.interviewService = interviewService;
    }

    @PostMapping("/persist")
    public ResponseEntity persistInterview(@RequestBody Interview interview) throws IOException {
        Interview persistedInterview = interviewService.persistInterview(interview);
        return new ResponseEntity(persistedInterview, HttpStatus.ACCEPTED);
    }

    @GetMapping("/get/{username}")
    public ResponseEntity getBlankInterview(@PathVariable("username") String username) {
        Interview interview = interviewService.getInterview(username);
        return new ResponseEntity(interview, HttpStatus.ACCEPTED);
    }

    @GetMapping("/form")
    public ResponseEntity getForm(){
        InterviewForm interviewForm = interviewService.getForm();
        return new ResponseEntity(interviewForm, HttpStatus.ACCEPTED);
    }

    @PostMapping("/form/{updateVersion}")
    public ResponseEntity updateForm(@RequestBody InterviewForm interviewForm,
                                     @PathVariable("updateVersion") Boolean updateVersion){
        InterviewForm updatedForm = interviewService.updateForm(interviewForm, updateVersion);
        return new ResponseEntity(updatedForm, HttpStatus.ACCEPTED);
    }
}

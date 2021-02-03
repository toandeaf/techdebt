package com.signifyd.techdebt.controller;

import com.signifyd.techdebt.model.Engineer;
import com.signifyd.techdebt.service.EngineerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/engineer")
public class EngineerController {

    final
    EngineerService engineerService;

    public EngineerController(EngineerService engineerService) {
        this.engineerService = engineerService;
    }

    @GetMapping("/getAll")
    public ResponseEntity getAllEngineers(){
        List<Engineer> engineers = engineerService.findAll();
        return new ResponseEntity(engineers, HttpStatus.ACCEPTED);
    }
}

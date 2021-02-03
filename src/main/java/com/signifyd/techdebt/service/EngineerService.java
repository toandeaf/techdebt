package com.signifyd.techdebt.service;

import com.signifyd.techdebt.dao.EngineerRepository;
import com.signifyd.techdebt.dao.InterviewRepository;
import com.signifyd.techdebt.model.Engineer;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class EngineerService {

    private final InterviewRepository interviewRepository;
    private final EngineerRepository engineerRepository;
    private static final String ENGINEER_CSV_FILE = "/app/Engineers.csv";

    public EngineerService(EngineerRepository engineerRepository, InterviewRepository interviewRepository) {
        this.engineerRepository = engineerRepository;
        this.interviewRepository = interviewRepository;
    }

    @PostConstruct
    public void loadEngineersFromCSV() throws IOException {
        System.out.println("Loading engineers from CSV File.");
        CSVParser parser = CSVParser.parse(new File(ENGINEER_CSV_FILE), StandardCharsets.UTF_8, CSVFormat.EXCEL);
        List<CSVRecord> records = parser.getRecords();
        // Removing header row.
        records.remove(0);
        List<Engineer> engineers = new ArrayList<>();
        for(CSVRecord record : records ) {
            engineers.add(mapRecordToEngineer(record));
        }
        System.out.println("Loading " + engineers.size() + " engineers into DB.");

        engineerRepository.saveAll(engineers);
    }

    private Engineer mapRecordToEngineer(CSVRecord record) {
        Engineer engineer = new Engineer();

        String fullName = record.get(0);
        String username = fullName.replaceAll(" ", ".").toLowerCase();

        engineer.setFullName(fullName);
        engineer.setUsername(username);
        engineer.setJobTitle(record.get(1));

        String title = record.get(2);
        engineer.setIsManager((title.contains("Manager")));
        engineer.setDepartment(record.get(3));
        engineer.setTeam(record.get(4));
        engineer.setLocation(record.get(5));
        engineer.setHasBeenInterviewed(interviewRepository.findByEngineerId(username).isPresent());
        return engineer;
    }

    public List<Engineer> findAll() {
        return engineerRepository.findAll();
    }
}

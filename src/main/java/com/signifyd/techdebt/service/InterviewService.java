package com.signifyd.techdebt.service;

import com.signifyd.techdebt.dao.EngineerRepository;
import com.signifyd.techdebt.dao.InterviewFormRepository;
import com.signifyd.techdebt.dao.InterviewRepository;
import com.signifyd.techdebt.model.Engineer;
import com.signifyd.techdebt.model.Interview;
import com.signifyd.techdebt.model.InterviewForm;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class InterviewService {

    final
    InterviewRepository interviewRepository;
    final
    InterviewFormRepository interviewFormRepository;
    final
    EngineerRepository engineerRepository;

    public InterviewService(InterviewRepository interviewRepository, InterviewFormRepository interviewFormRepository, EngineerRepository engineerRepository) {
        this.interviewRepository = interviewRepository;
        this.interviewFormRepository = interviewFormRepository;
        this.engineerRepository = engineerRepository;
    }

    public Interview persistInterview(Interview interview) {
        if (interview.getEngineer() != null) {
            Engineer engineerExists = interview.getEngineer();
            interview.setEngineer(engineerExists);
            engineerExists.setHasBeenInterviewed(true);
            engineerRepository.save(engineerExists);
        }
        return interviewRepository.save(interview);
    }

    @PostConstruct
    public void initialiseInterviewForm() {
        if(interviewFormRepository.findAll().size() == 0) {
            InterviewForm interview = new InterviewForm();
            List<String> questions = new ArrayList<>();
            questions.add("Example Question 1");
            questions.add("Example Question 2");
            interview.setQuestions(questions);
            interview.setVersion(-1);
            interviewFormRepository.save(interview);
        }
    }

    public InterviewForm getForm() {
        List<InterviewForm> interviewList = interviewFormRepository.findAll(Sort.by("version").descending());
        return interviewList.get(0);
    }

    public InterviewForm updateForm(InterviewForm interviewForm, Boolean updateVersion) {
        if (updateVersion) {
            interviewForm.setVersion((interviewForm.getVersion() + 1));
        }
        InterviewForm savedForm = interviewFormRepository.save(interviewForm);
        return savedForm;
    }

    public Interview getInterview(String username) {

        Engineer engineer = engineerRepository.findById(username).get();

        Optional<Interview> interviewOpt = interviewRepository.findByEngineerId(engineer.getUsername());

        if (interviewOpt.isPresent()) {
            return interviewOpt.get();
        } else {
            return constructInterview(engineer);
        }
    }

    private Interview constructInterview(Engineer engineer) {
        List<InterviewForm> interviewList = interviewFormRepository.findAll(Sort.by("version").descending());
        InterviewForm interviewForm = interviewList.get(0);
        Interview interview = new Interview();
        Map<String, String> questionsAndAnswers = new LinkedHashMap<>();
        for (String question : interviewForm.getQuestions()) {
            questionsAndAnswers.put(question, "");
        }
        interview.setQuestionsAndAnswers(questionsAndAnswers);
        interview.setEngineer(engineer);
        return interview;
    }
}

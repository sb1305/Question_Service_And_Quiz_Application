package com.example.demo.service;

import com.example.demo.model.Question;
import com.example.demo.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//same as @Component but with diff name, used it here bsc just randomly bsc its a service layer
public class QuestionService {

    @Autowired
    QuestionDao questionDao;
    public List<Question> getAllQuestion() {
        return questionDao.findAll();
    }

    public List<Question> getAllQuestionsByCategory(String category) {
        return questionDao.findByCategory(category);
    }

    public String addQuestion(Question question) {
        questionDao.save(question);
        return "success";
    }
}

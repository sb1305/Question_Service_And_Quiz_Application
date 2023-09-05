package com.example.demo.service;

import com.example.demo.dao.QuestionDao;
import com.example.demo.dao.QuizDao;
import com.example.demo.model.Question;
import com.example.demo.model.QuestionWrapper;
import com.example.demo.model.Quiz;
import com.example.demo.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuestionDao questionDao;
    @Autowired
    QuizDao quizDao;
    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Question> questions=questionDao.findRandomQuestionByCategory(category, numQ);

        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        quiz.setQuestion(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("Succes", HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(Integer id) {
        Optional<Quiz> quiz=quizDao.findById(id);
        List<Question> questionsFromDB=quiz.get().getQuestion();
        List<QuestionWrapper> questionForUser = new ArrayList<>();
        for(Question q : questionsFromDB){
            QuestionWrapper qw=new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            questionForUser.add(qw);

        }
        return new ResponseEntity<>(questionForUser, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz=quizDao.findById(id).get();
        List<Question> questions=quiz.getQuestion();
        int right=0,i=0;
        for(Response response:responses){
            if(response.getResponse().equals(questions.get(i).getRightAns()))right++;
            i++;
        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}

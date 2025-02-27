package com.ashahar.quiz_service.Service;

import com.ashahar.quiz_service.Dao.QuizDao;
import com.ashahar.quiz_service.Feign.QuizInterface;
import com.ashahar.quiz_service.Model.QuestionWrapper;
import com.ashahar.quiz_service.Model.Quiz;
import com.ashahar.quiz_service.Model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String category, Integer numQ, String title) {

        List<Integer> questions = quizInterface.getQuestionForQuiz(category, numQ).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Quiz created successfully", HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuiz(Integer id) {
        Quiz quiz = quizDao.findById(id).get();
        List<Integer> questionIds = quiz.getQuestions();
        ResponseEntity<List<QuestionWrapper>> questions = quizInterface.getQuestionsById(questionIds);
        return questions;
    }

    public ResponseEntity<Integer> submitQuiz(Integer id, List<Response> responses) {

        ResponseEntity<Integer> score = quizInterface.getScore(responses);
        System.out.println(score);

        return score;
    }
}

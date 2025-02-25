package com.ashahar.quiz_service.Service;

import com.ashahar.quiz_service.Dao.QuizDao;
import com.ashahar.quiz_service.Model.Question;
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

//    @Autowired
//    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, Integer numQ, String title) {
//
//        List<Question> questions = questionDao.findRandomQuesByCategory(category, numQ);
//
//        Quiz quiz = new Quiz();
//        quiz.setTitle(title);
//        quiz.setQuestions(questions);
//        quizDao.save(quiz);
//
        return new ResponseEntity<>("Quiz created successfully", HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuiz(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questionfromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questions = new ArrayList<>();

        for(Question Q: questionfromDB){
            QuestionWrapper questionWrapper = new QuestionWrapper(Q.getId(),
                            Q.getQuestionTitle(),
                            Q.getOption1(),
                            Q.getOption2(),
                            Q.getOption3(),
                            Q.getOption4());
            questions.add(questionWrapper);
        }

        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    public ResponseEntity<Integer> submitQuiz(Integer id, List<Response> responses) {
        Quiz quiz = quizDao.findById(id).get();
        List<Question> questions = quiz.getQuestions();

        int right = 0;
        int i = 0;
        for(Response response: responses){
            System.out.println(response.getResponse());
            System.out.println(questions.get(i).getRightAnswer());
            if(response.getResponse().equals(questions.get(i).getRightAnswer())){
                right++;
            }
            i++;
        }


        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}

package com.ashahar.question_service.Service;

import com.ashahar.question_service.Dao.QuestionDao;
import com.ashahar.question_service.Model.Question;
import com.ashahar.question_service.Model.QuestionWrapper;
import com.ashahar.question_service.Model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class questionService {

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions() {
        return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
    }

    public List<Question> getQuestionsByCategory(String category) {
        return questionDao.findByCategory(category);
    }

    public void addQuestion(Question question) {
        questionDao.save(question);
    }

    public ResponseEntity<List<Integer>> getQuestionForQuiz(String category, Integer numQues) {
        List<Integer> questions = questionDao.findRandomQuesByCategory(category, numQues);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsById(List<Integer> ids) {
        List<QuestionWrapper> wrappers = new ArrayList<>();
        List<Question> questions = new ArrayList<>();

        for(Integer id: ids){
            questions.add(questionDao.findById(id).get());
        }

        for(Question Q: questions){
            QuestionWrapper wrapper = new QuestionWrapper(Q.getId(),
                    Q.getQuestionTitle(),
                    Q.getOption1(),
                    Q.getOption2(),
                    Q.getOption3(),
                    Q.getOption4());
            wrappers.add(wrapper);
        }


        return new ResponseEntity<>(wrappers, HttpStatus.OK);
    }

    public ResponseEntity<Integer> getscore(List<Response> responses) {


        int right = 0;
        for(Response response: responses){
            Question question = questionDao.findById(response.getId()).get();
            if(response.getResponse().equals(question.getRightAnswer())){
                right++;
            }
        }


        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}

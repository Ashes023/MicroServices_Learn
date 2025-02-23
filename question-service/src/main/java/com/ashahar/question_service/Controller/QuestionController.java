package com.ashahar.question_service.Controller;

import com.ashahar.question_service.Model.Question;
import com.ashahar.question_service.Model.QuestionWrapper;
import com.ashahar.question_service.Model.Response;
import com.ashahar.question_service.Service.questionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    questionService questionService;

    @RequestMapping("/allQuestions")
    public ResponseEntity<List<Question>> getQuestions() {
        try{
            return questionService.getAllQuestions();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping("/category/{category}")
    public List<Question> getQuestionsByCategory(@PathVariable String category) {
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("/addQuestion")
    public String addQuestion(@RequestBody Question question) {
        questionService.addQuestion(question);
        return "Question added successfully";
    }

    @GetMapping("/generate")
    public ResponseEntity<List<Integer>> getQuestionForQuiz(@RequestParam String category, @RequestParam Integer numQues){
        return questionService.getQuestionForQuiz(category, numQues);
    }

    @PostMapping("/getquestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsById(@RequestBody List<Integer> ids){
        return questionService.getQuestionsById(ids);
    }

    @PostMapping("/score")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
        return questionService.getscore(responses);
    }
}
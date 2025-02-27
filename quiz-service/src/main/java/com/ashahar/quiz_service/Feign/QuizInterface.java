package com.ashahar.quiz_service.Feign;

import com.ashahar.quiz_service.Model.QuestionWrapper;
import com.ashahar.quiz_service.Model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {

    @GetMapping("/questions/generate")
    public ResponseEntity<List<Integer>> getQuestionForQuiz(@RequestParam String category, @RequestParam Integer numQues);

    @PostMapping("/questions/getquestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsById(@RequestBody List<Integer> ids);

    @PostMapping("/questions/score")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);

}

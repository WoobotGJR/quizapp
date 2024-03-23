package com.woobot.quizapp.controller;

import com.woobot.quizapp.model.QuestionWrapper;
import com.woobot.quizapp.model.Quiz;
import com.woobot.quizapp.model.Response;
import com.woobot.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {
    @Autowired
    QuizService quizService;
    @PostMapping("create")
    public ResponseEntity<Quiz> createQuiz(@RequestParam String category, @RequestParam int questionsNum, @RequestParam String title) {
        return ResponseEntity.ok(quizService.createQuiz(category, questionsNum, title));
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable int id) {
        return ResponseEntity.ok(quizService.getQuizQuestions(id));
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable int id, @RequestBody List<Response> responses) {
        return quizService.calculateResult(id, responses);
    }
}

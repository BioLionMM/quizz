package com.quizz.controller;


import com.quizz.model.Question;
import com.quizz.service.QuizzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class QuestionController {
    @Autowired
    QuizzService quizzService;

    @GetMapping("questions")
    public List<Question> getAllQuestions(){
        return quizzService.getAllQuestions();
    }

    @PostMapping("questions")
    public void addQuestion(@RequestBody Question question){
        quizzService.addQuestion(question);
    }

    @PostMapping("manyquestions")
    public void addManyQuestions(@RequestBody Question[] listQuestions){
        for(Question question:listQuestions){
            quizzService.addQuestion(question);
        }
    }

    @GetMapping("questions/{id}")
    public ResponseEntity<Question> findQuestionById(@PathVariable Long id){
        Optional<Question> o = quizzService.findQuestion(id);
        if(o.isPresent()){
            Question question=o.get();
            return ResponseEntity.status(HttpStatus.OK).body(question);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("questions/{id}")
    public ResponseEntity<Question> deleteQuestionById(@PathVariable Long id){
        Optional<Question> o = quizzService.findQuestion(id);
        if(o.isPresent()){
            quizzService.deleteQuestion(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("questions/{id}")
    public ResponseEntity<Question> updateQuestionById(@PathVariable Long id){
        Optional<Question> o = quizzService.findQuestion(id);
        if(o.isPresent()){
            quizzService.updateQuestion(o.get());
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

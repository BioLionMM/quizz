package com.quizz.controller;

import com.quizz.model.Reponse;
import com.quizz.service.QuizzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ReponseController {
    @Autowired
    QuizzService quizzService;

    @GetMapping("reponses")
    public List<Reponse> getAllReponses(){
        return quizzService.getAllReponses();
    }

    @PostMapping("reponses")
    public void addReponse(Reponse reponse){
        quizzService.addReponse(reponse);
    }

    @GetMapping("reponses/{id}")
    public ResponseEntity<Reponse> findReponseById(@PathVariable Long id){
        Optional<Reponse> o = quizzService.findReponse(id);
        if(o.isPresent()){
            Reponse reponse=o.get();
            return ResponseEntity.status(HttpStatus.OK).body(reponse);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("reponses/{id}")
    public ResponseEntity<Reponse> deleteReponseById(@PathVariable Long id){
        Optional<Reponse> o = quizzService.findReponse(id);
        if(o.isPresent()){
            quizzService.deleteReponse(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("reponses/{id}")
    public ResponseEntity<Reponse> updateReponseById(@PathVariable Long id){
        Optional<Reponse> o = quizzService.findReponse(id);
        if(o.isPresent()){
            quizzService.updateReponse(o.get());
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}

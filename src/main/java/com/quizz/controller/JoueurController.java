package com.quizz.controller;

import com.quizz.model.Joueur;
import com.quizz.model.Reponse;
import com.quizz.service.QuizzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class JoueurController {
    @Autowired
    QuizzService quizzService;

    @GetMapping("joueurs")
    public List<Joueur> getAllJoueurs(){
        return quizzService.getAllJoueurs();
    }

    @GetMapping("meilleursjoueurs")
    public List<Joueur> getBestJoueurs(){
        return quizzService.getBestJoueurs();
    }

    @PostMapping("joueurs")
    public void addJoueur(@RequestBody Joueur joueur){
        quizzService.addJoueur(joueur);
    }

    @GetMapping("joueurs/{id}")
    public ResponseEntity<Joueur> findJoueurById(@PathVariable Long id){
        Optional<Joueur> o = quizzService.findJoueur(id);
        if(o.isPresent()){
            Joueur joueur=o.get();
            return ResponseEntity.status(HttpStatus.OK).body(joueur);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("joueurs/{id}")
    public ResponseEntity<Joueur> deleteJoueurById(@PathVariable Long id){
        Optional<Joueur> o = quizzService.findJoueur(id);
        if(o.isPresent()){
            quizzService.deleteJoueur(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("joueurs/{id}")
    public ResponseEntity<Joueur> updateJoueurById(@PathVariable Long id){
        Optional<Joueur> o = quizzService.findJoueur(id);
        if(o.isPresent()){
            quizzService.updateJoueur(o.get());
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

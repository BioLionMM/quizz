package com.quizz.controller;

import com.quizz.model.Joueur;
import com.quizz.service.QuizzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@SessionAttributes({"joueur"})
public class JoueurControllerHTML {
    @Autowired
    QuizzService quizzService;

    @ModelAttribute("joueur")
    public Joueur setUpJoueurForm() {
        return new Joueur();
    }

    @GetMapping("topScores")
    public String getTopScores(Model model){
        model.addAttribute("topScores",quizzService.getBestJoueurs());
        return "topscores.html";
    }

    @PostMapping("creerJoueur")
    public RedirectView postJoueur(Model model,@ModelAttribute("joueur") Joueur joueur){
        quizzService.addJoueur(joueur);
        model.addAttribute("joueur",joueur);
        //QuestionControllerHTML questionController=new QuestionControllerHTML();
        //return questionController.getAllQuestions();
        //return "quizz.html";
        return new RedirectView("questionsList");
    }

    @GetMapping("creerJoueur")
    public String creerJoueur(){
        return "creerJoueur.html";
    }
}

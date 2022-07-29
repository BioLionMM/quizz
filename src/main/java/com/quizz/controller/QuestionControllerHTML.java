package com.quizz.controller;

import com.quizz.model.Joueur;
import com.quizz.model.Question;
import com.quizz.model.Reponse;
import com.quizz.service.QuizzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes({"joueur","score"})
public class QuestionControllerHTML {
    @Autowired
    QuizzService quizzService;

    @ModelAttribute("score")
    public int setUpScoreForm() {
        int score=0;
        return score;
    }


    @GetMapping("questionsList")
    public String getAllQuestions(Model model){
        model.addAttribute("listquestions",quizzService.getAllQuestions());
        /*if(model.getAttribute("indexQuestion")==null){
            model.addAttribute("indexQuestion",0);
            model.addAttribute("question",quizzService.getAllQuestions().get(0));
        }else{
            model.addAttribute("question",quizzService.findQuestion((Long)model.getAttribute("indexQuestion")));
        }*/

        return "quizz.html";
    }

    @PostMapping("questionsList")
    public RedirectView checkAnswer(Model model,
                                    @ModelAttribute("question") Question question,
                                    @RequestParam Map<String, String> responseMap,
                                    HttpSession session
                              ){
        /*@RequestParam("reponse") Long choixReponse
        @SessionAttribute("joueur") Joueur joueur,
        @SessionAttribute("score") int score
        if(choixReponse==question.getIndexBonneReponse()){
                score++;
        }*/
        //have to go through questions and find if reponses selected are right
        int score=0;
        for(Map.Entry<String,String> entree :responseMap.entrySet()){
            Long questionIdEntree=Long.parseLong(entree.getKey());
            String choixReponse=entree.getValue();
            //Long reponseIdEntree=Long.parseLong(entree.getValue());
            String bonneReponse= quizzService.findQuestion(questionIdEntree).get().returnRightReponse().getContenuReponse();
            //Long.valueOf(quizzService.findQuestion(questionIdEntree).get().getIndexBonneReponse());
            if(choixReponse==bonneReponse){
                System.out.println("Bonne reponse.");
                score=(int)model.getAttribute("score")+1;
                model.addAttribute("score",score);
            }
            else{
                System.out.println("Mauvaise reponse");
            }
        }
        //add to joueur score
        Joueur joueur= (Joueur) model.getAttribute("joueur");
        if(joueur!=null){
            joueur.setMeilleurScore(score);
        }


        /*if(model.getAttribute("indexQuestion")==null){
            model.addAttribute("indexQuestion",1);
        }else{
            Long indexQuestion= (Long) model.getAttribute("indexQuestion");
            model.addAttribute("indexQuestion",indexQuestion++);
        }*/
        return new RedirectView("topScores");
    }
}

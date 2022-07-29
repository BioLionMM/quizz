package com.quizz.service;

import com.quizz.model.Joueur;
import com.quizz.model.Question;
import com.quizz.model.Reponse;
import com.quizz.repository.JoueurRepository;
import com.quizz.repository.QuestionRepository;
import com.quizz.repository.ReponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizzService {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    ReponseRepository reponseRepository;

    @Autowired
    JoueurRepository joueurRepository;

    public List<Reponse> getAllReponses() {
        return reponseRepository.findAll();
    }

    public void addReponse(Reponse reponse){
        reponseRepository.save(reponse);
    }

    public Optional<Reponse> findReponse(Long id){
        return reponseRepository.findById(id);
    }

    public void deleteReponse(Long id){
        if(reponseRepository.existsById(id)) {
            reponseRepository.deleteById(id);
        }
    }

    public void updateReponse(Reponse reponse) {
        if(reponseRepository.existsById(reponse.getId())) {
            reponseRepository.save(reponse);
        }
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public void addQuestion(Question question){
        questionRepository.save(question);
        for(Reponse reponse:question.getListeReponses()){
            reponse.setQuestionCorrespondante(question);
            reponseRepository.save(reponse);
        }
    }

    public Optional<Question> findQuestion(Long id){
        return questionRepository.findById(id);
    }

    public void deleteQuestion(Long id){
        if(questionRepository.existsById(id)) {
            Question question=questionRepository.findById(id).get();
            for(Reponse reponse:question.getListeReponses()){
                reponseRepository.delete(reponse);
            }
            questionRepository.deleteById(id);
        }
    }

    public void updateQuestion(Question question) {
        if(questionRepository.existsById(question.getId())) {
            questionRepository.save(question);
        }
    }



    /////////////////////////////////////////////
    public List<Joueur> getAllJoueurs() {
        return joueurRepository.findAll();
    }

    public List<Joueur> getBestJoueurs() {
        return joueurRepository.findAll(Sort.by(Sort.Direction.ASC, "meilleurScore"));
    }

    public void addJoueur(Joueur joueur){
        joueurRepository.save(joueur);
    }

    public Optional<Joueur> findJoueur(Long id){
        return joueurRepository.findById(id);
    }

    public void deleteJoueur(Long id){
        if(joueurRepository.existsById(id)) {
            Joueur joueur=joueurRepository.findById(id).get();
            joueurRepository.deleteById(id);
        }
    }

    public void updateJoueur(Joueur joueur) {
        if(joueurRepository.existsById(joueur.getId())) {
            joueurRepository.save(joueur);
        }
    }
}

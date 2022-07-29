package com.quizz.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String contenuQuestion;
    @OneToMany(mappedBy="questionCorrespondante",fetch=FetchType.EAGER,cascade=CascadeType.ALL)
    private List<Reponse> listeReponses=new ArrayList<Reponse>();
    private int indexBonneReponse;

    public Question(){}

    public Question(Long id, String contenuQuestion, List<Reponse> listeReponses,int indexBonneReponse) {
        this.id = id;
        this.contenuQuestion = contenuQuestion;
        this.listeReponses = listeReponses;
        this.indexBonneReponse=indexBonneReponse;
    }

    public void addReponse(Reponse reponse) {
        this.listeReponses.add(reponse);
        reponse.setQuestionCorrespondante(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContenuQuestion() {
        return contenuQuestion;
    }

    public void setContenuQuestion(String contenuQuestion) {
        this.contenuQuestion = contenuQuestion;
    }

    public List<Reponse> getListeReponses() {
        return listeReponses;
    }

    public void setListeReponses(List<Reponse> listeReponses) {
        this.listeReponses = listeReponses;
    }

    public int getIndexBonneReponse() {
        return indexBonneReponse;
    }

    public void setIndexBonneReponse(int indexBonneReponse) {
        this.indexBonneReponse = indexBonneReponse;
    }

    ////////////////////////////
    public Reponse returnRightReponse(){
        return this.getListeReponses().get(this.getIndexBonneReponse());
    }

    public boolean isRight(Reponse reponse){
        return(this.returnRightReponse()==reponse);
    }

    public boolean isRight(int indexReponse){
        return(this.returnRightReponse()==this.getListeReponses().get(indexReponse));
    }

    @Override
    public String toString() {
        String displayQuestion=this.contenuQuestion;
        for(int reponseIndex=0;reponseIndex<listeReponses.size();reponseIndex++){
            displayQuestion=displayQuestion+"\n"+
                    reponseIndex+": "+listeReponses.get(reponseIndex).getContenuReponse();
        }
        return displayQuestion;
    }
}

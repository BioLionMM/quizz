package com.quizz.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="reponses")
public class Reponse {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String contenuReponse;
    @ManyToOne
    //@JoinColumn(name="questionId")
    @JsonIgnore()
    private Question questionCorrespondante;

    public Reponse(){}
    public Reponse(Long id, String contenuReponse) {
        this.id = id;
        this.contenuReponse = contenuReponse;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContenuReponse() {
        return contenuReponse;
    }

    public void setContenuReponse(String contenuReponse) {
        this.contenuReponse = contenuReponse;
    }

    public Question getQuestionCorrespondante() {
        return questionCorrespondante;
    }

    public void setQuestionCorrespondante(Question questionCorrespondante) {
        this.questionCorrespondante = questionCorrespondante;
    }
}

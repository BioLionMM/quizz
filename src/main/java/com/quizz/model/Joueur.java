package com.quizz.model;

import javax.persistence.*;

@Entity
@Table(name="joueurs")
public class Joueur {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String pseudo;
    private int meilleurScore;

    public Joueur(){}

    public Joueur(String pseudo) {
        this.pseudo = pseudo;
        this.meilleurScore = 0;
    }
    public Joueur(String pseudo, int meilleurScore) {
        this.pseudo = pseudo;
        this.meilleurScore = meilleurScore;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public int getMeilleurScore() {
        return meilleurScore;
    }

    public void setMeilleurScore(int score) {
        if(score>this.meilleurScore){
            this.meilleurScore = score;
        }
    }

    public void jouer(Jeu jeu){
        int newScore=jeu.jouer();
        if(newScore>this.meilleurScore){
            setMeilleurScore(newScore);
        }
    }
}

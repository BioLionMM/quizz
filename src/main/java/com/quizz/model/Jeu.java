package com.quizz.model;

import java.util.List;
import java.util.Scanner;

public class Jeu {
    private List<Question> listeQuestions;
    private int scoreQuizz;

    public Jeu(List<Question> listeQuestions) {

        this.listeQuestions = listeQuestions;
        this.scoreQuizz=0;
    }

    public List<Question> getListeQuestions() {
        return listeQuestions;
    }

    public void setListeQuestions(List<Question> listeQuestions) {
        this.listeQuestions = listeQuestions;
    }

    public int jouer(){
        for(Question question:listeQuestions){
            System.out.println(question);
            Scanner input=new Scanner(System.in);
            if(question.isRight(input.nextInt())){
                System.out.println("Bien joue!");
                this.scoreQuizz++;
            }else{
                System.out.println("He non. La reponse etait: "+question.returnRightReponse().getContenuReponse());
            }
        }
        System.out.println("Votre score est de "+scoreQuizz+"points!");
        return scoreQuizz;
    }

}

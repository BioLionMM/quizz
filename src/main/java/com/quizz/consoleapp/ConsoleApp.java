package com.quizz.consoleapp;

import com.quizz.model.Jeu;
import com.quizz.model.Question;
import com.quizz.model.Reponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleApp {
    public static void main(String[] args){
        Question questionA=new Question(1L,
                "Qui est le 25eme pokemon?",
                List.of(new Reponse[]{new Reponse(1L, "Bulbizarre"),
                        new Reponse(2L, "Salameche"),
                        new Reponse(3L, "Pikachu"),
                        new Reponse(4L, "Raichu")}),
                2);

        Question questionB=new Question(2L,
                "Qui est le pokemon fetiche de Jessie et James",
                List.of(new Reponse[]{new Reponse(1L, "Miaouss"),
                        new Reponse(2L, "Salameche"),
                        new Reponse(3L, "Pikachu"),
                        new Reponse(4L, "Raichu")}),
                0);


        System.out.println("Jeu Quiz");

        Scanner clavier = new Scanner(System.in);

        Jeu jeu=new Jeu(new ArrayList<Question>(List.of(new Question[]{questionA, questionB})));
        jeu.jouer();
    }

}

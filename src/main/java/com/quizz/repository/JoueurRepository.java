package com.quizz.repository;

import com.quizz.model.Joueur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JoueurRepository extends JpaRepository<Joueur,Long> {
}

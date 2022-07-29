package com.quizz;

import com.quizz.model.Question;
import com.quizz.model.Reponse;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class QuizzApplicationTests {

	@Test
	void validateAnswer() {
		List<Reponse> reponses= List.of(new Reponse[]{new Reponse(1L, "Bulbizarre"),
				new Reponse(2L, "Salameche"),
				new Reponse(3L, "Pikachu"),
				new Reponse(4L, "Raichu")});
		Question question=new Question(1L,
				"Qui est le 25eme pokemon?",
				reponses,
				2);
		Assertions.assertTrue(question.isRight(2));
		Assertions.assertFalse(question.isRight(0));
	}

}

package com.kolmak.Hangman;

import java.io.IOException;
import java.util.*;

public class HangmanApp {
    public static void main(String[] args) throws IOException {
        Dictionary dictionary = new Dictionary();
        GameLogic gameLogic;
        boolean playAgain;

        do {
            gameLogic = new GameLogic(dictionary.generateRandomWord());

            while (!gameLogic.gameOver()) {
                gameLogic.run();
            }

            System.out.println("Хотите сыграть ещё? - нажмите 'Д' - [ДА] или нажмите 'Н' - [НЕТ]");
            char userAnswer;
            playAgain = false;

            do {
                try {
                    userAnswer = gameLogic.readLetter();
                    if (userAnswer == 'Д') {
                        playAgain = true;
                        break;
                    } else if (userAnswer == 'Н') {
                        playAgain = false;
                        break;
                    } else {
                        System.out.println("Некорректный ввод. Попробуйте ещё раз: ");
                    }
                } finally {

                }
            } while (true);

            if (playAgain) {
                gameLogic.resetGame();
            }
        }
        while(playAgain);
    }
}
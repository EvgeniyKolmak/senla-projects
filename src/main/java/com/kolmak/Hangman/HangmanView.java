package com.kolmak.Hangman;

import java.util.Set;

/**
 * Класс {@code HangmanView} отвечает за отображение состояния игры "Виселица",
 * включая текущее изображение виселицы, количество оставшихся жизней,
 * правильные и неправильные буквы, а также прогресс угадываемого слова.
 */
public class HangmanView {
    private static final String[] VIEWS_OF_HANGMAN = {
            "  +------+\n" +
                    "  | /\n" +
                    "  |\n" +
                    "  |\n" +
                    "  |\n" +
                    "  |\n" +
                    " ===========\n",

            "  +------+\n" +
                    "  | /    |\n" +
                    "  |\n" +
                    "  |\n" +
                    "  |\n" +
                    "  |\n" +
                    " ===========\n",

            "  +------+\n" +
                    "  | /    |\n" +
                    "  |      0\n" +
                    "  |\n" +
                    "  |\n" +
                    "  |\n" +
                    " ===========\n",

            "  +------+\n" +
                    "  | /    |\n" +
                    "  |      0\n" +
                    "  |      |\n" +
                    "  |\n" +
                    "  |\n" +
                    " ===========\n",

            "  +------+\n" +
                    "  | /    |\n" +
                    "  |      0\n" +
                    "  |     /|\n" +
                    "  |\n" +
                    "  |\n" +
                    " ===========\n",

            "  +------+\n" +
                    "  | /    |\n" +
                    "  |      0\n" +
                    "  |     /|\\ \n" +
                    "  |\n" +
                    "  |\n" +
                    " ===========\n",

            "  +------+\n" +
                    "  | /    |\n" +
                    "  |      0\n" +
                    "  |     /|\\ \n" +
                    "  |     /\n" +
                    "  |\n" +
                    " ===========\n",

            "  +------+\n" +
                    "  | /    |\n" +
                    "  |      0\n" +
                    "  |     /|\\ \n" +
                    "  |     / \\ \n" +
                    "  |\n" +
                    " ===========\n"
    };

    /**
     * Отображает текущее состояние угадываемого слова, открывая угаданные буквы.
     *
     * @param guessedWord слово, которое игроки должны угадать.
     * @param letters     множество букв, которые были угаданы.
     */
    public void displayWordStatus(String guessedWord, Set<Character> letters) {
        StringBuilder hiddenWord = new StringBuilder(guessedWord.length());
        for(char l : guessedWord.toCharArray()) {
            if(letters.contains(l)) {
                hiddenWord.append(l);
            } else {
                hiddenWord.append('*');
            }
            hiddenWord.append(" ");
        }
        System.out.println("Текущее состояние угадываемого слова: " + hiddenWord);
    }

    /**
     * Отображает текущее состояние виселицы на основе количества оставшихся жизней.
     *
     * @param lives количество оставшихся жизней.
     */
    public void displayHangman(int lives) {
        System.out.println(VIEWS_OF_HANGMAN[VIEWS_OF_HANGMAN.length - lives - 1]);
    }

    /**
     * Отображает количество оставшихся жизней.
     *
     * @param livesLeft количество оставшихся жизней.
     */
    public void displayLivesLeft(int livesLeft) {
        System.out.println("Кол-во жизней осталось: " + livesLeft);
    }

    /**
     * Отображает буквы, которые были угаданы правильно.
     *
     * @param usedLetters множество правильно угаданных букв.
     */
    public void displayCorrectLetters(Set<Character> usedLetters) {
        StringBuilder sb = new StringBuilder();
        for (char l : usedLetters) {
            sb.append(l);
            sb.append(" ");
        }
        System.out.println("Правильные буквы: " + sb);
    }

    /**
     * Отображает буквы, которые были угаданы неправильно.
     *
     * @param usedLetters множество неправильно угаданных букв.
     */
    public void displayIncorrectLetters(Set<Character> usedLetters) {
        StringBuilder sb = new StringBuilder();
        for (char l : usedLetters) {
            sb.append(l);
            sb.append(" ");
        }
        System.out.println("Неправильные буквы: " +sb);
    }
}

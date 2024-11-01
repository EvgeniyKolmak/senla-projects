package com.kolmak.Hangman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Класс {@code GameLogic} управляет основной логикой игры "Виселица",
 * включая ввод букв, отслеживание оставшихся жизней и обновление прогресса игры.
 */
public class GameLogic {
    private static final int COUNT_OF_LIVES = 7;
    private String guessedWord;
    private String hiddenWord;
    private int livesLeft;
    private Set<Character> correctLetters;
    private Set<Character> incorrectLetters;
    private HangmanView hangmanView;

    /**
     * Конструктор создает объект {@code GameLogic}, инициализируя игровую логику
     * и задавая слово для угадывания.
     *
     * @param guessedWord слово, которое игроки должны угадать.
     */
    public GameLogic(String guessedWord) {
        this.guessedWord = guessedWord.toLowerCase().toUpperCase();
        this.hiddenWord = generateHiddenWord(guessedWord.length());
        this.livesLeft = COUNT_OF_LIVES;
        this.correctLetters = new HashSet<>();
        this.incorrectLetters = new HashSet<>();
        this.hangmanView = new HangmanView();
    }

    /**
     * Генерирует скрытую версию слова с помощью символов '*'.
     *
     * @param length длина слова для угадывания.
     * @return строка, состоящая из символов '*', представляющая скрытое слово.
     */
    private String generateHiddenWord(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append("*");
        }
        return sb.toString();
    }

    /**
     * Читает и возвращает одну букву, введенную пользователем.
     *
     * @return буква, введенная пользователем.
     */
    public char readLetter() {
        String inputData;
        do {
            System.out.println("\nВведите букву: ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
            inputData = "";
            try {
                inputData = br.readLine().toUpperCase();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (inputData.trim().length() != 1 || !Character.isLetter(inputData.trim().charAt(0))) {
                System.out.println("Некорректный ввод. Попробуйте ещё раз: ");
            }
        } while (inputData.trim().length() != 1 || !Character.isLetter(inputData.trim().charAt(0)));
        return inputData.charAt(0);
    }

    /**
     * Запускает игру "Виселица", предлагая пользователю начать или выйти.
     */
    public void run() {
        System.out.println("Добро пожаловать в игру 'Виселица'!\n" +
                "Чтобы начать игру нажмите 'Н' - [НАЧАТЬ] или нажмите 'В' - [ВЫЙТИ], чтобы завершить игру.");

        char letter;
        do {
            letter = readLetter();

            if(letter == 'Н') {
                playGame();
                return;
            } else if(letter == 'В') {
                System.out.println("До свидания!");
                return;
            } else {
                System.out.println("Некоректные данные, попробуйте ещё раз!");
            }
        } while (true);
    }

    /**
     * Основной игровой процесс, обрабатывающий ввод букв и обновляющий состояние игры.
     */
    public void playGame () {
        while (!gameOver()) {
            hangmanView.displayHangman(getLivesLeft());
            hangmanView.displayWordStatus(getHiddenWord(), correctLetters);
            hangmanView.displayCorrectLetters(correctLetters);
            hangmanView.displayIncorrectLetters(incorrectLetters);
            hangmanView.displayLivesLeft(livesLeft);

            char letter = readLetter();
            if (isGuessedLetter(letter)) {
                if (isWordGuessed()) {
                    hangmanView.displayWordStatus(getHiddenWord(), correctLetters);
                    System.out.println("Поздравляем. Вы выиграли!");
                    return;
                }
            } else {
                System.out.println("Некоректные данные, попробуйте ещё раз!");
                System.out.println();
            }
        }

        hangmanView.displayHangman(livesLeft);
        System.out.println("ВЫ ПРОИГРАЛИ! Задуманным было слово: " + getGuessedWord());
        System.out.println();
    }

    /**
     * Проверяет, угадано ли всё слово.
     *
     * @return {@code true}, если слово угадано полностью, иначе {@code false}.
     */
    public boolean  isWordGuessed() {
        return guessedWord.equals(hiddenWord);
    }

    /**
     * Проверяет, окончена ли игра, на основе оставшихся жизней.
     *
     * @return {@code true}, если жизни закончились, иначе {@code false}.
     */
    public boolean gameOver() {
        return livesLeft == 0;
    }

    /**
     * Возвращает слово, которое нужно угадать.
     *
     * @return загаданное слово.
     */
    public String getGuessedWord() {
        return guessedWord;
    }

    /**
     * Возвращает текущее скрытое слово с открытыми угаданными буквами.
     *
     * @return скрытое слово с раскрытыми угаданными буквами.
     */
    public String getHiddenWord() {
        return hiddenWord;
    }

    /**
     * Возвращает количество оставшихся жизней.
     *
     * @return количество оставшихся жизней.
     */
    public int getLivesLeft() {
        return livesLeft;
    }

    /**
     * Обрабатывает введенную букву и обновляет состояние игры в зависимости от того,
     * правильно ли угадана буква.
     *
     * @param letter буква, введенная пользователем.
     * @return {@code true}, если буква есть в слове, иначе {@code false}.
     */
    public boolean isGuessedLetter(char letter) {
        boolean letterFound = false;
        for (int i = 0; i < guessedWord.length(); i++) {
            if (guessedWord.charAt(i) == Character.toUpperCase(letter)) {
                hiddenWord = hiddenWord.substring(0, i) + letter + hiddenWord.substring(i + 1);
                letterFound = true;
            }
        }

        if(letterFound) {
            correctLetters.add(letter);
        } else {
            livesLeft--;
            incorrectLetters.add(letter);
        }

        return letterFound;
    }

    /**
     * Сбрасывает состояние игры, включая количество жизней, угаданные буквы и скрытое слово.
     */
    public void resetGame() {
        livesLeft = COUNT_OF_LIVES;
        correctLetters.clear();
        incorrectLetters.clear();
        hiddenWord = generateHiddenWord(guessedWord.length());
    }
}

package com.kolmak.Hangman;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Класс Dictionary загружает слова из файла и предоставляет случайное слово для игры.
 */
public class Dictionary {
    private ArrayList<String> wordList;
    private String[] words;
    private static final String FILENAME = "src/main/resources/russian_nouns.txt";

    /**
     * Конструктор Dictionary, загружает слова из файла по умолчанию.
     * @throws IOException если возникает ошибка при загрузке файла.
     */
    public Dictionary() throws IOException {
        this(FILENAME);
    }

    /**
     * Конструктор Dictionary, загружает слова из указанного файла.
     * @param fileName имя файла для загрузки слов.
     * @throws IOException если возникает ошибка при загрузке файла.
     */
    public Dictionary(String fileName) throws IOException{
        loadWordsFromFile();
        words = wordList.toArray(new String[0]);
    }

    /**
     * Загружает слова из файла в список слов.
     * @throws IOException если возникает ошибка при загрузке файла.
     */
    public void loadWordsFromFile() throws IOException {
        File file = new File(FILENAME);
        try (FileReader fileReader = new FileReader(file)) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            wordList  = new ArrayList<>();
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                str.trim();
                if(!str.isEmpty()) {
                    wordList.add(str);
                }
            }
        }
    }

    /**
     * Генерирует и возвращает случайное слово из загруженного списка слов.
     * @return случайное слово.
     */
    public String generateRandomWord() {
        Random random = new Random();
        int indexOfWord = random.nextInt(words.length);
        return words[indexOfWord];
    }
}

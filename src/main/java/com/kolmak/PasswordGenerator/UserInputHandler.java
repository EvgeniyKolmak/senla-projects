package com.kolmak.PasswordGenerator;

import java.util.Scanner;

/**
 * Класс UserInputHandler обрабатывает ввод пользователя для определения параметров пароля,
 * таких как длина и используемые символы.
 */
public class UserInputHandler {
    private static final int MIN_LENGTH = 8;
    private static final int MAX_LENGTH = 12;

    /**
     * Метод createPassword получает от пользователя параметры и создает пароль.
     *
     * @return сгенерированный пароль на основе параметров пользователя
     */
    public String createPassword() {
        Scanner scanner = new Scanner(System.in);

        // Запрос длины пароля
        int size = getPasswordLength(scanner);

        // Генерация пароля
        PasswordBuilder passwordBuilder = new PasswordBuilder(size);
        return passwordBuilder.generatePassword();
    }

    /**
     * Запрашивает у пользователя длину пароля и проверяет, что она находится в заданном диапазоне.
     *
     * @param scanner объект Scanner для считывания ввода
     * @return допустимая длина пароля, введенная пользователем
     */
    public static int getPasswordLength(Scanner scanner) {
        int size;
        while (true) {
            try {
                System.out.printf("Здравствуйте! Пароль какой длины вам нужен (%s - %s символов)? - ", MIN_LENGTH, MAX_LENGTH);
                size = scanner.nextInt();

                if (size >= MIN_LENGTH && size <= MAX_LENGTH) {
                    break;
                } else {
                    System.out.printf("Ошибка: длина пароля должна быть строго от %s до %s символов.\n", MIN_LENGTH, MAX_LENGTH);
                }
            } catch (Exception e) {
                System.out.println("Ошибка: пожалуйста, введите числовое значение.");
                scanner.next();
            }
        }
        return size;
    }
}

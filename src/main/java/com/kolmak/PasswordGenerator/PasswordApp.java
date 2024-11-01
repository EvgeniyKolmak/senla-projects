package com.kolmak.PasswordGenerator;

import java.util.Random;
import java.util.Scanner;

public class PasswordApp {
    private static final String SYMBOLS_TO_USE = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$%^&*()-_=+<>?";

    public static void main(String[] args) {
        // Создание экземпляра обработчика ввода для взаимодействия с пользователем
        UserInputHandler inputHandler = new UserInputHandler();
        String password = inputHandler.createPassword();

        // Вывод сгенерированного пароля
        System.out.println("Вот ваш новый пароль: " + password);
    }
}

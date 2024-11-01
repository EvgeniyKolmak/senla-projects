package com.kolmak.PasswordGenerator;

import java.util.Random;

/**
 * Класс PasswordBuilder выполняет генерацию пароля с использованием заданного набора символов и длины.
 * Позволяет пользователю выбрать символы, которые будут использоваться, и задать длину пароля.
 */
public class PasswordBuilder {
    private static final String SYMBOLS_TO_USE = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$%^&*()-_=+<>?";
    private final int size;

    /**
     * Конструктор PasswordBuilder создает генератор с заданными параметрами.
     *
     * @param size длина создаваемого пароля
     */
    public PasswordBuilder(int size) {
        this.size = size;
    }

    /**
     * Метод generatePassword выполняет генерацию случайного пароля.
     *
     * @return сгенерированный пароль в виде строки
     */
    public String generatePassword() {
        Random random = new Random();
        StringBuilder password = new StringBuilder(size);

        for (int i = 0; i < size; i++) {
            int index = random.nextInt(SYMBOLS_TO_USE.length());
            password.append(SYMBOLS_TO_USE.charAt(index));
        }

        return password.toString();
    }
}

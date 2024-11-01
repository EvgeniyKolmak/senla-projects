package com.kolmak.ExchangeRate;

import java.util.Scanner;

public class CurrencyApp {

    public static void main(String[] args) {
        // Создаем объект CurrencyConverter для загрузки курсов обмена из файла
        CurrencyConverter currencyConverter = new CurrencyConverter("src/main/resources/exchange_rates.txt");
        Scanner scanner = new Scanner(System.in);

        // Запрос у пользователя исходной валюты
        System.out.println("Введите исходную валюту (USD, EUR, GBP, CNY, RUB, BYN): ");
        String fromCurrency = scanner.nextLine().toUpperCase();

        // Запрос у пользователя целевой валюты
        System.out.println("Введите целевую валюту (USD, EUR, GBP, CNY, RUB, BYN): ");
        String toCurrency = scanner.nextLine().toUpperCase();

        // Запрос у пользователя суммы для конвертации
        System.out.printf("Введите сумму в %s: ", fromCurrency);
        double amount = scanner.nextDouble();

        // Выполнение конвертации и вывод результата
        double result = currencyConverter.convert(fromCurrency, toCurrency, amount);
        if (result != -1) {
            System.out.printf("Сумма в %s: %.2f", toCurrency, result);
        }
    }
}

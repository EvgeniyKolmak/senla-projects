package com.kolmak.ExchangeRate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс CurrencyConverter предоставляет функционал для конвертации валют
 * на основе курсов, загруженных из текстового файла. Каждый курс обмена
 * указывает отношение одной валюты к другой.
 */
public class CurrencyConverter {

    /** Хранит курсы обмена валют, где ключ представляет собой строку в формате "FROM,TO". */
    private final Map<String, Double> exchangeRate = new HashMap<>();

    /**
     * Конструктор класса, который загружает курсы обмена валют из указанного файла.
     *
     * @param fileName имя файла, содержащего курсы обмена валют. Формат каждой строки:
     *                 FROM,TO,RATE, где FROM и TO — валюты, а RATE — курс обмена.
     */
    public CurrencyConverter(String fileName) {
        loadExchangeRates(fileName);
    }

    /**
     * Загружает курсы обмена валют из текстового файла в карту exchangeRates.
     * Формат файла: каждая строка должна содержать FROM,TO,RATE, где FROM и TO — валюты,
     * а RATE — курс обмена. Например: "USD,EUR,0.85".
     *
     * @param fileName имя файла, содержащего курсы обмена валют.
     */
    public void loadExchangeRates(String fileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(",");
                if(parts.length == 3) {
                    String fromToCurrency = parts[0] + "," + parts[1];
                    double rate = Double.parseDouble(parts[2]);
                    exchangeRate.put(fromToCurrency, rate);
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }

    }

    /**
     * Конвертирует сумму из одной валюты в другую, используя загруженные курсы обмена.
     *
     * @param fromCurrency валюта, из которой выполняется конвертация, в формате кода валюты (например, "USD").
     * @param toCurrency валюта, в которую выполняется конвертация, в формате кода валюты (например, "EUR").
     * @param amount сумма в исходной валюте, которую требуется конвертировать.
     * @return конвертированная сумма в целевой валюте. Если курс обмена не найден, возвращает -1.
     */
    public double convert(String fromCurrency, String toCurrency, double amount) {
        String key = fromCurrency + "," + toCurrency;
        String reverseKey = toCurrency + "," + fromCurrency;
        double rate;

        if(exchangeRate.containsKey(key)) {
            rate = exchangeRate.get(key);
        } else if (exchangeRate.containsKey(reverseKey)) {
            rate = 1 / exchangeRate.get(reverseKey);
        } else {
            System.out.println("Курс обмена для выбранных валют не найден!\n");
            return -1;
        }

        return amount * rate;
    }
}


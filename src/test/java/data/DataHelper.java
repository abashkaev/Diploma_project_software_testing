package data;

import com.github.javafaker.Faker;
import io.qameta.allure.Param;
import lombok.Value;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Random;

public class DataHelper {
    private static Faker faker = new Faker();
    private static Faker rusFaker = new Faker(new Locale("ru_RU"));

    public static String getApprovedCardNumber() {
        String cardNumber = "'4444 4444 4444 4441";
        return cardNumber;
    }

    public static String getDeclinedCardNumber() {
        String cardNumber = "'4444 4444 4444 4442";
        return cardNumber;
    }
    public static String getRandomCardNumber () {
        return faker.business().creditCardNumber();
    }
    public static String getValidCvv () {
        Random random = new Random();
        int validCvv = random.nextInt(1000);
        String validCvvStr = String.format("%03d", validCvv);
        return String.valueOf(validCvvStr);
    }
    public static String validCardHolderName () {
        return faker.name().fullName();
    }
    public static String getRussianName () {
        return rusFaker.name().fullName();
    }


    public static String getCurrentMonth() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM");
        String currentMonthStr = currentDate.format(formatter);
        return currentMonthStr;
    }

    public static String getCurrentYear() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YY");
        String currentYearStr = currentDate.format(formatter);
        return currentYearStr;
    }

    public static SetDate getCurrentDate() { // Получение текущей даты для полей (Месяц, Год)
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatterMonth = DateTimeFormatter.ofPattern("MM");
        DateTimeFormatter formatterYear = DateTimeFormatter.ofPattern("yy");
        String currentMonthStr = currentDate.format(formatterMonth);
        String currentYearStr = currentDate.format(formatterYear);
        return new SetDate(currentMonthStr, currentYearStr);
    }

    public static SetDate getOtherDate(int month) { // В параметр вносится количество месяцев, насколько должен отличатся от текущего
        LocalDate currentDate = LocalDate.now();
        LocalDate pastDate = currentDate.plusMonths(month);
        DateTimeFormatter formatterMonth = DateTimeFormatter.ofPattern("MM");
        DateTimeFormatter formatterYear = DateTimeFormatter.ofPattern("yy");
        String monthStr = pastDate.format(formatterMonth);
        String yearStr = pastDate.format(formatterYear);
        return new SetDate(monthStr, yearStr);
    }



    @Value
    public static class SetDate {
        String month;
        String year;
    }
}

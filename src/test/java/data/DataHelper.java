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
        String cardNumber = "4444 4444 4444 4441";
        return cardNumber;
    }

    public static String getDeclinedCardNumber() {
        String cardNumber = "4444 4444 4444 4442";
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
    public static String getValidNameWithDash () {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String alterLastname = faker.name().lastName();
        return  lastName + "-" + alterLastname + " " + firstName;
    }


    public static SetDate getCurrentDate() { // Получение текущей даты для полей (Месяц, Год)
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatterMonth = DateTimeFormatter.ofPattern("MM");
        DateTimeFormatter formatterYear = DateTimeFormatter.ofPattern("yy");
        String currentMonthStr = currentDate.format(formatterMonth);
        String currentYearStr = currentDate.format(formatterYear);
        return new SetDate(currentMonthStr, currentYearStr);
    }
    public static String getCurrentMonth (){
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatterMonth = DateTimeFormatter.ofPattern("MM");
        String currentMonthStr = currentDate.format(formatterMonth);
        return currentMonthStr;
    }
    public static String getCurrentYear() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatterYear = DateTimeFormatter.ofPattern("yy");
        String currentYearStr = currentDate.format(formatterYear);
        return currentYearStr;
    }
    public static String getNextYear() {
        LocalDate currentDate = LocalDate.now();
        LocalDate futureDate = currentDate.plusYears(1);
        DateTimeFormatter formatterYear = DateTimeFormatter.ofPattern("yy");
        String nextYearStr = futureDate.format(formatterYear);
        return nextYearStr;
    }
    public static SetDate getOtherDate(int month) { // В параметр вносится количество месяцев, насколько должен отличатся от текущего
        LocalDate currentDate = LocalDate.now();
        LocalDate otherDate = currentDate.plusMonths(month);
        DateTimeFormatter formatterMonth = DateTimeFormatter.ofPattern("MM");
        DateTimeFormatter formatterYear = DateTimeFormatter.ofPattern("yy");
        String monthStr = otherDate.format(formatterMonth);
        String yearStr = otherDate.format(formatterYear);
        return new SetDate(monthStr, yearStr);
    }
    public static SetDate getNextYearJanuary () {
        LocalDate currentDate = LocalDate.now();
        LocalDate januaryNextYear = currentDate.plusYears(1).withMonth(1);
        DateTimeFormatter formatterMonth = DateTimeFormatter.ofPattern("MM");
        DateTimeFormatter formatterYear = DateTimeFormatter.ofPattern("yy");
        String monthStr = januaryNextYear.format(formatterMonth);
        String yearStr = januaryNextYear.format(formatterYear);
        return new SetDate(monthStr, yearStr);
    }
    public static SetDate getNextYearDecember () {
        LocalDate currentDate = LocalDate.now();
        LocalDate januaryNextYear = currentDate.plusYears(1).withMonth(12);
        DateTimeFormatter formatterMonth = DateTimeFormatter.ofPattern("MM");
        DateTimeFormatter formatterYear = DateTimeFormatter.ofPattern("yy");
        String monthStr = januaryNextYear.format(formatterMonth);
        String yearStr = januaryNextYear.format(formatterYear);
        return new SetDate(monthStr, yearStr);

    }
    public static String getRandomOneNumber () {
        String randomNumb = String.valueOf(faker.number().numberBetween(0,9));
        return randomNumb;
    }
    public static String setCustomValue (String customValue) {
        return customValue;
    }

    public static String getSpecialCharacters () {
        String specialCharacters = "!@#$%^&&*()_+";
        return specialCharacters;
    }




    @Value
    public static class SetDate {
        String month;
        String year;
    }
}

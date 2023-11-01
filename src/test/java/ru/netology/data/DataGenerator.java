package ru.netology.data;

import com.github.javafaker.Faker;

import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.time.LocalDate;

public class DataGenerator {
    private static Faker faker = new Faker(Locale.ENGLISH);
    private static Faker rufaker = new Faker(new Locale("ru"));

    public static String getApprovedCardNumber() {
        return "4444 4444 4444 4441";
    }

    public static String getDeclinedCardNumber() {
        return "4444 4444 4444 4442";
    }

    public static String getInvalidCardNumber() {
        return "4444 4444 4444 4444";
    }

    public static String getIncompleteCardNumber() {
        return "4444 4444 4444 444";
    }

    public static String getNullValue() {
        return null;
    }

    public static String getLastMonth() {
        return "12";
    }

    public static String getCurrentMonth() {
        String currentMonth = LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
        return currentMonth;
    }

    public static String getPreviousMonth() {
        String previousMonth = LocalDate.now().minusMonths(1).format(DateTimeFormatter.ofPattern("MM"));
        return previousMonth;
    }

    public static String getInvalidLowerLimitMonth() {
        return "00";
    }

    public static String getInvalidUpperLimitMonth() {
        return "13";
    }

    public static String getInvalidMiddleMonth() {
        return "56";
    }

    public static String getInvalidMaxMonth() {
        return "99";
    }

    public static String getCurrentYear() {
        String currentYear = LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
        return currentYear;
    }

    public static String getNextYear() {
        String nextYear = LocalDate.now().plusYears(1).format(DateTimeFormatter.ofPattern("yy"));
        return nextYear;
    }

    public static String getPreviousYear() {
        String previousYear = LocalDate.now().minusYears(1).format(DateTimeFormatter.ofPattern("yy"));
        return previousYear;
    }

    public static String getUpperLimitYear() {
        String upperLimitYear = LocalDate.now().plusYears(5).format(DateTimeFormatter.ofPattern("yy"));
        return upperLimitYear;
    }

    public static String getInvalidUpperLimitYear() {
        String InvalidUpperLimitYear = LocalDate.now().plusYears(6).format(DateTimeFormatter.ofPattern("yy"));
        return InvalidUpperLimitYear;
    }

    public static String getInvalidLowerLimitYear() {
        return "00";
    }

    public static String getInvalidMiddleYear() {
        return "56";
    }

    public static String getInvalidMaxYear() {
        return "99";
    }

    public static String getValidHolderName() {
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    public static String getValidCamelCaseHolderName() {
        return "eGoR PetROv";
    }

    public static String getFullValidHolderName() {
        return faker.name().firstName() + " " + faker.name().firstName() + "-" + faker.name().firstName() + " " + faker.name().lastName() + "-" + faker.name().lastName();
    }

    public static String getInvalidNumberHolderName() {
        return faker.number().digits(15);
    }

    public static String getInvalidSpecialCharacterHolderName() {
        return "!”№;%:?*()_+?><”}{";
    }

    public static String getInvalidOneLetterHolderName() {
        return faker.lorem().characters(1);
    }

    public static String getInvalidMaxLettersHolderName() {
        return faker.lorem().characters(65);
    }

    public static String getInvalidWebAddressHolderName() {
        return faker.internet().domainName();
    }

    public static String getInvalidTenSpaceButtonHolderName() {
        return "          ";
    }

    public static String getInvalidCyrillicHolderName() {
        return rufaker.name().firstName() + " " + rufaker.name().lastName();
    }

    public static String getValidCvc() {
        return faker.number().digits(3);
    }

    public static String getInvalidTwoCvc() {
        return faker.number().digits(2);
    }

    public static String getInvalidOneCvc() {
        return faker.number().digits(1);
    }

}

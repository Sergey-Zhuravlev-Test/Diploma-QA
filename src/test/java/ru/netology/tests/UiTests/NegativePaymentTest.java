package ru.netology.tests.UiTests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.CardInfo;
import ru.netology.data.DBHelper;
import ru.netology.page.Notifications;
import ru.netology.page.PaymentPage;
import ru.netology.page.StartPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.DataGenerator.*;

public class NegativePaymentTest {
    private static final String sutURL = System.getProperty("sut.url");
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    void setup() {
        open(sutURL);
        DBHelper.clearDB();
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    @DisplayName("1. Оплата по карте с вводом невалидного значения на вкладке “Купить” с несуществующим номером карты")
    void shouldFailureBuyWithInvalidCard() {
        CardInfo cardInfo = new CardInfo(getInvalidCardNumber(), getCurrentMonth(), getNextYear(), getValidHolderName(), getValidCvc());
        var startPage = new StartPage();
        startPage.buy();
        var paymentPage = new PaymentPage();
        paymentPage.fillingPaymentForm(cardInfo);
        var notification = new Notifications();
        notification.errorNotification();
    }

    @Test
    @DisplayName("2. Оплата по карте с вводом невалидного значения на вкладке “Купить” с неполным номером карты")
    void shouldFailureBuyWithIncompleteCard() {
        CardInfo cardInfo = new CardInfo(getIncompleteCardNumber(), getCurrentMonth(), getNextYear(), getValidHolderName(), getValidCvc());
        var startPage = new StartPage();
        startPage.buy();
        var paymentPage = new PaymentPage();
        paymentPage.fillingPaymentForm(cardInfo);
        var notification = new Notifications();
        notification.invalidFormatField();
    }

    @Test
    @DisplayName("3. Оплата по карте с вводом невалидного значения на вкладке “Купить” с незаполненным номером карты")
    void shouldFailureBuyWithEmptyCard() {
        CardInfo cardInfo = new CardInfo(getNullValue(), getCurrentMonth(), getNextYear(), getValidHolderName(), getValidCvc());
        var startPage = new StartPage();
        startPage.buy();
        var paymentPage = new PaymentPage();
        paymentPage.fillingPaymentForm(cardInfo);
        var notification = new Notifications();
        notification.invalidFormatField();
    }

    @Test
    @DisplayName("4. Оплата по карте с вводом невалидного значения в поле “Месяц” по нижней границе на вкладке “Купить”")
    void shouldFailureBuyWithLowerInvalidMonth() {
        CardInfo cardInfo = new CardInfo(getApprovedCardNumber(), getInvalidLowerLimitMonth(), getNextYear(), getValidHolderName(), getValidCvc());
        var startPage = new StartPage();
        startPage.buy();
        var paymentPage = new PaymentPage();
        paymentPage.fillingPaymentForm(cardInfo);
        var notification = new Notifications();
        notification.invalidDateField();
    }

    @Test
    @DisplayName("5. Оплата по карте с вводом невалидного значения в поле “Месяц” по верхней границе на вкладке “Купить”")
    void shouldFailureBuyWithUpperInvalidMonth() {
        CardInfo cardInfo = new CardInfo(getApprovedCardNumber(), getInvalidUpperLimitMonth(), getNextYear(), getValidHolderName(), getValidCvc());
        var startPage = new StartPage();
        startPage.buy();
        var paymentPage = new PaymentPage();
        paymentPage.fillingPaymentForm(cardInfo);
        var notification = new Notifications();
        notification.invalidDateField();
    }

    @Test
    @DisplayName("6. Оплата по карте с вводом заведомо невалидного значения в поле “Месяц” на вкладке “Купить”")
    void shouldFailureBuyWithObviouslyInvalidMonth() {
        CardInfo cardInfo = new CardInfo(getApprovedCardNumber(), getInvalidMiddleMonth(), getNextYear(), getValidHolderName(), getValidCvc());
        var startPage = new StartPage();
        startPage.buy();
        var paymentPage = new PaymentPage();
        paymentPage.fillingPaymentForm(cardInfo);
        var notification = new Notifications();
        notification.invalidDateField();
    }

    @Test
    @DisplayName("7. Оплата по карте с вводом максимально возможного невалидного значения в поле “Месяц” на вкладке “Купить”")
    void shouldFailureBuyWithMaxInvalidMonth() {
        CardInfo cardInfo = new CardInfo(getApprovedCardNumber(), getInvalidMaxMonth(), getNextYear(), getValidHolderName(), getValidCvc());
        var startPage = new StartPage();
        startPage.buy();
        var paymentPage = new PaymentPage();
        paymentPage.fillingPaymentForm(cardInfo);
        var notification = new Notifications();
        notification.invalidDateField();
    }

    @Test
    @DisplayName("8. Оплата по карте с вводом невалидного значения в поле “Месяц” на вкладке “Купить” - ввод предыдущего месяца и текущего года")
    void shouldFailureBuyWithPreviousMonth() {
        CardInfo cardInfo = new CardInfo(getApprovedCardNumber(), getPreviousMonth(), getCurrentYear(), getValidHolderName(), getValidCvc());
        var startPage = new StartPage();
        startPage.buy();
        var paymentPage = new PaymentPage();
        paymentPage.fillingPaymentForm(cardInfo);
        var notification = new Notifications();
        notification.invalidDateField();
    }

    @Test
    @DisplayName("9. Оплата по карте с вводом невалидного значения в поле “Месяц” на вкладке “Купить” - поле оставить незаполненным")
    void shouldFailureBuyWithEmptyMonth() {
        CardInfo cardInfo = new CardInfo(getApprovedCardNumber(), getNullValue(), getCurrentYear(), getValidHolderName(), getValidCvc());
        var startPage = new StartPage();
        startPage.buy();
        var paymentPage = new PaymentPage();
        paymentPage.fillingPaymentForm(cardInfo);
        var notification = new Notifications();
        notification.invalidFormatField();
    }

    @Test
    @DisplayName("10. Оплата по карте с вводом невалидного значения в поле “Год” по нижней границе на вкладке “Купить”")
    void shouldFailureBuyWithPreviousYear() {
        CardInfo cardInfo = new CardInfo(getApprovedCardNumber(), getCurrentMonth(), getPreviousYear(), getValidHolderName(), getValidCvc());
        var startPage = new StartPage();
        startPage.buy();
        var paymentPage = new PaymentPage();
        paymentPage.fillingPaymentForm(cardInfo);
        var notification = new Notifications();
        notification.expiredDate();
    }

    @Test
    @DisplayName("11. Оплата по карте с вводом невалидного значения в поле “Год” по верхней границе на вкладке “Купить”")
    void shouldFailureBuyWithNextInvalidYear() {
        CardInfo cardInfo = new CardInfo(getApprovedCardNumber(), getCurrentMonth(), getInvalidUpperLimitYear(), getValidHolderName(), getValidCvc());
        var startPage = new StartPage();
        startPage.buy();
        var paymentPage = new PaymentPage();
        paymentPage.fillingPaymentForm(cardInfo);
        var notification = new Notifications();
        notification.invalidDateField();
    }

    @Test
    @DisplayName("12. Оплата по карте с вводом минимально возможного невалидного значения в поле “Год” на вкладке “Купить”")
    void shouldFailureBuyWithMinInvalidYear() {
        CardInfo cardInfo = new CardInfo(getApprovedCardNumber(), getCurrentMonth(), getInvalidLowerLimitYear(), getValidHolderName(), getValidCvc());
        var startPage = new StartPage();
        startPage.buy();
        var paymentPage = new PaymentPage();
        paymentPage.fillingPaymentForm(cardInfo);
        var notification = new Notifications();
        notification.expiredDate();
    }

    @Test
    @DisplayName("13. Оплата по карте с вводом максимально возможного невалидного значения в поле “Год” на вкладке “Купить”")
    void shouldFailureBuyWithMaxInvalidYear() {
        CardInfo cardInfo = new CardInfo(getApprovedCardNumber(), getCurrentMonth(), getInvalidMaxYear(), getValidHolderName(), getValidCvc());
        var startPage = new StartPage();
        startPage.buy();
        var paymentPage = new PaymentPage();
        paymentPage.fillingPaymentForm(cardInfo);
        var notification = new Notifications();
        notification.invalidDateField();
    }

    @Test
    @DisplayName("14. Оплата по карте с вводом заведомо невалидного значения в поле “Год” на вкладке “Купить”")
    void shouldFailureBuyWithObviouslyInvalidYear() {
        CardInfo cardInfo = new CardInfo(getApprovedCardNumber(), getCurrentMonth(), getInvalidMiddleYear(), getValidHolderName(), getValidCvc());
        var startPage = new StartPage();
        startPage.buy();
        var paymentPage = new PaymentPage();
        paymentPage.fillingPaymentForm(cardInfo);
        var notification = new Notifications();
        notification.invalidDateField();
    }

    @Test
    @DisplayName("15. Оплата по карте с вводом невалидного значения в поле “Год” на вкладке “Купить” - поле оставить незаполненным")
    void shouldFailureBuyWithEmptyYear() {
        CardInfo cardInfo = new CardInfo(getApprovedCardNumber(), getCurrentMonth(), getNullValue(), getValidHolderName(), getValidCvc());
        var startPage = new StartPage();
        startPage.buy();
        var paymentPage = new PaymentPage();
        paymentPage.fillingPaymentForm(cardInfo);
        var notification = new Notifications();
        notification.invalidFormatField();
    }

    @Test
    @DisplayName("16. Оплата по карте с вводом невалидного значения в поле “Владелец” на вкладке “Купить” - оставляем поле пустым")
    void shouldFailureBuyWithEmptyHolder() {
        CardInfo cardInfo = new CardInfo(getApprovedCardNumber(), getCurrentMonth(), getNextYear(), getNullValue(), getValidCvc());
        var startPage = new StartPage();
        startPage.buy();
        var paymentPage = new PaymentPage();
        paymentPage.fillingPaymentForm(cardInfo);
        var notification = new Notifications();
        notification.requiredField();
    }

    @Test
    @DisplayName("17. Оплата по карте с вводом невалидного значения в поле “Владелец” на вкладке “Купить” - заполняем поле любым количеством цифр")
    void shouldFailureBuyWithNumberHolder() {
        CardInfo cardInfo = new CardInfo(getApprovedCardNumber(), getCurrentMonth(), getNextYear(), getInvalidNumberHolderName(), getValidCvc());
        var startPage = new StartPage();
        startPage.buy();
        var paymentPage = new PaymentPage();
        paymentPage.fillingPaymentForm(cardInfo);
        var notification = new Notifications();
        notification.invalidFormatField();
    }

    @Test
    @DisplayName("18. Оплата по карте с вводом невалидного значения в поле “Владелец” на вкладке “Купить” - заполняем поле любым количеством спецсимволов")
    void shouldFailureBuyWithSpecialCharacterHolder() {
        CardInfo cardInfo = new CardInfo(getApprovedCardNumber(), getCurrentMonth(), getNextYear(), getInvalidSpecialCharacterHolderName(), getValidCvc());
        var startPage = new StartPage();
        startPage.buy();
        var paymentPage = new PaymentPage();
        paymentPage.fillingPaymentForm(cardInfo);
        var notification = new Notifications();
        notification.invalidFormatField();
    }

    @Test
    @DisplayName("19. Оплата по карте с вводом невалидного значения в поле “Владелец” на вкладке “Купить” - заполняем 1 буквой")
    void shouldFailureBuyWithOneLetterHolder() {
        CardInfo cardInfo = new CardInfo(getApprovedCardNumber(), getCurrentMonth(), getNextYear(), getInvalidOneLetterHolderName(), getValidCvc());
        var startPage = new StartPage();
        startPage.buy();
        var paymentPage = new PaymentPage();
        paymentPage.fillingPaymentForm(cardInfo);
        var notification = new Notifications();
        notification.invalidFormatField();
    }

    @Test
    @DisplayName("20. Оплата по карте с вводом невалидного значения в поле “Владелец” на вкладке “Купить” - заполняем текстом из 65 знаков")
    void shouldFailureBuyWithOverLimitHolder() {
        CardInfo cardInfo = new CardInfo(getApprovedCardNumber(), getCurrentMonth(), getNextYear(), getInvalidMaxLettersHolderName(), getValidCvc());
        var startPage = new StartPage();
        startPage.buy();
        var paymentPage = new PaymentPage();
        paymentPage.fillingPaymentForm(cardInfo);
        var notification = new Notifications();
        notification.invalidFormatField();
    }

    @Test
    @DisplayName("21. Оплата по карте с вводом невалидного значения в поле “Владелец” на вкладке “Купить” - заполняем поле ссылкой")
    void shouldFailureBuyWithWebAddressHolder() {
        CardInfo cardInfo = new CardInfo(getApprovedCardNumber(), getCurrentMonth(), getNextYear(), getInvalidWebAddressHolderName(), getValidCvc());
        var startPage = new StartPage();
        startPage.buy();
        var paymentPage = new PaymentPage();
        paymentPage.fillingPaymentForm(cardInfo);
        var notification = new Notifications();
        notification.invalidFormatField();
    }

    @Test
    @DisplayName("22. Оплата по карте с вводом невалидного значения в поле “Владелец” на вкладке “Купить” - заполняем пробелами")
    void shouldFailureBuyWithSpaceButtonHolder() {
        CardInfo cardInfo = new CardInfo(getApprovedCardNumber(), getCurrentMonth(), getNextYear(), getInvalidTenSpaceButtonHolderName(), getValidCvc());
        var startPage = new StartPage();
        startPage.buy();
        var paymentPage = new PaymentPage();
        paymentPage.fillingPaymentForm(cardInfo);
        var notification = new Notifications();
        notification.requiredField();
    }

    @Test
    @DisplayName("23. Оплата по карте с вводом невалидного значения в поле “Владелец” на вкладке “Купить” - заполняем кириллицей")
    void shouldFailureBuyWithCyrillicHolder() {
        CardInfo cardInfo = new CardInfo(getApprovedCardNumber(), getCurrentMonth(), getNextYear(), getInvalidCyrillicHolderName(), getValidCvc());
        var startPage = new StartPage();
        startPage.buy();
        var paymentPage = new PaymentPage();
        paymentPage.fillingPaymentForm(cardInfo);
        var notification = new Notifications();
        notification.invalidFormatField();
    }

    @Test
    @DisplayName("24. Оплата по карте с вводом невалидного значения в поле “CVC/CVV” на вкладке “Купить” - оставляем пустым")
    void shouldFailureBuyWithEmptyCVC() {
        CardInfo cardInfo = new CardInfo(getApprovedCardNumber(), getCurrentMonth(), getNextYear(), getValidHolderName(), getNullValue());
        var startPage = new StartPage();
        startPage.buy();
        var paymentPage = new PaymentPage();
        paymentPage.fillingPaymentForm(cardInfo);
        var notification = new Notifications();
        notification.invalidFormatField();
    }

    @Test
    @DisplayName("25. Оплата по карте с вводом невалидного значения в поле “CVC/CVV” на вкладке “Купить” - заполняем двумя любыми цифрами")
    void shouldFailureBuyWithTwoDigitsCVC() {
        CardInfo cardInfo = new CardInfo(getApprovedCardNumber(), getCurrentMonth(), getNextYear(), getValidHolderName(), getInvalidTwoCvc());
        var startPage = new StartPage();
        startPage.buy();
        var paymentPage = new PaymentPage();
        paymentPage.fillingPaymentForm(cardInfo);
        var notification = new Notifications();
        notification.invalidFormatField();
    }

    @Test
    @DisplayName("26. Оплата по карте с вводом невалидного значения в поле “CVC/CVV” на вкладке “Купить” - заполняем любой 1 цифрой")
    void shouldFailureBuyWithOneDigitCVC() {
        CardInfo cardInfo = new CardInfo(getApprovedCardNumber(), getCurrentMonth(), getNextYear(), getValidHolderName(), getInvalidOneCvc());
        var startPage = new StartPage();
        startPage.buy();
        var paymentPage = new PaymentPage();
        paymentPage.fillingPaymentForm(cardInfo);
        var notification = new Notifications();
        notification.invalidFormatField();
    }

}

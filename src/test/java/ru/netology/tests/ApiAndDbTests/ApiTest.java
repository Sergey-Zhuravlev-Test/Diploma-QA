package ru.netology.tests.ApiAndDbTests;


import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.ApiHelper;
import ru.netology.data.CardInfo;

import static ru.netology.data.DataGenerator.*;

public class ApiTest {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    @DisplayName("1. Отправка Post-запроса в PAYMENT GATE с валидными данными и номером карты со статусом APPROVED")
    void shouldSuccessfulPostApprovedInPayment() {
        CardInfo cardInfo = new CardInfo(getApprovedCardNumber(), getCurrentMonth(), getNextYear(), getValidHolderName(), getValidCvc());
        ApiHelper.paymentApprovedRequest(cardInfo);
    }

    @Test
    @DisplayName("2. Отправка Post-запроса в CREDIT GATE с валидными данными и номером карты со статусом APPROVED")
    void shouldSuccessfulPostApprovedInCredit() {
        CardInfo cardInfo = new CardInfo(getApprovedCardNumber(), getCurrentMonth(), getNextYear(), getValidHolderName(), getValidCvc());
        ApiHelper.creditApprovedRequest(cardInfo);
    }

    @Test
    @DisplayName("3. Отправка Post-запроса в PAYMENT GATE с валидными данными и номером карты со статусом DECLINED")
    void shouldSuccessfulPostDeclinedInPayment() {
        CardInfo cardInfo = new CardInfo(getDeclinedCardNumber(), getCurrentMonth(), getNextYear(), getValidHolderName(), getValidCvc());
        ApiHelper.paymentDeclinedRequest(cardInfo);
    }

    @Test
    @DisplayName("4. Отправка Post-запроса в CREDIT GATE с валидными данными и номером карты со статусом DECLINED")
    void shouldSuccessfulPostDeclinedInCredit() {
        CardInfo cardInfo = new CardInfo(getDeclinedCardNumber(), getCurrentMonth(), getNextYear(), getValidHolderName(), getValidCvc());
        ApiHelper.creditDeclinedRequest(cardInfo);
    }

    @Test
    @DisplayName("5. Отправка Post-запроса в PAYMENT GATE с неверным номером карты")
    void shouldPostWithFailureCardNumberInPayment() {
        CardInfo cardInfo = new CardInfo(getInvalidCardNumber(), getCurrentMonth(), getNextYear(), getValidHolderName(), getValidCvc());
        ApiHelper.paymentInvalidRequest(cardInfo);
    }

    @Test
    @DisplayName("6. Отправка Post-запроса в CREDIT GATE с неверным номером карты")
    void shouldPostWithFailureCardNumberInCredit() {
        CardInfo cardInfo = new CardInfo(getInvalidCardNumber(), getCurrentMonth(), getNextYear(), getValidHolderName(), getValidCvc());
        ApiHelper.creditInvalidRequest(cardInfo);
    }
}

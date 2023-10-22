package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.CardInfo;

import static com.codeborne.selenide.Selenide.$$;

public class PaymentPage {
    private SelenideElement buyHeading = $$("h3").findBy(Condition.text("Оплата по карте"));
    private SelenideElement cardNumberField = $$("[class='input__top']").findBy(Condition.text("Номер карты"));
    private SelenideElement monthField = $$("[class='input__top']").findBy(Condition.text("Месяц"));
    private SelenideElement yearField = $$("[class='input__top']").findBy(Condition.text("Год"));
    private SelenideElement holderField = $$("[class='input__top']").findBy(Condition.text("Владелец"));
    private SelenideElement cvcField = $$("[class='input__top']").findBy(Condition.text("CVC/CVV"));
    private SelenideElement continueButton = $$("button").findBy(Condition.text("Продолжить"));

    public PaymentPage() {
        buyHeading.shouldBe(Condition.visible);
    }

    public void fillingPaymentForm(CardInfo cardInfo) {
        cardNumberField.setValue(cardInfo.getCardNumber());
        monthField.setValue(cardInfo.getMonth());
        yearField.setValue(cardInfo.getYear());
        holderField.setValue(cardInfo.getHolderName());
        cvcField.setValue(cardInfo.getCvc());
        continueButton.click();
    }


}

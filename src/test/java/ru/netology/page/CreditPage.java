package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.CardInfo;

import static com.codeborne.selenide.Selenide.$$;

public class CreditPage {
    private SelenideElement creditHeading = $$("h3").findBy(Condition.text("Кредит по данным карты"));
    private SelenideElement cardNumberField = $$("[class='input__top']").findBy(Condition.text("Номер карты"));
    private SelenideElement monthField = $$("[class='input__top']").findBy(Condition.text("Месяц"));
    private SelenideElement yearField = $$("[class='input__top']").findBy(Condition.text("Год"));
    private SelenideElement holderField = $$("[class='input__top']").findBy(Condition.text("Владелец"));
    private SelenideElement cvcField = $$("[class='input__top']").findBy(Condition.text("CVC/CVV"));
    private SelenideElement continueButton = $$("button").findBy(Condition.text("Продолжить"));

    public CreditPage() {
        creditHeading.shouldBe(Condition.visible);
    }

    public void fillingCreditForm(CardInfo cardInfo) {
        cardNumberField.setValue(cardInfo.getCardNumber());
        monthField.setValue(cardInfo.getMonth());
        yearField.setValue(cardInfo.getYear());
        holderField.setValue(cardInfo.getHolderName());
        cvcField.setValue(cardInfo.getCvc());
        continueButton.click();
    }


}

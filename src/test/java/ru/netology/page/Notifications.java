package ru.netology.page;

import com.codeborne.selenide.Condition;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class Notifications {
    public void successNotification() {
        $$("[class='notification__title']").findBy(Condition.text("Успешно")).shouldBe(Condition.visible, Duration.ofSeconds(15));
        $$("[class='notification__content']").findBy(Condition.text("Операция одобрена Банком.")).shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void errorNotification() {
        $$("[class='notification__title']").findBy(Condition.text("Ошибка")).shouldBe(Condition.visible, Duration.ofSeconds(15));
        $$("[class='notification__content']").findBy(Condition.text("Ошибка! Банк отказал в проведении операции.")).shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void invalidFormatField() {
        $(".input__sub").shouldHave(Condition.text("Неверный формат")).shouldBe(Condition.visible);
    }

    public void requiredField() {
        $(".input__sub").shouldHave(Condition.text("Поле обязательно для заполнения")).shouldBe(Condition.visible);
    }

    public void invalidDateField() {
        $(".input__sub").shouldHave(Condition.text("Неверно указан срок действия карты")).shouldBe(Condition.visible);
    }

    public void expiredDate() {
        $(".input__sub").shouldHave(Condition.text("Истёк срок действия карты")).shouldBe(Condition.visible);
    }
}

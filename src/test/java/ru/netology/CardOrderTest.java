package ru.netology;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardOrderTest {

    @BeforeAll
    static  void setUpAll() {
        System.setProperty("webdriver.chrome.driver","C:\\NeuroStartUp\\aqa\\homeworksTheTask\\3\\CardOrder\\artifacts\\chromedriver.exe");
    }

    @Test
    void shouldTestCorrectForm () {
        open ("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Иванов Василий");
        $("[data-test-id=phone] input").setValue("+79273332211");
        $("[data-test-id=agreement]").click();
        $(".button__content").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldTestFormNameHyphen () {
        open ("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Иванов-Алекксаров Василий");
        $("[data-test-id=phone] input").setValue("+79273332211");
        $("[data-test-id=agreement]").click();
        $(".button__content").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldTestFormNameSpace () {
        open ("http://localhost:9999");
        $("[data-test-id=name] input").setValue("   Иванов Василий  ");
        $("[data-test-id=phone] input").setValue("+79273332211");
        $("[data-test-id=agreement]").click();
        $(".button__content").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldTestFormNameEmpty () {
        open ("http://localhost:9999");
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("+79273332211");
        $("[data-test-id=agreement]").click();
        $(".button__content").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldTestFormPhoneEmpty () {
        open ("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Иванов Василий");
        $("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement]").click();
        $(".button__content").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldTestFormPhoneIncorrectly () {
        open ("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Иванов Василий");
        $("[data-test-id=phone] input").setValue("+46546");
        $("[data-test-id=agreement]").click();
        $(".button__content").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldTestFormAgreementNot () {
        open ("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Иванов Василий");
        $("[data-test-id=phone] input").setValue("+79273332211");
        $(".button__content").click();
        $("[data-test-id=agreement].input_invalid").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }

    @Test
    void shouldTestFormNameAndPhoneAndAgreementEmpty () {
        open ("http://localhost:9999");
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("");
        $(".button__content").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldTestFormNameAndPhoneEmpty () {
        open ("http://localhost:9999");
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement]").click();
        $(".button__content").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

}


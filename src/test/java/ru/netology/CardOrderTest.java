package ru.netology;

import org.junit.jupiter.api.Test;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardOrderTest {

    @Test
    void shouldIncorrectName() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id='name'] input").setValue("Test");
        form.$("[data-test-id='phone'] input").setValue("+70123456789");
        form.$("[data-test-id='agreement']").click();
        form.$(".button").click();
        $(".input__sub").shouldHave(text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldIncorrectPhone() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id='name'] input").setValue("Салтыков-Щедрин Михаил");
        form.$("[data-test-id='phone'] input").setValue("70123456789");
        form.$("[data-test-id='agreement']").click();
        form.$(".button").click();
        $("[data-test-id='phone'].input__sub").shouldHave(text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldEmptyName() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id='phone'] input").setValue("+70123456789");
        form.$("[data-test-id='agreement']").click();
        form.$(".button").click();
        $(".input__sub").shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldEmptyPhone() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id='name'] input").setValue("Салтыков-Щедрин Михаил");
        form.$("[data-test-id='agreement']").click();
        form.$(".button").click();
        $("[data-test-id='phone'].input__sub").shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldEmptyCheckbox() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id='name'] input").setValue("Салтыков-Щедрин Михаил");
        form.$("[data-test-id='phone'] input").setValue("+70123456789");
        form.$(".button").click();
        $(".checkbox__text").shouldHave(text("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }
}

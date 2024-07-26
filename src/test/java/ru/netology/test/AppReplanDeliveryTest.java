package ru.netology.test;


import com.codeborne.selenide.Condition;


import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.Keys;


import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static ru.netology.test.DateGenerator.getDate;
import static ru.netology.test.UserGenerator.generateUser;

public class AppReplanDeliveryTest {
    User user = generateUser();
    String date1 = getDate(3);
    String date2 = getDate(4);
    String invalidDate = getDate(2);

@BeforeAll
static void setUpAll(){
    SelenideLogger.addListener("allure", new AllureSelenide());
}

@AfterAll
static void tearDownAll(){
    SelenideLogger.removeListener("allure");
}
    @Test
    void sendSuccess() {
        open("http://localhost:9999/");
        $("[data-test-id='city'] .input__control").sendKeys(user.getCity());
        $("[data-test-id='phone'] .input__control").sendKeys(user.getPhone());
        $("[data-test-id='date'] .input__control").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] .input__control").sendKeys(date1);
        $("[data-test-id='name'] .input__control").sendKeys(user.getName());
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $(".notification__content").shouldHave(Condition.text(date1)).shouldBe(Condition.visible);

    }

    @Test
    void successReplan() {

        open("http://localhost:9999/");
        $("[data-test-id='city'] .input__control").sendKeys(user.getCity());
        $("[data-test-id='phone'] .input__control").sendKeys(user.getPhone());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] .input__control").sendKeys(date1);
        $("[data-test-id='name'] .input__control").sendKeys(user.getName());
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $(".notification__content").shouldHave(Condition.text(date1)).shouldBe(Condition.visible);
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] .input__control").sendKeys(date2);
        $(".button").click();
        $("[data-test-id='replan-notification'] .button").click();
        $(".notification__content").shouldHave(Condition.text(date2)).shouldBe(Condition.visible);

    }

    @Test
    void failNameEn() {
        open("http://localhost:9999/");
        $("[data-test-id='city'] .input__control").sendKeys(user.getCity());
        $("[data-test-id='phone'] .input__control").sendKeys(user.getPhone());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] .input__control").sendKeys(date1);
        $("[data-test-id='name'] .input__control").sendKeys("Ivan");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $(byText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.")).shouldBe(Condition.visible);
    }

    @Test
    void failNameDigit() {
        open("http://localhost:9999/");
        $("[data-test-id='city'] .input__control").sendKeys(user.getCity());
        $("[data-test-id='phone'] .input__control").sendKeys(user.getPhone());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] .input__control").sendKeys(date1);
        $("[data-test-id='name'] .input__control").sendKeys("Иван1");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $(byText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.")).shouldBe(Condition.visible);
    }

    @Test
    void failNameSpecSymbol() {
        open("http://localhost:9999/");
        $("[data-test-id='city'] .input__control").sendKeys(user.getCity());
        $("[data-test-id='phone'] .input__control").sendKeys(user.getPhone());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] .input__control").sendKeys(date1);
        $("[data-test-id='name'] .input__control").sendKeys("Иван)");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $(byText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.")).shouldBe(Condition.visible);
    }

    @Test
    void failCity() {
        open("http://localhost:9999/");
        $("[data-test-id='city'] .input__control").sendKeys("Жуковский");
        $("[data-test-id='phone'] .input__control").sendKeys(user.getPhone());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] .input__control").sendKeys(date1);
        $("[data-test-id='name'] .input__control").sendKeys(user.getName());
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $(byText("Доставка в выбранный город недоступна")).shouldBe(Condition.visible);
    }

    @Test
    void failDate() {
        open("http://localhost:9999/");
        $("[data-test-id='city'] .input__control").sendKeys(user.getCity());
        $("[data-test-id='phone'] .input__control").sendKeys(user.getPhone());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] .input__control").sendKeys(invalidDate);
        $("[data-test-id='name'] .input__control").sendKeys(user.getName());
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $(byText("Заказ на выбранную дату невозможен")).shouldBe(Condition.visible);
    }

    @Test
    void failPhone10() {
        open("http://localhost:9999/");
        $("[data-test-id='city'] .input__control").sendKeys(user.getCity());
        $("[data-test-id='phone'] .input__control").sendKeys("+7000112");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] .input__control").sendKeys(date1);
        $("[data-test-id='name'] .input__control").sendKeys(user.getName());
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $(byText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.")).shouldBe(Condition.visible);
    }

    @Test
    void failPhone12() {
        open("http://localhost:9999/");
        $("[data-test-id='city'] .input__control").sendKeys(user.getCity());
        $("[data-test-id='phone'] .input__control").sendKeys("+700011222");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] .input__control").sendKeys(date1);
        $("[data-test-id='name'] .input__control").sendKeys(user.getName());
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $(byText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.")).shouldBe(Condition.visible);
    }

    @Test
    void nameWithЁ() {
        open("http://localhost:9999/");
        $("[data-test-id='city'] .input__control").sendKeys(user.getCity());
        $("[data-test-id='phone'] .input__control").sendKeys(user.getPhone());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] .input__control").sendKeys(date1);
        $("[data-test-id='name'] .input__control").sendKeys("Алёна");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $(".notification__content").shouldHave(Condition.text(date1)).shouldBe(Condition.visible);
    }


}
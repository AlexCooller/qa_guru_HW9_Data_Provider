import data.Language;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class ParametrizedTests extends TestBase {


    @ParameterizedTest(name = "Язык {0} ")
    @EnumSource(Language.class)
    @DisplayName("Тест языков через EnumSource")
    void gazpromSiteShouldDisplayCorrectTextTests(Language language) {
        open("https://www.gazprom.ru/");
        $(".language").$(byText(language.name())).click();
        $("menu").shouldHave(text(language.description));
    }


    @BeforeEach
    void setUp() {
        open("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }


    @ValueSource(strings = {
            "fghfgh", "fdghdfgerw", "12234123"
    })
    @ParameterizedTest(name = "Проверка пароля {0} ")
    @DisplayName("Проверка неверных паролей через ValueSource")
    void negativeTestPasswordsTests(String searchQuery) {
        $("[name=username]").setValue("Admin");
        $("[name=password]").setValue(searchQuery).pressEnter();
        $(".oxd-alert-content-text").shouldHave(text("Invalid credentials"));
    }



    @CsvSource(value = {
            "Master",
            "Root",
            "Adminka"
    })
    @ParameterizedTest(name = "Проверка логина {0} ")
    @DisplayName("Проверка неверных логинов через CSVSource")
    void negativeTestLogins(String searchQuery) {
        $("[name=username]").setValue(searchQuery);
        $("[name=password]").setValue("admin123").pressEnter();
        $(".oxd-alert-content-text").shouldHave(text("Invalid credentials"));
    }




}

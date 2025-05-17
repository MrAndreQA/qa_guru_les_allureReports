package allure;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.logevents.SelenideLogger.step;
import static org.openqa.selenium.By.linkText;

public class SelenideTest {

    private static final String REPOSITORY = "qa-guru/knowledge-base";
    private static final String TAB_NAME = "Issues";
    WebSteps steps = new WebSteps();

    @BeforeEach
    public void initListener() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }



    @Feature("Раздел Issue в репозитории")
    @Story("Отображение Issue")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Проверка названия Issue - чистый Selenide (с Listener)")
    @Link(value = "Testing", url = "https://testing.github.com")
    @Owner("Volodin_as")
    @Test
    public void issueNameSelenideTest() {
        open("https://github.com");
        $(".search-input-container").click();
        $("#query-builder-test").sendKeys(REPOSITORY);
        $("#query-builder-test").submit();
        $(linkText(REPOSITORY)).click();
        //*Проверочка*//
        $("[data-content=Issues]").shouldHave(text(TAB_NAME));
    }


    @Feature("Раздел Issue в репозитории")
    @Story("Отображение Issue")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Проверка названия Issue - лямбда шаги через step")
    @Link(value = "Testing", url = "https://testing.github.com")
    @Owner("Volodin_as")
    @Test
    public void issueNameLambdaStepTest() {
        step("Открываем главную страницу", () -> {
            open("https://github.com");
        });
        step("Ищем репозиторий " + REPOSITORY, () -> {
            $(".search-input-container").click();
            $("#query-builder-test").sendKeys(REPOSITORY);
            $("#query-builder-test").submit();
        });
        step("Кликаем по ссылке репозитория " + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });
        step("Проверяем, что название вкладки = " + TAB_NAME, () -> {
            $("[data-content=Issues]").shouldHave(text(TAB_NAME));
        });
    }

    @Feature("Раздел Issue в репозитории")
    @Story("Отображение Issue")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Проверка названия Issue - шаги с аннотацией @Step")
    @Link(value = "Testing", url = "https://testing.github.com")
    @Owner("Volodin_as")
    @Test
    public void issueNameStepAnnotationTest() {
        steps.openMainPage();
        steps.searchRepository(REPOSITORY);
        steps.clickToRepository(REPOSITORY);
        steps.checkTabName(TAB_NAME);
        steps.takeScreenshot();
    }








}
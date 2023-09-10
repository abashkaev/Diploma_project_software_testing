package PageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.files.DownloadActions.click;

public class HomePage {
    private final SelenideElement header = $(".heading");
    private final ElementsCollection buttons = $$(".button");
    private final SelenideElement buttonForPurchase = buttons.filterBy(Condition.text("Купить")).first();
    private final SelenideElement buttonForCredit = buttons.filterBy(Condition.text("Купить в кредит")).first();

    public void isOpenHomePage() {
        header.shouldHave(Condition.text("Путешествие дня"));
    }

    public void openByeForm() {
        buttonForPurchase.click();
    }
    public void openCreditForm () {
        buttonForCredit.click();
    }

}
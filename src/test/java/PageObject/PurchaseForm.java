package PageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.impl.CollectionElement;
import data.DataHelper;

import java.time.Duration;
import java.util.Collection;

import static com.codeborne.selenide.Selenide.*;

public class PurchaseForm {
    private final SelenideElement numberCardField = $x("//*[@id=\"root\"]/div/form/fieldset/div[1]/span/span/span[2]/input");

    private final SelenideElement monthField = $x("//*[@id=\"root\"]/div/form/fieldset/div[2]/span/span[1]/span/span/span[2]/input");
    private final SelenideElement yearField = $x("//*[@id=\"root\"]/div/form/fieldset/div[2]/span/span[2]/span/span/span[2]/input");
    private final SelenideElement NameField = $x("//*[@id=\"root\"]/div/form/fieldset/div[3]/span/span[1]/span/span/span[2]/input");
    private final SelenideElement cvvField = $x("//*[@id=\"root\"]/div/form/fieldset/div[3]/span/span[2]/span/span/span[2]/input");
    private final SelenideElement acceptButton = $x("//*[@id=\"root\"]/div/form/fieldset/div[4]/button");
    private final SelenideElement notification = $(".notification__content");
    public void setDate (DataHelper.SetDate date) {
        monthField.setValue(date.getMonth());
        yearField.setValue(date.getYear());
    }
    public void setCardHolderName (String name) {
        NameField.setValue(name);
    }
    public void setCvvField (String cvv) {
        cvvField.setValue(cvv);
    }
    public void setNumberCardField (String cardNumber) {
        numberCardField.setValue(cardNumber);
    }
    public void acceptButtonClick (){
        acceptButton.click();
    }
    public void notificationSuccess () {
        notification.shouldHave(Condition.text("Операция одобрена Банком.").visible.appear, Duration.ofSeconds(10));
    }
}

package PageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.impl.CollectionElement;
import data.DataHelper;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.util.Collection;

import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Selenide.*;

public class PurchaseForm {
    private final SelenideElement numberCardField = $x("//*[@id=\"root\"]/div/form/fieldset/div[1]/span/span/span[2]/input");

    private final SelenideElement monthField = $x("//*[@id=\"root\"]/div/form/fieldset/div[2]/span/span[1]/span/span/span[2]/input");
    private final SelenideElement yearField = $x("//*[@id=\"root\"]/div/form/fieldset/div[2]/span/span[2]/span/span/span[2]/input");
    private final SelenideElement NameField = $x("//*[@id=\"root\"]/div/form/fieldset/div[3]/span/span[1]/span/span/span[2]/input");
    private final SelenideElement cvvField = $x("//*[@id=\"root\"]/div/form/fieldset/div[3]/span/span[2]/span/span/span[2]/input");
    private final SelenideElement acceptButton = $x("//*[@id=\"root\"]/div/form/fieldset/div[4]/button");
    private final ElementsCollection notifications = $$(".notification__content");
    private final SelenideElement notificationSuccess = notifications.filterBy(Condition.text("Операция одобрена Банком.")).first();
    private final SelenideElement notificationError = notifications.filterBy(Condition.text("Ошибка! Банк отказал в проведении операции.")).first();
    private final SelenideElement messageSubCarNumber = $x("//*[@id=\"root\"]/div/form/fieldset/div[1]/span/span/span[3]");
    private final SelenideElement messageSubMonthField = $x("//*[@id=\"root\"]/div/form/fieldset/div[2]/span/span[1]/span/span/span[3]");
    private final SelenideElement messageSubYearField = $x("//*[@id=\"root\"]/div/form/fieldset/div[2]/span/span[2]/span/span/span[3]");
    private final SelenideElement messageSubCardholderNameField = $x("//*[@id=\"root\"]/div/form/fieldset/div[3]/span/span[1]/span/span/span[3]");
    private final SelenideElement messageSubCvvCadeField = $x("//*[@id=\"root\"]/div/form/fieldset/div[3]/span/span[2]/span/span/span[3]");
    public void setDate (DataHelper.SetDate date) {
        monthField.setValue(date.getMonth());
        yearField.setValue(date.getYear());
    }
    public void setMount (String mount) {
        monthField.setValue(mount);
    }
    public void setYear (String year) {
        yearField.setValue(year);
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
    public void isNotificationSuccess () {
        notificationSuccess.shouldBe(Condition.visible, Duration.ofSeconds(20));
    }
    public void isNotificationError () {
        notificationError.shouldBe(Condition.visible, Duration.ofSeconds(20));
    }
    public void emptyErrorSubCurdNumberField () {
        messageSubCarNumber.shouldHave(Condition.text("Поле обязательно для заполнения"), Condition.visible);
    }
    public void emptyErrorSubMonthField () {
        messageSubMonthField.shouldHave(Condition.text("Поле обязательно для заполнения"), Condition.visible);
    }
    public void emptyErrorSubYearField () {
        messageSubYearField.shouldHave(Condition.text("Поле обязательно для заполнения"), Condition.visible);
    }
    public void emptyErrorSubCardholderNameField () {
       messageSubCardholderNameField.shouldHave(Condition.text("Поле обязательно для заполнения"), Condition.visible);
    }
    public void emptyErrorSubCvvCodeField () {
        messageSubCvvCadeField.shouldHave(Condition.text("Поле обязательно для заполнения"), Condition.visible);
    }
    public void wrongErrorSubCvvCodeField () {
        messageSubCvvCadeField.shouldHave(Condition.text("Неверный формат"), Condition.visible);
    }

    public void wrongErrorSubCurdNumberField () {
        messageSubCarNumber.shouldHave(Condition.text("Неверный формат"), Condition.visible);
    }
    public void wrongErrorSubMonthField () {
        messageSubMonthField.shouldHave(Condition.text("Неверный формат"), Condition.visible);
    }
    public void wrongErrorSubYearField () {
        messageSubYearField.shouldHave(Condition.text("Неверный формат"), Condition.visible);
    }
    public void wrongErrorSubCardholderNameField () {
        messageSubCardholderNameField.shouldHave(Condition.text("Неверный формат"), Condition.visible);
    }

    public void isEmptyCardNumberField () {
        numberCardField.shouldBe(empty);
    }
    public void backspaceOnCardNumberField () {
        numberCardField.sendKeys(Keys.BACK_SPACE);
    }
    public void checkingLengthValueNumberCardField(){
        String numberCard = numberCardField.getValue();
        Assertions.assertEquals(19, numberCard.length());
    }
    public void checkingValueNumberCardField (String numberCard) {
        String cardNumberValue = numberCardField.getValue();
        Assertions.assertEquals(numberCard, cardNumberValue);
    }

}


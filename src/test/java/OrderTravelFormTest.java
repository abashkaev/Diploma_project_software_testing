import PageObject.HomePage;
import PageObject.PurchaseForm;
import com.codeborne.selenide.logevents.SelenideLogger;
import data.DataHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import data.SQLHelper;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderTravelFormTest {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setUp() {
        SQLHelper.clearTables();
        open("http://localhost:8080/");
    }

    @AfterEach
    void setAfterEach() {
        closeWindow();
    }


    @Test
    @DisplayName("Покупка валидной картой со статусом Approved")
    public void buyWithValidApprovedCard() {
        var homePage = new HomePage();
        homePage.isOpenHomePage();
        homePage.openBuyForm();
        var purchaseForm = new PurchaseForm();
        purchaseForm.setNumberCardField(DataHelper.getApprovedCardNumber());
        purchaseForm.setDate(DataHelper.getOtherDate(1));
        purchaseForm.setCardHolderName(DataHelper.validCardHolderName());
        purchaseForm.setCvvField(DataHelper.getValidCvv());
        purchaseForm.acceptButtonClick();
        purchaseForm.isNotificationSuccess();
        assertEquals("APPROVED", SQLHelper.findPayStatus());
    }

    @Test
    @DisplayName("Покупка валидной картой со статусом DECLINED")
    public void buyWithDeclinedValidCard() {
        var homePage = new HomePage();
        homePage.isOpenHomePage();
        homePage.openBuyForm();
        var purchaseForm = new PurchaseForm();
        purchaseForm.setNumberCardField(DataHelper.getDeclinedCardNumber());
        purchaseForm.setDate(DataHelper.getOtherDate(1));
        purchaseForm.setCardHolderName(DataHelper.validCardHolderName());
        purchaseForm.setCvvField(DataHelper.getValidCvv());
        purchaseForm.acceptButtonClick();
        purchaseForm.isNotificationError();
        assertEquals("DECLINED", SQLHelper.findPayStatus());
    }

    @Test
    @DisplayName("Оформление кредита валидной картой со статусом 2Aproved")
    public void creditWithValidAApprovedCard() {
        var homePage = new HomePage();
        homePage.isOpenHomePage();
        homePage.openCreditForm();
        var purchaseForm = new PurchaseForm();
        purchaseForm.setNumberCardField(DataHelper.getApprovedCardNumber());
        purchaseForm.setDate(DataHelper.getOtherDate(1));
        purchaseForm.setCardHolderName(DataHelper.validCardHolderName());
        purchaseForm.setCvvField(DataHelper.getValidCvv());
        purchaseForm.acceptButtonClick();
        purchaseForm.isNotificationSuccess();
        assertEquals("APPROVED", SQLHelper.findCreditStatus());
    }

    @Test
    @DisplayName("Оформление кредита валидной картой со статусом DECLINED")
    public void CreditWithDeclinedValidCard() {
        var homePage = new HomePage();
        homePage.isOpenHomePage();
        homePage.openBuyForm();
        var purchaseForm = new PurchaseForm();
        purchaseForm.setNumberCardField(DataHelper.getDeclinedCardNumber());
        purchaseForm.setDate(DataHelper.getOtherDate(1));
        purchaseForm.setCardHolderName(DataHelper.validCardHolderName());
        purchaseForm.setCvvField(DataHelper.getValidCvv());
        purchaseForm.acceptButtonClick();
        purchaseForm.isNotificationError();
        assertEquals("DECLINED", SQLHelper.findCreditStatus());
    }

    @Test
    @DisplayName("Попытка оформления кредита на тур, с валидной картой у которой истекает срок действия в январе следующего года")
    public void creditWithValidAApprovedCardAndJanuaryNextYear() {
        var homePage = new HomePage();
        homePage.isOpenHomePage();
        homePage.openCreditForm();
        var purchaseForm = new PurchaseForm();
        purchaseForm.setNumberCardField(DataHelper.getApprovedCardNumber());
        purchaseForm.setDate(DataHelper.getNextYearJanuary());
        purchaseForm.setCardHolderName(DataHelper.validCardHolderName());
        purchaseForm.setCvvField(DataHelper.getValidCvv());
        purchaseForm.acceptButtonClick();
        purchaseForm.isNotificationSuccess();
        assertEquals("APPROVED", SQLHelper.findCreditStatus());
    }

    @DisplayName("Попытка покупки тура, с валидной картой у которой истекает срок действия в январе следующего года")
    @Test
    public void buyWithValidAApprovedCardAndJanuaryNextYear() {
        var homePage = new HomePage();
        homePage.isOpenHomePage();
        homePage.openBuyForm();
        var purchaseForm = new PurchaseForm();
        purchaseForm.setNumberCardField(DataHelper.getApprovedCardNumber());
        purchaseForm.setDate(DataHelper.getNextYearJanuary());
        purchaseForm.setCardHolderName(DataHelper.validCardHolderName());
        purchaseForm.setCvvField(DataHelper.getValidCvv());
        purchaseForm.acceptButtonClick();
        purchaseForm.isNotificationSuccess();
        assertEquals("APPROVED", SQLHelper.findPayStatus());
    }

    @DisplayName("Попытка оформления кредита на тур, с валидной картой у которой истекает срок действия в декабре следующего года")
    @Test
    public void creditWithValidAApprovedCardAndDecemberNextYear() {
        open("http://localhost:8080/");
        var homePage = new HomePage();
        homePage.isOpenHomePage();
        homePage.openCreditForm();
        var purchaseForm = new PurchaseForm();
        purchaseForm.setNumberCardField(DataHelper.getApprovedCardNumber());
        purchaseForm.setDate(DataHelper.getNextYearDecember());
        purchaseForm.setCardHolderName(DataHelper.validCardHolderName());
        purchaseForm.setCvvField(DataHelper.getValidCvv());
        purchaseForm.acceptButtonClick();
        purchaseForm.isNotificationSuccess();
        assertEquals("APPROVED", SQLHelper.findCreditStatus());
    }

    @DisplayName("Попытка покупки, с валидной картой у которой истекает срок действия в декабре следующего года")
    @Test
    public void buyWithValidAApprovedCardAndDecemberNextYear() {
        var homePage = new HomePage();
        homePage.isOpenHomePage();
        homePage.openBuyForm();
        var purchaseForm = new PurchaseForm();
        purchaseForm.setNumberCardField(DataHelper.getApprovedCardNumber());
        purchaseForm.setDate(DataHelper.getNextYearDecember());
        purchaseForm.setCardHolderName(DataHelper.validCardHolderName());
        purchaseForm.setCvvField(DataHelper.getValidCvv());
        purchaseForm.acceptButtonClick();
        purchaseForm.isNotificationSuccess();
        assertEquals("APPROVED", SQLHelper.findPayStatus());
    }

    @DisplayName("Попытка покупки тура, с картой которая истекает в текущем месяце")
    @Test
    public void buyWithValidApprovedCardAndCurrentDate() {
        var homePage = new HomePage();
        homePage.isOpenHomePage();
        homePage.openBuyForm();
        var purchaseForm = new PurchaseForm();
        purchaseForm.setNumberCardField(DataHelper.getApprovedCardNumber());
        purchaseForm.setDate(DataHelper.getCurrentDate());
        purchaseForm.setCardHolderName(DataHelper.validCardHolderName());
        purchaseForm.setCvvField(DataHelper.getValidCvv());
        purchaseForm.acceptButtonClick();
        purchaseForm.isNotificationSuccess();
        assertEquals("APPROVED", SQLHelper.findPayStatus());
    }

    @DisplayName("Попытка оформления кредита, с валидной картой которая истекает в текущем месяце")
    @Test
    public void creditWithValidApprovedCardAndCurrentDate() {
        var homePage = new HomePage();
        homePage.isOpenHomePage();
        homePage.openCreditForm();
        var purchaseForm = new PurchaseForm();
        purchaseForm.setNumberCardField(DataHelper.getApprovedCardNumber());
        purchaseForm.setDate(DataHelper.getCurrentDate());
        purchaseForm.setCardHolderName(DataHelper.validCardHolderName());
        purchaseForm.setCvvField(DataHelper.getValidCvv());
        purchaseForm.acceptButtonClick();
        purchaseForm.isNotificationSuccess();
        assertEquals("APPROVED", SQLHelper.findCreditStatus());
    }

    @DisplayName("Попытка оформления кредита, с владельцем карты содержащим фамилию с тире")
    @Test
    public void creditCardholderHaveLastnameWithDash() {
        var homePage = new HomePage();
        homePage.isOpenHomePage();
        homePage.openCreditForm();
        var purchaseForm = new PurchaseForm();
        purchaseForm.setNumberCardField(DataHelper.getApprovedCardNumber());
        purchaseForm.setDate(DataHelper.getCurrentDate());
        purchaseForm.setCardHolderName(DataHelper.getValidNameWithDash());
        purchaseForm.setCvvField(DataHelper.getValidCvv());
        purchaseForm.acceptButtonClick();
        purchaseForm.isNotificationSuccess();
        assertEquals("APPROVED", SQLHelper.findCreditStatus());
    }

    @DisplayName("Попытка покупки, с владельцем карты содержащим фамилию с тире")
    @Test
    public void buyCardholderHaveLastnameWithDash() {
        var homePage = new HomePage();
        homePage.isOpenHomePage();
        homePage.openBuyForm();
        var purchaseForm = new PurchaseForm();
        purchaseForm.setNumberCardField(DataHelper.getApprovedCardNumber());
        purchaseForm.setDate(DataHelper.getCurrentDate());
        purchaseForm.setCardHolderName(DataHelper.getValidNameWithDash());
        purchaseForm.setCvvField(DataHelper.getValidCvv());
        purchaseForm.acceptButtonClick();
        purchaseForm.isNotificationSuccess();
        assertEquals("APPROVED", SQLHelper.findPayStatus());
    }

    @DisplayName("Попытка покупки с пустым полем 'Номер карты'")
    @Test
    public void buyWithEmptyCardNumberField() {
        var homePage = new HomePage();
        homePage.isOpenHomePage();
        homePage.openBuyForm();
        var purchaseForm = new PurchaseForm();
        purchaseForm.setDate(DataHelper.getCurrentDate());
        purchaseForm.setCardHolderName(DataHelper.validCardHolderName());
        purchaseForm.setCvvField(DataHelper.getValidCvv());
        purchaseForm.acceptButtonClick();
        purchaseForm.emptyErrorSubCurdNumberField();
    }

    @DisplayName("Попытка оформления кредита с пустым полем 'Номер карты'")
    @Test
    public void creditWithEmptyCardNumberField() {
        var homePage = new HomePage();
        homePage.isOpenHomePage();
        homePage.openCreditForm();
        var purchaseForm = new PurchaseForm();
        purchaseForm.setDate(DataHelper.getCurrentDate());
        purchaseForm.setCardHolderName(DataHelper.validCardHolderName());
        purchaseForm.setCvvField(DataHelper.getValidCvv());
        purchaseForm.acceptButtonClick();
        purchaseForm.emptyErrorSubCurdNumberField();
    }

    @DisplayName("Попытка оформления кредита с пустым полем 'Месяц'")
    @Test
    public void creditWithEmptyMountField() {
        var homePage = new HomePage();
        homePage.isOpenHomePage();
        homePage.openCreditForm();
        var purchaseForm = new PurchaseForm();
        purchaseForm.setNumberCardField(DataHelper.getApprovedCardNumber());
        purchaseForm.setYear(DataHelper.getCurrentYear());
        purchaseForm.setCardHolderName(DataHelper.validCardHolderName());
        purchaseForm.setCvvField(DataHelper.getValidCvv());
        purchaseForm.acceptButtonClick();
        purchaseForm.emptyErrorSubMonthField();
    }

    @DisplayName("Попытка покупки тура с пустым полем 'Месяц'")
    @Test
    public void buyWithEmptyMountField() {
        var homePage = new HomePage();
        homePage.isOpenHomePage();
        homePage.openBuyForm();
        var purchaseForm = new PurchaseForm();
        purchaseForm.setNumberCardField(DataHelper.getApprovedCardNumber());
        purchaseForm.setYear(DataHelper.getCurrentYear());
        purchaseForm.setCardHolderName(DataHelper.validCardHolderName());
        purchaseForm.setCvvField(DataHelper.getValidCvv());
        purchaseForm.acceptButtonClick();
        purchaseForm.emptyErrorSubMonthField();
    }

    @DisplayName("Попытка оформления кредита с пустым полем 'Год'")
    @Test
    public void creditWithEmptyYearField() {
        var homePage = new HomePage();
        homePage.isOpenHomePage();
        homePage.openCreditForm();
        var purchaseForm = new PurchaseForm();
        purchaseForm.setNumberCardField(DataHelper.getApprovedCardNumber());
        purchaseForm.setMount(DataHelper.getCurrentMonth());
        purchaseForm.setCardHolderName(DataHelper.validCardHolderName());
        purchaseForm.setCvvField(DataHelper.getValidCvv());
        purchaseForm.acceptButtonClick();
        purchaseForm.emptyErrorSubYearField();
    }

    @DisplayName("Попытка покупки тура с пустым полем 'Год'")
    @Test
    public void buyWithEmptyYearField() {
        var homePage = new HomePage();
        homePage.isOpenHomePage();
        homePage.openBuyForm();
        var purchaseForm = new PurchaseForm();
        purchaseForm.setNumberCardField(DataHelper.getApprovedCardNumber());
        purchaseForm.setMount(DataHelper.getCurrentMonth());
        purchaseForm.setCardHolderName(DataHelper.validCardHolderName());
        purchaseForm.setCvvField(DataHelper.getValidCvv());
        purchaseForm.acceptButtonClick();
        purchaseForm.emptyErrorSubYearField();
    }

    @DisplayName("Попытка покупки с пустым полем 'Владелец карты'")
    @Test
    public void buyWithEmptyCardHolderNameField() {
        var homePage = new HomePage();
        homePage.isOpenHomePage();
        homePage.openBuyForm();
        var purchaseForm = new PurchaseForm();
        purchaseForm.setNumberCardField(DataHelper.getApprovedCardNumber());
        purchaseForm.setDate(DataHelper.getCurrentDate());
        purchaseForm.setCvvField(DataHelper.getValidCvv());
        purchaseForm.acceptButtonClick();
        purchaseForm.emptyErrorSubCardholderNameField();
    }

    @DisplayName("Попытка оформления кредита с пустым полем 'Владелец карты'")
    @Test
    public void creditWithEmptyCardHolderNameField() {
        var homePage = new HomePage();
        homePage.isOpenHomePage();
        homePage.openCreditForm();
        var purchaseForm = new PurchaseForm();
        purchaseForm.setNumberCardField(DataHelper.getApprovedCardNumber());
        purchaseForm.setDate(DataHelper.getCurrentDate());
        purchaseForm.setCvvField(DataHelper.getValidCvv());
        purchaseForm.acceptButtonClick();
        purchaseForm.emptyErrorSubCardholderNameField();
    }

    @DisplayName("Попытка покупки с пустым полем 'CVV'")
    @Test
    public void buyWithEmptyCvvCodeField() {
        var homePage = new HomePage();
        homePage.isOpenHomePage();
        homePage.openBuyForm();
        var purchaseForm = new PurchaseForm();
        purchaseForm.setNumberCardField(DataHelper.getApprovedCardNumber());
        purchaseForm.setDate(DataHelper.getCurrentDate());
        purchaseForm.setCardHolderName(DataHelper.validCardHolderName());
        purchaseForm.acceptButtonClick();
        purchaseForm.emptyErrorSubCvvCodeField();
    }

    @DisplayName("Попытка оформления кредита с пустым полем 'CVV'")
    @Test
    public void creditWithEmptyCvvCodeField() {
        var homePage = new HomePage();
        homePage.isOpenHomePage();
        homePage.openCreditForm();
        var purchaseForm = new PurchaseForm();
        purchaseForm.setNumberCardField(DataHelper.getApprovedCardNumber());
        purchaseForm.setDate(DataHelper.getCurrentDate());
        purchaseForm.setCardHolderName(DataHelper.validCardHolderName());
        purchaseForm.acceptButtonClick();
        purchaseForm.emptyErrorSubCvvCodeField();
    }

    @DisplayName("Попытка ввести не числовое значение в поле 'Номер карты' при оформлении кредита")
    @Test
    public void useLettersInCardNumberFieldWithCreditForm() {
        var homePage = new HomePage();
        homePage.isOpenHomePage();
        homePage.openCreditForm();
        var purchaseForm = new PurchaseForm();
        purchaseForm.setNumberCardField(DataHelper.getValidNameWithDash());
        purchaseForm.isEmptyCardNumberField();
    }

    @DisplayName("Попытка ввести не числовое значение в поле 'Номер карты' при покупке тура")
    @Test
    public void useLettersInCardNumberFieldWithBuyForm() {
        var homePage = new HomePage();
        homePage.isOpenHomePage();
        homePage.openCreditForm();
        var purchaseForm = new PurchaseForm();
        purchaseForm.setNumberCardField(DataHelper.getValidNameWithDash());
        purchaseForm.isEmptyCardNumberField();
    }

    @DisplayName("Попытка оформить кредит, где поле 'Номер карты' содержит одну цифру")
    @Test
    public void useOneNumberInCardNumberFieldWithCreditForm() {
        var homePage = new HomePage();
        homePage.isOpenHomePage();
        homePage.openCreditForm();
        var purchaseForm = new PurchaseForm();
        purchaseForm.setNumberCardField(DataHelper.getRandomOneNumber());
        purchaseForm.setDate(DataHelper.getCurrentDate());
        purchaseForm.setCardHolderName(DataHelper.validCardHolderName());
        purchaseForm.setCvvField(DataHelper.getValidCvv());
        purchaseForm.acceptButtonClick();
        purchaseForm.wrongErrorSubCurdNumberField();
    }

    @DisplayName("Попытка оформить покупку, где поле 'Номер карты' содержит одну цифру")
    @Test
    public void useOneDigitsInCardNumberFieldWithBuyForm() {
        var homePage = new HomePage();
        homePage.isOpenHomePage();
        homePage.openBuyForm();
        var purchaseForm = new PurchaseForm();
        purchaseForm.setNumberCardField(DataHelper.getRandomOneNumber());
        purchaseForm.setDate(DataHelper.getCurrentDate());
        purchaseForm.setCardHolderName(DataHelper.validCardHolderName());
        purchaseForm.setCvvField(DataHelper.getValidCvv());
        purchaseForm.acceptButtonClick();
        purchaseForm.wrongErrorSubCurdNumberField();
    }

    @DisplayName("Попытка покупки, где поле 'Номер карты' содержит 15 цифр")
    @Test
    public void useFifteenDigitsNumbersInCardNumberFieldWithBuyForm() {
        var homePage = new HomePage();
        homePage.isOpenHomePage();
        homePage.openBuyForm();
        var purchaseForm = new PurchaseForm();
        purchaseForm.setNumberCardField(DataHelper.getRandomCardNumber());
        purchaseForm.backspaceOnCardNumberField();
        purchaseForm.setDate(DataHelper.getCurrentDate());
        purchaseForm.setCardHolderName(DataHelper.validCardHolderName());
        purchaseForm.setCvvField(DataHelper.getValidCvv());
        purchaseForm.acceptButtonClick();
        purchaseForm.wrongErrorSubCurdNumberField();

    }

    @DisplayName("Попытка оформления кредита, где поле 'Номер карты' содержит 15 цифр")
    @Test
    public void useFifteenDigitsInCardNumberFieldWithCreditForm() {
        var homePage = new HomePage();
        homePage.isOpenHomePage();
        homePage.openCreditForm();
        var purchaseForm = new PurchaseForm();
        purchaseForm.setNumberCardField(DataHelper.getRandomCardNumber());
        purchaseForm.backspaceOnCardNumberField();
        purchaseForm.setDate(DataHelper.getCurrentDate());
        purchaseForm.setCardHolderName(DataHelper.validCardHolderName());
        purchaseForm.setCvvField(DataHelper.getValidCvv());
        purchaseForm.acceptButtonClick();
        purchaseForm.wrongErrorSubCurdNumberField();
    }

    @DisplayName("Попытка ввести 17 цифр в поле 'Номер карты' при оформлении кредита")
    @Test
    public void TryUseSeventeenDigitsInNumberCardFieldWithCreditForm() {
        var homePage = new HomePage();
        homePage.isOpenHomePage();
        homePage.openCreditForm();
        var purchaseForm = new PurchaseForm();
        purchaseForm.setNumberCardField(DataHelper.getApprovedCardNumber() + DataHelper.getRandomOneNumber());
        purchaseForm.checkingValueNumberCardField(DataHelper.getApprovedCardNumber());
        purchaseForm.checkingLengthValueNumberCardField();
    }

    @DisplayName("Попытка ввести 17 цифр в поле 'Номер карты' при оформлении покупки")
    @Test
    public void TryUseSeventeenDigitsInNumberCardFieldWitBuyForm() {
        var homePage = new HomePage();
        homePage.isOpenHomePage();
        homePage.openBuyForm();
        var purchaseForm = new PurchaseForm();
        purchaseForm.setNumberCardField(DataHelper.getApprovedCardNumber() + DataHelper.getRandomOneNumber());
        purchaseForm.checkingValueNumberCardField(DataHelper.getApprovedCardNumber());
        purchaseForm.checkingLengthValueNumberCardField();
    }

    @DisplayName("Попытка направить форму кредита с полем 'Месяц' содержащую всего одну цифру")
    @Test
    public void tryPushCreditFormWithValueInMountFieldHaveOnlyOneDigits() {
        var homePage = new HomePage();
        homePage.isOpenHomePage();
        homePage.openCreditForm();
        var purchaseForm = new PurchaseForm();
        purchaseForm.setNumberCardField(DataHelper.getRandomCardNumber());
        purchaseForm.setMount(DataHelper.getRandomOneNumber());
        purchaseForm.setYear(DataHelper.getCurrentYear());
        purchaseForm.setCardHolderName(DataHelper.validCardHolderName());
        purchaseForm.setCvvField(DataHelper.getValidCvv());
        purchaseForm.acceptButtonClick();
        purchaseForm.wrongErrorSubMonthField();
    }
    @DisplayName("Попытка направить форму покупки с полем 'Месяц' содержащую всего одну цифру")
    @Test
    public void tryPushBuyFormWithValueInMountFieldHaveOnlyOneDigits() {
        var homePage = new HomePage();
        homePage.isOpenHomePage();
        homePage.openBuyForm();
        var purchaseForm = new PurchaseForm();
        purchaseForm.setNumberCardField(DataHelper.getRandomCardNumber());
        purchaseForm.setMount(DataHelper.getRandomOneNumber());
        purchaseForm.setYear(DataHelper.getCurrentYear());
        purchaseForm.setCardHolderName(DataHelper.validCardHolderName());
        purchaseForm.setCvvField(DataHelper.getValidCvv());
        purchaseForm.acceptButtonClick();
        purchaseForm.wrongErrorSubMonthField();
    }

    @DisplayName("Попытка направить форму покупки с полем 'Месяц', содержащим значение '00'")
    @Test
    public void tryPushBuyFormWithValueInMountFieldHaveDoubleZero() {
        var homePage = new HomePage();
        homePage.isOpenHomePage();
        homePage.openBuyForm();
        var purchaseForm = new PurchaseForm();
        purchaseForm.setNumberCardField(DataHelper.getRandomCardNumber());
        purchaseForm.setMount(DataHelper.setCustomValue("00"));
        purchaseForm.setYear(DataHelper.getNextYear());
        purchaseForm.setCardHolderName(DataHelper.validCardHolderName());
        purchaseForm.setCvvField(DataHelper.getValidCvv());
        purchaseForm.acceptButtonClick();
        purchaseForm.incorrectlyPeriodErrorSubMonthField();
    }
    @DisplayName("Попытка направить форму кредита с полем 'Месяц', содержащим значение '00'")
    @Test
    public void tryPushCreditFormWithValueInMountFieldHaveDoubleZero() {
        var homePage = new HomePage();
        homePage.isOpenHomePage();
        homePage.openCreditForm();
        var purchaseForm = new PurchaseForm();
        purchaseForm.setNumberCardField(DataHelper.getRandomCardNumber());
        purchaseForm.setMount(DataHelper.setCustomValue("00"));
        purchaseForm.setYear(DataHelper.getNextYear());
        purchaseForm.setCardHolderName(DataHelper.validCardHolderName());
        purchaseForm.setCvvField(DataHelper.getValidCvv());
        purchaseForm.acceptButtonClick();
        purchaseForm.incorrectlyPeriodErrorSubMonthField();
    }
}



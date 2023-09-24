import PageObject.HomePage;
import PageObject.PurchaseForm;
import com.codeborne.selenide.logevents.SelenideLogger;
import data.DataHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import data.SQLHelper;

import static com.codeborne.selenide.Selenide.closeWindow;
import static com.codeborne.selenide.Selenide.open;
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
    void setAfterEach () {
        closeWindow();
    }


    @Test
    @DisplayName("Покупка валидной картой со статусом Approved")
    public void byeWithValidApprovedCard() {
        var homePage = new HomePage();
        homePage.isOpenHomePage();
        homePage.openByeForm();
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
    public void byeWithDeclinedValidCard() {
        var homePage = new HomePage();
        homePage.isOpenHomePage();
        homePage.openByeForm();
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
    @DisplayName("Оформление кредита валидной картой со статусом Aproved")
    public void creditWithValidAApprovedCard () {
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
        homePage.openByeForm();
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
    public void creditWithValidAApprovedCardAndJanuaryNextYear () {
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
    public void byeWithValidAApprovedCardAndJanuaryNextYear () {
        var homePage = new HomePage();
        homePage.isOpenHomePage();
        homePage.openByeForm();
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
   public void creditWithValidAApprovedCardAndDecemberNextYear () {
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
    public void byeWithValidAApprovedCardAndDecemberNextYear () {
        var homePage = new HomePage();
        homePage.isOpenHomePage();
        homePage.openByeForm();
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
    public void byeWithValidApprovedCardAndCurrentDate() {
        var homePage = new HomePage();
        homePage.isOpenHomePage();
        homePage.openByeForm();
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
    public void byeCardholderHaveLastnameWithDash() {
        var homePage = new HomePage();
        homePage.isOpenHomePage();
        homePage.openByeForm();
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
    public void byeWithEmptyCardNumberField () {
    var homePage = new HomePage();
    homePage.isOpenHomePage();
    homePage.openByeForm();
    var purchaseForm = new PurchaseForm();
    purchaseForm.setDate(DataHelper.getCurrentDate());
    purchaseForm.setCardHolderName(DataHelper.validCardHolderName());
    purchaseForm.setCvvField(DataHelper.getValidCvv());
    purchaseForm.acceptButtonClick();
    purchaseForm.emptyErrorSubCurdNumberField();
}

    @DisplayName("Попытка оформления кредита с пустым полем 'Номер карты'")
    @Test
    public void creditWithEmptyCardNumberField () {
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
    public void creditWithEmptyMountField () {
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
    public void beyWithEmptyMountField () {
        var homePage = new HomePage();
        homePage.isOpenHomePage();
        homePage.openByeForm();
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
    public void creditWithEmptyYearField () {
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
    public void beyWithEmptyYearField () {
        var homePage = new HomePage();
        homePage.isOpenHomePage();
        homePage.openByeForm();
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
    public void byeWithEmptyCardHolderNameField () {
        var homePage = new HomePage();
        homePage.isOpenHomePage();
        homePage.openByeForm();
        var purchaseForm = new PurchaseForm();
        purchaseForm.setNumberCardField(DataHelper.getApprovedCardNumber());
        purchaseForm.setDate(DataHelper.getCurrentDate());
        purchaseForm.setCvvField(DataHelper.getValidCvv());
        purchaseForm.acceptButtonClick();
        purchaseForm.emptyErrorSubCardholderNameField();
    }

    @DisplayName("Попытка оформления кредита с пустым полем 'Владелец карты'")
    @Test
    public void creditWithEmptyCardHolderNameField () {
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
    public void byeWithEmptyCvvCodeField () {
        var homePage = new HomePage();
        homePage.isOpenHomePage();
        homePage.openByeForm();
        var purchaseForm = new PurchaseForm();
        purchaseForm.setNumberCardField(DataHelper.getApprovedCardNumber());
        purchaseForm.setDate(DataHelper.getCurrentDate());
        purchaseForm.setCardHolderName(DataHelper.validCardHolderName());
        purchaseForm.acceptButtonClick();
        purchaseForm.emptyErrorSubCvvCodeField();
    }

    @DisplayName("Попытка оформления кредита с пустым полем 'CVV'")
    @Test
    public void creditWithEmptyCvvCodeField () {
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
}

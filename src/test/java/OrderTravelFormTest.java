import PageObject.HomePage;
import PageObject.PurchaseForm;
import com.codeborne.selenide.logevents.SelenideLogger;
import data.DataHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import data.SQLHelper;

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

//    @BeforeEach
//     void clearDataBase() {
//        SQLHelper.clearTables();
//    }


    @Test
    @DisplayName("Покупка валидной картой со статусом Approved")
    public void byeWithValidApprovedCard() {
        open("http://localhost:8080/");
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
        open("http://localhost:8080/");
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
        open("http://localhost:8080/");
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
        open("http://localhost:8080/");
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
        open("http://localhost:8080/");
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
        open("http://localhost:8080/");
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
        assertEquals("APPROVED", SQLHelper.findCreditStatus());
    }


}

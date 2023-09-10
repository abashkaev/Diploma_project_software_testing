import PageObject.HomePage;
import PageObject.PurchaseForm;
import com.codeborne.selenide.logevents.SelenideLogger;
import data.DataHelper;
import data.SQLHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
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


    @Test
    public void byeWithValidCard () {
        open("http://localhost:8080/");
        var sqlHelper = new SQLHelper();
        var homePage = new HomePage();
        homePage.isOpenHomePage();
       homePage.openByeForm();
       var purchaseForm = new PurchaseForm();
       purchaseForm.setNumberCardField(DataHelper.getApprovedCardNumber());
       purchaseForm.setDate(DataHelper.getOtherDate(1));
       purchaseForm.setCardHolderName(DataHelper.validCardHolderName());
       purchaseForm.setCvvField(DataHelper.getValidCvv());
       purchaseForm.acceptButtonClick();
       purchaseForm.notificationSuccess();
            assertEquals("APPROVED", sqlHelper.findPayStatus());
       sleep(5000);
    }

}

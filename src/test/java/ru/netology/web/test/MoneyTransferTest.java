package ru.netology.web.test;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.CardReplenishment;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;
import static com.codeborne.selenide.Selenide.open;

class MoneyTransferTest {

    @Test
    void shouldCheckingSuccessTransferMoneyToFirstCard() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        val balance = new DashboardPage();
        val balance1 = balance.getFirstCardBalance();
        val balance2 = balance.getSecondCardBalance();
        val dashboard = new DashboardPage();
        dashboard.topUpAccount1();
        val cardReplenishment = new CardReplenishment();
        val newBalance = cardReplenishment.putMoneyOn1Card(400);
        Assertions.assertEquals(balance1+400,newBalance.getFirstCardBalance());
        Assertions.assertEquals(balance2-400,newBalance.getSecondCardBalance());
    }

    @Test
    void shouldCheckingSuccessTransferMoneyToSecondCard() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        val balance = new DashboardPage();
        val balance1 = balance.getFirstCardBalance();
        val balance2 = balance.getSecondCardBalance();
        val dashboard = new DashboardPage();
        dashboard.topUpAccount2();
        val cardReplenishment = new CardReplenishment();
        val newBalance = cardReplenishment.putMoneyOn2Card(100);
        Assertions.assertEquals(balance1-100,newBalance.getFirstCardBalance());
        Assertions.assertEquals(balance2+100,newBalance.getSecondCardBalance());
    }
}


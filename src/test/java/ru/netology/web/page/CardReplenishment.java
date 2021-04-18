package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class CardReplenishment {
    private SelenideElement amount = $("[data-test-id='amount'] input");
    private SelenideElement cardFrom = $("[data-test-id='from'] input");
    private SelenideElement button = $(withText(("Пополнить")));

    public DashboardPage putMoneyOn1Card(int amountForTransfer) {
        amount.setValue(String.valueOf(amountForTransfer));
        cardFrom.setValue(DataHelper.getCard2().getCardNumber());
        button.click();
        return new DashboardPage();
    }

    public DashboardPage putMoneyOn2Card(int amountForTransfer) {
        amount.setValue(String.valueOf(amountForTransfer));
        cardFrom.setValue(DataHelper.getCard1().getCardNumber());
        button.click();
        return new DashboardPage();
    }
}

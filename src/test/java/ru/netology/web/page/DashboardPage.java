package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
  private SelenideElement heading = $("[data-test-id=dashboard]");
  private SelenideElement topUpButton1 = $(byText("**** **** **** 0001")).$("[data-test-id='action-deposit'], .button");
  private SelenideElement topUpButton2 = $(byText("**** **** **** 0002")).$("[data-test-id='action-deposit'], .button");

  public DashboardPage() {
    heading.shouldBe(visible);
  }

  public CardReplenishment topUpAccount1(){
    topUpButton1.click();
    return new CardReplenishment();
  }

  public CardReplenishment topUpAccount2(){
    topUpButton2.click();
    return new CardReplenishment();
  }

  private ElementsCollection cards = $$(".list__item");
  private final String balanceStart = "баланс: ";
  private final String balanceFinish = " р.";


  public int getFirstCardBalance() {
    val text = cards.first().text();
    return extractBalance(text);
  }

  public int getSecondCardBalance() {
    val text = cards.last().text();
    return extractBalance(text);
  }

  private int extractBalance(String text) {
    val start = text.indexOf(balanceStart);
    val finish = text.indexOf(balanceFinish);
    val value = text.substring(start + balanceStart.length(), finish);
    return Integer.parseInt(value);
  }
}

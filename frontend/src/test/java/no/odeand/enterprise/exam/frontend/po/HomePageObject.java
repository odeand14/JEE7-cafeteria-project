package no.odeand.enterprise.exam.frontend.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Objects;

// Created by Andreas Ødegaard on 05.06.2017.
public class HomePageObject extends PageObject {

    public HomePageObject(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isOnPage() {

        return driver.getTitle().equals("MyCantina Home Page");
    }

    public HomePageObject toStartingPage() {
        String context = "/my_cantina"; // see jboss-web.xml
        driver.get("localhost:8080" + context + "/my_cantina/home.jsf");
        waitForPageToLoad();

        return this;
    }

    public boolean isCorrectDate(String date) {
        WebElement element = driver.findElement(
                By.id("currentMenuDate"));
        return Objects.equals(element.getText(), "Menu for " + date);
    }

    public int getNumberOfDishesInDisplayedMenu() {
        List<WebElement> elements = driver.findElements(
                By.xpath("//table[@id='dishInMenuTable']//tbody//tr"));

        return elements.size();
    }

    public boolean clickOnLink(String link) {
        WebElement element = driver.findElement(By.xpath("//*[contains(@id, '"+ link +"')]"));
        if (element != null) {
            element.click();
            waitForPageToLoad();
            return true;
        }
        return false;
    }

    public DishesPageObject toDishes() {
        WebElement link = driver.findElement(By.id("dishesLink"));
        link.click();
        waitForPageToLoad();

        return new DishesPageObject(driver);
    }

    public MenuPageObject toMenus() {
        WebElement link = driver.findElement(By.id("menusLink"));
        link.click();
        waitForPageToLoad();

        return new MenuPageObject(driver);
    }




}

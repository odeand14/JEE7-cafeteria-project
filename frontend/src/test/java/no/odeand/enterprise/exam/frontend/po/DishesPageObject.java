package no.odeand.enterprise.exam.frontend.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

// Created by Andreas Ødegaard on 07.06.2017.
public class DishesPageObject extends PageObject {


    public DishesPageObject(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isOnPage() {
        return driver.getTitle().contains("Dishes");
    }


    public boolean isTitleTaken(String title) {
        List<WebElement> elements = driver.findElements(
                By.xpath("//table[@id='dishTable']//tbody//tr/td[contains(text(), '"+ title +"')]"));

        return ! elements.isEmpty();
    }

    public void createDish(String name, String text) {
        setText("createForm:createName", name);
        setText("createForm:createText", text);
        WebElement button = driver.findElement(By.id("createForm:createButton"));
        button.click();
        waitForPageToLoad();
    }

    public boolean isDishVisible(String name) {
        List<WebElement> elements = driver.findElements(
                By.xpath("//table[@id='dishTable']//thead//tr/th[contains(text(), '" + name + "')]"));

        return elements.isEmpty();
    }

}

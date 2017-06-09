package no.odeand.enterprise.exam.frontend.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

// Created by Andreas Ødegaard on 07.06.2017.
public class MenuPageObject extends PageObject {


    public MenuPageObject(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isOnPage() {
        return driver.getTitle().contains("Menu");
    }


    public void createMenu(String date, List<Integer> positions) {
        setText("createForm:createDate", date);
        for (Integer i: positions) {
            WebElement checkbox = driver.findElement(By.xpath("(//table/tbody/tr/td/input)["+ i +"]"));
            checkbox.click();
        }

        WebElement button = driver.findElement(By.id("createForm:createButton"));
        button.click();
        waitForPageToLoad();

    }

    public int getNumberOfDishes() {
        List<WebElement> elements = driver.findElements(
                By.xpath("//table[@id='createForm:dishTable']//tbody//tr"));

        return elements.size();
    }

}

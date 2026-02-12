package com.coursera.pages;

import com.coursera.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PythonPage extends BasePage {
    public PythonPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath="(//a[@data-click-key='seo_entity_page.search.click.search_card'])[1]") WebElement pythonCourse;
    By levelLocator = By.xpath("//div[@class='css-6mrk5o']//div[contains(text(),'level')] | (//div[contains(@class, 'css-fk6qfz') and contains(text(), 'level')])[2]");

    public void extractLevel(){
        wait.until(ExpectedConditions.visibilityOf(pythonCourse));
        wait.until(ExpectedConditions.elementToBeClickable(pythonCourse)).click();
        log.info("PAGE: Switching tab after clicking first Python course");
        String current_tab = driver.getWindowHandle();
        for(String handle: driver.getWindowHandles()){
            if(!(handle.equals(current_tab))){
                driver.switchTo().window(handle);
            }
        }
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        log.info("PAGE: Printing the level of the course in console");
        String level =wait.until(ExpectedConditions.visibilityOfElementLocated(levelLocator)).getText();
        System.out.println("Level of the Python course: "+ level);
    }
}

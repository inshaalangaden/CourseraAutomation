package com.coursera.pages;

import com.coursera.core.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
    }

    //Web elements
    @FindBy(id="search-autocomplete-input") WebElement searchInput;
    @FindBy(css="button[data-testid='megamenu-explore-button']") WebElement exploreButton;
    @FindBy(css="a[data-track-href='/courses?query=python']") WebElement python;
    @FindBy(css = "a[data-click-key='front_page.front_page_story.click.navigation_meta_nav_Campus']") WebElement forUniversities;

    //Methods
    public void search(String text){
        log.info("PAGE: Searching for: {}", text);
        searchInput.sendKeys(text);
        searchInput.sendKeys(Keys.ENTER);
    }

    public void selectPythonCourse(){
        log.info("PAGE: Selecting the first python course");
        Actions action = new Actions(driver);
        action.moveToElement(exploreButton).perform();
        python.click();
    }

    public void navigateToUniversities(){
        log.info("PAGE: Navigating to forUniversities page");
        forUniversities.click();
    }

}

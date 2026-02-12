package com.coursera.pages;

import com.coursera.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResultPage extends BasePage {

    public SearchResultPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @FindBy(css="button[data-testid='filter-dropdown-productDuration']") WebElement duration_btn;
    @FindBy(xpath="h3[class='css-fk6qfz']") WebElement duration_h3;
    @FindBy(xpath="//div[contains(@data-testid,'productDuration:1-3 Months-false')]/label") WebElement month_select;
    @FindBy(xpath="//span[contains(text(),'View')]/parent::button") WebElement view_btn;
    @FindBy(xpath = "//h3[contains(@class,'cds-CommonCard-title css-6ecy9b')] | //a[@data-click-key='search.search.click.search_card']") WebElement first_title;

    public void verification() {
        log.info("PAGE: filtering based on duration of course");
        duration_btn.click();
        month_select.click();
        view_btn.click();
        log.info("PAGE: Printing the first course title in the console");
        String title = first_title.getText();
        System.out.println("Title of the first course: "+ title);
    }
}

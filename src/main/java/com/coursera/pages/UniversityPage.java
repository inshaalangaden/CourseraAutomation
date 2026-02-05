package com.coursera.pages;

import com.coursera.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UniversityPage extends BasePage {
    public UniversityPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "a[data-click-key='ent_website._campus.click.navigation_priority_cta']")
    WebElement contactUs;

    public void clickContactUs(){
        log.info("PAGE: Click contactUs button");
        contactUs.click();
    }

}

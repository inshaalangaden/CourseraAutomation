package com.coursera.pages;

import com.coursera.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class FormPage extends BasePage {


    public FormPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @FindBy(id="FirstName") WebElement firstName;
    @FindBy(id="LastName") WebElement lastName;
    @FindBy(id="Email") WebElement email;
    @FindBy(id="Phone") WebElement phoneNumber;
    @FindBy(id="Institution_Type__c") WebElement institutionType;
    @FindBy(id="Company") WebElement institutionName;
    @FindBy(id="Title") WebElement jobRole;
    @FindBy(id="Department") WebElement department;
    @FindBy(id="Self_Reported_Needs__c") WebElement needs;
    @FindBy(id="Country") WebElement country;
    @FindBy(css= "button[type='submit']") WebElement submitButton;
    //@FindBy(css = "div[role='alert']") WebElement alertMsg;

    By alertLocator = By.cssSelector("div[role='alert']");

    public void fillForm(String fName, String lName, String mail, String phone, String instType, String instName, String role, String dpmt, String need, String cntry){
        log.info("PAGE: Filling the form");

        firstName.sendKeys(fName);
        lastName.sendKeys(lName);
        email.sendKeys(mail);
        phoneNumber.sendKeys(phone);
        institutionName.sendKeys(instName);

        Select s1 = new Select(institutionType);
        s1.selectByVisibleText(instType);

        Select s2 = new Select(jobRole);
        s2.selectByVisibleText(role);

        Select s3 = new Select(department);
        s3.selectByVisibleText(dpmt);

        Select s4 = new Select(needs);
        s4.selectByVisibleText(need);

        Select s5 = new Select(country);
        s5.selectByVisibleText(cntry);
    }

    public void clickSubmit(){
        log.info("PAGE: Click submit button");
        submitButton.click();
    }
    public boolean isAlertPresent(){
        log.info("PAGE: Return the alert");
        return !(driver.findElements(alertLocator).isEmpty());
    }

    public String getAlert() {
        log.info("PAGE: Return alert message if the alert is present");

        if (isAlertPresent()) return driver.findElement(alertLocator).getText();
        else{
            return "No error present";
        }
    }
}

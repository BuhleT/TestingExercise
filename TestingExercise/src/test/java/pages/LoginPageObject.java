package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPageObject {
    WebDriver driver = null;
    //Login
    By signInLink = By.xpath("//a[normalize-space()='Sign in']");
    By emailText = By.xpath("//input[@placeholder='Email']");
    By passwordText = By.xpath("//input[@placeholder='Password']");
    By signInButton = By.xpath("//button[@class='btn btn-lg btn-primary pull-xs-right']");
    By userNotFoundErrorText = By.xpath("//li[normalize-space()='body User not found']");

    //SignUp
    By username = By.xpath("//input[@placeholder='Username']");

    public LoginPageObject(WebDriver driver){
        this.driver = driver;
    }

    public  void clickSignInLink(){
        driver.findElement(signInLink).click();
    }

    public  void setEmailText(String text){
        driver.findElement(emailText).sendKeys(text);
    }

    public  void setPasswordText(String text){
        driver.findElement(passwordText).sendKeys(text);
    }

    public  void clickSignInButton (){
        driver.findElement(signInButton).click();
    }

    public  void setUsernameText (String text){
        driver.findElement(username).sendKeys(text);
    }

    public String userNotFoundText (){
        return driver.findElement(userNotFoundErrorText).getText();

    }

}

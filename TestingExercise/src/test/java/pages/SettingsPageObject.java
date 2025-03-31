package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SettingsPageObject {
    WebDriver driver = null;
    By logoutButton = By.xpath("//button[@class='btn btn-outline-danger']");
    By passwordText = By.xpath("//input[@placeholder='Password']");
    By updateButton = By.xpath("//button[@class='btn btn-lg btn-primary pull-xs-right']");

    public SettingsPageObject(WebDriver driver){
        this.driver = driver;
    }

    public  void clickLogoutButton(){
        driver.findElement(logoutButton).click();
    }

    public  void clickUpdate(){
        driver.findElement(updateButton).click();
    }

    public  void setPasswordText(String text){
        driver.findElement(passwordText).sendKeys(text);
    }
}

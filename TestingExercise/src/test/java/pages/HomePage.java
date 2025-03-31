package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    WebDriver driver = null;
    //Before login
    By homePageLink = By.xpath("//a[normalize-space()='Home']"); //Can be used before and after login
    By signUpLink = By.xpath("//a[normalize-space()='Sign up']");

    //After login in
    By articleLink = By.xpath("//a[@href='#/editor']");
    By settingsLink = By.xpath("//a[@href='#/settings']");

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public  void clickArticleLink(){
        driver.findElement(articleLink).click();
    }

    public  void clickHomeLink(){
        driver.findElement(homePageLink).click();
    }

    public  void clickSettingsLink(){
        driver.findElement(settingsLink).click();
    }

    public  void clickSignUpLink(){
        driver.findElement(signUpLink).click();
    }
}

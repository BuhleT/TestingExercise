package tests;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.ArticlePageObject;
import pages.HomePage;
import pages.LoginPageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.SettingsPageObject;

import java.time.Duration;
import java.util.List;

public class ConduitTest {
    public static WebDriver driver = null;
    public HomePage homepage;
    public LoginPageObject loginPage;
    public ArticlePageObject article;
    public SettingsPageObject setting;

    Utility utility = new Utility();

    @BeforeTest
    public void setup() {
        //Match the browser version of your machine
        WebDriverManager.chromedriver().setup();

        //Change browser between firefox and chrome
        String browser = System.getProperty("browser", "chrome");
        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("http://localhost:8080/#/");

        //Using Page object model approach
        homepage = new HomePage(driver);
        loginPage = new LoginPageObject(driver);
        article = new ArticlePageObject(driver);
        setting = new SettingsPageObject(driver);
    }

    //Passing Positive Test	Verify that a user can successfully create an article.
    @Test
    public void createArticle(){
        login();
        homepage.clickArticleLink();
        article.setArticleTitleText("Java tutor");
        article.setArticleIntroText("Introduction to exception handling");
        article.setArticleBodyText("In Java, exception handling is a mechanism to manage runtime errors, ensuring that the program can handle unexpected situations gracefully without crashing.");
        article.setArticleTagText("Try");
        article.submitArticle();
        Assert.assertTrue(article.showSuccessIdentifier(), "Article was not created!");
        utility.genericWaitTime(4000);
        logout();
    }

    //Passing Negative Test	Ensure that a user cannot sign up with an invalid email format
    @Test
    public void signUp(){
        homepage.clickSignUpLink();
        utility.genericWaitTime(2000);
        loginPage.setUsernameText("buhlesingos");
        loginPage.setEmailText("singo@123.com");
        loginPage.setPasswordText("12345");

        String pageSourceBeforeClick = driver.getPageSource();
        loginPage.clickSignInButton();
        String pageSourceAfterClick = driver.getPageSource();

        Assert.assertEquals(pageSourceBeforeClick, pageSourceAfterClick);
        logout();
    }

    //Failing Negative Test	Check if the system allows submission of an empty comment when it should prevent it.
    @Test
    public void postComment_fail(){
        boolean emptyCommentPosted = false;
        login();
        article.clickArticle();
        List<WebElement> cardElementsBefore = driver.findElements(By.className("card"));
        article.setCommentText(String.valueOf(Keys.SPACE));
        article.clickPostComment();
        utility.genericWaitTime(3000);
        List<WebElement> cardElementsAfter = driver.findElements(By.className("card"));
        if (cardElementsBefore != cardElementsAfter){
            emptyCommentPosted = true;
        }

        Assert.assertFalse(emptyCommentPosted, "Empty comment posted");
        logout();

    }

    //A failing positive test of a feature that should update password and use updated password to login
    @Test
    public void updatePassword(){
        loginPage.clickSignInLink();
        loginPage.setEmailText("gg@gmail.com");
        loginPage.setPasswordText("12345");
        loginPage.clickSignInButton();
        homepage.clickSettingsLink();
        setting.setPasswordText("123456");
        setting.clickUpdate();
        logout();

        //Login again with the updated password
        loginPage.clickSignInLink();
        loginPage.setEmailText("gg@gmail.com");
        loginPage.setPasswordText("123456");
        loginPage.clickSignInButton();
        try {
            String text = loginPage.userNotFoundText();
            System.out.println(text);
            Assert.assertEquals("User not found", text, "Failed to login with the changed password");
        } catch (Exception e) {
            Assert.assertTrue(true, "Password changed successfully");
        }
        logout();
    }

    //A security test to check if the url starts with https as secure connection
    @Test
    public void loginDataTransmission(){
        login();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.startsWith("https://"), "The page is not loaded over HTTPS. Sensitive data may not be encrypted during transmission.");
        logout();
    }


    private  void login(){
        loginPage.clickSignInLink();
        loginPage.setEmailText("buhlesingo97@gmail.com");
        loginPage.setPasswordText("Buhle12345");
        loginPage.clickSignInButton();
    }

    private  void logout(){
        homepage.clickSettingsLink();
        setting.clickLogoutButton();
    }

   @AfterTest
    public void teardown() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }
}



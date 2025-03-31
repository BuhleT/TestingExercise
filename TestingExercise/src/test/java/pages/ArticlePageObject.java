package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ArticlePageObject {
    WebDriver driver = null;
    By articleTitleText = By.xpath("//input[@placeholder='Article Title']");
    By articleIntroText = By.xpath("//input[@placeholder=\"What's this article about?\"]");
    By articleBodyText = By.cssSelector("textarea[placeholder='Write your article (in markdown)']");
    By articleTagText = By.xpath("//input[@placeholder='Enter tags']");
    By submitButton = By.xpath("//button[@type='submit']");
    By articleSuccessIdentifier = By.xpath("//button[@class='btn btn-sm btn-primary']");
    By articleTitle = By.xpath("//h1[normalize-space()='Java tutorial']");
    By comment = By.xpath("//textarea[@placeholder='Write a comment...']");

    public ArticlePageObject(WebDriver driver){
        this.driver = driver;
    }

    public  void setArticleTitleText(String text){
        driver.findElement(articleTitleText).clear();
        driver.findElement(articleTitleText).sendKeys(text);
    }

    public  void setArticleIntroText(String text){
        driver.findElement(articleIntroText).clear();
        driver.findElement(articleIntroText).sendKeys(text);
    }

    public  void setArticleBodyText(String text){
        driver.findElement(articleBodyText).clear();
        driver.findElement(articleBodyText).sendKeys(text);
    }

    public  void setArticleTagText(String text){
        driver.findElement(articleTagText).clear();
        driver.findElement(articleTagText).sendKeys(text);
    }

    public  void submitArticle(){
        driver.findElement(submitButton).click();
    }

    public boolean showSuccessIdentifier(){
        driver.findElement(articleSuccessIdentifier);
        return true;
    }

    public void clickPostComment(){
        driver.findElement(articleSuccessIdentifier).click();
    }

    public void clickArticle(){
        driver.findElement(articleTitle).click();
    }

    public  void setCommentText(String text){
        driver.findElement(comment).clear();
        driver.findElement(comment).sendKeys(text);
    }
}

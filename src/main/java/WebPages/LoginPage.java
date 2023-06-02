package WebPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
public class LoginPage {
    WebDriver driver;
    @FindBy(css = "#username")
    WebElement userName;
    @FindBy(css = "#password")
    WebElement userPassword;
    @FindBy(css = ".btn-primary")
    WebElement loginButton;
    public By invalidUserOrPassword = By.xpath("//*[@id=\"root\"]/div/div/div[1]");
    public By successfulLoginNameValidation = By.className("username");
    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver,this);
    }
    public void loginUser(String userName, String password){
        this.userName.sendKeys(userName);
        this.userPassword.sendKeys(password);
        this.loginButton.click();
    }
}

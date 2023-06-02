package Tests;

import BrowserFactory.BrowserFactory;
import Helpers.ExelReader;
import Helpers.Waits;
import WebPages.ActiveSales;
import WebPages.LoginPage;
import WebPages.RobotOrder;
import org.junit.jupiter.api.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

@TestMethodOrder(OrderAnnotation.class)

public class TestCases {
    private static String typeOfBrowser = "Chrome";
    private String baseUrl = "https://robotsparebinindustries.com/#/";
    static String message;
    static WebDriver driver;
    static LoginPage loginPage;
    static RobotOrder robotOrderPage;
    static ActiveSales activeSalesPage;
    static ExelReader exelReader;
    private static String userName;
    private static String userPass;
    private static String expectedResult;
    private static int count;
    static Waits customWait = new Waits();

    @BeforeAll
    public static void beforeClass() {
        driver = BrowserFactory.getBrowser(typeOfBrowser);
        loginPage = new LoginPage(driver);
        robotOrderPage = new RobotOrder(driver);
        activeSalesPage = new ActiveSales(driver);
        exelReader = new ExelReader(driver);
    }

  //  @AfterAll
  //  public static void afterClass() {
  //      BrowserFactory.closeBrowser();
  //  }

//    @Test
//    @Order(1)
//    public void loginWithDataDrivingTesting() {
//        driver.get(baseUrl);
//
//        loginPage.loginUser(UserName, UserPass);
//        //driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
//        message = driver.findElement(loginPage.invalidUserOrPassword).getText();
//        Assert.assertEquals(ExpectedResult, message);
//
//        driver.get(baseUrl);
//        loginPage.loginUser(secondUserName,secondUserPass);
//        //driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
//        message = driver.findElement(loginPage.successfulLoginNameValidation).getText();
//        Assert.assertEquals("maria",message);
//    }
    @Test
    @Order(1)
    public void dataDrivingTesting(){
        count = exelReader.getRowCount();

        for (int i = 0; i < count; i++ ){

            driver.get(baseUrl);
            userName = exelReader.getCellDataUserName(i);
            userPass = exelReader.getCellUserPassword(i);
            expectedResult = exelReader.getCellExpectedResult(i);

            loginPage.loginUser(userName, userPass);

           try {
               Thread.sleep(3000);
           } catch (InterruptedException e) {
               throw new RuntimeException(e);
           }
            try {
                driver.findElement(activeSalesPage.logOut).isDisplayed();
                customWait.customWait(driver,Duration.ofSeconds(40), "presenceOfElementLocated",activeSalesPage.logOut);
                //customWait.customWait(driver, Duration.ofMinutes(1), "visibilityOfElementLocated",activeSalesPage.logOut);
                System.out.println(expectedResult + "1");
                message = driver.findElement(loginPage.successfulLoginNameValidation).getText();
                Assert.assertEquals(expectedResult,message);
            }
            catch (NoSuchElementException b){
               // customWait.customWait(driver, Duration.ofSeconds(40), "visibilityOfElementLocated",loginPage.invalidUserOrPassword);
                message = driver.findElement(loginPage.invalidUserOrPassword).getText();
                Assert.assertEquals(expectedResult, message);
            }
        }
    }
}
/*
Task
Description
Add a package that would contain data source at the main level i.e. in java_data_driven
Create a source file in Excel that will contain input data for the login form (you will need username, password and expected)
Modify the Credentials class so that it will read all the Excel records (rows and columns that are used)
Modify the test method so that
it will run and assert every record in the Excel file
it will print in the console the result (pass/fail) for every record
 */

package Helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;

public class Waits {
    public void customWait(WebDriver driver, Duration timeout,@NotNull String conditions, By locator){
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        switch (conditions){
            case "visibilityOfElementLocated":
                System.out.println("1");
                wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
                break;
            case "elementToBeClickable":
                System.out.println("2");
                wait.until(ExpectedConditions.elementToBeClickable(locator));
                break;
            case "elementToBeSelected":
                System.out.println("3");
                wait.until(ExpectedConditions.elementToBeSelected(locator));
                break;
            case "presenceOfElementLocated":
                System.out.println("4");
                wait.until(ExpectedConditions.presenceOfElementLocated(locator));
                break;
            default:
                System.out.println(driver);
                break;
        }
    }
}

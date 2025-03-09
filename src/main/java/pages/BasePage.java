package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import managers.DriverManager;

import java.time.Duration;

public class BasePage {

    protected DriverManager driverManager = DriverManager.getInstance();
    protected WebDriverWait wait = new WebDriverWait(driverManager.getDriver(), Duration.ofSeconds(10), Duration.ofMillis(1000));


    public BasePage() {
        PageFactory.initElements(driverManager.getDriver(), this);
    }

    protected void scrollToElementJs(WebElement element) {
        // Прокрутка через JavaScript
        ((JavascriptExecutor) driverManager.getDriver()).executeScript(
                "arguments[0].scrollIntoView({block: 'center', behavior: 'smooth'});",
                element
        );
    }

    protected WebElement waitUntilElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void waitUntilElementToBeVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void waitUntilElementToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void moveToElement(WebElement element) {
        Actions action = new Actions(driverManager.getDriver());
        action.moveToElement(element).build().perform();
    }

    protected Alert waitAlert() {
        return wait.until(ExpectedConditions.alertIsPresent());
    }
}


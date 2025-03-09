package managers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utils.PropConst;

import java.util.UUID;

public class DriverManager {
    private WebDriver driver;
    private static DriverManager INSTANCE = null;

    private DriverManager() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments(
                "--no-sandbox",
                "--disable-dev-shm-usage",
                "--headless=new", // Безголовый режим для GitHub Actions
                "--disable-gpu",
                "--remote-allow-origins=*" // Разрешить удаленные подключения
        );
        driver = new ChromeDriver(options);
    }

    public static DriverManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DriverManager();
        }
        return INSTANCE;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit(); // Используйте quit(), а не close()
            driver = null;
            INSTANCE = null; // Сбросьте синглтон
        }
    }
}



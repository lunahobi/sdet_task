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
        // Указываем уникальный временный каталог для каждого экземпляра
        options.addArguments("--user-data-dir=" + System.getProperty("java.io.tmpdir") + "/chrome_profile_" + UUID.randomUUID());
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
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



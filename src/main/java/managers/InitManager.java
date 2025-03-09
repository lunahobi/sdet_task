package managers;

import java.util.concurrent.TimeUnit;

import static utils.PropConst.*;

public class InitManager {
    private static final DriverManager driverManager = DriverManager.getInstance();

    public static void initFramework() {
        // Настройки драйвера
        driverManager.getDriver().manage().window().maximize();
        driverManager.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public static void quitFramework() {
        driverManager.quitDriver();
    }
}



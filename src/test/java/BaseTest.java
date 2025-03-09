import org.junit.AfterClass;
import managers.DriverManager;
import managers.InitManager;
import org.junit.BeforeClass;

public class BaseTest {
    private static final DriverManager driverManager = DriverManager.getInstance();

    @BeforeClass
    public static void before(){
        InitManager.initFramework();
        driverManager.getDriver().get("https://practice-automation.com/form-fields/");
    }

    @AfterClass
    public static void after(){
        InitManager.quitFramework();
    }

}

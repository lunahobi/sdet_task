import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pages.FormFieldsPage;

public class FormFieldsTest extends BaseTest{

    @Test
    @DisplayName("Работа с полями и формами")
    public void test(){
        FormFieldsPage formFieldsPage = new FormFieldsPage();
        formFieldsPage.fillName("Lusine")
                .fillPassword("1235")
                .chooseMilkCoffee()
                .chooseYellow()
                .likeAutomation("yes")
                .fillEmail("muradyan@mail.ru")
                .writeCountOfTools()
                .writeLongestTool()
                .clickSubmit();
    }
}

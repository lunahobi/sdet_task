package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class FormFieldsPage extends BasePage{

    @FindBy(id = "name-input")
    private WebElement nameInput;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[@type='checkbox']")
    private List<WebElement> checkboxList;

    @FindBy(xpath = "//input[@type='radio']")
    private List<WebElement> radioList;

    @FindBy(id = "automation")
    private WebElement automationSelect;

    @FindBy(xpath = "//*[@id='automation']//option")
    private List<WebElement> optionList;

    @FindBy(xpath = "//label/text()[. ='Automation tools']/following::li")
    private List<WebElement> automationToolsItems;

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "message")
    private WebElement messageInput;

    @FindBy(id = "submit-btn")
    private WebElement submitButton;

    @Step("Заполнить поле Name")
    public FormFieldsPage fillName(String name){
        waitUntilElementToBeVisible(nameInput);
        moveToElement(nameInput);
        nameInput.click();
        nameInput.sendKeys(name);
        return this;
    }

    @Step("Заполнить поле Password")
    public FormFieldsPage fillPassword(String password){
        waitUntilElementToBeVisible(passwordInput);
        moveToElement(passwordInput);
        passwordInput.click();
        passwordInput.sendKeys(password);
        return this;
    }

    @Step("Из списка What is your favorite drink? выбрать Milk и Coffee")
    public FormFieldsPage chooseMilkCoffee(){
        for (WebElement checkbox: checkboxList){
            moveToElement(checkbox);
            String value = checkbox.getAttribute("value");
            if (value.equals("Milk") || value.equals("Coffee")){
                checkbox.click();
            }
        }
        return this;
    }

    @Step("Из списка What is your favorite color? выбрать Yellow")
    public FormFieldsPage chooseYellow(){
        for (WebElement radio: radioList){
            moveToElement(radio);
            String value = radio.getAttribute("value");
            if (value.equals("Yellow")){
                radio.click();
            }
        }
        return this;
    }

    @Step("В поле Do you like automation? выбрать любой вариант")
    public FormFieldsPage likeAutomation(String answer){
        moveToElement(automationSelect);
        waitUntilElementToBeClickable(automationSelect);
        automationSelect.click();
        for (WebElement option: optionList){
            String value = option.getAttribute("value");
            if (value.contains(answer)){
                option.click();
            }
            return this;
        }
        Assert.fail("Вариант ответа '" + answer + "' не был найден на cтранице!");
        return this;
    }

    @Step("Поле Email заполнить строкой формата name@example.com")
    public FormFieldsPage fillEmail(String email){
        waitUntilElementToBeVisible(emailInput);
        moveToElement(emailInput);
        emailInput.click();
        emailInput.sendKeys(email);
        return this;
    }

    @Step("В поле Message написать количество инструментов, описанных в пункте Automation tools")
    public FormFieldsPage writeCountOfTools(){
        moveToElement(messageInput);
        messageInput.click();
        messageInput.sendKeys(String.valueOf(automationToolsItems.size()));
        return this;
    }

    @Step("В поле Message дополнительно написать инструмент из списка Automation tools, содержащий наибольшее количество символов")
    public FormFieldsPage writeLongestTool(){
        moveToElement(messageInput);
        messageInput.click();
        WebElement longestTool = null;
        int cnt=0;
        for (WebElement tool: automationToolsItems){
            int cnt_tool = tool.getText().length();
            if (cnt_tool > cnt){
                cnt = cnt_tool;
                longestTool = tool;
            }
        }
        messageInput.sendKeys(longestTool.getText());
        return this;
    }

    @Step("Нажать на кнопку Submit")
    public FormFieldsPage clickSubmit(){
        moveToElement(submitButton);
        submitButton.submit();
        Alert alert = waitAlert();
        Assert.assertEquals("Текст алерта неверный", "Message received!", alert.getText());
        return this;
    }
}

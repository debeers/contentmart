package PageObjects.General;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;


public class MyMessagesPage extends LeftMenuGeneralPage {


    @FindBy(id = "comment_message")
    public WebElement messageTextField;

    @FindBy(id = "send_message")
    public  WebElement sendMessageButton;

    @FindBy(id = "comment_file")
    public  WebElement attachFileInput;

    @FindBy(xpath = ".//*[@id='comments-form']/div[1]")
    public WebElement allertMessageUnderTheTextField;

    @FindBy(xpath = "html/body/div[2]/div[1]/div/div/div[1]/span")
    public  WebElement closeMessageWindow;

    @FindBy(xpath = "html/body/div/div[3]/div/div/div/h1")
    public WebElement header;

    @FindBy(xpath = "//*[contains(text(),'DMX')]")
    public  WebElement downloadedFileToMessagesXpathDepend;

    @FindBy(className = "fileDownloadIcon")
    public  WebElement fileDownloadIcon;


    public void closeMessageWindowClick() {

        $(closeMessageWindow).shouldBe(present).shouldBe(visible).click();

    }


    public void inputFileToTheMessage(String filePath) {

        $(attachFileInput).shouldBe(present).sendKeys(filePath);
        $(sendMessageButton).shouldBe(visible).click();
    }


    public void sendMessageButtonClick() {

        $(sendMessageButton).shouldBe(present).click();

    }


    public void sendTextMessage(String message) {

        $(messageTextField).shouldBe(visible).sendKeys(message);
        sendMessageButtonClick();

    }

    public void sendTextMessageWithFile(String message) {

        $(messageTextField).shouldBe(visible).sendKeys(message);
        $(sendMessageButton).shouldBe(exist).click();

    }


    public MyMessagesPage(WebDriver driver) {

        super(driver);
    }


}
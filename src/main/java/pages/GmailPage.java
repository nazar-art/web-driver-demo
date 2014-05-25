package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.utils.TestUtils;
import task2.ElementDecorator;
import task2.elements.Button;
import task2.elements.Link;
import task2.elements.Menu;
import task2.elements.TextField;

import java.util.List;
import static java.util.concurrent.TimeUnit.*;

public class GmailPage extends BasePage {

    private static Logger log = Logger.getLogger(GmailPage.class);

    @FindBy(xpath = "//div[@class='T-I J-J5-Ji T-I-KE L3']")
    private Button composeBtn;

    @FindBy(css = "#gb_71")
    private Link signOutLink;

    //    @FindBy(xpath = "//iframe[@tabindex='1']")
    @FindBy(xpath = "//div[@class = 'Am Al editable']/iframe")
    private WebElement frameMessageEditor;

    @FindBy(xpath = "//img[@aria-label='Save & Close']")
    private Button frameSaveAndCloseBtn;

    @FindBy(xpath = "//*[starts-with(@title, 'Inbox')]")
    private TextField inboxMessagesLink;

    @FindBy(xpath = "//a[starts-with(@title, 'Drafts')]")
    private Link draftMessagesLink;

    @FindBy(xpath = "//*[@class='ait']")
    private Link moreOptionsLink;

    @FindBy(xpath = "//*[@title='All Mail']")
    private Link allMailLink;

    @FindBy(css = ".gb_0")
    private Menu userOptionsDropDownMenu;

    public GmailPage() {
        PageFactory.initElements(new ElementDecorator(driver), this);
    }

    public void clickProfileOptionMenu() {
        userOptionsDropDownMenu.click();
    }

    public void clickSignOut() {
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.
                elementToBeClickable(signOutLink));
        signOutLink.click();
    }

    public void pressComposeButton() {
        composeBtn.click();
    }

    public void sendTextToMessageFrame(String msg) {
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(frameMessageEditor));
//            TestUtils.interrupt(SECONDS, 1);
            driver.switchTo().frame(frameMessageEditor);
            WebElement element = driver.switchTo().activeElement();
            TestUtils.interrupt(SECONDS, 1);
            element.sendKeys(msg);
            TestUtils.interrupt(SECONDS, 1);
            driver.switchTo().defaultContent();
            frameSaveAndCloseBtn.click();
    }

    public void clickAllMailLink() {
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.
                elementToBeClickable(moreOptionsLink));
        moreOptionsLink.click();

        (new WebDriverWait(driver, 10)).until(ExpectedConditions.
                elementToBeClickable(allMailLink));
        allMailLink.click();
    }

    public void clickInboxLink() {
        inboxMessagesLink.click();
    }

    public void clickDraftLink() {
        draftMessagesLink.click();
    }

    public List<WebElement> takeAllMessages() {
        List<WebElement> elements = null;
            TestUtils.interrupt(SECONDS, 1);
            elements = driver.findElements(By.xpath(TestUtils.XPATH_ALL_LETTERS_FROM_PAGE));
        return elements;
    }

    public List<WebElement> takeInboxMessage() {
        return driver.findElements(By.xpath(TestUtils.XPATH_ALL_LETTERS_FROM_PAGE));
    }

    public List<WebElement> takeDraftMessage() {
        return driver.findElements(By.xpath(TestUtils.XPATH_ALL_LETTERS_FROM_PAGE));
    }

    public void clickComposeBtn() {
        composeBtn.click();
    }

    public void typeTextToMessage(String msg) {
        frameMessageEditor.sendKeys(msg);
    }

    public void clickSaveAndCloseBtn() {
        frameSaveAndCloseBtn.click();
    }

}

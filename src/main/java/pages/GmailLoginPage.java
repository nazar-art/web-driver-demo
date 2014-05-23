package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.interfaces.IPage;

/**
 * @author Nazar Lelyak.
 * @version 1.00 2014-05-20.
 */
public class GmailLoginPage implements IPage {

    private final WebDriver driver;

    public static final String LOGIN_URL = "https://accounts.google.com/ServiceLogin?sacu=1&scc=1&continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&hl=uk&service=mail";

    @FindBy(id = "Email")
    private WebElement loginField;

    @FindBy(id = "Passwd")
    private WebElement passwordField;

    @FindBy(id = "signIn")
    private WebElement submitBtn;

    public GmailLoginPage(WebDriver driver) {
        this.driver = driver;

        // Check that we're on the right page.
        if (!"gmail".equals(driver.getTitle().toLowerCase())) {
            throw new IllegalStateException("This is not the login page");
        }
        PageFactory.initElements(driver, this);
    }
    public GmailPage loginAs(String username, String userpassword) {
        loginField.sendKeys(username);
        passwordField.sendKeys(userpassword);
        submitBtn.click();

        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith("gmail");
            }
        });
        // Return a new page object representing the destination.
        return new GmailPage(driver);
    }

    @Override
    public void init(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
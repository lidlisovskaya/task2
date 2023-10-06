package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import java.time.Duration;

public class PastebinPage extends BasePage {
    private static final String HOMEPAGE_URL = "https://pastebin.com/";
    @FindBy(name = ("PostForm[text]"))
    private WebElement pasteField;
    @FindBy(id = "select2-postform-expiration-container")
    private WebElement pasteExpirationPushing;
    @FindBy(xpath = "//li[text()='10 Minutes']")
    private WebElement pasteExpirationSelection;
    @FindBy(id = "postform-name")
    private WebElement pasteNameFieldPushing;
    @FindBy(xpath = ("//button[text() = 'Create New Paste']"))
    private WebElement newPasteButton;

    @FindBy(id = "select2-postform-format-container")
    private WebElement syntaxField;

    @FindBy(xpath = "//ul/li[text()[contains(.,'Bash')]]")
    private WebElement syntaxPushing;

    @FindBy(xpath = "//div[@class = 'info-top']/h1")
    private WebElement ResultTextName;

    @FindBy(xpath = "//ol[@class='bash']")
    private WebElement ResultTextFieldContext;

    @FindBy(xpath = "//a[text() = 'Bash']")
    private WebElement ResultTextHighlight;

    public PastebinPage(WebDriver driver) {

        super(driver);
    }

    protected PastebinPage pastebinPage;

    @Override
    public void openPage() {
        driver.get(HOMEPAGE_URL);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
    }


    public void writeTextInToNewPasteField(String textForNewPaste) {

        waitForElementLocated(pasteField).sendKeys(textForNewPaste);
    }

    public void selectPasteExpiration() {
        pasteExpirationPushing.click();
        waitForElementLocated(pasteExpirationSelection).click();
    }

    public void writeTextInToPasteNameField(String textForPasteName) {
        pasteNameFieldPushing.sendKeys(textForPasteName);
    }

    public void pressCreateNewPasteButton() {
        newPasteButton.click();
    }

    public void selectSyntaxHighlighting() {
        syntaxField.click();
        syntaxPushing.click();
    }

    public String getResultTextName() {
        String resultTextName = waitForElementLocated(ResultTextName).getText();
        return resultTextName;
    }

    public String getResultText() {
        String text = waitForElementLocated(ResultTextFieldContext).getText();
        return text;
    }

    public String getResultTextHighlight() {
        String highlight = waitForElementLocated(ResultTextHighlight).getText();
        return highlight;
    }
}


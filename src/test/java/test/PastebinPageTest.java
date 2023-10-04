package test;


import pages.PastebinPage;
import dev.failsafe.internal.util.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

class PastebinPageTest {
    private static final String TEXT_FOR_PASTE_NAME = "how to gain dominance among developers";
    private static final String TEXT_FOR_NEW_PASTE = "git config --global user.name  \"New Sheriff in Town\"" +
            "\ngit reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")" +
            "\ngit push origin master --force";

    private WebDriver driver;
    protected PastebinPage pastebinPage;


    @BeforeEach
    public void setDriver() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        pastebinPage = new PastebinPage(driver);
    }

    @Test
    public void IsTheActualTitleEqualledToTheExpectedTitle() {
        pastebinPage.openPage();
        pastebinPage.writeTextInToNewPasteField(TEXT_FOR_NEW_PASTE);
        pastebinPage.selectSyntaxHighlighting();
        pastebinPage.selectPasteExpiration();
        pastebinPage.writeTextInToPasteNameField(TEXT_FOR_PASTE_NAME);
        pastebinPage.pressCreateNewPasteButton();
        WebElement textFieldName = new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class = 'info-top']/h1")));
        Assert.isTrue(textFieldName.getText().equals(TEXT_FOR_PASTE_NAME), "Failed");
    }

    @Test
    public void IsTheActualTextInFieldEqualledToTheExpectedText() {
        pastebinPage.openPage();
        pastebinPage.writeTextInToNewPasteField(TEXT_FOR_NEW_PASTE);
        pastebinPage.selectSyntaxHighlighting();
        pastebinPage.selectPasteExpiration();
        pastebinPage.writeTextInToPasteNameField(TEXT_FOR_PASTE_NAME);
        pastebinPage.pressCreateNewPasteButton();
        WebElement textField = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ol[@class='bash']")));
        Assert.isTrue(textField.getText().equals(TEXT_FOR_NEW_PASTE), "Failed");

    }

    @Test
    public void IsTheSyntaxHighlighted() {
        pastebinPage.openPage();
        pastebinPage.writeTextInToNewPasteField(TEXT_FOR_NEW_PASTE);
        pastebinPage.selectSyntaxHighlighting();
        pastebinPage.selectPasteExpiration();
        pastebinPage.writeTextInToPasteNameField(TEXT_FOR_PASTE_NAME);
        pastebinPage.pressCreateNewPasteButton();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        WebElement textField = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text() = 'Bash']")));
        Assert.isTrue(textField.getText().equals("Bash"),"Failed");
    }

    @AfterEach
    public void closeDriver() {
        driver.quit();
    }
}

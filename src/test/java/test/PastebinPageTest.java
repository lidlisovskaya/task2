package test;

import pages.PastebinPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import org.assertj.core.api.SoftAssertions;

class PastebinPageTest {
    private static final String TEXT_FOR_PASTE_NAME = "how to gain dominance among developers";
    private static final String TEXT_FOR_NEW_PASTE = "git config --global user.name  \"New Sheriff in Town\"" +
            "\ngit reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")" +
            "\ngit push origin master --force";

    private WebDriver driver;
    protected PastebinPage pastebinPage;
    private SoftAssertions softAssertions;


    @BeforeEach
    public void setDriver() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        pastebinPage = new PastebinPage(driver);
        softAssertions = new SoftAssertions();

    }

    @Test
    public void pasteBinResultTest() {
        pastebinPage.openPage();
        pastebinPage.writeTextInToNewPasteField(TEXT_FOR_NEW_PASTE);
        pastebinPage.selectSyntaxHighlighting();
        pastebinPage.selectPasteExpiration();
        pastebinPage.writeTextInToPasteNameField(TEXT_FOR_PASTE_NAME);
        pastebinPage.pressCreateNewPasteButton();
        String result = pastebinPage.getResultTextName();
        String resultText = pastebinPage.getResultText();
        String resultTextHighlight = pastebinPage.getResultTextHighlight();
        softAssertions.assertThat(result).isEqualTo(TEXT_FOR_PASTE_NAME);
        softAssertions.assertThat(resultText).isEqualTo(TEXT_FOR_NEW_PASTE);
        softAssertions.assertThat(resultTextHighlight).isEqualTo("Bash");
    }

    @AfterEach
    public void closeDriver() {
        softAssertions.assertAll();
        driver.quit();
    }

}

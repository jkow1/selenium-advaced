package pages.basic;

import lombok.extern.slf4j.Slf4j;
import org.decimal4j.util.DoubleRounder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

@Slf4j
public class BasePage {

    public WebDriver driver;
    public WebDriverWait wait;

    public BasePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(System.getProperty("webElementTimeOut"))));
    }

    public BasePage(WebDriver driver, WebElement element) {
        new BasePage(driver);
        PageFactory.initElements(new DefaultElementLocatorFactory(element), this);
    }

    public String getWebElementText(WebElement element) {
        String text = element.getText();
        log.debug("Element text: {}", text);
        return text;
    }

    public void clickOnBtn(WebElement element) {
        log.debug("Clicking on {}", element.getAttribute("class"));
        element.click();
    }

    public void sendKeys(WebElement element, String keys) {
        log.debug("Typing: {}", keys);
        element.sendKeys(keys);
    }

    public void sendKeysWithClear(WebElement element, String keys) {
        element.clear();
        sendKeys(element, keys);
    }

    public WebElement getRandomElementFromList(List<WebElement> list) {
        return list.get(new Random(System.currentTimeMillis()).nextInt(list.size()));
    }

    public void waitToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void returnToPrevSite() {
        driver.navigate().back();
    }

    public void waitToBeInvisible(WebElement element) {
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public boolean elementIsVisible(WebElement element) {
        return element.isDisplayed();
    }

    public boolean elementIsPresent(List<WebElement> element) {
        return element.size() > 0;
    }

    public String getValue(WebElement element) {
        String value = element.getAttribute("value");
        log.debug("Value of WebElement is {}", value);
        return value;
    }

    public double getPrice(WebElement element) {
        return getFormattedDouble(Double.parseDouble(getWebElementText(element).replaceAll("\\$", "")));
    }

    public double getFormattedDouble(double number) {
        return DoubleRounder.round(number, 2);
    }

    public void waitToBtnIsClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}

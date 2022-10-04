package configuration;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

@Slf4j
public class DriverFactory {

    private WebDriver driver;
    private String browserName;


    public WebDriver getDriver() {
        this.browserName = System.getProperty("browserName");
        log.info("Configuring {} driver", browserName);
        switch (this.browserName) {
            case "chrome" -> {
                ChromeOptions optionsC = new ChromeOptions();
                WebDriverManager.chromedriver().setup();
                optionsC.addArguments("start-maximized");
                driver = new ChromeDriver(optionsC);
            }
            case "firefox" -> {
                FirefoxOptions optionsF = new FirefoxOptions();
                WebDriverManager.firefoxdriver().setup();
                optionsF.addArguments("start-maximized");
                driver = new FirefoxDriver(optionsF);
            }
            default -> {
                EdgeOptions optionsE = new EdgeOptions();
                optionsE.addArguments("start-maximized");
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
            }
        }
        return this.driver;
    }

}

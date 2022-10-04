package base;

import configuration.AppProperties;
import configuration.DriverFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

import java.util.logging.Level;

@ExtendWith(TestResultLoggerExtension.class)
@Slf4j
public class TestBase {

    public WebDriver driver;
    protected static DriverFactory driverHandler;
    private static AppProperties appProperties;

    @BeforeAll
    public static void setDriver() {
        appProperties = AppProperties.getInstance();
        driverHandler = new DriverFactory();
        java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
    }
    @BeforeEach
    public void setUp(){
        driver = driverHandler.getDriver();
        log.info("Driver initialized");
        driver.get(System.getProperty("appUrl"));
        log.info("Entering url {}", System.getProperty("appUrl"));
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
        log.debug("Driver closed.");
    }

}

package base;

import configuration.AppProperties;
import configuration.DriverHandler;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

@ExtendWith(TestResultLoggerExtension.class)
@Slf4j
public class TestBase {

    protected static WebDriver driver;
    protected static DriverHandler driverHandler;
    private static AppProperties appProperties;

    @BeforeAll
    public static void setDriver() {
        appProperties = AppProperties.getInstance();
        driverHandler = new DriverHandler();
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

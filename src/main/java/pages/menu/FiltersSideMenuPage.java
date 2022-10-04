package pages.menu;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.basic.BasePage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class FiltersSideMenuPage extends BasePage {

    public FiltersSideMenuPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#search_filters_wrapper")
    private WebElement filtersSideMenu;

    @FindBy(css = ".ui-slider a")
    private List<WebElement> priceSliders;

    @FindBy(css = ".faceted-slider p")
    private WebElement priceInterval;

    @FindBy(css = ".overlay__content")
    private WebElement overlay;

    @FindBy(css = ".clear-all-wrapper button")
    private List<WebElement> clearFiltersBtn;

    public boolean sideMenuIsDisplayed() {
        return filtersSideMenu.isDisplayed();
    }

    public List<Integer> getPriceIntervalValues() {
        String interval = getWebElementText(priceInterval);
        List<String> list = new ArrayList<>();
        List.of(interval.split(" - ")).stream()
                .forEach(element -> list.add(Arrays.stream(element.split("\\.00"))
                        .toList().get(0).replaceAll("\\D+", ""))
                );
        List<Integer> priceInterval = new ArrayList<>();
        list.stream()
                .forEach(element -> priceInterval.add(Integer.parseInt(element)));

        log.debug("Min {} and max {} price", priceInterval.get(0), priceInterval.get(1));
        return priceInterval;
    }

    public int getCurrentMinInterval() {
        return getPriceIntervalValues().get(0);
    }

    public int getCurrentMaxInterval() {
        return getPriceIntervalValues().get(1);
    }

    public void moveLeftSlider(int minPrice) {
        while (getCurrentMinInterval() < minPrice) {
            priceSliders.get(0).sendKeys(Keys.ARROW_RIGHT);
            waitToBeInvisible(overlay);
        }
    }

    public void moveRightSlider(int maxPrice) {
        while (getCurrentMaxInterval() > maxPrice) {
            priceSliders.get(1).sendKeys(Keys.ARROW_LEFT);
            waitToBeInvisible(overlay);
        }
    }

    public void clearAllFilters() {
        clickOnBtn(clearFiltersBtn.get(0));
        waitToBeInvisible(overlay);
    }

    public boolean clearFiltersBtnIsPresent() {
        return elementIsPresent(clearFiltersBtn);
    }

}

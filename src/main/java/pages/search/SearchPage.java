package pages.search;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.basic.BasePage;

import java.util.List;

@Slf4j
public class SearchPage extends BasePage {

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#search_widget input[name='s']")
    private WebElement searchInput;

    @FindBy(css = "button .search")
    private WebElement searchButton;

    @FindBy(css = ".ui-autocomplete")
    private WebElement dropdownMenu;

    @FindBy(css = ".ui-autocomplete .ui-menu-item")
    private List<WebElement> dropdownResults;

    public SearchPage searchThis(String text) {
        sendKeys(searchInput, text);
        return this;
    }

    public SearchPage clickOnSearchBtn() {
        clickOnBtn(searchButton);
        return this;
    }

    public List<String> getAllSearchResults() {
        String actualSearchInput = searchInput.getText();
        waitToBeVisible(dropdownMenu);
        return dropdownResults.stream()
                .map(WebElement::getText)
                .toList();
    }
}
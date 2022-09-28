package pages.menu;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.basic.BasePage;

import java.util.List;

public class CategoriesTopMenuPage extends BasePage {
    public CategoriesTopMenuPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "a[data-depth='0']")
    private List<WebElement> topMenuCategories;

    @FindBy(css = "#main .h1")
    private WebElement openedCategory;

    public List<WebElement> getTopMenuCategories() {
        return topMenuCategories;
    }

    public void clickOn(int index) {
        clickOnBtn(topMenuCategories.get(index));
    }

    public String getOpenedCategoryName(){
        return getWebElementText(openedCategory);
    }




    public String getCategoryName(int index){
        return getWebElementText(topMenuCategories.get(index));
    }
}

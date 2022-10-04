package pages.menu;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.basic.BasePage;

import java.util.List;

public class CategoriesPage extends BasePage {
    public CategoriesPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "a[data-depth='0']")
    private List<WebElement> topMenuCategories;

    @FindBy(css = "#main .h1")
    private WebElement openedCategory;

    @FindBy(css = ".category-sub-menu li[data-depth='0']")
    private List<WebElement> subcategoriesList;

    public List<WebElement> getCategoryAllSubcategories() {
        return subcategoriesList;
    }

    public List<WebElement> getTopMenuCategories() {
        return topMenuCategories;
    }

    public String clickOn(int index) {
        String btnText = getWebElementText(topMenuCategories.get(index));
        clickOnBtn(topMenuCategories.get(index));
        return btnText;
    }

    public String clickOnSubcategory(int index) {
        String btnText = getWebElementText(subcategoriesList.get(index)).toUpperCase();
        clickOnBtn(subcategoriesList.get(index));
        return btnText;
    }

    public String getOpenedCategoryName() {
        return getWebElementText(openedCategory);
    }

    public void clickOnCategory(String catName) {
        clickOnBtn(topMenuCategories.stream()
                .filter(category -> getWebElementText(category).equals(catName))
                .findFirst()
                .get());
    }


}

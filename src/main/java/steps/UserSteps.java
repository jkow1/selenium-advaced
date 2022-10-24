package steps;

import models.entities.User;
import org.openqa.selenium.WebDriver;
import pages.menu.TopHeaderPage;

public class UserSteps extends TopHeaderPage {

    public UserSteps(WebDriver driver) {
        super(driver);
    }

    public UserSteps logInUser(User user) {
        clickOnOnSignInBtn().waitToSignInFormBeVisible()
                .typeUserEmail(user.getEmail())
                .typeInUserPassword(user.getPassword())
                .clickOnSignInBtn();
        return this;
    }
}

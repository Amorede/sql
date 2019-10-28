package BigmirNet;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BigmirMain {

    @FindBy(xpath = "//a[@href='//passport.bigmir.net/registration']")
    private WebElement registrationButton;


    private WebDriver webDriver;
    public BigmirMain(final WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    final static Logger logger = Logger.getLogger(BigmirRegistration.class);

    public void clickRegistration() {
        registrationButton.click();
        logger.info("Registration button was clicked");
    }
}
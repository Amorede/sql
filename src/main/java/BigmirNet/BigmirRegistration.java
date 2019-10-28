package BigmirNet;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BigmirRegistration {

    @FindBy(xpath = "//input[@id='login']")
    private WebElement inputLogin;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement inputPassword;

    @FindBy(xpath = "//input[@id='password_conf']")
    private WebElement inputPasswordConf;

    @FindBy(xpath = "//input[@id='secret_a']")
    private WebElement inputSecretWord;

    @FindBy(xpath = "//input[@id='rules_agree_id']")
    private WebElement termsAndConditionsCheck;

    @FindBy(xpath = "//input[@id='submit']")
    private WebElement sumitButton;


    @FindBy(xpath = "//span[@id='recaptcha-anchor']")
    private WebElement capchaCheck;


    private WebDriver webDriver;

    public BigmirRegistration(final WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    final static Logger logger = Logger.getLogger(BigmirRegistration.class);

    public void setCapchaCheck() {
        webDriver.switchTo().frame(0);
        capchaCheck.click();
        webDriver.switchTo().defaultContent();
        termsAndConditionsCheck.click();
        logger.info("Capcha 'i am not a robot' and 'Terms and conditions' was clicked");

    }

    public void setInputPassword(String password) {
        inputPassword.sendKeys(password);
        inputPasswordConf.sendKeys(password);
        logger.info("Password and Confirmation (" + password + ") has been set");
    }

    public void setInputSecretWord(String secretWord) {
        inputSecretWord.sendKeys(secretWord);
        logger.info("'Secret word' has been set");

    }

    public void submitButton() {
        sumitButton.click();
        logger.info("Submit button was clicked");

    }

    private double generateNewLogin(double min, double max) {
        return (int) (Math.random() * ((max - min) + 1)) + min;
    }

    public void setInputLogin() {
        double rand = generateNewLogin(0, 999);
        String login = "fkg7h" + rand + "4f3v6";
        inputLogin.sendKeys(login);
        logger.info("unique login '" + login + "' has been set");
    }
}

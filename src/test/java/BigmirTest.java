import BigmirNet.BigmirMain;
import BigmirNet.BigmirRegistration;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class BigmirTest {

    private WebDriver driver;
    final static Logger logger = Logger.getLogger(BigmirRegistration.class);

    @Before
    public void setDriver() {
        System.setProperty("webdriver.firefox.driver", "gecodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        logger.info("Browser is opened");
        String log4jConfPath = "D://Amorede-qa_automation_and_exercise-34b6730766ff/src/main/resources/log4j.properties";
        PropertyConfigurator.configure(log4jConfPath);
    }

    @After
    public void driverClose() {
        driver.close();
        logger.info("Driver was closed");
    }

    @Test
    public void bigmirRegistrationTest() {
        String password = "password999";
        driver.get("https://mail.bigmir.net");
        logger.info("'https://mail.bigmir.net' link is opened");

        BigmirMain bigmirMain = new BigmirMain(driver);
        bigmirMain.clickRegistration();
        BigmirRegistration bigmirRegistration = new BigmirRegistration(driver);

        bigmirRegistration.setInputLogin();
        bigmirRegistration.setInputPassword(password);
        bigmirRegistration.setInputSecretWord(password);
        bigmirRegistration.setCapchaCheck();
        bigmirRegistration.submitButton();
    }
}

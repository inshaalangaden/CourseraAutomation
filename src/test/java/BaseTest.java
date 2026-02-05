import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.time.Duration;


public class BaseTest {
    protected final Logger log = LogManager.getLogger(this.getClass());
    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public WebDriver getDriver(){
        return driver.get();
    }

    @BeforeMethod
    @Parameters("browser")
    public void setUp(String browserName){
        log.info("Setting up the browser");
        WebDriver localDriver;
        if(browserName.equalsIgnoreCase("chrome")){
            localDriver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {
            System.getProperty("webdriver.edge.driver","src/main/resources/msedgedriver.exe");
            localDriver = new EdgeDriver();
        }else{
            throw new RuntimeException("Browser not supported");
        }

        driver.set(localDriver);
        getDriver().get("https://www.coursera.org/");
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod
    public void tearDown(){
        log.info("Closing the browser");
        if(getDriver() != null) getDriver().quit();
        driver.remove();
    }
}

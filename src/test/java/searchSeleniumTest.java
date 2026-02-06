import com.coursera.pages.HomePage;
import com.coursera.pages.SearchResultPage;
import org.testng.annotations.Test;

public class searchSeleniumTest extends BaseTest {

    @Test
    public void searchSeleniumJava(){
        log.info("TEST: Search Selenium Java");
        HomePage hp = new HomePage(getDriver());
        hp.search("Selenium Java");
        SearchResultPage srp = new SearchResultPage(getDriver());
        srp.verification();
    }
}

import com.coursera.pages.HomePage;
import com.coursera.pages.PythonPage;
import org.testng.annotations.Test;

public class findLevelTest extends BaseTest{
    @Test
    public void levelSearch(){
        log.info("TEST: Extracting the level of the first Python course");
        HomePage hp = new HomePage(getDriver());
        hp.selectPythonCourse();
        PythonPage pp = new PythonPage(getDriver());
        pp.extractLevel();
    }
}

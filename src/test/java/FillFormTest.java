import com.coursera.pages.FormPage;
import com.coursera.pages.HomePage;
import com.coursera.pages.UniversityPage;
import com.coursera.utils.ExcelUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class FillFormTest extends BaseTest {

    @DataProvider(name = "FormExcelData" , parallel = true)
    public Object[][] getExcelData() throws IOException {
        String filePath = System.getProperty("user.dir") + "/src/test/resources/FormData.xlsx";
        return ExcelUtils.getTestData(filePath, "Sheet1");
    }

    @Test(dataProvider = "FormExcelData")
    public void fillForm(String sNo,String fName, String lName, String mail, String phone,
                         String instType, String instName, String role,
                         String dpmt, String need, String cntry, String testType)throws IOException {

        String filePath = System.getProperty("user.dir") + "/src/test/resources/FormData.xlsx";
        int currentRow = Integer.parseInt(sNo);

        log.info("TEST: Navigate to universities");
        HomePage hp = new HomePage(getDriver());
        hp.navigateToUniversities();

        log.info("TEST: Click contact us");
        UniversityPage up = new UniversityPage(getDriver());
        up.clickContactUs();

        log.info("TEST: fill the form");
        FormPage fp = new FormPage(getDriver());
        fp.fillForm(fName, lName, mail, phone, instType, instName, role, dpmt, need, cntry);

        log.info("TEST: Submit the form");
        fp.clickSubmit();

        boolean isAlertPresent = fp.isAlertPresent();
        boolean testPassed = false;
        String resultMessage = "";

        log.info("TEST: If the test is negative, check if the alert is present and resultMessage will be 'PASS'");
        if (testType.equalsIgnoreCase("Negative")) {
            testPassed = isAlertPresent;
            resultMessage = testPassed ? "PASS" : "FAIL: Expected alert but none appeared";
        } else {
            log.info("TEST: If the test is positive, if the alert is not present, resultMessage will be 'PASS'");
            testPassed = !isAlertPresent;
            resultMessage = testPassed ? "PASS" : "FAIL: " + fp.getAlert();
        }
        log.info("TEST: write the content in resultMessage into the excel sheet");
        ExcelUtils.setCellData(filePath, "Sheet1", currentRow, resultMessage);
        Assert.assertTrue(testPassed);
    }
}
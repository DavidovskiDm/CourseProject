package VelcomTest.Tests;

import VelcomTest.Pages.LoginPersonalOffice;
import VelcomTest.Pages.MainPage;
import VelcomTest.Pages.PersonalOffice;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import webdriver.BaseTest;

/**
 * Created by Kefir on 28.03.2017.
 */




public class FinancialReportTest extends BaseTest {

    private String code;
    private String numberPhone;
    private String password;
    private String year;
    private String month;

    @BeforeTest
    @Parameters({"code","numberPhone","password","year","month"})
     public void setUp(String code, String numberPhone, String password, String year,String month ) {
        System.setProperty("webdriver.gecko.driver", "\\src\\java\\resources\\geckodriver.exe");
        System.setProperty("webdriver.chrome.driver", "\\src\\java\\resources\\chromedriver.exe");
        this.code = code;
        this.numberPhone = numberPhone;
        this.password = password;
        this.year = year;
        this.month = month;

           }



    public void runTest() {

        logStep("Go to ISSA");
        MainPage mainpage = new MainPage();
        mainpage.goToAuthorization();

        logStep("Autorization on ISSA");
        LoginPersonalOffice login = new LoginPersonalOffice();
        login.authorization(code, numberPhone, password);


        logStep("Select invoice menu");
        PersonalOffice report = new PersonalOffice();
        report.navigateMenu();
        logStep("Get to invoice to mail");
        report.reportToMail(month, year);
    }


}

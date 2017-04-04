package VelcomTest.Tests;

import VelcomTest.Pages.CareerPage;
import VelcomTest.Pages.MainPage;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import webdriver.BaseTest;

/**
 * Created by Kefir on 04.04.2017.
 */
public class AboutVelcomTest  extends BaseTest{

    private String aboutcompany;
    private String work;
    private String nameMenuCareer;


    @BeforeTest
    @Parameters({"aboutcompany","work","nameMenuCareer"})
    public void setUp(String aboutcompany, String work, String nameMenuCareer ) {
        System.setProperty("webdriver.gecko.driver", "\\src\\java\\resources\\geckodriver.exe");
        System.setProperty("webdriver.chrome.driver", "\\src\\java\\resources\\chromedriver.exe");
        this.aboutcompany = aboutcompany;
        this.work = work;
        this.nameMenuCareer = nameMenuCareer;

    }

    public void runTest(){

        logStep();
        MainPage mainPage = new MainPage();
        logStep();
        mainPage.navigateTopMenu(aboutcompany);
        logStep();
        mainPage.navigateSubMenu(work);
        logStep();
        CareerPage careerPage = new CareerPage();
        careerPage.selectMenu(nameMenuCareer);

    }
}

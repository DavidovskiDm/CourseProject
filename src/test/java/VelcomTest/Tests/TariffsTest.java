package VelcomTest.Tests;

import VelcomTest.Pages.MainPage;
import VelcomTest.Pages.TariffsPage;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import webdriver.BaseTest;

/**
 * Created by Kefir on 03.04.2017.
 */
public class TariffsTest extends BaseTest {
    private String nameTopMenu;
    private String nameSubMenu;
    private String tariffGroup;
    private String nameTariff;

    @BeforeTest
    @Parameters({"nameTopMenu","nameSubMenu","tariffGroup","nameTariff"})
    public void setUp(String nameTopMenu, String nameSubMenu, String tariffGroup, String nameTariff ) {
        System.setProperty("webdriver.gecko.driver", "\\src\\java\\resources\\geckodriver.exe");
        System.setProperty("webdriver.chrome.driver", "\\src\\java\\resources\\chromedriver.exe");
        this.nameTopMenu = nameTopMenu;
        this.nameSubMenu = nameSubMenu;
        this.tariffGroup = tariffGroup;
        this.nameTariff = nameTariff;
        }

    public void runTest(){

        logStep("Select TopMenu");
        MainPage mainPage = new MainPage();
        mainPage.navigateTopMenu(nameTopMenu);

        logStep("Select SubMenu");
        mainPage.navigateSubMenu(nameSubMenu);

        logStep("Select Tariff");
        TariffsPage tariff = new TariffsPage();
        tariff.selectTariff(tariffGroup,nameTariff);



    }
}

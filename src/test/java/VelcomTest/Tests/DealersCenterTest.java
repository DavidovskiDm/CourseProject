package VelcomTest.Tests;

import VelcomTest.Pages.DealerCenterPage;
import VelcomTest.Pages.MainPage;
import VelcomTest.Pages.SupportPage;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import webdriver.BaseTest;

/**
 * Created by Kefir on 03.04.2017.
 */
public class DealersCenterTest extends BaseTest {
    private String nameTopMenu;
    private String nameSubMenu;
    private String nameSupportMenu;
    private String dealer;
    private String town;
    private String street;

    @BeforeTest
    @Parameters({"nameTopMenuTwo","nameSubMenuTwo","nameSupportMenu","dealer","town", "street"})
    public void setUp(String nameTopMenuTwo, String nameSubMenuTwo, String nameSupportMenu, String dealer, String town,String street ) {
        System.setProperty("webdriver.gecko.driver", "\\src\\java\\resources\\geckodriver.exe");
        System.setProperty("webdriver.chrome.driver", "\\src\\java\\resources\\chromedriver.exe");
        this.nameTopMenu = nameTopMenuTwo;
        this.nameSubMenu = nameSubMenuTwo;
        this.nameSupportMenu = nameSupportMenu;
        this.dealer = dealer;
        this.town = town;
        this.street = street;

    }


    public void runTest(){

        logStep("Select TopMenu");
        MainPage mainPage = new MainPage();
        mainPage.navigateTopMenu(nameTopMenu);

        logStep("Select SubMenu");
        mainPage.navigateSubMenu(nameSubMenu);

        logStep("Select Support Menu");
        SupportPage supportPage = new SupportPage();
        supportPage.selectSupportMenu(nameSupportMenu);

        logStep("Select Dealer, Town, Street");
        DealerCenterPage dealerPage = new DealerCenterPage();
        dealerPage.selectDealer(dealer);
        dealerPage.selectTown(town);
        dealerPage.selectStreet(street);
        logStep("Validate Result");
        dealerPage.validateSelectDealer(dealer, town, street);
    }
}

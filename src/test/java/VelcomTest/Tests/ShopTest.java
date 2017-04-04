package VelcomTest.Tests;

import VelcomTest.Pages.MainPage;
import VelcomTest.Pages.MobilePage;
import VelcomTest.Pages.ShopPage;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import webdriver.BaseTest;

/**
 * Created by Kefir on 03.04.2017.
 */
public class ShopTest extends BaseTest {
   private String nameShopMenu;
   private String nameItem;
   private String brand;
   private String type;
   private String maxSize;
   private String minSize;
   private String maxPrice;
   private String minPrice;


    @BeforeTest
    @Parameters({"nameShopMenu","nameItem","brand","type","minPrice","maxPrice","minSize","maxSize"})
    public void setUp(String nameShopMenu, String nameItem, String  brand, String type, String minPrice, String maxPrice, String minSize, String maxSize) {
        System.setProperty("webdriver.gecko.driver", "\\src\\java\\resources\\geckodriver.exe");
        System.setProperty("webdriver.chrome.driver", "\\src\\java\\resources\\chromedriver.exe");
        this.nameShopMenu = nameShopMenu;
        this.nameItem = nameItem;
        this.brand = brand;
        this.type = type;
        this.maxPrice = maxPrice;
        this.minPrice = minPrice;
        this.maxSize = maxSize;
        this.minSize = minSize;
    }

   public void runTest(){
        logStep("Go to Shop");
        MainPage  mainPage = new MainPage();
        mainPage.navigateTopMenu(nameShopMenu);

        logStep("Go to Item");
       ShopPage shopPage = new ShopPage();
       shopPage.navigateItem(nameItem);

       logStep("Select Mobile");
       MobilePage mobilepage = new MobilePage();
       mobilepage.selectMobile(brand,type);

       logStep("Select Price and Size");
       mobilepage.selectPrice(minPrice, maxPrice);
       mobilepage.selectSize(minSize, maxSize);

       logStep("Validate Result");
       mobilepage.validateSelectMobile(brand,maxPrice);

    }

}

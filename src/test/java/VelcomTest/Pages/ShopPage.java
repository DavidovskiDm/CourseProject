package VelcomTest.Pages;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Button;

/**
 * Created by Kefir on 03.04.2017.
 */
public class ShopPage extends BaseForm {

    public ShopPage() {
        super(By.xpath("//*[@id='page_title' and .='Интернет-магазин']"),"Shop Page");
    }

    private String locatorItem = "//*[@class='dashboard_panel']//a[.='%s']";


    public void navigateItem(String text){
        Button btnItem = new Button(By.xpath(String.format(locatorItem, text)), "btnItem");
        btnItem.click();
    }
}

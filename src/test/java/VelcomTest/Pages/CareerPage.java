package VelcomTest.Pages;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Button;

/**
 * Created by Kefir on 04.04.2017.
 */
public class CareerPage extends BaseForm {

    public CareerPage() {
        super(By.xpath("//*[@id='top']//h1[text()='КАРЬЕРА']"), "Career Page");
    }

    private Button btnMenuOpen = new Button(By.xpath("//*[@id='sidebar-wrapper']//span[@class='open']"), "btnOpenMenu");
    private String locatorMenu = "//*[@class='nav sidebar-nav']//span[contains(., '%s')]";

    public void selectMenu(String name){
        btnMenuOpen.click();
        Button btnSelectMenu = new Button(By.xpath(String.format(locatorMenu, name)), "btnSelectMenu");
        btnSelectMenu.click();
    }
}

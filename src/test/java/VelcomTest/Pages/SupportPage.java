package VelcomTest.Pages;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Button;

/**
 * Created by Kefir on 03.04.2017.
 */
public class SupportPage extends BaseForm {

    public SupportPage() {
        super(By.xpath("//h1[.='Помощь и поддержка']"),"Support Page");
    }

    private String locatorSupportMenu = "//*[@class='left_menu']//a[.='%s']";

    public void selectSupportMenu(String name){
        Button btnSupport = new Button(By.xpath(String.format(locatorSupportMenu,name)),"btnSupport");
        btnSupport.click();
        }


}

package VelcomTest.Pages;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Button;

/**
 * Created by Kefir on 28.03.2017.
 */
public class MainPage extends BaseForm {

    public MainPage() {
        super(By.xpath("//*[@class='news']//h2[contains(.,'Главные новости')]"),"Main Page");
    }

    private Button btnPrivateOffice = new Button(By.xpath("//*[@id='right_column']//a"), "btnPrivateOffice");
    private String locatorTopMenu ="//*[@class='t_menu']//a[contains(normalize-space(.),'%s')]";
    private String locatorSubMenu ="//*[@class='header_submenu']//a[.='%s']";

    public void navigateTopMenu(String nameButton){
        Button btnTopMenu = new Button(By.xpath(String.format(locatorTopMenu,nameButton)),"btnTopMenu");
        btnTopMenu.click();
    }

    public void navigateSubMenu(String nameButton){
        Button btnTopMenu = new Button(By.xpath(String.format(locatorSubMenu,nameButton)),"btnSubMenu");
        btnTopMenu.click();
    }



    public void goToAuthorization() {
        btnPrivateOffice.click();

    }

}

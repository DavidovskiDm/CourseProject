package VelcomTest.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import webdriver.BaseForm;
import webdriver.elements.Button;
import webdriver.elements.Label;
import webdriver.elements.TextBox;

import java.util.List;

import static org.testng.Assert.assertTrue;

/**
 * Created by Kefir on 03.04.2017.
 */
public class DealerCenterPage extends BaseForm {

    public DealerCenterPage() {
        super(By.xpath("//h1[.='Дилерские салоны']"),"Dealers Center Page");
    }

    private Button btnTop = new Button(By.xpath("//h1[.='Дилерские салоны']"));
    private Button btnDealer = new Button(By.id("dealer_name_id"));
    private Button btnTown = new Button(By.id("town"));
    private TextBox txtStreet = new TextBox(By.xpath("//*[@class='wrapper']//input"));
    private String locatorDealer = "//*[@id='dealer_name_id']//*[.='%s']";
    private String locatorTown = "//*[@id='town']//*[contains(., '%s')]";
    private String locatorResult = "//*[@class='misc_result']//*[@class='diler']";
    private String locatorInfo = "//*[@class='misc_result']//*[@class='info']";
    private String locatorInfoDealer = "//*[@class='misc_result']//*[@class='hint_info']/b";

    private Button btnGo = new Button(By.className("gobtn"));


    public void selectDealer(String name){
        btnDealer.click();
        Button btnSelectDeler = new Button(By.xpath(String.format(locatorDealer, name)), "SelectDealer");
        btnSelectDeler.click();
        }

     public void selectTown(String name){
        btnTown.click();
        Button btnSelectTown = new Button(By.xpath(String.format(locatorTown, name)), "SelectTown");
        btnSelectTown.click();
         }

    public void selectStreet(String name){
        txtStreet.setText(name);
        btnGo.click();
        }

     public void validateSelectDealer(String dealer, String town, String street)  {
         browser.waitForPageToLoad();
         logStep(" Get List");
         List<WebElement> listDealer = browser.getDriver().findElements(By.xpath(locatorResult));
         int num=0;
         if (dealer.equals("Все дилеры")) {
             for (int i = 0; i < listDealer.size(); i++) {
                 ++num;
                 Label lblDealer = new Label(By.xpath(locatorResult + "[" + num + "]"));
                 String text = lblDealer.getText();
                 assertTrue(text.contains(town));
                 assertTrue(text.contains(street));
                }

             }
          else
             for (int i = 0; i < listDealer.size(); i++) {
                 ++num;
                 Label lblDealer = new Label(By.xpath(locatorResult + "[" + num + "]"));
                 String text = lblDealer.getText();
                 assertTrue(text.contains(town));
                 assertTrue(text.contains(street));
                 Button btnInfo = new Button(By.xpath(locatorInfo + "[" + num + "]"),"btnInfo");
                 btnInfo.click();
                 Label lblDealerInfo = new Label(By.xpath(locatorInfoDealer ));
                 String info = lblDealerInfo.getText();
                 assertTrue(info.contains(dealer));
                 btnTop.click();
                try {
                     Thread.sleep(1000);
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
              }
         }
     }



package VelcomTest.Pages;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Button;
import webdriver.elements.TextBox;


import static org.testng.Assert.assertTrue;

/**
 * Created by Kefir on 03.04.2017.
 */
public class TariffsPage extends BaseForm {

    public TariffsPage() {
        super(By.xpath("//h1[.='Тарифы']"),"Tariffs Page");
    }

    private String locatorTariff = "//*[@class='left_menu']//a[.='%s']";
    private String txtValidate = "//h1[@class='middle' and contains(normalize-space(.),'%s')]";

    public void selectTariff(String tariffGroup, String tariffName){
        Button btnTariffGroup = new Button(By.xpath(String.format(locatorTariff,tariffGroup)),"btnTariffGroup");
        btnTariffGroup.click();
        Button btnTariff = new Button(By.xpath(String.format(locatorTariff,tariffName)),"btnTariff");
        btnTariff.click();
        logStep("Validate Result");
        TextBox validate = new TextBox(By.xpath(String.format(txtValidate,tariffName)), "ValidateTariff");
        String text = validate.getText();
        assertTrue(text.contains(tariffName));


    }



}

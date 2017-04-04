package VelcomTest.Pages;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Button;
import webdriver.elements.TextBox;

/**
 * Created by Kefir on 28.03.2017.
 */
public class PersonalOffice extends BaseForm {

    public PersonalOffice() {
        super(By.xpath("//*[@id='DIRNUM']/span"+"[contains(.,'375447545481')]"),"Personal Office");
    }

    private String txtMenu = "Счета, детализация";
    private String txtSubMenu = "Заказ счета на E-mail";



    private String locatorMenu = "//*[@class='issaLink' and .=contains(.,'%s')]";
    private String locatorSubMenu = "//*[@class='issaSubMenu' and .=contains(.,'%s')]";
    private Button btnTimePeriod = new Button(By.id("DPC_BILL_PERIOD"));
    private String locatorMonth = "//*[@class='datepick-month-header']/select";
    private String locatorSelectMonth = "//*[@class='datepick-month-year']//option[.='%s']";
    private String locatorYear = "//*[@class='datepick-month-header']/select[2]";
    private String locatorSelectYear = "//*[@class='datepick-month-year']//option[.='%s']";
    private Button btnNext = new Button(By.id("_next"),"Next");
    private Button btnSelect = new Button(By.xpath("//*[@class='datepick']//button[.='Выбрать']"),"Select");
    private String txtValidate = "//*[@id='MESS']/span[.='Счет за указанный период будет отправлен на ваш e-mail.']";








    public void navigateMenu() {
        Button btnMenu = new Button(By.xpath(String.format(locatorMenu, txtMenu)), "btnMenu");
        btnMenu.click();
        Button btnSubMenu = new Button(By.xpath(String.format(locatorSubMenu, txtSubMenu)), "btnSubMenu");
        btnSubMenu.click();
    }
    public void reportToMail(String month, String year ){
        btnTimePeriod.click();
        Button btnMonth = new Button(By.xpath(locatorMonth), "btnMonth");
        btnMonth.click();
        Button btnSelectMonth = new Button(By.xpath(String.format(locatorSelectMonth,month )), "btnSelectMonth");
        btnSelectMonth.click();
        Button btnYear = new Button(By.xpath(locatorYear), "btnYear");
        btnYear.click();
        Button btnSelectYear = new Button(By.xpath(String.format(locatorSelectYear,year )), "btnSelectYear");
        btnSelectYear.click();
        btnSelect.click();
        btnNext.click();
        logStep("Validate Result");
        TextBox validate = new TextBox(By.xpath(txtValidate), "Validate");
        String text = validate.getText();
        assertEquals(text,"Счет за указанный период будет отправлен на ваш e-mail.");
    }
}

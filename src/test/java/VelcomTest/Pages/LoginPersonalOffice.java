package VelcomTest.Pages;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Button;
import webdriver.elements.TextBox;

/**
 * Created by Kefir on 28.03.2017.
 */
public class LoginPersonalOffice extends BaseForm {

    private Button btnSelectionCode = new Button(By.id("ASK_PREF"), "SelectionCode");
    private String locatorCodePhone = "//*[@id='ASK_PREF']//*[contains(., '%s')]";
    private TextBox txbNumberPhone = new TextBox(By.className("auth_login"), "numberPhone");
    private TextBox txbPassword = new TextBox(By.id("ASK_PWD"), "password");
    private Button btnEnter = new Button(By.id("_next_auth"), "Enter");


    public LoginPersonalOffice() {
        super(By.className("issa_small_text"),"LoginPersonalOffice Page");
}
    public void authorization(String code, String numberPhone, String password) {
        btnSelectionCode.click();
        Button btnCodePhone = new Button(By.xpath(String.format(locatorCodePhone, code)), "SelectCode");
        btnCodePhone.click();
        txbNumberPhone.setText(numberPhone);
        txbPassword.setText(password);
        btnEnter.click();
    }
}


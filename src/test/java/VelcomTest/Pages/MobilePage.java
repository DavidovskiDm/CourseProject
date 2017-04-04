package VelcomTest.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import webdriver.BaseForm;
import webdriver.elements.Button;
import webdriver.elements.Label;

import java.util.List;

import static org.testng.Assert.assertTrue;

/**
 * Created by Kefir on 04.04.2017.
 */
public class MobilePage extends BaseForm {

    public MobilePage() {
        super(By.xpath("//h1[.='Мобильные телефоны']"), "Mobile Page");
    }

    private Button btnMobile = new Button(By.xpath("//*[@name='param_device_brand']"));
    private Button btnType = new Button(By.xpath("//*[@name='param_smart']"));
    private String locatorModel = "//*[@name='param_device_brand']//*[.='%s']";
    private String locatorType = "//*[@name='param_smart']//*[.='%s']";
    private String locatorSliderPrice = "//*[@id='slider-range']/div";
    private String locatorSliderPriceLeft = "//*[@id='slider-range']/a[1]";
    private String locatorSliderPriceRight = "//*[@id='slider-range']/a[2]";
    private String locatorSliderPriceMin = "//*[@id='vals']/nobr[1]";
    private String locatorSliderPriceMax = "//*[@id='vals']/nobr[2]";
    private String locatorSliderSizeLeft = "//*[@id='slider-range2']/a[1]";
    private String locatorSliderSizeRight = "//*[@id='slider-range2']/a[2]";
    private String locatorResultModel = "//*[@class='td_title']//a[contains(., '%s')]";
    private String locatorResultPrice = "//*[@class='td_prices1']//nobr";


    public void selectMobile(String brand, String type) {
        btnMobile.click();
        Button btnBrand = new Button(By.xpath(String.format(locatorModel, brand)), "Select Brand");
        btnBrand.click();
        btnType.click();
        Button btnSelectType = new Button(By.xpath(String.format(locatorType, type)), "Select Brand");
        btnSelectType.click();
    }

    public void selectSize(String minSize, String maxSize) {
        double min = Double.parseDouble(minSize);
        double max = Double.parseDouble(maxSize);
        int minpoz = (int) ((min - 4) * 83) - 1;
        int maxpoz = (int) ((6.4 - max) * 83);

        WebElement slider = browser.getDriver().findElement(By.xpath(locatorSliderSizeLeft));
        Actions move = new Actions(browser.getDriver());
        Action action = move.dragAndDropBy(slider, minpoz, 0).build();
        action.perform();
        WebElement sliderTwo = (new WebDriverWait(browser.getDriver(), 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(locatorSliderSizeRight)));
        Action actionTwo = move.dragAndDropBy(sliderTwo, -maxpoz, 0).build();
        actionTwo.perform();
    }

    public void selectPrice(String minPrice, String maxPrice) {
        double min = Double.parseDouble(minPrice);
        double max = Double.parseDouble(maxPrice);

        Label priceOne = new Label(By.xpath(locatorSliderPriceMin));
        double minStart = Double.parseDouble(priceOne.getText());
        Label priceTwo = new Label(By.xpath(locatorSliderPriceMax));
        double maxStart = Double.parseDouble(priceTwo.getText());
        WebElement sliderOne = browser.getDriver().findElement(By.xpath(locatorSliderPrice));
        int width = sliderOne.getSize().getWidth();

        double percent = width / (maxStart - minStart);
        int minpoz = (int) ((min - minStart) * percent);
        int maxpoz = (int) ((maxStart - max) * percent);

        WebElement slider = browser.getDriver().findElement(By.xpath(locatorSliderPriceLeft));
        Actions move = new Actions(browser.getDriver());
        Action action = move.dragAndDropBy(slider, minpoz, 0).build();
        action.perform();
        WebElement sliderTwo = (new WebDriverWait(browser.getDriver(), 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(locatorSliderPriceRight)));
        Action actionTwo = move.dragAndDropBy(sliderTwo, -maxpoz, 0).build();
        actionTwo.perform();
    }

    public void validateSelectMobile(String brand, String price) {

        browser.waitForPageToLoad();
        logStep();
        List<WebElement> listMobile = browser.getDriver().findElements(By.xpath(String.format(locatorResultModel, brand)));

         for (int i = 0; i < listMobile.size(); i++) {
            String text = listMobile.get(i).getText();
            assertTrue(text.contains(brand));
        }
        logStep();
        List<WebElement> listPrice = browser.getDriver().findElements(By.xpath(locatorResultPrice));

        for (int i = 0; i < listPrice.size(); i++) {
            String text = listPrice.get(i).getText();
            double priceTemp = Double.parseDouble(text);
            double priceMax = Double.parseDouble(price);
            assertTrue(priceTemp<priceMax );
        }


    }
}

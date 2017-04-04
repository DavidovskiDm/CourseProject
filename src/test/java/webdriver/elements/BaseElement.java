package webdriver.elements;


import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import webdriver.BaseEntity;
import webdriver.Browser;
import webdriver.Logger;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * Abstract class, describing base element.
 */
public abstract class BaseElement extends BaseEntity {

    private static final String LINK = "link=";

    private static final String ID = "id=";
    private static final String CSS = "css=";
    private static final int TIMEOUT_WAIT_0 = 0;

    protected String name;
    protected By locator;
    protected RemoteWebElement element;



    /**
     * The simple constructor, name will be extracted
     *
     * @param loc By Locator
     */
    protected BaseElement(final By loc) {
        locator = loc;
    }

    /**
     * The main constructor
     *
     * @param loc    By Locator
     * @param nameOf Output in logs
     */
    protected BaseElement(final By loc, final String nameOf) {
        locator = loc;
        name = nameOf;
    }


    /**
     * easy adapting from Selenium RC locators. CSS, ID, LINK
     *
     * @param stringLocator String locator
     * @param nameOfElement Name
     */
    protected BaseElement(String stringLocator, final String nameOfElement) {
        String clearLoc;
        if (stringLocator.contains(CSS)) {
            clearLoc = stringLocator.replaceFirst(CSS, "");
            locator = By.cssSelector(clearLoc);
            name = nameOfElement;
        } else if (stringLocator.contains(ID)) {
            clearLoc = stringLocator.replaceFirst(ID, "");
            locator = By.id(clearLoc);
            name = nameOfElement;
        } else if (stringLocator.contains(LINK)) {
            clearLoc = stringLocator.replaceFirst(LINK, "");
            locator = By.linkText(clearLoc);
            name = nameOfElement;
        } else {
            logger.fatal("UNKNOWN LOCATOR's TYPE. Change to 'By'");
        }
    }

    public RemoteWebElement getElement() {
        waitForIsElementPresent();
        return element;
    }

    public void setElement(final RemoteWebElement elementToSet) {
        element = elementToSet;
    }


    /**
     * @return Locator
     */
    public By getLocator() {
        return locator;
    }


    /**
     * The implementation of an abstract method for logging of BaseEntity
     *
     * @param message Message to display in the log
     * @return Formatted message (containing the name and type of item)
     */
    protected String formatLogMsg(final String message) {
        return String.format("%1$s '%2$s' %3$s %4$s", getElementType(), name, Logger.LOG_DELIMITER, message);
    }

    /**
     * The method returns the element type (used for logging)
     *
     * @return Type of element
     */
    protected abstract String getElementType();

    /**
     * Send keys.
     */
    public void sendKeys(Keys key) {
        waitForIsElementPresent();
        browser.getDriver().findElement(locator).sendKeys(key);
    }

    /**
     * Wait for element is present.
     */
    public void waitForIsElementPresent() {

        isPresent(Integer.valueOf(browser.getTimeoutForCondition()));
        Assert.assertTrue(formatLogMsg(getLoc("loc.is.absent")), element.isDisplayed());
    }


    /**
     * Click on the item.
     */
    public void click() {
        waitForIsElementPresent();
        info(getLoc("loc.clicking"));
        browser.getDriver().executeScript("arguments[0].style.border='3px solid red'", element);
        element.click();
    }


    /**
     * Get the item text (inner text).
     *
     * @return Text of element
     */
    public String getText() {
        waitForIsElementPresent();
        return element.getText();
    }

    /**
     * Check for is element present on the page.
     *
     * @return Is element present
     */
    public boolean isPresent() {
        boolean isPresent = isPresent(TIMEOUT_WAIT_0);
        info("is present : " + isPresent);
        return isPresent;
    }

    /**
     * Check for is element present on the page.
     *
     * @return Is element present
     */
    public boolean isPresent(int timeout) {

        WebDriverWait wait = new WebDriverWait(Browser.getInstance().getDriver(), timeout);
        browser.getDriver().manage().timeouts().implicitlyWait(TIMEOUT_WAIT_0, TimeUnit.SECONDS);
        try {
            wait.until(driver -> {
                try {
                    List<WebElement> list = driver.findElements(locator);
                    for (WebElement el : list) {
                        if (el instanceof RemoteWebElement && el.isDisplayed()) {
                            element = (RemoteWebElement) el;
                            return element.isDisplayed();
                        }
                    }
                    element = (RemoteWebElement) driver.findElement(locator);
                } catch (Exception e) {
                    return false;
                }
                return element.isDisplayed();
            });
        } catch (Exception e) {
            return false;
        }
        try {
            browser.getDriver().manage().timeouts().implicitlyWait(Integer.valueOf(browser.getTimeoutForCondition()), TimeUnit.SECONDS);
            return element.isDisplayed();
        } catch (Exception e) {
            warn(e.getMessage());
        }
        return false;
    }


}


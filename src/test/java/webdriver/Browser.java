package webdriver;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.naming.NamingException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static webdriver.Logger.getLoc;

/**
 * The main class to access the browser, which extends the capabilities of the standard Webdriver
 */
public final class Browser {

    /**
     * defaultPageLoadTimeout=60 defaultConditionTimeout=180
     * urlLoginPage=http://opensesame:%23pears0n@dev.pearsoninnovationlabs.com/ #overrided in
     * Browser.initProperties-default=firefox; #if null - use argument 'browser' to JVM; if other value (without '*'),
     * #will use it as browserStartCommand #Usage: #firefox #iexplore #chrome #null browser=iexplore
     */
    static final String PROPERTIES_FILE = "selenium.properties";
    private static final long IMPLICITY_WAIT = 10;
    private static final String DEFAULT_CONDITION_TIMEOUT = "defaultConditionTimeout";
    // имя файла с настройками Selenium
    private static final String DEFAULT_PAGE_LOAD_TIMEOUT = "defaultPageLoadTimeout";
    private static final String BROWSER_BY_DEFAULT = "firefox";
    private static final String BROWSER_PROP = "browser";
    static Browsers currentBrowser;
    private static Browser instance;
    private static RemoteWebDriver driver;
    private static PropertiesResourceManager props;
    private static String timeoutForPageLoad;
    private static String timeoutForCondition;

    /**
     * Private constructor (singleton pattern)
     */
    private Browser() {
        Logger.getInstance().info(String.format(getLoc("loc.browser.ready"), currentBrowser.toString()));
    }

    public static String getBaseUrl() {
        return System.getProperty("urlLoginPage", props.getProperty("urlLoginPage"));
    }

    /**
     * Gets instance of Browser
     *
     * @return browser instance
     */
    public static Browser getInstance() {
        if (instance == null) {
            initProperties();
            try {
                driver = BrowserFactory.setUp(currentBrowser.toString());
                driver.manage().timeouts().implicitlyWait(IMPLICITY_WAIT, TimeUnit.SECONDS);
                Logger.getInstance().info(getLoc("loc.browser.constructed"));
            } catch (NamingException e) {
                Logger.getInstance().info("NamingException...");
                e.printStackTrace();
            }
            instance = new Browser();
        }
        return instance;
    }

    /**
     * init
     */
    private static void initProperties() {

        props = new PropertiesResourceManager(PROPERTIES_FILE);
        timeoutForPageLoad = props.getProperty(DEFAULT_PAGE_LOAD_TIMEOUT);
        timeoutForCondition = props.getProperty(DEFAULT_CONDITION_TIMEOUT);
        currentBrowser = Browsers.valueOf(System.getProperty(BROWSER_PROP, props.getProperty(BROWSER_PROP, BROWSER_BY_DEFAULT)).toUpperCase());
    }

    /**
     * Checks is Browser slive
     *
     * @return true\false
     */
    public boolean isBrowserAlive() {
        return instance != null;
    }

    /**
     * The implementation of the browser is closed
     * void after test
     */
    public void exit() {
        try {
            driver.quit();
            Logger.getInstance().info(getLoc("loc.browser.driver.qiut"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            instance = null;
        }
    }

    /**
     * gets TimeoutForCondition
     *
     * @return timeoutForCondition
     */
    public String getTimeoutForCondition() {
        return timeoutForCondition;
    }


    /**
     * gets TimeoutForPageLoad
     *
     * @return timeoutForPageLoad
     */
    public String getTimeoutForPageLoad() {
        return timeoutForPageLoad;
    }

    /**
     * wait the download page (on Javascript readyState)
     */
    public void waitForPageToLoad() {
        // Logger.getInstance().info("waitForPageToLoad started");
        WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(getTimeoutForPageLoad()));

        try {
            wait.until(d -> {
                if (!(d instanceof JavascriptExecutor)) {
                    return true;
                }
                Object result = ((JavascriptExecutor) d)
                        .executeScript("return document['readyState'] ? 'complete' == document.readyState : true");
                if (result != null && result instanceof Boolean && (Boolean) result) {
                    return true;
                }
                return false;
            });
        } catch (Exception e) {
            Logger.getInstance().warn(getLoc("loc.browser.page.timeout"));
        }
        // Logger.getInstance().info("waitForPageToLoad ended");
    }

    /**
     * maximizes the window
     */
    public void windowMaximise() {
        driver.manage().window().maximize();

    }

    /**
     * Navgates to the Url
     *
     * @param url Url
     */
    public void navigate(final String url) {
        driver.navigate().to(url);
    }

    /**
     * get RemoteWebDriver
     *
     * @return driver
     */
    public RemoteWebDriver getDriver() {
        return driver;
    }

    /**
     * Gets current URL
     *
     * @return current URL
     */
    public String getLocation() {
        return driver.getCurrentUrl();
    }


    /**
     * Browsers enumeration
     */
    public enum Browsers {
        FIREFOX("firefox"),
        IEXPLORE("iexplore"),
        CHROME("chrome"),
        OPERA("opera"),
        SAFARI("safari");
        public String value;

        /**
         * Constructor
         *
         * @param values Value
         */
        Browsers(final String values) {
            value = values;
        }

        /**
         * Returns string value
         *
         * @return String value
         */
        public String toString() {
            return value;
        }
    }

}

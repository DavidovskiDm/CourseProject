package webdriver;

import com.opera.core.systems.OperaDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import webdriver.Browser.Browsers;

import javax.naming.NamingException;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;

import static webdriver.Logger.getLoc;

/**
 * The class-initializer-based browser string parameter.
 */
public abstract class BrowserFactory {

    /**
     * Setting up Driver
     *
     * @param type Browser type
     * @return RemoteWebDriver
     */
    public static RemoteWebDriver setUp(final Browsers type) {
        RemoteWebDriver driver = null;
        File myFile = null;
        switch (type) {
            case CHROME:
                DesiredCapabilities cp1 = DesiredCapabilities.chrome();
                cp1.setCapability("chrome.switches", Arrays.asList("--disable-popup-blocking"));
                URL myTestURL;
                if (System.getProperty("os.name").toLowerCase().contains("mac")) {
                    myTestURL = ClassLoader.getSystemResource("chromedriver");
                }
                else {
                    myTestURL = ClassLoader.getSystemResource("chromedriver.exe");
                }
                try {
                    myFile = new File(myTestURL.toURI());
                    myFile.setExecutable(true);
                } catch (URISyntaxException e1) {
                    Logger.getInstance().debug(e1.getMessage());
                }
                System.setProperty("webdriver.chrome.driver", myFile.getAbsolutePath());
                driver = new ChromeDriver(cp1);
                break;

            case FIREFOX:
                URL geckodriver;
                if (System.getProperty("os.name").toLowerCase().contains("mac")) {
                    geckodriver = ClassLoader.getSystemResource("geckodriver");
                }
                else {
                    geckodriver = ClassLoader.getSystemResource("geckodriver.exe");
                }
                try {
                    myFile = new File(geckodriver.toURI());
                    myFile.setExecutable(true);
                } catch (URISyntaxException e1) {
                    Logger.getInstance().debug(e1.getMessage());
                }
                System.setProperty("webdriver.gecko.driver", myFile.getAbsolutePath());
                driver = new FirefoxDriver();
                break;

            case IEXPLORE:
                //local security request flag INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS
                //(but this flag may cause appearing "skipped" tests)
                if (new PropertiesResourceManager(Browser.PROPERTIES_FILE).getProperty("localrun").equalsIgnoreCase("true")) {
                    DesiredCapabilities cp = DesiredCapabilities.internetExplorer();
                    cp.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                    URL myTestURL2 = ClassLoader.getSystemResource("IEDriverServer.exe");
                    try {
                        myFile = new File(myTestURL2.toURI());
                    } catch (URISyntaxException e1) {
                        Logger.getInstance().debug(e1.getMessage());
                    }
                    System.setProperty("webdriver.ie.driver", myFile.getAbsolutePath());
                    driver = new InternetExplorerDriver(cp);
                    // better to avoid
                } else {
                    // now remote connection will be refused, so use selenium server instead
                    driver = new InternetExplorerDriver();
                }
                break;

            case OPERA:
                //work on v.11-12 (Presto engine)

                driver = new OperaDriver();
                break;
            case SAFARI:
                //work on v.5.1+

                driver = new SafariDriver();
                break;
            default:
                break;
        }
        return driver;
    }

    /**
     * Setting up Driver
     *
     * @param type Browser type
     * @return RemoteWebDriver
     * @throws NamingException NamingException
     */
    public static RemoteWebDriver setUp(final String type) throws NamingException {
        for (Browsers t : Browsers.values()) {
            if (t.toString().equalsIgnoreCase(type)) {
                return setUp(t);
            }
        }
        throw new NamingException(getLoc("loc.browser.name.wrong") + ":\nchrome\nfirefox\niexplore\nopera\nsafari");
    }
}

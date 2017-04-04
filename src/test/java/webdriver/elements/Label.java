package webdriver.elements;

import org.openqa.selenium.By;

public class Label extends BaseElement {

    public Label(final By locator, final String name) {
        super(locator, name);
    }

    public Label(String string, String name) {
        super(string, name);
    }


    public Label(By locator) {
        super(locator);
    }

    protected String getElementType() {
        return getLoc("loc.label");
    }

}

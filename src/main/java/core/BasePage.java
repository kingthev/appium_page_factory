package core;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.time.Duration;

public class BasePage<T extends BasePage<T>> {
    protected final AppiumDriver driver;
    public boolean isAndroid;


    public BasePage(final AppiumDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(30)), this);
        this.driver = driver;

    }

    public BasePage(BasePage page) {
        this.driver = page.driver;
    }

    protected AppiumDriver getDriver() {
        return this.driver;
    }


    public <P extends BasePage> P getPage(Class<P> cls) {

        P pageInstance = null;

        Constructor<T>[] constructors = (Constructor<T>[]) cls.getConstructors();
        for (Constructor c : constructors) {
            Class<T>[] paramTypes = c.getParameterTypes();
            if (paramTypes.length == 1 && BasePage.class.isAssignableFrom(paramTypes[0])) {
                try {
                    pageInstance = (P) c.newInstance(this);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return pageInstance;
    }
}

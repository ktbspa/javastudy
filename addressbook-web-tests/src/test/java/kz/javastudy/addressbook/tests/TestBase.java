package kz.javastudy.addressbook.tests;

import kz.javastudy.addressbook.appmanager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager(BrowserType.CHROME);

    @BeforeSuite(alwaysRun = true)
     public void setUp() throws Exception {
         app.init();
     }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        app.stop();
    }

    public ApplicationManager getApp() {
        return app;
    }
}
package kz.javastudy.addressbook.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class ContactDeletionTest extends TestBase {
    WebDriver wd;

    @Test
    public void testContactDeletion() {
        app.getNavigationHelper().returnToHomePage();
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContact();
    }
}

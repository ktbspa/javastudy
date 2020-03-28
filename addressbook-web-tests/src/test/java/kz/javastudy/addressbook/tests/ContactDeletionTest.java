package kz.javastudy.addressbook.tests;

import kz.javastudy.addressbook.model.ContactData;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class ContactDeletionTest extends TestBase {
    WebDriver wd;

    @Test
    public void testContactDeletion() {
        app.getNavigationHelper().returnToHomePage();
        if (app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Anthony", "Howard", "Stark", "ironman", "Stark Industries", "NYC", "123", "456", "789", "0", "ironman@avengers.us", "avengers@avengers.us", "shield@shield.us", "www.im.us", "29", "May", "1970", "20", "May", "2008", "Alpha"), true);
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContact();
    }
}

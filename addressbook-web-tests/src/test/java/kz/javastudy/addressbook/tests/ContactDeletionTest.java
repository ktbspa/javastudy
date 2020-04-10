package kz.javastudy.addressbook.tests;

import kz.javastudy.addressbook.model.ContactData;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class ContactDeletionTest extends TestBase {
    WebDriver wd;

    @BeforeMethod
    public void ensurePreconditions() {
        app.getNavigationHelper().returnToHomePage();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Anthony", "Howard", "Stark", "ironman", "Stark Industries", "NYC", "123", "456", "789", "0", "ironman@avengers.us", "avengers@avengers.us", "shield@shield.us", "www.im.us", "29", "May", "1970", "20", "May", "2008", "Alpha"), true);
        }
    }
    @Test
    public void testContactDeletion() {
        app.getNavigationHelper().returnToHomePage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Anthony", "Howard", "Stark", "ironman", "Stark Industries", "NYC", "123", "456", "789", "0", "ironman@avengers.us", "avengers@avengers.us", "shield@shield.us", "www.im.us", "29", "May", "1970", "20", "May", "2008", "Alpha"), true);
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().deleteSelectedContact();
        app.getNavigationHelper().returnToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        for (int i = 0; i < after.size(); i++) {
            Assert.assertEquals(before, after);
        }
    }
}

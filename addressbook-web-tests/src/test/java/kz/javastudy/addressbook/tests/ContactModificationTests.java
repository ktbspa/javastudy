package kz.javastudy.addressbook.tests;

import kz.javastudy.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getNavigationHelper().returnToHomePage();
        app.getContactHelper().selectContact();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Tony", "H", "Stark", "ironman", "Stark Industries", "NYC, Stark Tower", "123", "456", "789", "0", "ironman@avengers.us", "avengers@avengers.us", "shield@shield.us", "www.im.us", "29", "May", "1970", "20", "May", "2008"));
        app.getContactHelper().submitContactModification();
    }
}
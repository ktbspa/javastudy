package kz.javastudy.addressbook.tests;

import kz.javastudy.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getNavigationHelper().returnToHomePage();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Anthony", "Howard", "Stark", "ironman", "Stark Industries", "NYC", "123", "456", "789", "0", "ironman@avengers.us", "avengers@avengers.us", "shield@shield.us", "www.im.us", "29", "May", "1970", "20", "May", "2008", "Alpha"), true);
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().initContactModification();
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "Tony", "H", "Stark", "ironman", "Stark Industries", "NYC, Stark Tower", "123", "456", "789", "0", "ironman@avengers.us", "avengers@avengers.us", "shield@shield.us", "www.im.us", "29", "May", "1970", "20", "May", "2008", null);
        app.getContactHelper().fillContactForm(contact, false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().returnToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }
}

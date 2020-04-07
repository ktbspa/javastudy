package kz.javastudy.addressbook.tests;

import kz.javastudy.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().returnToHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    ContactData contact = new ContactData("Anthony", "Howard", "Stark", "ironman", "Stark Industries", "NYC", "123", "456", "789", "0", "ironman@avengers.us", "avengers@avengers.us", "shield@shield.us", "www.im.us", "29", "May", "1970", "20", "May", "2008", "Alpha");
    app.getContactHelper().createContact(contact, true);
    app.getNavigationHelper().returnToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);
    int max = 0;
    for (ContactData c : after) {
      if (c.getId()>max) {
        max = c.getId();
      }
    }
    contact.setId(max);
   Assert.assertEquals(before, after);
  }

}

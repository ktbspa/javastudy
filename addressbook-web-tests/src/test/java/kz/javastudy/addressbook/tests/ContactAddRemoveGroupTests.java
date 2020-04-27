package kz.javastudy.addressbook.tests;

import kz.javastudy.addressbook.model.ContactData;
import kz.javastudy.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.NoSuchElementException;

public class ContactAddRemoveGroupTests extends TestBase {

   @BeforeMethod
   public void ensurePreconditions() {
      if (app.db().contacts().size() == 0) {
         app.goTo().homePage();
         app.contact().create(new ContactData().withFirstname("Anthony").withLastname("Stark").withMiddlename("Howard")
                 .withNickname("ironman").withCompany("Stark Industries").withAddress("StarkTower").withHomephone("123").withMobile("456")
                 .withWork("789").withFax("000").withEmail("ironman@k.kk").withEmail2("email@k.kk").withEmail3("email3@k.kk")
                 .withHomepage("avengers.kk").withBday("29").withBmonth("May").withByear("1970").withAday("20").withAmonth("May").withAyear("2008"), true);
      }
      if (app.db().groups().size() == 0) {
         app.goTo().groupPage();
         app.group().create(new GroupData().withName("Beta").withHeader("test1").withFooter("test2"));
      }
   }

   @Test
   public void testAddContactToGroup() {
      ContactData contact = app.db().contacts().iterator().next();
      if (contact.getGroups().equals(app.db().groups())) {
         app.goTo().groupPage();
         app.group().create(new GroupData().withName("Beta").withHeader("test1").withFooter("test2"));
      }

      GroupData group = getGroupWithoutContact(contact);
      app.goTo().homePage();
      app.contact().addContactToGroup(contact, group);
      ContactData modifiedContact = app.db().getContactById(contact.getId());
      GroupData modifiedGroup = app.db().getGroupById(group.getId());
      Assert.assertTrue(modifiedContact.getGroups().contains(group));
      Assert.assertTrue(modifiedGroup.getContacts().contains(contact));
   }

   private GroupData getGroupWithoutContact(ContactData contact) {
      return app.db().groups().stream().filter((g) -> !g.getContacts().contains(contact)).findFirst().get();
   }

   @Test(enabled = false)
   public void testRemoveContactFromGroup() {
      //need precondition if there a grouped contact
      ContactData contact = getContactsInGroups();
      GroupData group = contact.getGroups().iterator().next();
      app.goTo().homePage();
      app.contact().removeContactFromGroup(contact, group);

      ContactData modifiedContact = app.db().getContactById(contact.getId());
      GroupData modifiedGroup = app.db().getGroupById(group.getId());

      Assert.assertFalse(modifiedContact.getGroups().contains(group));
      Assert.assertFalse(modifiedGroup.getContacts().contains(contact));
   }

   private ContactData getContactsInGroups() {
      try {
         return app.db().contacts().stream().filter((c) -> c.getGroups().size() > 0).iterator().next();
      } catch (NoSuchElementException ex) {
         System.out.println("GroupedContact not found");
         return null;
      }
   }
}

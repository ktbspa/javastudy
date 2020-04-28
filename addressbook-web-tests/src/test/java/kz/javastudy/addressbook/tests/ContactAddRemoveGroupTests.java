package kz.javastudy.addressbook.tests;

import kz.javastudy.addressbook.model.ContactData;
import kz.javastudy.addressbook.model.Contacts;
import kz.javastudy.addressbook.model.GroupData;
import kz.javastudy.addressbook.model.Groups;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddRemoveGroupTests extends TestBase {

   @BeforeMethod
   public void ensurePreconditions() {
      if (app.db().contacts().size() == 0) {
         File photo = new File("src/test/resources/icon.jpg");
         app.contact().create(new ContactData().withFirstname("Anthony").withLastname("Stark").withMiddlename("Howard")
                 .withNickname("ironman").withPhoto(photo).withCompany("Stark Industries").withAddress("StarkTower").withHomephone("123").withMobile("456")
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

      GroupData group = app.db().groups().stream().filter((g) -> !g.getContacts().contains(contact)).findFirst().get();
      app.goTo().homePage();
      Contacts cBefore = app.db().contacts();
      Groups gBefore = app.db().groups();
      app.contact().addContactToGroup(contact, group);

      ContactData modifiedContact = app.db().getContactById(contact.getId());
      GroupData modifiedGroup = app.db().getGroupById(group.getId());

      Contacts cAfter = app.db().contacts();
      Groups gAfter = app.db().groups();

      assertThat(app.contact().count(), equalTo(cBefore.size()));
      assertThat(cAfter, equalTo(cBefore.without(modifiedContact).with(modifiedContact)));
      assertThat(gAfter, equalTo(gBefore.without(modifiedGroup).with(modifiedGroup)));
      Assert.assertTrue(modifiedContact.getGroups().contains(group));
      Assert.assertTrue(modifiedGroup.getContacts().contains(contact));
   }

   @Test
   public void testRemoveContactFromGroup() {
      ContactData contact = app.db().contacts().iterator().next();
      GroupData group = app.db().groups().iterator().next();

      if (contact.getGroups().size() == 0) {
         app.goTo().homePage();
         app.contact().addContactToGroup(contact, group);
      }

      ContactData groupedContact = app.db().contacts().stream().filter((c) -> c.getGroups().size() > 0).iterator().next();
      GroupData groupToRemove = groupedContact.getGroups().iterator().next();

      app.goTo().homePage();
      Contacts cBefore = app.db().contacts();
      Groups gBefore = app.db().groups();
      app.contact().removeContactFromGroup(groupedContact, groupToRemove);

      ContactData modifiedContact = app.db().getContactById(contact.getId());
      GroupData modifiedGroup = app.db().getGroupById(group.getId());
      Contacts cAfter = app.db().contacts();
      Groups gAfter = app.db().groups();

      assertThat(app.contact().count(), equalTo(cBefore.size()-1)); //потому что выше выбрана группа, из которой удалили 1 контакт
      assertThat(cAfter, equalTo(cBefore.without(modifiedContact).with(modifiedContact)));
      assertThat(gAfter, equalTo(gBefore.without(modifiedGroup).with(modifiedGroup)));
      Assert.assertFalse(modifiedContact.getGroups().contains(group));
      Assert.assertFalse(modifiedGroup.getContacts().contains(contact));
   }
 }


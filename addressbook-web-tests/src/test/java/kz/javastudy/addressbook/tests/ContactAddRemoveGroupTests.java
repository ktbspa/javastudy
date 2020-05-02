package kz.javastudy.addressbook.tests;

import kz.javastudy.addressbook.model.ContactData;
import kz.javastudy.addressbook.model.Contacts;
import kz.javastudy.addressbook.model.GroupData;
import kz.javastudy.addressbook.model.Groups;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.File;
import java.util.Optional;

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
      Contacts cBefore = app.db().contacts();
      Groups gBefore = app.db().groups();
      ContactData contact = null;
      GroupData group = null;
      for (ContactData c : cBefore) {
         for (GroupData g : gBefore) {
            if (!c.getGroups().contains(g)) {
               contact = c;
               group = g;
            }
         }
      }
      if (contact == null) {
         app.goTo().groupPage();
         GroupData newGroup = new GroupData().withName("Beta").withHeader("test1").withFooter("test2");
         app.group().create(newGroup);
         GroupData freeGroup = app.db().groups().stream().filter((g) -> g.getName()
                 .equals(newGroup.getName())).findAny().get();
         group = freeGroup;
         contact = app.db().contacts().iterator().next();
      }

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
      Contacts cBefore = app.db().contacts();
      Groups gBefore = app.db().groups();
      ContactData contact = null;
      GroupData groupToRemove = null;

      Optional<ContactData> groupedContact = cBefore.stream()
              .filter(contactData -> contactData.getGroups().stream().anyMatch(g -> gBefore.contains(g)))
              .findAny();
      if (groupedContact.isPresent()) {
         contact = groupedContact.get();
         groupToRemove = contact.getGroups().stream().findAny().get();
      } else {
         ContactData anyContact = app.db().contacts().iterator().next();
         GroupData anyGroup = app.db().groups().iterator().next();
         app.contact().addContactToGroup(anyContact, anyGroup);
         contact = anyContact;
         groupToRemove = anyGroup;
      }
      app.contact().removeContactFromGroup(contact, groupToRemove);

      ContactData modifiedContact = app.db().getContactById(contact.getId());
      GroupData modifiedGroup = app.db().getGroupById(groupToRemove.getId());
      Contacts cAfter = app.db().contacts();
      Groups gAfter = app.db().groups();

      assertThat(app.contact().count(), equalTo(cBefore.size()-1)); //потому что выше выбрана группа, из которой удалили 1 контакт
      assertThat(cAfter, equalTo(cBefore.without(modifiedContact).with(modifiedContact)));
      assertThat(gAfter, equalTo(gBefore.without(modifiedGroup).with(modifiedGroup)));
      Assert.assertFalse(modifiedContact.getGroups().contains(groupToRemove));
      Assert.assertFalse(modifiedGroup.getContacts().contains(contact));
   }
 }


package kz.javastudy.addressbook.tests;

import kz.javastudy.addressbook.model.ContactData;
import org.testng.annotations.Test;
import java.util.Set;
import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.goTo().homePage();
    Set<ContactData> before = app.contact().all();
    ContactData contact = new ContactData().withId(before.size()-1).withFirstname("Anthony").withLastname("Stark").withMiddlename("Howard")
            .withNickname("ironman").withCompany("Stark Industries").withAddress("StarkTower").withHomephone("123").withMobile("456")
            .withWork("789").withFax("000").withEmail("ironman@k.kk").withEmail2("email@k.kk").withEmail3("email3@k.kk")
            .withHomepage("avengers.kk").withBday("29").withBmonth("May").withByear("1970").withAday("20").withAmonth("May").withAyear("2008").withGroup("Alpha");
    app.contact().create(contact, true);
    app.goTo().homePage();
    Set<ContactData> after = app.contact().all();
    assertEquals(after.size(), before.size() + 1);

    contact.withId(after.stream().mapToInt((c)->c.getId()).max().getAsInt());
    before.add(contact);
    assertEquals(before, after);
  }

}

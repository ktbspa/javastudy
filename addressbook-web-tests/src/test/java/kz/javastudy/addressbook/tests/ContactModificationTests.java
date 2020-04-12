package kz.javastudy.addressbook.tests;

import kz.javastudy.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Set;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withFirstname("Anthony").withLastname("Stark").withNickname("ironman"), true);
        }
    }

    @Test
    public void testContactModification() {
        Set<ContactData> before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Anthony").withLastname("Stark").withMiddlename("Howard")
                .withNickname("ironman").withCompany("Stark Industries").withAddress("StarkTower").withHomephone("123").withMobile("456")
                .withWork("789").withFax("000").withEmail("ironman@k.kk").withEmail2("email@k.kk").withEmail3("email3@k.kk")
                .withHomepage("avengers.kk").withBday("29").withBmonth("May").withByear("1970").withAday("20").withAmonth("May").withAyear("2008").withGroup("Alpha");
        app.contact().modify(contact);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedContact);
        before.add(contact);
        Assert.assertEquals(before, after);
    }
}

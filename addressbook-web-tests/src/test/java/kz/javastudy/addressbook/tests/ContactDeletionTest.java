package kz.javastudy.addressbook.tests;

import kz.javastudy.addressbook.model.ContactData;
import kz.javastudy.addressbook.model.Contacts;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTest extends TestBase {
    WebDriver wd;

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contact().create(new ContactData().withFirstname("Anthony").withLastname("Stark").withMiddlename("Howard")
                    .withNickname("ironman").withCompany("Stark Industries").withAddress("StarkTower").withHomephone("123").withMobile("456")
                    .withWork("789").withFax("000").withEmail("ironman@k.kk").withEmail2("email@k.kk").withEmail3("email3@k.kk")
                    .withHomepage("avengers.kk").withBday("29").withBmonth("May").withByear("1970").withAday("20").withAmonth("May").withAyear("2008").withGroup("Beta 0"), true);
        }
    }
    @Test
    public void testContactDeletion() {
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();
        app.goTo().homePage();
        app.contact().delete(deletedContact);
        assertThat(app.contact().count(), equalTo(before.size()-1));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(deletedContact)));
    }
}

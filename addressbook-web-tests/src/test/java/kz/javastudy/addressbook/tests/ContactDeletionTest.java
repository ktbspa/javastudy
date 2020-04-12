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
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withFirstname("Anthony").withLastname("Stark").withNickname("ironman"), true);
        }
    }
    @Test
    public void testContactDeletion() {
        Contacts before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        Contacts after = app.contact().all();
        assertThat(after.size(), equalTo(before.size()-1));
        assertThat(after, equalTo(before.without(deletedContact)));
    }
}

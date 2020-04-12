package kz.javastudy.addressbook.tests;

import kz.javastudy.addressbook.model.ContactData;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Set;
import static org.testng.Assert.assertEquals;

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
        Set<ContactData> before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        Set<ContactData> after = app.contact().all();
        assertEquals(after.size(), before.size() - 1);

        before.remove(deletedContact);
        assertEquals(before, after);
    }
}

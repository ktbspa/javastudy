package kz.javastudy.addressbook.tests;

import kz.javastudy.addressbook.model.ContactData;
import org.testng.annotations.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {
   @Test
   public void testContactPhones() {
      app.goTo().homePage();
      ContactData contact = app.contact().all().iterator().next();
      ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
      assertThat(contact.getHomephone(), equalTo(cleaned(contactInfoFromEditForm.getHomephone())));
      assertThat(contact.getMobile(), equalTo(cleaned(contactInfoFromEditForm.getMobile())));
      assertThat(contact.getWork(), equalTo(cleaned(contactInfoFromEditForm.getWork())));
   }

   public String cleaned(String phone) {
      return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
   }
}

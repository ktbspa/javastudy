package kz.javastudy.addressbook.tests;

import kz.javastudy.addressbook.model.ContactData;
import org.testng.annotations.Test;
import java.util.Arrays;
import java.util.stream.Collectors;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactInfoTests extends TestBase {
   @Test
   public void testContactInfo() {
      app.goTo().homePage();
      ContactData contact = app.contact().all().iterator().next();
      ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

      assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
      assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
      assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
   }

   private String mergeEmails(ContactData contact) {
      return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
              .stream().filter((s)->!s.equals("")).collect(Collectors.joining("\n"));
   }

   private String mergePhones(ContactData contact) {
      return Arrays.asList(contact.getHomephone(), contact.getMobile(), contact.getWork())
              .stream().filter((s)->!s.equals("")).map(ContactInfoTests::cleaned).collect(Collectors.joining("\n"));
   }

   public static String cleaned(String phone) {
      return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
   }
}

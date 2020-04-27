package kz.javastudy.addressbook.appmanager;

import kz.javastudy.addressbook.model.ContactData;
import kz.javastudy.addressbook.model.Contacts;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import java.util.List;

public class ContactHelper extends HelperBase {
   public ContactHelper(WebDriver wd) {
      super(wd);
   }

   public void fillContactForm(ContactData contactData, boolean creation) {
      type(By.name("firstname"), contactData.getFirstname());
      type(By.name("middlename"), contactData.getMiddlename());
      type(By.name("lastname"), contactData.getLastname());
      type(By.name("nickname"), contactData.getNickname());
      attach(By.name("photo"), contactData.getPhoto());
      type(By.name("company"), contactData.getCompany());
      type(By.name("address"), contactData.getAddress());
      type(By.name("home"), contactData.getHomephone());
      type(By.name("mobile"), contactData.getMobile());
      type(By.name("work"), contactData.getWork());
      type(By.name("fax"), contactData.getFax());
      type(By.name("email"), contactData.getEmail());
      type(By.name("email2"), contactData.getEmail2());
      type(By.name("email3"), contactData.getEmail3());
      type(By.name("homepage"), contactData.getHomepage());
      click(By.name("bday"));
      new Select(wd.findElement(By.name("bday"))).selectByVisibleText(contactData.getBday());
      click(By.name("bday"));
      click(By.name("bmonth"));
      new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText(contactData.getBmonth());
      click(By.name("bmonth"));
      type(By.name("byear"), contactData.getByear());
      click(By.name("aday"));
      new Select(wd.findElement(By.name("aday"))).selectByVisibleText(contactData.getAday());
      click(By.name("aday"));
      click(By.name("amonth"));
      new Select(wd.findElement(By.name("amonth"))).selectByVisibleText(contactData.getAmonth());
      click(By.name("amonth"));
      type(By.name("ayear"), contactData.getAyear());

      if (creation) {
         if (contactData.getGroups().size() > 0) {
            Assert.assertTrue(contactData.getGroups().size() == 1);
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData
                    .getGroups().iterator().next().getName());
         }
      } else {
         Assert.assertFalse(isElementPresent(By.name("new_group")));
      }
   }

   public void submitContactCreation() {
      click(By.xpath("(//input[@name='submit'])[2]"));
   }
   public void gotoAddNewContactPage() {
      click(By.linkText("add new"));
   }
   private void selectContactById(int id) { wd.findElement(By.cssSelector("input[value='"+id+"']")).click();   }
   public void deleteSelectedContact() {
      click(By.xpath("//input[@value='Delete']"));
      Alert alert = wd.switchTo().alert();
      alert.accept();
   }

   public void initContactModification(int id) {
      wd.findElement(By.cssSelector("a[href='edit.php?id=" +id+ "']")).click();
   }

   public void submitContactModification() {
      click(By.name("update"));
   }

   public void create(ContactData contact, boolean b) {
      gotoAddNewContactPage();
      fillContactForm(contact, true);
      submitContactCreation();
      contactCache = null;
      returnToHomePage();
   }
   public void modify(ContactData contact) {
      returnToHomePage();
      initContactModification(contact.getId());
      fillContactForm(contact, false);
      submitContactModification();
      contactCache = null;
      returnToHomePage();
   }

   public void delete(ContactData contact) {
      selectContactById(contact.getId());
      deleteSelectedContact();
      contactCache = null;
      returnToHomePage();
   }

   private void returnToHomePage() {
      click(By.linkText("home"));
   }
   public boolean isThereAContact() {
      return isElementPresent(By.name("selected[]"));
   }
   public int count() {
      return wd.findElements(By.name("selected[]")).size();
   }

   private Contacts contactCache = null;

   public Contacts all() {
      if (contactCache !=null) {
         return new Contacts(contactCache);
      }
      contactCache = new Contacts();
      List<WebElement> elements = wd.findElements(By.cssSelector("tr[name='entry']"));
      for (WebElement element : elements) {
         int id = Integer.parseInt(element.findElement(By.name("selected[]")).getAttribute("id"));
         List<WebElement> cells = element.findElements(By.tagName("td"));
         String lastname = cells.get(1).getText();
         String firstname = cells.get(2).getText();
         String address = cells.get(3).getText();
         String allEmails = cells.get(4).getText();
         String allPhones = cells.get(5).getText();
         contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname).withAllPhones(allPhones).withAddress(address).withAllEmails(allEmails));
      }
      return new Contacts(contactCache);
   }

   public ContactData infoFromEditForm(ContactData contact) {
      initContactModification(contact.getId());
      String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
      String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
      String homephone = wd.findElement(By.name("home")).getAttribute("value");
      String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
      String work = wd.findElement(By.name("work")).getAttribute("value");
      String address = wd.findElement(By.name("address")).getAttribute("value");
      String email = wd.findElement(By.name("email")).getAttribute("value");
      String email2 = wd.findElement(By.name("email2")).getAttribute("value");
      String email3 = wd.findElement(By.name("email3")).getAttribute("value");

      wd.navigate().back();
      return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
              .withHomephone(homephone).withMobile(mobile).withWork(work).withAddress(address).withEmail(email).withEmail2(email2).withEmail3(email3);
   }
}

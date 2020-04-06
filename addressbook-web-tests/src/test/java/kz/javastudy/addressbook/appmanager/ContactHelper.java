package kz.javastudy.addressbook.appmanager;

import kz.javastudy.addressbook.model.ContactData;
import kz.javastudy.addressbook.model.GroupData;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {
   public ContactHelper(WebDriver wd) {
      super(wd);
   }

   public void fillContactForm(ContactData contactData, boolean creation) {
      type(By.name("firstname"), contactData.getFirstname());
      wd.findElement(By.name("middlename")).clear();
      wd.findElement(By.name("middlename")).sendKeys(contactData.getMiddlename());
      type(By.name("lastname"), contactData.getLastname());
      type(By.name("nickname"), contactData.getNickname());
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
         new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
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

   public void selectContact(int index) {
      wd.findElements(By.name("selected[]")).get(index).click();
   }

   public void deleteSelectedContact() {
      click(By.xpath("//input[@value='Delete']"));
      Alert alert = wd.switchTo().alert();
      alert.accept();
   }

   public void initContactModification() {
      click(By.xpath("//img[@alt='Details']"));
      click(By.name("modifiy"));
   }

   public void submitContactModification() {
      click(By.name("update"));
   }

   public void createContact(ContactData contact, boolean b) {
      click(By.linkText("add new"));
      fillContactForm(contact, true);
      submitContactCreation();
      returnToHomePage();
   }

   private void returnToHomePage() {
      click(By.linkText("home"));
   }

   public boolean isThereAContact() {
      return isElementPresent(By.name("selected[]"));
   }

   public int getContactCount() {
      return wd.findElements(By.name("selected[]")).size();
   }

   public List<ContactData> getContactList() {
      List<ContactData> contacts = new ArrayList<ContactData>();
      List<WebElement> elements = wd.findElements(By.cssSelector("tr.odd"));
      for (WebElement element : elements) {
         String firstname = element.getText();
         String middlename = element.getText();
         String lastname = element.getText();
         int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));

         ContactData contact = new ContactData (firstname, middlename, lastname, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
         contacts.add(contact);
      }
      return contacts;
   }
}

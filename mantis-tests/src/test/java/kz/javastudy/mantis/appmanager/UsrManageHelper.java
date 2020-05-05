package kz.javastudy.mantis.appmanager;

import kz.javastudy.mantis.model.UserData;
import org.openqa.selenium.By;

public class UsrManageHelper extends HelperBase {

   public UsrManageHelper(ApplicationManager app) { super(app); }

   public void adminLogin() {
      wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
      type(By.name("username"), app.getProperty("web.adminLogin"));
      type(By.name("password"), app.getProperty("web.adminPassword"));
      type(By.cssSelector("input[type='submit']"), System.getProperty("web.adminPassword"));
   }

   public void gotoOverViewPage() {
      click(By.xpath("//a[@href='/mantisbt-1.3.0/manage_overview_page.php']"));
   }

   public void gotoUsersPage() {
      click(By.xpath("//a[@href='/mantisbt-1.3.0/manage_user_page.php']"));
   }

   private void selectUser() {
      //click(By.linkText(username));
      wd.findElements(By.xpath("//a[contains(@href,'manage_user_edit_page.php?user_id=')]"))
              .stream().filter((u) -> !u.getText().equals(app.getProperty("web.adminLogin"))).findFirst().get().click();
   }

   public UserData selectUserToResetPass() {
     selectUser();
     return new UserData().withUsername(wd.findElement(By.name("username")).getAttribute("value"))
              .withEmail(wd.findElement(By.name("email")).getAttribute("value"));
   }

   public void resetPassword() {
    click(By.cssSelector("input[value='Reset Password']"));
   }
}

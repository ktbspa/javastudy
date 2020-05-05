package kz.javastudy.mantis.tests;

import kz.javastudy.mantis.appmanager.HttpSession;
import kz.javastudy.mantis.model.MailMessage;
import kz.javastudy.mantis.model.UserData;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import static org.testng.Assert.assertTrue;

public class ResetPasswordTests extends TestBase {

   @BeforeMethod
   public void startMailServer() throws IOException, MessagingException {
      app.mail().start();
      if (app.db().users().size() < 2) {
         long now = System.currentTimeMillis();
         String user = String.format("user%s", now);
         String password = "password";
         String email = String.format("user%s@localhost", now);
         app.registration().start(user, email);
         List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
         String confirmationLink = findLink(mailMessages, email);
         app.registration().finish(confirmationLink, password);
         }
   }

   @Test
   public void testResetPassword() throws IOException, MessagingException {
      app.manage().adminLogin();
      app.manage().gotoOverViewPage();
      app.manage().gotoUsersPage();
      UserData user = app.manage().selectUserToResetPass();
      app.manage().resetPassword();
      List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
      String passResetLink = findLink(mailMessages, user.getEmail());
      String newpassword = "newpassword";
      app.registration().finish(passResetLink, newpassword);

      HttpSession session = app.newSession();
      assertTrue(session.login(user.getUsername(), newpassword));
      assertTrue(session.isLoggedInAs(user.getUsername()));
   }

   private String findLink(List<MailMessage> mailMessages, String email) {
      MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
      VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
      return regex.getText(mailMessage.text);
   }

   @AfterMethod(alwaysRun = true)
   public void stopMailServer() {
      app.mail().stop();
   }
}

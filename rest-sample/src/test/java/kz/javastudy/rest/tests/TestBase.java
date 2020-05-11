package kz.javastudy.rest.tests;

import kz.javastudy.rest.appmanager.ApplicationManager;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import java.io.IOException;

public class TestBase {
   protected static final ApplicationManager app = new ApplicationManager();

   @BeforeSuite(alwaysRun = true)
   public void setUp() throws IOException { app.init(); }

   public boolean isIssueOpen(int issueId) throws IOException {
      String issueStatus = app.rest().getIssueStatus(issueId);
      if (issueStatus.equals("Resolved") || (issueStatus.equals("Closed"))) {
         return false;
      }
      return true;
   }

   public void skipIfNotFixed(int issueId) throws IOException {
      if (isIssueOpen(issueId)) {
         throw new SkipException("Ignored because of issue " + issueId +" is not resolved");
      }
   }

   @AfterSuite(alwaysRun = true)
   public void tearDown() { app.stop(); }
}

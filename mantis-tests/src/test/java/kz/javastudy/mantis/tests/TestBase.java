package kz.javastudy.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import javassist.tools.rmi.RemoteException;
import org.hibernate.service.spi.ServiceException;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import kz.javastudy.mantis.appmanager.ApplicationManager;

import java.io.File;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;

public class TestBase {

  protected static final ApplicationManager app
          = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite(alwaysRun = true)
     public void setUp() throws Exception {
       app.init();
       app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.bak");
     }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
       app.ftp().restore("config_inc.php.bak", "config_inc.php");
       app.stop();
    }

   public boolean isIssueOpen(int issueId) throws MalformedURLException, ServiceException, RemoteException, javax.xml.rpc.ServiceException, java.rmi.RemoteException {
      MantisConnectPortType mc = getMantisConnect();
      IssueData issue = mc.mc_issue_get(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"), BigInteger.valueOf(issueId));
      return !issue.getStatus().getName().equals("resolved");
   }

   public void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException, javax.xml.rpc.ServiceException, java.rmi.RemoteException {
      if (isIssueOpen(issueId)) {
         throw new SkipException("Ignored because of issue " + issueId);
      }
 }
   private MantisConnectPortType getMantisConnect() throws javax.xml.rpc.ServiceException, MalformedURLException {
      return new MantisConnectLocator()
              .getMantisConnectPort(new URL(app.getProperty("soap.url")));
   }
}

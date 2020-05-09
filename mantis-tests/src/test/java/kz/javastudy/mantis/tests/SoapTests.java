package kz.javastudy.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import kz.javastudy.mantis.model.Issue;
import kz.javastudy.mantis.model.Project;
import org.testng.annotations.Test;
import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;
import static org.testng.AssertJUnit.assertEquals;

public class SoapTests extends TestBase{

   @Test
   public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
      Set<Project> projects = app.soap().getProjects();
      System.out.println(projects.size());
      for (Project project : projects) {
         System.out.println(project.getName());
      }
   }

   @Test
   public void testCreateIssue() throws MalformedURLException, ServiceException, RemoteException {
      Set<Project> projects = app.soap().getProjects();
      Issue issue = new Issue().withSummary("Test issue 3")
              .withDescription("Test issue description 3d").withProject(projects.iterator().next());
      Issue created = app.soap().addIssue(issue);
      assertEquals(issue.getSummary(), created.getSummary());
   }

   @Test
   public void testSkipIfNotFixed() throws RemoteException, ServiceException, MalformedURLException {
      int issueId = 0000003;
      skipIfNotFixed(issueId);
      System.out.println("the issue " +issueId+ " is successfully resolved. Test is ready to start.");
   }
}

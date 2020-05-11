package kz.javastudy.rest.tests;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import kz.javastudy.rest.model.Issue;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Set;
import static org.testng.Assert.assertEquals;

public class RestAssuredTests extends TestBase {

   @BeforeClass
   public void init() {
      RestAssured.authentication = RestAssured.basic(app.getProperty("authToken"), app.getProperty("authPass"));
   }

   @Test
   public void testSkipIfNotFixed() throws IOException {
      int issueId = 49;
      skipIfNotFixed(issueId);
      System.out.println("The issue " +issueId+ " is successfully resolved. Test started.");
   }

   @Test
   public void testCreateIssue() throws IOException {
      Set<Issue> oldIssues = getIssues();
      Issue newIssue = new Issue().withSubject("New marvel 6").withDescription("Marvel description");
      int issueId = createIssue(newIssue);
      Set<Issue> newIssues = getIssues();
      oldIssues.add(newIssue.withId(issueId));
      assertEquals(newIssues, oldIssues);
      System.out.println(newIssue.getId()); //checkout the new issue id
   }

   private Set<Issue> getIssues() throws IOException {
      String json = RestAssured.get(app.getProperty("IssuesPage")+String.format("?limit=500")).asString();
      JsonElement parsed = new JsonParser().parse(json);
      JsonElement issues = parsed.getAsJsonObject().get("issues");
      return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
   }

   private int createIssue(Issue newIssue) throws IOException {
      String json = RestAssured.given()
              .parameter("subject", newIssue.getSubject())
              .parameter("description", newIssue.getDescription())
              .post(app.getProperty("IssuesPage")).asString();
      JsonElement parsed = new JsonParser().parse(json);
      return parsed.getAsJsonObject().get("issue_id").getAsInt();
   }
}
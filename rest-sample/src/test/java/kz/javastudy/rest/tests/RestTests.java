package kz.javastudy.rest.tests;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import kz.javastudy.rest.model.Issue;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestTests extends TestBase {

   @Test
   public void testSkipIfNotFixed() throws IOException {
      int issueId = 49;
      skipIfNotFixed(issueId);
      System.out.println("The issue " +issueId+ " is successfully resolved. Test started.");
   }

   @Test
   public void testCreateIssue() throws IOException {
      Set<Issue> oldIssues = getIssues();
      Issue newIssue = new Issue().withSubject("Iron issue").withDescription("New iron test issue");
      int issueId = createIssue(newIssue);
      Set<Issue> newIssues = getIssues();
      oldIssues.add(newIssue.withId(issueId));
      assertEquals(newIssues, oldIssues);
      System.out.println(newIssue.getId());  //checkout the new issue id
   }

   private Set<Issue> getIssues() throws IOException {
      String json = getExecutor()
              .execute(Request.Get(app.getProperty("IssuesPage")+String.format("?limit=500")))
              .returnContent().asString();
      JsonElement parsed = new JsonParser().parse(json);
      JsonElement issues = parsed.getAsJsonObject().get("issues");
      return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
   }

   private int createIssue(Issue newIssue) throws IOException {
      String json = getExecutor().execute(Request.Post(app.getProperty("IssuesPage"))
              .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()),
                      new BasicNameValuePair("description", newIssue.getDescription())))
              .returnContent().asString();
      JsonElement parsed = new JsonParser().parse(json);
      return parsed.getAsJsonObject().get("issue_id").getAsInt();
   }

   private Executor getExecutor() {
      return Executor.newInstance().auth(app.getProperty("authToken"), app.getProperty("authPass"));
   }
}

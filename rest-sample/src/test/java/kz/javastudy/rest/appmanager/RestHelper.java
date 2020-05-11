package kz.javastudy.rest.appmanager;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;

import java.io.IOException;

public class RestHelper extends HelperBase {

   public RestHelper(ApplicationManager app) { super(app); }

   public String getIssueStatus(int id) throws IOException {
      String json = getExecutor().execute(Request.Get(String
              .format(app.getProperty("apiURL") + String.format("/issues/%s.json", id))))
              .returnContent().asString();;
      JsonElement parsed = new JsonParser().parse(json);
      String status = parsed.getAsJsonObject().getAsJsonArray("issues")
              .get(0).getAsJsonObject().get("state_name").getAsString();
      return status;
   }
   private Executor getExecutor() {
      return Executor.newInstance().auth(app.getProperty("authToken"), app.getProperty("authPass"));
   }
}

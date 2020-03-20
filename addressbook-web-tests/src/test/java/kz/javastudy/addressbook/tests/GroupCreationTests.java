package kz.javastudy.addressbook.tests;

import kz.javastudy.addressbook.model.GroupData;
import org.testng.annotations.*;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {

    app.gotoGroupPage();
    app.initGroupCreation();
    app.fillGroupForm(new GroupData("Alpha", "A", "B"));
    app.submitGroupCreation();
    app.returnToGroupPage();
    //wd.findElement(By.linkText("Logout")).click();
  }

}

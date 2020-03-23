package kz.javastudy.addressbook.tests;

import kz.javastudy.addressbook.model.GroupData;
import org.testng.annotations.*;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {

    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().initGroupCreation();
    app.getGroupHelper().fillGroupForm(new GroupData("Alpha", "A", "B"));
    app.getGroupHelper().submitGroupCreation();
    app.getGroupHelper().returnToGroupPage();
    //wd.findElement(By.linkText("Logout")).click();
  }

}

package kz.javastudy.addressbook;

import org.testng.annotations.*;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {

    gotoGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupData("Alpha", "A", "B"));
    submitGroupCreation();
    returnToGroupPage();
    //wd.findElement(By.linkText("Logout")).click();
  }

}

package kz.javastudy.addressbook.tests;

import kz.javastudy.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.goTo().groupPage();
    List<GroupData> before = app.group().list();
    GroupData group = new GroupData().withId(before.size()-1).withName("beta").withHeader("test1").withFooter("test2");
    app.group().create(group);
    List<GroupData> after = app.group().list();
    Assert.assertEquals(after.size(), before.size()+1);

    before.add(group);
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g2.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
    };


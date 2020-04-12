package kz.javastudy.addressbook.tests;

import kz.javastudy.addressbook.model.GroupData;
import org.testng.annotations.Test;
import java.util.Set;
import static org.testng.Assert.assertEquals;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.goTo().groupPage();
    Set<GroupData> before = app.group().all();
    GroupData group = new GroupData().withName("Alpha").withHeader("test1").withFooter("test2");
    app.group().create(group);
    Set<GroupData> after = app.group().all();
    assertEquals(after.size(), before.size()+1);

    group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt());
    before.add(group);
    assertEquals(before, after);
  }
    };


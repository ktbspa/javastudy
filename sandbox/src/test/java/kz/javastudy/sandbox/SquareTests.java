package kz.javastudy.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SquareTests {

   @Test
   public void testArea() {
      Square s = new Square (4);
//      assert s.area()==25;
      Assert.assertEquals(s.area(),16);
   }
}

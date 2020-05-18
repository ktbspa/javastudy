package kz.javastudy.sandbox;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test (enabled = false)
public class DistanceTests {
   public void testDistance() {
      Point p1 = new Point(10, 10);
      Point p2 = new Point(1,1);
      System.out.println("x1 = " + p1.x + " у1 = " + p1.y);
      System.out.println("x2 = " + p2.x + " у2 = " + p2.y);
      System.out.println("Правильное расстояние между точками="+p1.distance(p2));

      Assert.assertEquals(p1.distance(p2),9.219544457292887);
   }

   @Test
   public class DistanceTests1 {
      public void testDistance() {
         Point p1 = new Point(10, 10);
         Point p2 = new Point(1, 1);
         System.out.println("x1 = " + p1.x + " у1 = " + p1.y);
         System.out.println("x2 = " + p2.x + " у2 = " + p2.y);
         System.out.println("Правильное расстояние между точками=" + p1.distance(p2));

         Assert.assertEquals(p1.distance(p2), 12.727922061357855);
      }
   }

   @Test (enabled=false)
   public class DistanceTests2 {
      public void testDistance() {
         Point p1 = new Point(10, 10);
         Point p2 = new Point(1, 1);
         System.out.println("x1 = " + p1.x + " у1 = " + p1.y);
         System.out.println("x2 = " + p2.x + " у2 = " + p2.y);
         System.out.println("Правильное расстояние между точками=" + p1.distance(p2));

         Assert.assertEquals(p1.distance(p2), 0);
      }
   }
}

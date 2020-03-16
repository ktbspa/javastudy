package kz.javastudy.sandbox;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class DistanceTests {
   public void testDistance() {
      Point p1 = new Point(10, 10);
      Point p2 = new Point(1,1);
      System.out.println("x1 = " + p1.x + " у1 = " + p1.y);
      System.out.println("x2 = " + p2.x + " у2 = " + p2.y);
      System.out.println("Правильное расстояние между точками="+Point.distance(p1, p2));

      Assert.assertEquals(Point.distance(p1, p2),9.219544457292887);
   }

   @Test
   public class DistanceTests1 {
      public void testDistance() {
         Point p1 = new Point(10, 10);
         Point p2 = new Point(1, 1);
         System.out.println("x1 = " + p1.x + " у1 = " + p1.y);
         System.out.println("x2 = " + p2.x + " у2 = " + p2.y);
         System.out.println("Правильное расстояние между точками=" + Point.distance(p1, p2));

         Assert.assertEquals(Point.distance(p1, p2), 12.727922061357855);
      }
   }

   @Test
   public class DistanceTests2 {
      public void testDistance() {
         Point p1 = new Point(10, 10);
         Point p2 = new Point(1, 1);
         System.out.println("x1 = " + p1.x + " у1 = " + p1.y);
         System.out.println("x2 = " + p2.x + " у2 = " + p2.y);
         System.out.println("Правильное расстояние между точками=" + Point.distance(p1, p2));

         Assert.assertEquals(Point.distance(p1, p2), 0);
      }
   }
}

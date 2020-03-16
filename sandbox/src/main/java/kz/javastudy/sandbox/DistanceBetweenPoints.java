package kz.javastudy.sandbox;

public class DistanceBetweenPoints {
   public static void main(String [] args) {
      Point p1 = new Point(1, 1);
      Point p2 = new Point(10,10);


      System.out.println("x1 = " + p1.x + " у1 = " + p1.y);
      System.out.println("x2 = " + p2.x + " у2 = " + p2.y);

      System.out.println("Расстояние между точками="+Point.distance(p1, p2));
   }
}
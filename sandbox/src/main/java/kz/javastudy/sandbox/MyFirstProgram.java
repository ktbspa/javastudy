package kz.javastudy.sandbox;

public class MyFirstProgram {
public static void main (String[] args) {
  hello("world");
   hello("user");
   hello("Alexei");
   double l=5;
   System.out.println("Площадь квадрата со стороной "+l+"="+area(l));

   double a=2;
   double b=3;
   System.out.println("Площадь прямоугольника со сторонами "+a+ " и "+ b+" = "+square(a,b));
 }
public static void hello(String somebody) {
   System.out.println("Hello,"+somebody+"!");
}
public static double area(double len) {
   return len*len;
}
public static double square(double a, double b) {
   return a*b;
}
}
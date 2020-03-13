package kz.javastudy.sandbox;

public class MyFirstProgram {
public static void main (String[] args) {
  hello("world");

   Square s=new Square(9);
   System.out.println("Площадь квадрата со стороной "+s.l+"="+s.area());

   Rectangle c=new Rectangle(5.2,6.1);
   System.out.println("Площадь прямоугольника со сторонами "+c.a+ " и "+ c.b+" = "+c.area());

 }
public static void hello(String somebody) {
   System.out.println("Hello,"+somebody+"!");

}


}





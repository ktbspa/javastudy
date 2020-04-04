package kz.javastudy.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {
   public static void main(String[] args) {
      String[] langs = {"java", "c#", "python", "PHP"};

      List<String> languages = Arrays.asList("java", "c#", "python", "PHP");

      for (String l : languages) {
         System.out.println("I want to learn " + l);
      }
   }
}

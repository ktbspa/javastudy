package kz.javastudy.sandbox;

import java.util.ArrayList;
import java.util.List;

public class Collections {
   public static void main (String[] args) {
      String[] langs = {"java", "c#", "python", "PHP"};

      List<String> languages = new ArrayList<String>();
      languages.add("Java");
      languages.add("C#");
      languages.add("Python");

      for (String l: languages) {
         System.out.println("I want to study "+l);
      }

   }
}

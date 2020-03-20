package kz.javastudy.addressbook;

public class ContactDates {
   private final String bday;
   private final String bmonth;
   private final String byear;
   private final String aday;
   private final String amonth;
   private final String ayear;

   public ContactDates(String bday, String bmonth, String byear, String aday, String amonth, String ayear) {
      this.bday = bday;
      this.bmonth = bmonth;
      this.byear = byear;
      this.aday = aday;
      this.amonth = amonth;
      this.ayear = ayear;
   }

   public String getBday() {
      return bday;
   }

   public String getBmonth() {
      return bmonth;
   }

   public String getByear() {
      return byear;
   }

   public String getAday() {
      return aday;
   }

   public String getAmonth() {
      return amonth;
   }

   public String getAyear() {
      return ayear;
   }
}

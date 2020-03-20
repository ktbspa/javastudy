package kz.javastudy.addressbook.model;

public class ContactPhones {
   private final String fax;
   private final String work;
   private final String mobile;
   private final String home;

   public ContactPhones(String fax, String work, String mobile, String home) {
      this.fax = fax;
      this.work = work;
      this.mobile = mobile;
      this.home = home;
   }

   public String getFax() {
      return fax;
   }

   public String getWork() {
      return work;
   }

   public String getMobile() {
      return mobile;
   }

   public String getHome() {
      return home;
   }
}

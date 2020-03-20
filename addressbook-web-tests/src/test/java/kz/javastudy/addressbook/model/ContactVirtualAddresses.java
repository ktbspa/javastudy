package kz.javastudy.addressbook.model;

public class ContactVirtualAddresses {
   private final String homepage;
   private final String workemail;
   private final String secondemail;
   private final String mainemail;

   public ContactVirtualAddresses(String homepage, String workemail, String secondemail, String mainemail) {
      this.homepage = homepage;
      this.workemail = workemail;
      this.secondemail = secondemail;
      this.mainemail = mainemail;
   }

   public String getHomepage() {
      return homepage;
   }

   public String getWorkemail() {
      return workemail;
   }

   public String getSecondemail() {
      return secondemail;
   }

   public String getMainemail() {
      return mainemail;
   }
}

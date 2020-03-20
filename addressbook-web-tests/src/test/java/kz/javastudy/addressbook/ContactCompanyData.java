package kz.javastudy.addressbook;

public class ContactCompanyData {
   private final String company;
   private final String address;

   public ContactCompanyData(String company, String address) {
      this.company = company;
      this.address = address;
   }

   public String getCompany() {
      return company;
   }

   public String getAddress() {
      return address;
   }
}

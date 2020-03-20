package kz.javastudy.addressbook;

public class ContactNames {
   private final String firstname;
   private final String middlename;
   private final String lastname;
   private final String nickname;

   public ContactNames(String firstname, String middlename, String lastname, String nickname) {
      this.firstname = firstname;
      this.middlename = middlename;
      this.lastname = lastname;
      this.nickname = nickname;
   }

   public String getFirstname() {
      return firstname;
   }

   public String getMiddlename() {
      return middlename;
   }

   public String getLastname() {
      return lastname;
   }

   public String getNickname() {
      return nickname;
   }
}

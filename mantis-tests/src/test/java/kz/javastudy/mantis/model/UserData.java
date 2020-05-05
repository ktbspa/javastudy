package kz.javastudy.mantis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mantis_user_table")
public class UserData {
   @Id
   int id;
   String username;
   String password;
   String email;
   @Column(columnDefinition = "TINYINT")
   int enabled;

   public String getUsername() { return username; }
   public String getEmail() { return email; }

   public UserData withUsername(String username) {
      this.username = username;
      return this;
   }
   public UserData withEmail(String email) {
      this.email = email;
      return this;
   }

   @Override
   public String toString() {
      return "UserData{" +
              "id=" + id +
              ", username='" + username + '\'' +
              ", email='" + email + '\'' +
              ", password='" + password + '\'' +
              ", enabled=" + enabled +
              '}';
   }
}

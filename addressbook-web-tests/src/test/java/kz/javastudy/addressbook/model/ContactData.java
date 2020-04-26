package kz.javastudy.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.Objects;

@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")
public class ContactData {
    @XStreamOmitField
    @Id
    @Column (name = "id")
    private int id = Integer.MAX_VALUE;
    @Expose
    @Column (name = "firstname")
    private String firstname;
    @Expose
    @Column (name = "middlename")
    private String middlename;
    @Expose
    @Column (name = "lastname")
    private String lastname;
    @Expose
    @Column (name = "nickname")
    private String nickname;
    @Expose
    @Column (name = "company")
    private String company;
    @Expose
    @Column (name = "address")
    @Type(type = "text")
    private String address;
    @Expose
    @Column (name = "home")
    @Type(type = "text")
    private String homephone;
    @Expose
    @Column (name = "mobile")
    @Type(type = "text")
    private String mobile;
    @Expose
    @Column (name = "work")
    @Type(type = "text")
    private String work;
    @Expose
    @Transient
    private String allPhones;
    @Expose
    @Column (name = "fax")
    @Type(type = "text")
    private String fax;
    @Expose
    @Column (name = "email")
    @Type(type = "text")
    private String email;
    @Expose
    @Column (name = "email2")
    @Type(type = "text")
    private String email2;
    @Expose
    @Column (name = "email3")
    @Type(type = "text")
    private String email3;
    @Expose
    @Transient
    private String allEmails;
    @Expose
    @Column (name = "homepage")
    @Type(type = "text")
    private String homepage;
    @Expose
    @Transient
    private String bday;
    @Expose
    @Transient
    private String bmonth;
    @Expose
    @Transient
    private String byear;
    @Expose
    @Transient
    private String aday;
    @Expose
    @Transient
    private String amonth;
    @Expose
    @Transient
    private String ayear;
    @Expose
    @Transient
    private String group;

    @Column (name = "photo")
    @Type(type = "text")
    private String photo;

    public File getPhoto() { return new File (photo); }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public String getAllEmails() {
        return allEmails;
    }

    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public int getId() {
        return id;
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
    public String getCompany() {
        return company;
    }
    public String getAddress() {
        return address;
    }
    public String getHomephone() {
        return homephone;
    }
    public String getMobile() {
        return mobile;
    }
    public String getWork() {
        return work;
    }
    public String getFax() {
        return fax;
    }
    public String getEmail() {
        return email;
    }
    public String getEmail2() {
        return email2;
    }
    public String getEmail3() {
        return email3;
    }
    public String getHomepage() {
        return homepage;
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
    public String getGroup() {
        return group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id &&
                Objects.equals(firstname, that.firstname) &&
                Objects.equals(middlename, that.middlename) &&
                Objects.equals(lastname, that.lastname) &&
                Objects.equals(nickname, that.nickname) &&
                Objects.equals(company, that.company) &&
                Objects.equals(address, that.address) &&
                Objects.equals(homephone, that.homephone) &&
                Objects.equals(homepage, that.homepage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, middlename, lastname, nickname, company, address, homephone, homepage);
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ContactData withMiddlename(String middlename) {
        this.middlename = middlename;
        return this;
    }

    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ContactData withNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public ContactData withCompany(String company) {
        this.company = company;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withHomephone(String homephone) {
        this.homephone = homephone;
        return this;
    }

    public ContactData withMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public ContactData withWork(String work) {
        this.work = work;
        return this;
    }

    public ContactData withFax(String fax) {
        this.fax = fax;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public ContactData withHomepage(String homepage) {
        this.homepage = homepage;
        return this;
    }

    public ContactData withBday(String bday) {
        this.bday = bday;
        return this;
    }

    public ContactData withBmonth(String bmonth) {
        this.bmonth = bmonth;
        return this;
    }

    public ContactData withByear(String byear) {
        this.byear = byear;
        return this;
    }

    public ContactData withAday(String aday) {
        this.aday = aday;
        return this;
    }

    public ContactData withAmonth(String amonth) {
        this.amonth = amonth;
        return this;
    }

    public ContactData withAyear(String ayear) {
        this.ayear = ayear;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

}

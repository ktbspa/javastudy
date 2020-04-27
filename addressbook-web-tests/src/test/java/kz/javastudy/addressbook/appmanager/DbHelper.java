package kz.javastudy.addressbook.appmanager;

import kz.javastudy.addressbook.model.ContactData;
import kz.javastudy.addressbook.model.Contacts;
import kz.javastudy.addressbook.model.GroupData;
import kz.javastudy.addressbook.model.Groups;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import java.util.List;

public class DbHelper {
   private final SessionFactory sessionFactory;

   public DbHelper() {
      // A SessionFactory is set up once for an application!
      final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
              .configure() // configures settings from hibernate.cfg.xml
              .build();
      sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
   }

   public Groups groups() {
      Session session = sessionFactory.openSession();
      session.beginTransaction();
      List<GroupData> result = session.createQuery("from GroupData").list();
      session.getTransaction().commit();
      session.close();
      return new Groups(result);
   }

   public Contacts contacts() {
      Session session = sessionFactory.openSession();
      session.beginTransaction();
      List<ContactData> result = session.createQuery("from ContactData where deprecated='0000-00-00'").list();
      session.getTransaction().commit();
      session.close();
      return new Contacts(result);
   }

   public GroupData getGroupById(int id) {
      Session session = sessionFactory.openSession();
      session.beginTransaction();
      Query query = session.createQuery("from GroupData where id=:id");
      query.setParameter("id", id);
      GroupData result = (GroupData) query.list().get(0);
      session.getTransaction().commit();
      session.close();
      return result;
   }

   public ContactData getContactById(int id) {
      Session session = sessionFactory.openSession();
      session.beginTransaction();
      Query query = session.createQuery("from ContactData where id=:id");
      query.setParameter("id", id);
      ContactData result = (ContactData) query.list().get(0);
      session.getTransaction().commit();
      session.close();
      return result;
   }
}

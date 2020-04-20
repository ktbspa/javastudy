package kz.javastudy.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import kz.javastudy.addressbook.model.ContactData;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {
   @Parameter(names="-c", description = "Group count")
   public int count;
   @Parameter (names="-f", description = "Target file")
   public String file;
   @Parameter (names="-d", description = "Data format")
   public String format;

       public static void main(String [] args) throws IOException {
          ContactDataGenerator generator = new ContactDataGenerator();
          JCommander jCommander = new JCommander(generator);
          try {
             jCommander.parse(args);
          } catch (ParameterException ex) {
             jCommander.usage();
             return;
          }
          generator.run();
      }

   private void run() throws IOException {
      List<ContactData> contacts = generateContacts(count);
      if (format.equals("csv")) {
         saveAsCsv(contacts, new File(file));
      } else if (format.equals("xml")) {
         saveAsXml(contacts, new File(file));
      } else if (format.equals("json")) {
         saveAsJson(contacts, new File(file));
      } else {
         System.out.println("Unrecognized format "+format);
      }
   }

   private static List<ContactData> generateContacts(int count) {
         List<ContactData> contacts = new ArrayList<ContactData>();
         for (int i=0; i<count; i++) {
            contacts.add(new ContactData().withFirstname(String.format("Anthony %s", i)).withLastname(String.format("Stark", i))
                    .withMiddlename(String.format("Howard")).withNickname(String.format("ironman"))
                    .withCompany(String.format("Stark Industries")).withAddress(String.format("StarkTower"))
                    .withHomephone(String.format("123")).withMobile(String.format("456")).withWork(String.format("789"))
                    .withFax(String.format("000")).withEmail(String.format("ironman@k.kk"))
                    .withEmail2(String.format("email@k.kk")).withEmail3(String.format("email3@k.kk"))
                    .withHomepage(String.format("avengers.kk")).withBday(String.format("29")).withBmonth(String.format("May"))
                    .withByear(String.format("1970")).withAday(String.format("20")).withAmonth(String.format("May"))
                    .withAyear(String.format("2008")).withGroup(String.format("Alpha")));
                 }
         return contacts;
      }

      private static void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
         Writer writer = new FileWriter(file);
         for (ContactData contact : contacts) {
            writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n",
                    contact.getFirstname(), contact.getLastname(), contact.getMiddlename(), contact.getNickname(),
                    contact.getCompany(), contact.getAddress(), contact.getHomepage(), contact.getMobile(),
                    contact.getWork(), contact.getFax(), contact.getEmail(), contact.getEmail2(), contact.getEmail3(),
                    contact.getHomepage(), contact.getBday(), contact.getBmonth(), contact.getByear(),
                    contact.getAday(), contact.getAmonth(), contact.getAyear(), contact.getGroup()));
         }
         writer.close();
      }

      private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
      XStream xstream = new XStream();
      xstream.processAnnotations(ContactData.class);
      String xml =xstream.toXML(contacts);
      Writer writer = new FileWriter(file);
      writer.write(xml);
      writer.close();
   }

      private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
      Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
      String json = gson.toJson(contacts);
      Writer writer = new FileWriter(file);
      writer.write(json);
      writer.close();
   }
   }

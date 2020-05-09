package kz.javastudy.soap.tests;

import com.lavasoft.GeoIP;
import com.lavasoft.GeoIPService;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GeoIpServiceTests {

   @Test
   public void testMyIp() {
      GeoIP geoIP = new GeoIP(new GeoIPService().getGeoIPServiceSoap().getIpLocation("2.132.39.22"));
      Assert.assertEquals(geoIP.getСountryCode(),"KZ");
   }

   @Test
   public void testInvalidIp() {
      GeoIP geoIP = new GeoIP(new GeoIPService().getGeoIPServiceSoap().getIpLocation("2.132.39.22"));
      Assert.assertEquals(geoIP.getСountryCode(),"RUS");
   }
}
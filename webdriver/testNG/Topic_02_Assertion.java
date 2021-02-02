package testNG;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_02_Assertion {
  @Test
  public void TC_01() {
	  String name = "Automation testing";
	  
	  Assert.assertTrue(name.equals("Automation testing"));
	  Assert.assertTrue(name.equals("Automation testing"), "Name is not matching"); //in ra thông báo lỗi	  
	  Assert.assertEquals(name, "in ra nếu sai");
	  
	  String address= null;
	  Assert.assertNull(address);
  }
}

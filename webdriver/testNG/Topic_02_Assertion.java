package testNG;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_02_Assertion {
	
	//thứ tự chạy testcase theo Alphabet: 0-9, A-Z
	//Cách đặt tên: Tên chức năng-số thứ tự-tên testcase, vd: Login_01_Empty_Email_And_Password
	
  @Test (priority = 01)
  public void TC_01() {
	  String name = "Automation testing";
	  
	  Assert.assertTrue(name.equals("Automation testing"));
	  Assert.assertTrue(name.equals("Automation testing"), "Name is not matching"); //in ra thÃ´ng bÃ¡o lá»—i	  
	  Assert.assertEquals(name, "in ra náº¿u sai");
	  
	  String address= null;
	  Assert.assertNull(address);
  }
  
  @Test
  public void TC_02() {
	  
  }
  
  
  @Test
  public void TC_032() {
	  
  }
}

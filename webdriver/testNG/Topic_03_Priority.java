package testNG;

import org.testng.annotations.Test;

public class Topic_03_Priority {

	// thứ tự chạy testcase theo Alphabet: 0-9, A-Z
	// Cách đặt tên: Tên chức năng-số thứ tự-tên testcase, vd:
	// Login_01_Empty_Email_And_Password

	@Test(priority = 01)
	public void TC_01() {
	}

	@Test(priority = 02)
	public void TC_02() {
	}

	// khoongchay testcase này
	@Test(enabled = false)
	public void TC_03() {
	}

	@Test(enabled = true)
	public void TC_04() {
	}

	@Test(description = "JIRA-0999: Edit the User, ...note gì đó")
	public void TC_05() {
	}
}

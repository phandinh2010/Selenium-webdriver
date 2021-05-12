package testNG;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;

public class ReportListener implements ITestListener{
	
  @Test
  public void f() {
  }

@Override
public void onFinish(ITestContext arg0) {
	// Lamf gif ddos sau khi class nay được chạy xong
	
}

@Override
public void onStart(ITestContext arg0) {
	// Lamf gif ddos sau khi class nay được chạy xong
	
}

@Override
public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
	// Lamf gif ddos sau khi testcase fail trong khoang bao nhieu %
	
}

@Override
public void onTestFailure(ITestResult arg0) {
	/// Lamf gif ddos sau khi testcase bị fail
	
}

@Override
public void onTestSkipped(ITestResult arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void onTestStart(ITestResult arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void onTestSuccess(ITestResult arg0) {
	// TODO Auto-generated method stub
	
}
}

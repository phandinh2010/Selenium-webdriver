package API;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_User_Interaction_I {
	WebDriver driver;
	Alert alert;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	Actions action;
	String project_location = System.getProperty("user.dir");
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", project_location + "\\BrowserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 30);
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		action = new Actions(driver);
	}

	//@Test
	public void TC_01_Hover_Mouse() {
		driver.get("https://tiki.vn/");
		Assert.assertFalse(driver.findElement(By.xpath("//button[text()='Ä�Äƒng nháº­p']")).isDisplayed());
		WebElement shortCutAcc = driver.findElement(By.xpath("//div[@data-view-id='header_header_account_container']"));
		action.moveToElement(shortCutAcc).perform();
		Assert.assertTrue(driver.findElement(By.xpath("//button[text()='Ä�Äƒng nháº­p']")).isDisplayed());
		sleepInSecond(2);
		
		driver.get("https://jqueryui.com/resources/demos/tooltip/default.html");
		action.moveToElement(driver.findElement(By.cssSelector("#age"))).perform();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).isDisplayed());
		
		driver.get("https://www.myntra.com/");
		driver.manage().window().maximize();
		sleepInSecond(2);
		WebElement elmKids = driver.findElement(By.xpath("//a[text()='Kids'][@class='desktop-main']"));
		action.moveToElement(elmKids).perform();
		sleepInSecond(2);
		
		clickToElement(By.xpath("//a[text()='Home & Bath']"));
		sleepInSecond(2);
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.myntra.com/kids-home-bath");
		
		driver.get("https://hn.telio.vn/");
		sleepInSecond(2);
		WebElement elmDrink = driver.findElement(By.xpath("//main[@id='maincontent']//a[@href='/do-uong?source=home']"));
		action.moveToElement(elmDrink).perform();
		sleepInSecond(1);
		clickToElement(By.xpath("//main[@id='maincontent']//a[@text='Bia']"));
		Assert.assertTrue(driver.findElement(By.cssSelector(".groupmenu-drop")).isDisplayed());
		
	}

	//@Test
	public void TC_02_clickAndHold() {
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		List<WebElement> listItems = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		
		//1. chá»�n hÃ ng loáº¡t
		action.clickAndHold(listItems.get(0)).moveToElement(listItems.get(3)).release().perform();
		sleepInSecond(3);
		
		 List<WebElement> numberSelected = driver.findElements(By.xpath("//li[contains(@class,'ui-selected')]"));
		 System.out.println("number selected is " +numberSelected.size());
	
	Assert.assertEquals(numberSelected.size(),4);
	
	//2.chá»�n tá»«ng sá»‘
	
	//Nháº¥n phÃ­m Ctrl xuá»‘ng
	action.keyDown(Keys.CONTROL).perform();
	
	action.click(listItems.get(0)).
	click(listItems.get(2)).
	click(listItems.get(4)).
	click(listItems.get(6));
	
	//Nháº£ phÃ­m Ctrl ra
	action.keyUp(Keys.CONTROL).perform();
	sleepInSecond(2);
	}

	//@Test
	public void TC_05_doubleClick() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.manage().window().maximize();
		WebElement element = driver.findElement(By.xpath("//button[text()='Double click me']"));
		action.doubleClick(element).perform();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='demo']")).getText(), "Hello Automation Guys!");
		
	}
	
	//@Test
	public void TC_06_rightClick() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		driver.manage().window().maximize();
		WebElement element = driver.findElement(By.xpath("//span[text()='right click me']"));
		action.contextClick(element).perform();
		sleepInSecond(2);
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')"
				+ "and not(contains(@class,'context-menu-hover')) and not(contains(@class,'context-menu-visible'))]")).isDisplayed());
		
		//hover vao Quit
		action.moveToElement(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')"
				+ " and not(contains(@class,'context-menu-hover')) and not(contains(@class,'context-menu-visible'))]"))).perform();
		
		//verify Quit chuaws thuoc tinh visible va hover status
		Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit') "
				+ "and (contains(@class,'context-menu-hover')) and (contains(@class,'context-menu-visible'))]")).isDisplayed());
		//click to Quit
		
		clickToElement(By.xpath("//li[contains(@class,'context-menu-icon-quit') "
				+ "and not(contains(@class,'context-menu-hover')) and not(contains(@class,'context-menu-visible'))]"));
		
		//accept alert
		driver.switchTo().alert().accept();
		
	}
	
	@Test
	public void TC_08_dragDropHTML4() {
		driver.get("https://demos.telerik.com/kendo-ui/dragdrop/index");
		sleepInSecond(2);
		driver.findElement(By.cssSelector("#onetrust-accept-btn-handler")).click();
		WebElement elmSource = driver.findElement(By.cssSelector("#.draggable"));
		WebElement elmTarget = driver.findElement(By.cssSelector("#.droptarget"));
		
		WebElement elmToScroll = driver.findElement(By.cssSelector("#.kd-example-runner tabstrip-container"));
		
		
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", elmToScroll);
		action.dragAndDrop(elmSource, elmTarget);
		sleepInSecond(2);		
		Assert.assertEquals(driver.findElement(By.cssSelector("#droptarget")).getText(), "You did great!");	
		
	}
	
	//@Test
	public void TC_08_dragDropHTML5() throws AWTException {
		driver.get("https://automationfc.github.io/drag-drop-html5/");
		sleepInSecond(2);
		
		//A to B
		drag_the_and_drop_html5_by_Offset("//div[@id='column-a']", "//div[@id='column-b']");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='B']")).isDisplayed());	
		
		//B to A
		drag_the_and_drop_html5_by_Offset("//div[@id='column-a']", "//div[@id='column-b']");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='A']")).isDisplayed());	
		
	}
	
	
	
	public void clickToElement(By by) {
		WebElement element = driver.findElement(by);
		element.click();
	}
	
	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void drag_the_and_drop_html5_by_Offset(String sourceLocator, String targetLocator) throws AWTException {

		WebElement source = driver.findElement(By.xpath(sourceLocator));
		WebElement target = driver.findElement(By.xpath(targetLocator));

		// Setup robot
		Robot robot = new Robot();
		robot.setAutoDelay(500);

		// Get size of elements
		Dimension sourceSize = source.getSize();
		Dimension targetSize = target.getSize();

		// Get center distance
		int xCentreSource = sourceSize.width / 2;
		int yCentreSource = sourceSize.height / 2;
		int xCentreTarget = targetSize.width / 2;
		int yCentreTarget = targetSize.height / 2;

		Point sourceLocation = source.getLocation();
		Point targetLocation = target.getLocation();
		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Make Mouse coordinate center of element
		sourceLocation.x += 20 + xCentreSource;
		sourceLocation.y += 110 + yCentreSource;
		targetLocation.x += 20 + xCentreTarget;
		targetLocation.y += 110 + yCentreTarget;

		//System.out.println(sourceLocation.toString());
		//System.out.println(targetLocation.toString());

		// Move mouse to drag from location
		robot.mouseMove(sourceLocation.x, sourceLocation.y);

		// Click and drag
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

		// Move to final position
		robot.mouseMove(targetLocation.x, targetLocation.y);

		// Drop
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
package leafGround;

import org.testng.annotations.Test;

import CommonUtility.base;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;


public class WindowHandles extends base{

	@Test
	public void OpenWindow() {
	    driver=initializeDriver();   //initialize webdriver from base class
		driver.get("http://www.leafground.com/pages/Window.html");
		driver.manage().window().maximize();
		driver.findElement(By.id("home")).click();

		String parent = driver.getWindowHandle();
		Set<String> s = driver.getWindowHandles();

		Iterator<String> I1 = s.iterator();

		while (I1.hasNext()) {
			String child_window = I1.next();

			if (!parent.equals(child_window)) {
				driver.switchTo().window(child_window);
				System.out.println(driver.switchTo().window(child_window).getTitle());
				driver.quit();
			}

		
		
		}

	}

	@Test
	public void openMultipleWindow() throws InterruptedException {
		driver=initializeDriver(); 
		driver.manage().window().maximize();
		driver.get("http://www.leafground.com/pages/Window.html");
		driver.findElement(By.xpath("//button[contains(text(),'Open Multiple')]")).click();

		//String parent = driver.getWindowHandle();
		Set<String> windows = driver.getWindowHandles();
		//System.out.println(driver.getTitle());
	    //System.out.println(parent);
		System.out.println(windows);

		for (String moveChild : windows) {
			driver.switchTo().window(moveChild);

			if (driver.getTitle().contains("Interact with HyperLinks")) {
				driver.manage().window().maximize();
				driver.findElement(By.xpath("//section/div[1]/div/div/a[contains(@href,'../home.html')]"));
				Thread.sleep(2000);
				//System.out.println(driver.getTitle());
				driver.close();
			}
			
		}
		
		Set<String> windows1 = driver.getWindowHandles();
		for (String parent : windows1) {
			driver.switchTo().window(parent);
			driver.manage().window().maximize();
			System.out.println(driver.getTitle());
			if (driver.getTitle().contains("Interact with Windows")) {
			
				System.out.println(driver.getTitle());
				driver.close();
			}
		}

	}

}

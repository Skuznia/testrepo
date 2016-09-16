package seltest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class selenium1 {
	public static void main(String[] args){
		WebDriver driver = new ChromeDriver();

        driver.get("http://www.google.com");
        
        WebElement element = driver.findElement(By.name("q"));

        element.sendKeys("Pineapple");

        element.submit();

        System.out.println("Page name is: " + driver.getTitle());
        
        //got this code from the example which timeouts after 10 second
        // Wait for the page to load, timeout after 10 seconds
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith("pineapple");
            }
        });

        System.out.println("Page name is: " + driver.getTitle());
        
        //driver.quit() closes the browser
        driver.quit();
        
	}

}
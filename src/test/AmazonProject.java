package test;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AmazonProject {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.setProperty("wbdriver.chrome.driver", "chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		//Launch the Amazon.in website
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);

		//Enter 'samsung' into search field
		WebElement SearchField = driver.findElement(By.xpath("//input[@name='field-keywords']"));
		SearchField.sendKeys("samsung");

		//Click the search button
		WebElement ClickSearchButton = driver.findElement(By.xpath("//input[@type='submit']"));
		ClickSearchButton.click();

		//List the product names and prices
		List<WebElement> ProductNames = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']//h2/a"));
		System.out.println("Number of products: " +ProductNames.size());
		List<WebElement> ProductPrices = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']//span[@class='a-price']"));

		for(int i=0;i<ProductNames.size();i++) {
			System.out.print(ProductNames.get(i).getText()+ " Price = ");
			System.out.println(ProductPrices.get(i).getText());
		}

		//Validate the name is same on the product description page
		String ParentWin = driver.getWindowHandle();

		ProductNames.get(0).click();
		String ValidateProductName = ProductNames.get(0).getText();
		System.out.println(ValidateProductName);

		Set<String> AllWins = driver.getWindowHandles();
		System.out.println("Before clicking tab button the win is " + ParentWin);

		for(String win : AllWins) {
			if(!win.equals(ParentWin)) {
				driver.switchTo().window(win);

			}
		}

		WebElement TitleOnNewTab = driver.findElement(By.xpath("//div[@id='title_feature_div']//span"));
		String str = TitleOnNewTab.getText();
		System.out.println(str);

		if(str.equals(ValidateProductName)) {
			System.out.println("The product name matches!");
		} else {
			System.out.println("The product name does not match!");
		}

		driver.quit();
	}

}

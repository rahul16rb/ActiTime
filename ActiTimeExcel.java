package qsp;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ActiTimeExcel {

	public static void main(String[] args) throws IOException, InterruptedException {
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		Methods m=new Methods();
		String URL = m.readDataFromProperty("url");
		driver.get(URL);
		String un = m.readDataFromProperty("username");
		driver.findElement(By.id("username")).sendKeys(un);
		String pass = m.readDataFromProperty("password");
		driver.findElement(By.name("pwd")).sendKeys(pass);
		driver.findElement(By.xpath("//div[.='Login ']")).click();
		driver.findElement(By.linkText("Tasks")).click();
		driver.findElement(By.xpath("//div[.='Add New']")).click();
		driver.findElement(By.xpath("//div[.='New Customer']")).click();
		FileInputStream fis=new FileInputStream("./Testdata/TestingData.xlsx");
		Workbook wb= WorkbookFactory.create(fis);
		String custname= wb.getSheet("Testingdata1").getRow(4).getCell(1).getStringCellValue();
		driver.findElement(By.xpath("(//input[@placeholder='Enter Customer Name'])[2]")).sendKeys(custname);
		String des = wb.getSheet("Testingdata1").getRow(4).getCell(2).getStringCellValue();
		driver.findElement(By.xpath("//textarea[@placeholder='Enter Customer Description']")).sendKeys(des);
		Thread.sleep(5000);
		driver.close();
		

	}

}

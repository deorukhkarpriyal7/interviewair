package interviewair;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
// Mozilla import
//import org.openqa.selenium.firefox.FirefoxDriver;
// Chrome import
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import org.openqa.selenium.support.ui.Select;

import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class candidate_login_with_file_input {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  
  FileInputStream InputFileName;
  XSSFWorkbook inputworkbook;
  XSSFSheet inputsheet;
  
  XSSFRow row;
  XSSFCell cell;

  @Before
  public void setUp() throws Exception {
	  
	// Mozilla code
    //System.setProperty("webdriver.gecko.driver","E:\\geckodriver\\geckodriver.exe");
	//System.setProperty("webdriver.gecko.driver","./../interviewair/external path files/geckodriver.exe");
	//driver = new FirefoxDriver();
		  
	// Chrome code
	System.setProperty("webdriver.chrome.driver", "./../interviewair/external path files/chromedriver.exe");
	//System.setProperty("webdriver.chrome.driver", "E:\\Browser\\chromedriver.exe");
	ChromeOptions opts = new ChromeOptions();
	opts.addArguments("--disable-extensions");
	driver = new ChromeDriver(opts);
	
    baseUrl = "https://test.interviewair.com/";
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    InputFileName = new FileInputStream("E:\\testexcelfile.xlsx");
    inputworkbook = new XSSFWorkbook(InputFileName);
    inputsheet = inputworkbook.getSheet("Sheet1");
  }

  @Test
  public void testCandidateLoginjunit() throws Exception {
	Iterator rows = inputsheet.rowIterator();  
	
	while(rows.hasNext()){
		
		row = (XSSFRow) rows.next();
		
		Iterator cells = row.cellIterator();
		
		int count = 0;
		String username = null;
		String password = null;
		
		while(cells.hasNext()){
			cell = (XSSFCell) cells.next();
			//System.out.println(cell);
					
			if((cell.getCellTypeEnum()) == CellType.STRING){
				if(count == 0){
					System.out.println("username = "+cell.getStringCellValue());
					username = cell.getStringCellValue();
					count++;
				}
				else{
					System.out.println("password = "+cell.getStringCellValue());
					password = cell.getStringCellValue();
				}
			}
			
		}
		
	
		driver.get(baseUrl + "/");
	    driver.findElement(By.id("tool_btn3")).click();
	    Thread.sleep(1000*3);
	    driver.findElement(By.id("email_input")).clear();
	    Thread.sleep(1000*3);
	    driver.findElement(By.id("email_input")).sendKeys(username);
	    driver.findElement(By.id("pwd_input")).click();
	    Thread.sleep(1000*3);
	    driver.findElement(By.id("pwd_input")).clear();
	    Thread.sleep(1000*3);
	    driver.findElement(By.id("pwd_input")).sendKeys(password);
	    driver.findElement(By.id("signIn_btn")).click();
	    Thread.sleep(1000*8);
	    driver.findElement(By.id("settings_btn")).click();
	    Thread.sleep(1000*3);
	    driver.findElement(By.id("logout_div")).click();
	    Thread.sleep(1000*3);
	}
	
	InputFileName.close();
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}

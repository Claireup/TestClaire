package HotelApp_BusinessFunctions;

import java.io.File;
import java.io.FileInputStream;
import java.util.Locale;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

public class HotelApp_BusinessFunctions {
	public static Properties prop;
	public static WebDriver driver;
	
	public void HA_BF_Login (WebDriver driver, String sUsername, String sPassword){
	

driver.findElement(By.xpath(prop.getProperty("Txt_Login_Username"))).sendKeys(sUsername);
driver.findElement(By.id(prop.getProperty("Txt_Login_Password"))).sendKeys(sPassword);
driver.findElement(By.name(prop.getProperty("Btn_Login_Login"))).click();

}
	public void HA_Search_Hotel(String Location){
		
		
		new Select(driver.findElement(By.cssSelector(prop.getProperty("Txt_Select_Location")))).selectByVisibleText(Location) ;
	    new Select(driver.findElement(By.xpath(prop.getProperty("Txt_Select_Hotel")))).selectByVisibleText("Hotel Sunshine");
	    driver.findElement(By.name(prop.getProperty("Bt_Search_Search"))).click();
	    		
		
	}
	
	//This function is used to read data from specified cell of Excel sheet once you give Excelsheet name and path



		public static String HA_GF_readXL (int row, String column, String strFilePath) 

		{

				jxl.Cell c= null;

				int reqCol=0;

				WorkbookSettings ws = null;

				Workbook workbook = null;

				Sheet sheet = null;

				FileInputStream fs = null;

		try{

			fs = new FileInputStream(new File(strFilePath));

			ws = new WorkbookSettings();

			ws.setLocale(new Locale("en", "EN"));

					

			// opening the work book and sheet for reading data

			workbook = Workbook.getWorkbook(fs, ws);

			sheet = workbook.getSheet(0);

			

			// Sanitise given data

			String col = column.trim();

			

			//loop for going through the given row

			for(int j=0; j<sheet.getColumns(); j++)

			{

				jxl.Cell cell = sheet.getCell(j,0);

				if((cell.getContents().trim()).equalsIgnoreCase(col))

				{	

					reqCol= cell.getColumn();

					//System.out.println("column No:"+reqCol);

					c = sheet.getCell(reqCol, row);

					fs.close();

					return c.getContents();

				}

			}

		}

		catch(BiffException be)

		{

			

			System.out.println("The given file should have .xls extension.");

		}

		catch(Exception e)

		{

			e.printStackTrace();

			

		}

		System.out.println("NO MATCH FOUND IN GIVEN FILE: PROBLEM IS COMING FROM DATA FILE");

		

		return null;

		}

		

		

		

		public static int HA_GF_XLRowCount (String strFilePath, String sColumn)

		{

			int k;

			for (k = 1; k < 999; k++)

			{

			

			String sParamVal = 	HA_GF_readXL(k,sColumn,strFilePath);

				if (sParamVal.equals("ENDOFROW"))

					{

						break;

					}

			

			}

			

			return k;

			
		}
			
}
	
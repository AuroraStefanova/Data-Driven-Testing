package Helpers;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
public class ExelReader {
    WebDriver driver;
     static XSSFWorkbook workbook;
     static XSSFSheet sheet;
     private static String cellData;
    public ExelReader(WebDriver driver){
         this.driver = driver;
         PageFactory.initElements(this.driver,this);
     }
     public static Integer getRowCount() {
         int rowCount = 0;
         try {
             workbook = new XSSFWorkbook("C:\\OrderARobot\\AutomationPractice-main\\excel\\DDT.xlsx");
             sheet = workbook.getSheet("Лист1");
             rowCount = sheet.getPhysicalNumberOfRows();
             //System.out.println("Number of rows: " + rowCount);

         } catch (Exception exp) {
             System.out.println(exp.getMessage());
             System.out.println(exp.getCause());
             exp.printStackTrace();
         }
         return rowCount;
     }
     public  static String getCellDataUserName(int rollNum){
         return GetUserData(rollNum, 0);
     }
    public static String getCellUserPassword(int rollNum){
        return  GetUserData(rollNum, 1);
    }
    public static String getCellExpectedResult(int rollNum){
        return  GetUserData(rollNum, 2);
    }
    public static String GetUserData(int rollNum, int collNum){
        try {
            workbook = new XSSFWorkbook("C:\\OrderARobot\\AutomationPractice-main\\excel\\DDT1.xlsx");
            sheet = workbook.getSheet("Лист1");
            cellData = sheet.getRow(rollNum).getCell(collNum).getStringCellValue();
            //System.out.println(cellData);

        }catch (Exception exp) {
            System.out.println(exp.getMessage());
            System.out.println(exp.getCause());
            exp.printStackTrace();
        }
        return cellData;
    }
}

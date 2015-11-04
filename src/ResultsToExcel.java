import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;


public class ResultsToExcel {
	
	//private??
	public static void createExcelDoc (Map<String, Integer> map) throws IOException{
		Workbook wb = new HSSFWorkbook();
	    Sheet sheet1 = wb.createSheet("new sheet");
	    
	    //https://www.branah.com/unicode-converter
	    wb.setSheetName(0, "\u004a\u0075\u006f\u006b\u0065\u006c\u0069\u0173 " + 
                "\u0064\u0075\u006f\u006d\u0065\u006e\u0079\u0073" );	    	    
	    sheet1.setColumnWidth((short) (0), (short) ((50 * 8) / ((double) 1 / 20)));
		sheet1.autoSizeColumn((short)1);
	    	    
        putDataToExcel(map, wb, sheet1);
	    
	    //Write the output to a file
	    FileOutputStream fileOut = new FileOutputStream("/Users/kristinaslekyte/Desktop/data.xls");
	    wb.write(fileOut);
	    fileOut.close();	    
	    System.out.println("The data is already in Excel");
		//return wb;
	}	
	
	public static void createCellInStyle (CellStyle cs, Font f){
		
		cs.setAlignment(CellStyle.ALIGN_CENTER);
		cs.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
	    cs.setFont(f);
	}
	

	public static void createCellInFont (Font f, short c){
		
	    f.setFontHeightInPoints((short) 12);
	    f.setFontName("Times New Roman");
	    //make it blue
	    f.setColor( (short) c );
	}

	
	public static void createCellInFont (Font f){
		
	    createCellInFont (f, (short)0xc);		
	    f.setFontHeightInPoints((short) 12);
	    f.setFontName("Times New Roman");
	    //make it bold
	    f.setBoldweight(Font.BOLDWEIGHT_BOLD);   

	}
	
	
	public static void putDataToExcel (Map<String, Integer> map, Workbook wb, Sheet s){
		
		    int rownum = 0;
		    Row row = s.createRow((short)rownum);
		    row.createCell(0).setCellValue("\u017d\u006f\u0064\u017e\u0069\u0061\u0069");
		    row.createCell(1).setCellValue("Pasikartojimai");
		    
		    CellStyle cs0 = wb.createCellStyle();
			Font f0 = wb.createFont();
			
			
			//set the color of the background
			cs0.setFillForegroundColor(IndexedColors.RED.getIndex());
			cs0.setFillPattern(CellStyle.SOLID_FOREGROUND);
			//createCellInFont (f0);
			
			//http://www.sourcecodebrowser.com/libjakarta-poi-java/3.5plus-pdfsg/classorg_1_1apache_1_1poi_1_1hssf_1_1util_1_1_h_s_s_f_color.html
			//gold color
			createCellInFont (f0, (short)0x33);
			createCellInStyle(cs0, f0);
   
		  for (Map.Entry<String, Integer> entry : map.entrySet()) {
		    row = s.createRow(++rownum);
		    row.setHeight((short) 0x249);
		    row.setRowNum((short)rownum);
		   
		    Cell cell0 = row.createCell((short) 0);  
		    cell0.setCellStyle(cs0);
		    Cell cell1 = row.createCell((short) 1);  
		    cell1.setCellStyle(cs0);
		    cell0.setCellValue(entry.getKey());		    
            cell1.setCellValue(entry.getValue());

		}
		
	}

}
